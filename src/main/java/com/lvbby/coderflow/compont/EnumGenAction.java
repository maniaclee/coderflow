package com.lvbby.coderflow.compont;

import com.alibaba.fastjson.JSONObject;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.utils.CommonUtils;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowProp;
import com.lvbby.flashflow.core.utils.FlowHelper;
import com.lvbby.flashflow.core.utils.FlowUtils;

/**
 *
 * @author dushang.lp
 * @version $Id: EnumGenAction.java, v 0.1 2020年04月05日 下午6:17 dushang.lp Exp $
 */
public class EnumGenAction extends AbtAction {
    @FlowProp
    public static FlowKey<String> enumClassName = new FlowKey<>("enumClassName");

    @Override
    public void invoke(FlowContext context) throws Exception {
        String enumClassName = FlowHelper.getValueOrProp(EnumGenAction.enumClassName);
        FlowUtils.isTrue(FlowUtils.isNotBlank(enumClassName),"enum class name can't be blank");

        String packageName = FlowUtils.getPackageName(enumClassName);
        String enumName = FlowUtils.getShortClassName(enumClassName);

        String javaSrc = CommonUtils.renderTemplate("template/enum.template",
                new JSONObject()
                        .fluentPut("packageName", packageName)
                        .fluentPut("name", enumName)
        );

        /** support writer */
        context.put(CoderFlowKeys.beanJavaSrc,javaSrc);
    }
}