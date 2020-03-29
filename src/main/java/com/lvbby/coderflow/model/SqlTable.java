package com.lvbby.coderflow.model;

import lombok.Data;

import java.util.List;


/**
 * Created by peng on 16/7/27.
 */
@Data
public class SqlTable {
    /**
     * Capital camel name like UserDetail
     */
    String name;
    String primaryKey;
    List<SqlColumn> fields;

}
