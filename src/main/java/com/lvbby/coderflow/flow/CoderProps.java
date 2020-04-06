package com.lvbby.coderflow.flow;

import com.lvbby.coderflow.compont.FileWriterAction;
import com.lvbby.coderflow.compont.mybatis.MybatisConfig;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowPropConfig;
import com.lvbby.flashflow.core.anno.FlowProp;

/**
 *
 * @author dushang.lp
 * @version $Id: FlowGlobalProps.java, v 0.1 2020年03月28日 下午8:59 dushang.lp Exp $
 */
@FlowPropConfig
public class CoderProps {
    /** java src目录*/
    @FlowProp
    public static final FlowKey<String>        javaSrcDirectory = new FlowKey<>("java.src");

    @FlowProp
    public static final FlowKey<MybatisConfig> mybatisGenerator = new FlowKey<>("mybatisGenerator");
    /**
     * 写文件：文件内容
     * @see FileWriterAction
     */
    @FlowProp
    public static final FlowKey<String>        fileWriteSrc       = new FlowKey<>("fileWriteSrc");
    /**
     * 写文件：文件全路径
     * @see FileWriterAction
     */
    @FlowProp
    public static final FlowKey<String>        fileWritePath       = new FlowKey<>("fileWritePath");
}