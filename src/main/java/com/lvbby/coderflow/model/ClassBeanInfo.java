package com.lvbby.coderflow.model;

import com.lvbby.flashflow.core.utils.FlowUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 *
 * @author dushang.lp
 * @version $Id: ClassBeanInfo.java, v 0.1 2020年03月27日 上午9:41 dushang.lp Exp $
 */
@Data
public class ClassBeanInfo {
    private String className;
    private String packageName;
    private List<ClassField>  fields;

    public ClassBeanInfo(String className, String packageName) {
        this.className = StringUtils.capitalize(FlowUtils.camel(className));
        this.packageName = packageName;
    }
}