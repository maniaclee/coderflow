package com.lvbby.coderflow.model;

import lombok.Data;

/**
 *
 * @author dushang.lp
 * @version $Id: SqlColumn.java, v 0.1 2020年03月26日 下午8:22 dushang.lp Exp $
 */
@Data
public class SqlColumn {
    private String   name;
    private String   nameCamel;
    private String   dbType;
    private Class<?> javaType;
    private String   javaTypeName;
    private String   comment;
    private boolean primaryKey = false;
    private boolean nullable   = true;
    private boolean hasIndex   = false;
    private boolean unique     = false;
}