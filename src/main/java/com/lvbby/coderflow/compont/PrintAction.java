package com.lvbby.coderflow.compont;

import com.alibaba.fastjson.JSON;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowProp;
import com.lvbby.flashflow.core.utils.FlowHelper;
import org.apache.commons.lang3.StringUtils;

/**
 * 打印
 * @author dushang.lp
 * @version $Id: PrintAction.java, v 0.1 2020年03月28日 下午4:08 dushang.lp Exp $
 */
public class PrintAction extends AbtAction {

    private String key;

    @FlowProp
    public static FlowKey<String> printKey = new FlowKey<>("printKey");

    public PrintAction() {
    }

    public PrintAction(String key) {
        this.key = key;
    }

    public PrintAction(FlowKey key) {
        this.key = key.getKey();
    }

    @Override
    public void invoke(FlowContext context) throws Exception {
        String printKey = key;
        if (StringUtils.isBlank(printKey)) {
            printKey = FlowHelper.getValueOrProp(printKey);
        }
        if (StringUtils.isBlank(printKey)) {
            return;
        }
        Object value = context.getValue(printKey);
        if(value!=null && !(value instanceof String)){
            value = JSON.toJSONString(value, true);
        }

        System.out.println(String.format("print-[%s]:%s", printKey, value));
    }
}