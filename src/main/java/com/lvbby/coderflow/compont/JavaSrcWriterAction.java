package com.lvbby.coderflow.compont;

import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.flow.CoderGlobalProps;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.utils.FlowHelper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.FileWriter;

/**
 * 写java文件
 * @author dushang.lp
 * @version $Id: JavaSrcWriterAction.java, v 0.1 2020年03月28日 下午4:28 dushang.lp Exp $
 */
public class JavaSrcWriterAction extends AbtAction{

    @Override
    public void invoke(FlowContext context) throws Exception {

        String javaSrc = context.getValueString(CoderFlowKeys.beanJavaSrc.getKey());
        String classFullName = context.getValueString(CoderFlowKeys.beanClassFullName.getKey());

        String javaSrcDirectory = FlowHelper.getValueOrProp(CoderGlobalProps.javaSrcDirectory.getKey());

        Validate.notBlank(javaSrcDirectory,"java src can't be null");

        File file = new File(javaSrcDirectory, classFullName.replace(".", "/"));
        File parentFile = file.getParentFile();
        if(!parentFile.exists()){
            Validate.isTrue(parentFile.mkdirs(),"mkdirs failed");
        }
        IOUtils.write(javaSrc,new FileWriter(file));
    }
}