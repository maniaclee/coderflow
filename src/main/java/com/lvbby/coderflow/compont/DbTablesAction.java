package com.lvbby.coderflow.compont;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.model.SqlColumn;
import com.lvbby.coderflow.model.SqlTable;
import com.lvbby.coderflow.model.SqlType;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowAction;
import com.lvbby.flashflow.core.anno.FlowProp;
import com.lvbby.flashflow.core.utils.FlowHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dushang.lp
 * @version $Id: DbTableReaderAction.java, v 0.1 2020年03月26日 下午7:55 dushang.lp Exp $
 */
@FlowAction
@Component
public class DbTablesAction extends AbtAction {
    @FlowProp
    public static final FlowKey<List<String>> tables = new FlowKey<>("db.tables");

    @Override
    public void invoke(final FlowContext context) {
        final List<String> tableNames = FlowHelper.getValueOrProp(tables);

        DataSource dataSource = context.get(CoderFlowKeys.datasource);
        List<String> ts = getTableNames(dataSource);
        List<SqlTable> tables = ts.stream().filter(t -> CollectionUtils.isEmpty(tableNames) || tableNames.contains(t))
                .map(t -> {
                    SqlTable sqlTable = new SqlTable();
                    sqlTable.setFields(getFields(dataSource, t));
                    sqlTable.setName(t);
                    sqlTable.setPrimaryKey(getPrimaryKey(dataSource,t));
                    return sqlTable;
                }).collect(Collectors.toList());
        context.put(CoderFlowKeys.dbTables, tables);
        //dispatch
        FlowHelper.stream(CoderFlowKeys.dbTable.getKey(),tables);
    }

    private String getPrimaryKey(DataSource dataSource,String table)  {
        try {
            ResultSet pkRSet = dataSource.getConnection().getMetaData().getPrimaryKeys(null, null, table);
            while (pkRSet.next()) {
                return pkRSet.getObject(4).toString();
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getTableNames(DataSource dataSource) {
        try {
            DatabaseMetaData md = dataSource.getConnection().getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            List<String> re = Lists.newLinkedList();
            while (rs.next()) { re.add(rs.getString(3)); }
            return re;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<SqlColumn> getFields(DataSource dataSource, String table) {
        try {
            List<SqlColumn> re = Lists.newArrayList();
            DatabaseMetaData meta = dataSource.getConnection().getMetaData();
            ResultSet rs = meta.getColumns(null, null, table, "%");
            while (rs.next()) {
                SqlColumn f = new SqlColumn();
                f.setName(rs.getString(4));
                f.setNameCamel(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,f.getName()));
                f.setDbType(rs.getString(6));
                f.setJavaType(SqlType.getJavaType(f.getDbType()));
                f.setJavaTypeName(f.getJavaType().getSimpleName());
                f.setComment(rs.getString(12));
                re.add(f);
            }
            return re;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}