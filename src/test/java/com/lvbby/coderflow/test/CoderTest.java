package com.lvbby.coderflow.test;

import com.lvbby.coderflow.compont.EnumGenAction;
import com.lvbby.coderflow.compont.PrintAction;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.flashflow.core.Flow;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.tool.FlowTool;
import org.junit.Test;

/**
 *
 * @author dushang.lp
 * @version $Id: CoderTest.java, v 0.1 2020年03月29日 下午2:10 dushang.lp Exp $
 */
public class CoderTest {

    @Test
    public void doc() throws Exception {
        Flow.scanProps("com.lvbby.coderflow");
        System.out.println(FlowTool.createFlowDoc());
    }

    @Test
    public void enumDemo() throws Exception {
        FlowContext context = new FlowContext();
        context.put(EnumGenAction.enumClassName, "com.lvbby.test.TypeEnum");

        Flow.execSimple(context,
                new EnumGenAction(),
                new PrintAction(CoderFlowKeys.beanJavaSrc)
        );
    }
}