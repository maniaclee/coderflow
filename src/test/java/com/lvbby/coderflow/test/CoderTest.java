package com.lvbby.coderflow.test;

import com.alibaba.fastjson.JSON;
import com.lvbby.flashflow.core.tool.FlowActionInfo;
import com.lvbby.flashflow.core.tool.FlowTool;
import org.junit.Test;

import java.util.List;

/**
 *
 * @author dushang.lp
 * @version $Id: CoderTest.java, v 0.1 2020年03月29日 下午2:10 dushang.lp Exp $
 */
public class CoderTest {

    @Test
    public void name() throws Exception {
        String packages = "com.lvbby.coderflow";
        List<FlowActionInfo> flowPropInfos = FlowTool.scanActionProps(packages);
        System.out.println(JSON.toJSONString(flowPropInfos,true));
        System.out.println(JSON.toJSONString(FlowTool.scanGlobalProps(packages),true));
    }

    @Test
    public void doc() throws Exception {
        System.out.println(FlowTool.createFlowDoc("com.lvbby.coderflow"));
    }
}