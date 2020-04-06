package com.lvbby.coderflow.compont.mybatis;
import com.alibaba.fastjson.JSONObject;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.flow.CoderProps;
import com.lvbby.coderflow.utils.CommonUtils;
import com.lvbby.flashflow.core.AbstractFlowAction;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.anno.FlowAction;
import com.lvbby.flashflow.core.utils.FlowHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author dushang.lp
 * @version $Id: DbReaderAction.java, v 0.1 2020年03月26日 下午3:59 dushang.lp Exp $
 */
@FlowAction
@Component
public class MybatisDaoGenAction extends AbstractFlowAction {

    public void invoke(FlowContext context) throws Exception {
        Class beanClass = FlowHelper.getValueOrProp(CoderFlowKeys.beanClass);
        MybatisConfig mybatisConfig = FlowHelper.getValueOrProp(CoderProps.mybatisGenerator,MybatisConfig.class);
        String mapperPackage = mybatisConfig.getMapperPackage();
        String mapperClassName = String.format("%sMapper", beanClass.getSimpleName());

        String script = CommonUtils.renderTemplate("template/mybatisDao.template", new JSONObject()
                .fluentPut("mapperPackage",mapperPackage)
                .fluentPut("mapperClassName",mapperClassName)
                .fluentPut("beanClass",beanClass)
        );
        //设置当前bean上下文
        context.put(CoderFlowKeys.beanJavaSrc,script);
    }


}