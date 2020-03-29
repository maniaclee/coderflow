package com.lvbby.coderflow.compont;

import com.alibaba.fastjson.JSONObject;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.utils.CommonUtils;
import com.lvbby.flashflow.core.AbstractFlowAction;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.anno.FlowAction;
import com.lvbby.flashflow.core.utils.FlowHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author dushang.lp
 * @version $Id: DbReaderAction.java, v 0.1 2020年03月26日 下午3:59 dushang.lp Exp $
 */
@FlowAction
@Component
public class BeanAction extends AbstractFlowAction {

    public void invoke(FlowContext context) throws Exception {
        Class clz = FlowHelper.getValueOrProp(CoderFlowKeys.beanClass);
        String script = CommonUtils.renderTemplate("template/javaBean.template", new JSONObject().fluentPut("src",clz));
        context.put(CoderFlowKeys.beanJavaSrc,script);
    }
}