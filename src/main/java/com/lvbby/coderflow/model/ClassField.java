package com.lvbby.coderflow.model;

import com.lvbby.flashflow.core.utils.FlowUtils;
import lombok.Data;

/**
 *
 * @author dushang.lp
 * @version $Id: ClassField.java, v 0.1 2020年03月26日 下午10:40 dushang.lp Exp $
 */
@Data
public class ClassField {
    public Class type;
    public String name;

    public ClassField(Class type, String name) {
        this.type = type;
        this.name = FlowUtils.camel(name);
    }
}