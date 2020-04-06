package com.lvbby.coderflow.compont;

import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowProp;

/**
 *
 * @author dushang.lp
 * @version $Id: EnumGenAction.java, v 0.1 2020年04月05日 下午6:17 dushang.lp Exp $
 */
public class JavaInterfaceDocAction extends AbtAction {
    @FlowProp
    public static FlowKey<String> enumClassName = new FlowKey<>("enumClassName");

    @Override
    public void invoke(FlowContext context) throws Exception {

    }
}