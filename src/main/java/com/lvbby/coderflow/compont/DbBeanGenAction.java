package com.lvbby.coderflow.compont;
import com.alibaba.fastjson.JSONObject;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.model.ClassBeanInfo;
import com.lvbby.coderflow.model.ClassField;
import com.lvbby.coderflow.model.SqlTable;
import com.lvbby.coderflow.utils.CommonUtils;
import com.lvbby.flashflow.core.AbstractFlowAction;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowAction;
import com.lvbby.flashflow.core.anno.FlowProp;
import com.lvbby.flashflow.core.utils.FlowHelper;
import com.lvbby.flashflow.core.utils.FlowUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 *
 * @author dushang.lp
 * @version $Id: DbReaderAction.java, v 0.1 2020年03月26日 下午3:59 dushang.lp Exp $
 */
@FlowAction
@Component
public class DbBeanGenAction extends AbstractFlowAction {

    @FlowProp
    public static final FlowKey<String> beanPackage = new FlowKey<>("beanPackage");

    public void invoke(FlowContext context) throws Exception {
        SqlTable table = FlowHelper.getValueOrProp(CoderFlowKeys.dbTable);

        ClassBeanInfo classBeanInfo = new ClassBeanInfo(table.getName(),FlowHelper.getValueOrProp(beanPackage));
        classBeanInfo.setFields(table.getFields().stream().map(f-> new ClassField(f.getJavaType(), f.getName())).collect(Collectors.toList()));


        String script = CommonUtils.renderTemplate("template/beanGroovy.template", new JSONObject().fluentPut("src",classBeanInfo));
        Class clz = FlowUtils.groovyClass(script);

        context.put(CoderFlowKeys.beanClass, clz);
        context.put(CoderFlowKeys.beanClassFullName, clz.getName());
    }
}