package com.lvbby.coderflow.compont;

import com.lvbby.flashflow.core.AbstractFlowAction;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowAction;
import com.lvbby.flashflow.core.anno.FlowProp;
import com.lvbby.flashflow.core.utils.FlowHelper;
import com.lvbby.flashflow.core.utils.FlowUtils;

import java.io.FileInputStream;

/**
 * 读取资源放入context里
 * @author dushang.lp
 * @version $Id: ResourceReaderAction.java, v 0.1 2020年03月31日 下午8:48 dushang.lp Exp $
 */
@FlowAction(id = "文件资源读取器")
public class ResourceReaderAction extends AbstractFlowAction {


    @FlowProp("资源路径")
    public static FlowKey<String> resourcePath = new FlowKey<>("resource.path");
    @FlowProp("资源类型:file/classpathFile")
    public static FlowKey<String> resourceType = new FlowKey<>("resource.type");


    /** 资源读取结果，这里不设置类型，可以任意转化、adaptor */
    public static FlowKey<Object> resource = new FlowKey<>("resource");

    @Override
    public void invoke(FlowContext context) throws Exception {
        String path = FlowHelper.getValueOrProp(resourcePath);
        String type = FlowHelper.getValueOrProp(resourceType);
        ResourceTypeEnum resourceTypeEnum = ResourceTypeEnum.valueOf(type);
        FlowUtils.isTrue(FlowUtils.isNotBlank(path),"resourcePath can't be blank");
        FlowUtils.isTrue(FlowUtils.isNotBlank(type) && resourceTypeEnum!=null, String.format("invalid resource type:%s", type));

        if(resourceTypeEnum == ResourceTypeEnum.classPathFile){
            context.put(resource,FlowUtils.readResourceFile(path));
            return;
        }
        if(resourceTypeEnum == ResourceTypeEnum.file){
            context.put(resource,FlowUtils.readFile(new FileInputStream(path)));
            return;
        }
    }
}