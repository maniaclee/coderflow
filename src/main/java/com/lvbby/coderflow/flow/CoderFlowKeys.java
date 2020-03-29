package com.lvbby.coderflow.flow;

import com.lvbby.coderflow.model.SqlTable;
import com.lvbby.flashflow.core.FlowKey;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * @author dushang.lp
 * @version $Id: CoderFlowKeys.java, v 0.1 2020年03月26日 下午7:45 dushang.lp Exp $
 */
public class CoderFlowKeys {

    public static final FlowKey<DataSource>     datasource   = new FlowKey<>("datasource");
    public static final FlowKey<JdbcTemplate>   jdbcTemplate = new FlowKey<>("jdbcTemplate");
    public static final FlowKey<List<SqlTable>> dbTables     = new FlowKey<>("dbTables");
    public static final FlowKey<SqlTable>       dbTable      = new FlowKey<>("dbTable");

    public static final FlowKey<Class>       beanClass      = new FlowKey<>("beanClass");
    public static final FlowKey<String>       beanClassFullName      = new FlowKey<>("beanClassFullName");
    /** 当前java src */
    public static final FlowKey<String>       beanJavaSrc      = new FlowKey<>("beanJavaSrc");

}