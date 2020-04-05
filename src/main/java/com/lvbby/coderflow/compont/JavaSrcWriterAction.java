package com.lvbby.coderflow.compont;

import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.flow.CoderProps;
import com.lvbby.coderflow.utils.CommonUtils;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.anno.FlowAction;
import com.lvbby.flashflow.core.utils.FlowHelper;
import com.lvbby.flashflow.core.utils.FlowUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;

/**
 * 写java文件
 * @author dushang.lp
 * @version $Id: JavaSrcWriterAction.java, v 0.1 2020年03月28日 下午4:28 dushang.lp Exp $
 */
@FlowAction
public class JavaSrcWriterAction extends AbtAction {

    @Override
    public void invoke(FlowContext context) throws Exception {

        String javaSrc = context.getValueString(CoderFlowKeys.beanJavaSrc.getKey());
        String javaSrcDirectory = FlowHelper.getValueOrProp(CoderProps.javaSrcDirectory.getKey());
        FlowUtils.isTrue(FlowUtils.isNotBlank(javaSrc), "java src can't be blank");
        FlowUtils.isTrue(FlowUtils.isNotBlank(javaSrcDirectory), "java src directory can't be blank");

        String packageName = FlowUtils.findFirst(javaSrc, "package\\s+(\\S+)\\s*;", matcher -> matcher.group(1));
        String className = FlowUtils.extractJavaClassNameFromSrc(javaSrc);

        String classFullName = String.format("%s.%s", packageName, className);

        File file = new File(javaSrcDirectory, classFullName.replace(".", "/") + ".java");
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            Validate.isTrue(parentFile.mkdirs(), "mkdirs failed");
        }
        CommonUtils.write(javaSrc, file);
    }
}