package com.lvbby.coderflow.compont;

import com.lvbby.coderflow.flow.CoderFlowKeys;
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
public class FileWriterAction extends AbtAction{

    @Override
    public void invoke(FlowContext context) throws Exception {

        String filePath = FlowHelper.getValueOrProp(CoderFlowKeys.fileWritePath);
        String src = FlowHelper.getValueOrProp(CoderFlowKeys.fileWriteSrc);
        FlowUtils.isTrue(FlowUtils.isNotBlank(filePath),"file path can't be blank");
        FlowUtils.isTrue(FlowUtils.isNotBlank(src),"src can't be blank");

        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if(!parentFile.exists()){
            Validate.isTrue(parentFile.mkdirs(),"mkdirs failed");
        }

        CommonUtils.write(src,file);
    }
}