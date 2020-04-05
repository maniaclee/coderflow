package com.lvbby.coderflow.compont;

import com.alibaba.druid.pool.DruidDataSource;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.utils.CommonUtils;
import com.lvbby.flashflow.core.AbstractFlowAction;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowAction;
import com.lvbby.flashflow.core.anno.FlowProp;
import com.lvbby.flashflow.core.utils.FlowHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author dushang.lp
 * @version $Id: DbReaderAction.java, v 0.1 2020年03月26日 下午3:59 dushang.lp Exp $
 */
@FlowAction
@Component
public class DatasourceAction extends AbstractFlowAction {

    @FlowProp("jdbc:访问db")
    public static final FlowKey<String> jdbcUrl  = new FlowKey<>("db.jdbcUrl");
    @FlowProp("jdbc的用户名")
    public static final FlowKey<String> jdbcUser = new FlowKey<>("db.jdbcUser");
    @FlowProp("jdbc的密码")
    public static final FlowKey<String> jdbcPwd  = new FlowKey<>("db.jdbcPwd");

    public void invoke(FlowContext context) throws Exception {
        String jdbcUrl = FlowHelper.getValueOrProp(DatasourceAction.jdbcUrl);
        String jdbcUser = FlowHelper.getValueOrProp(DatasourceAction.jdbcUser);
        String jdbcPwd = FlowHelper.getValueOrProp(DatasourceAction.jdbcPwd);

        DruidDataSource dataSource = CommonUtils.createDataSource(jdbcUrl, jdbcUser, jdbcPwd);
        dataSource.init();

        context.put(CoderFlowKeys.datasource, dataSource);
        context.put(CoderFlowKeys.jdbcTemplate, new JdbcTemplate(dataSource));

    }
}