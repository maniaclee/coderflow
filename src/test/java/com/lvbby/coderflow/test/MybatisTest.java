package com.lvbby.coderflow.test;

import com.google.common.collect.Lists;
import com.lvbby.coderflow.compont.BeanAction;
import com.lvbby.coderflow.compont.DatasourceAction;
import com.lvbby.coderflow.compont.DbBeanGenAction;
import com.lvbby.coderflow.compont.DbTablesAction;
import com.lvbby.coderflow.compont.FileWriterAction;
import com.lvbby.coderflow.compont.JavaSrcWriterAction;
import com.lvbby.coderflow.compont.PrintAction;
import com.lvbby.coderflow.compont.mybatis.MybatisConfig;
import com.lvbby.coderflow.compont.mybatis.MybatisDaoGenAction;
import com.lvbby.coderflow.compont.mybatis.MybatisMapperXmlGenAction;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.flow.CoderProps;
import com.lvbby.coderflow.test.model.TestBean;
import com.lvbby.flashflow.core.Flow;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.utils.FlowUtils;
import org.junit.Test;

/**
 *
 * @author dushang.lp
 * @version $Id: MybatisTest.java, v 0.1 2020年03月28日 下午2:01 dushang.lp Exp $
 */
public class MybatisTest {

    @Test
    public void name() throws Exception {
        FlowContext context = new FlowContext();
        context.put(DatasourceAction.jdbcUrl,"jdbc:mysql://localhost:3306/lvbby");
        context.put(DatasourceAction.jdbcUser,"test");
        context.put(DatasourceAction.jdbcPwd,"12345");
        context.put(DbTablesAction.tables, Lists.newArrayList("article"));
        context.put(DbBeanGenAction.beanPackage, "com.lvbby.coderflow.test._gen");
        context.put(CoderProps.javaSrcDirectory, "/Users/dushang.lp/workspace/project/coderflow/src/test/java");

        MybatisConfig mybatisConfig = new MybatisConfig();
        mybatisConfig.setMapperPackage("com.lvbby.coderflow.test._gen");
        mybatisConfig.setMapperXmlDir("/Users/dushang.lp/workspace/project/coderflow/src/test/resousrces/_gen");

        context.put(CoderProps.mybatisGenerator, mybatisConfig);


        Flow.execSimple(context,
                new DatasourceAction(),//datasource
                new DbTablesAction(), //加载表，转换模型
                new DbBeanGenAction(), //生成bean
                new JavaSrcWriterAction(),
                new PrintAction(CoderFlowKeys.beanJavaSrc),
                new MybatisDaoGenAction(),
                new JavaSrcWriterAction(),
                new MybatisMapperXmlGenAction(),
                new FileWriterAction()
        );
    }

    @Test
    public void mybatisJsonConfig() throws Exception {
        Flow.scanActions("com.lvbby.coderflow");
        Flow.scanProps("com.lvbby.coderflow");

        Flow.loadConfig(FlowUtils.readResourceFile("flow/mybatis.json"));
        Flow.exec(new FlowContext("mybatisTest"));
    }

    @Test
    public void beanTest() throws Exception {
        FlowContext context = new FlowContext();
        context.put(CoderFlowKeys.beanClass, TestBean.class);
        Flow.execSimple(context,new BeanAction());
    }
}