package com.lvbby.coderflow.test;

import com.google.common.collect.Lists;
import com.lvbby.coderflow.compont.BeanAction;
import com.lvbby.coderflow.compont.DatasourceAction;
import com.lvbby.coderflow.compont.DbBeanGenAction;
import com.lvbby.coderflow.compont.DbTablesAction;
import com.lvbby.coderflow.compont.mybatis.MybatisDaoGenAction;
import com.lvbby.coderflow.compont.mybatis.MybatisMapperXmlGenAction;
import com.lvbby.coderflow.compont.PrintAction;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.test.model.TestBean;
import com.lvbby.flashflow.core.Flow;
import com.lvbby.flashflow.core.FlowContext;
import org.junit.Test;

/**
 *
 * @author dushang.lp
 * @version $Id: MybatisTest.java, v 0.1 2020年03月28日 下午2:01 dushang.lp Exp $
 */
public class MybatisTest {

    @Test
    public void name() throws Exception {

        //Field field = getClass().getDeclaredFields()[0];
        //Flow.scan("com.lvbby");
        FlowContext context = new FlowContext();
        context.put(DatasourceAction.jdbcUrl,"jdbc:mysql://an.lvbby.com:3306/lvbby");
        context.put(DatasourceAction.jdbcUser,"lee");
        context.put(DatasourceAction.jdbcPwd,"#Caonima123");
        context.put(DbTablesAction.tables, Lists.newArrayList("article"));
        context.put(DbBeanGenAction.beanPackage, "com.lvbby.mybatis.test");
        context.put(MybatisDaoGenAction.mybatisMapperPackage, "com.lvbby.mybatis.test.dao");


        Flow.execSimple(context,
                new DatasourceAction(),//datasource
                new DbTablesAction(), //加载表，转换模型
                new DbBeanGenAction(),
                new BeanAction(),
                new PrintAction(CoderFlowKeys.beanJavaSrc),
                new MybatisDaoGenAction(),
                new MybatisMapperXmlGenAction()
        );
    }

    @Test
    public void beanTest() throws Exception {
        FlowContext context = new FlowContext();
        context.put(CoderFlowKeys.beanClass, TestBean.class);
        Flow.execSimple(context,new BeanAction());
    }
}