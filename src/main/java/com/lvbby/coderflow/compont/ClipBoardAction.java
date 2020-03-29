package com.lvbby.coderflow.compont;

import com.lvbby.coderflow.utils.CommonUtils;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author dushang.lp
 * @version $Id: ClipBoardAction.java, v 0.1 2020年03月28日 下午4:21 dushang.lp Exp $
 */
public class ClipBoardAction extends AbtAction {

    private String key;

    public static ClipBoardAction of(String key) {
        ClipBoardAction clipBoardAction = new ClipBoardAction();
        clipBoardAction.key = key;
        return clipBoardAction;
    }

    public static ClipBoardAction of(FlowKey key) {
        return of(key.getKey());
    }

    @Override
    public void invoke(FlowContext context) throws Exception {
        Object res = context.getValue(key);
        if (res != null) {
            Clipboard sysClb = null;
            sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();
            sysClb.setContents(new StringSelection(CommonUtils.objectString(res)), null);
        }
    }
}