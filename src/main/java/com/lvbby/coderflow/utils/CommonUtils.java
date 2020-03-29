package com.lvbby.coderflow.utils;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lvbby.flashflow.core.template.BeetlTemplateEngine;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ClassUtils;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author dushang.lp
 * @version $Id: CommonUtils.java, v 0.1 2020年03月26日 下午7:40 dushang.lp Exp $
 */
public class CommonUtils {

    public static String renderTemplate(String classPathResource, JSONObject args) {
        String template = readResource(classPathResource);
        BeetlTemplateEngine beetlTemplateEngine = new BeetlTemplateEngine(template);
        beetlTemplateEngine.binding(args);
        return beetlTemplateEngine.render();
    }

    public static Class toPrimitiveClass(Class clz) {
        Class<?> aClass = ClassUtils.wrapperToPrimitive(clz);
        return aClass == null ? clz : aClass;
    }

    public static String objectString(Object s ){
        if(s ==null ){
            return null;
        }
        if (s instanceof String){
            return (String) s;
        }
        return JSON.toJSONString(s, true);
    }
    public static String readResource(String classPathResource) {
        try {
            return IOUtils.toString(CommonUtils.class.getClassLoader().getResourceAsStream(classPathResource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DruidDataSource createDataSource(String url, String username, String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(60);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMaxWait(60000);//60s
        druidDataSource.setMinIdle(1);
        druidDataSource.setTimeBetweenEvictionRunsMillis(3000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("select 1");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        Properties properties = new Properties();
        properties.put("config.decrypt", "true");
        druidDataSource.setConnectProperties(properties);

        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(10000);//10s。。慢
        statFilter.setMergeSql(true);
        statFilter.setLogSlowSql(true);

        druidDataSource.setProxyFilters(Lists.newArrayList(statFilter));

        return druidDataSource;
    }
}