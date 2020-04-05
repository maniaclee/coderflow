package com.lvbby.coderflow.test;

import com.lvbby.flashflow.core.Flow;
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
}