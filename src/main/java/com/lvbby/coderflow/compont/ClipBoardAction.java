package com.lvbby.coderflow.compont;

import com.lvbby.flashflow.core.AbstractFlowAction;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowAction;
import com.lvbby.flashflow.core.anno.FlowProp;
import com.lvbby.flashflow.core.utils.FlowHelper;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * copy某个属性
 * @author dushang.lp
 * @version $Id: ClipBoardAction.java, v 0.1 2020年03月28日 下午4:21 dushang.lp Exp $
 */
@FlowAction
public class ClipBoardAction extends AbstractFlowAction {

    @FlowProp
    public static FlowKey<String> copyClipBoardKey = new FlowKey<>("printKey");

    @Override
    public void invoke(FlowContext context) throws Exception {
        String key = FlowHelper.getValueOrProp(copyClipBoardKey);
        if (StringUtils.isNotBlank(key)) {
            Object valueOrProp = FlowHelper.getValueOrProp(key);
            if (valueOrProp != null) {
                Clipboard sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();
                sysClb.setContents(new StringSelection(printString(valueOrProp)), null);
            }
        }
    }

    /***
     * 对象的序列化方式
     * @param o
     * @return
     */
    protected String printString(Object o){
        return o.toString();
    }
}