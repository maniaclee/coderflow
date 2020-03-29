package com.lvbby.coderflow.compont.mybatis;
import com.alibaba.fastjson.JSONObject;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.utils.CommonUtils;
import com.lvbby.flashflow.core.AbstractFlowAction;
import com.lvbby.flashflow.core.FlowContext;
import com.lvbby.flashflow.core.FlowKey;
import com.lvbby.flashflow.core.anno.FlowAction;
import com.lvbby.flashflow.core.anno.FlowProp;
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
    @FlowProp
    public static final FlowKey<String> mybatisMapperPackage = new FlowKey<>("mybatis.mapperPackage");

    public static final FlowKey<Class> mybatisMapperClass = new FlowKey<>("mybatis.mapperClass");

    public void invoke(FlowContext context) throws Exception {
        Class beanClass = FlowHelper.getValueOrProp(CoderFlowKeys.beanClass);
        String mapperPackage = FlowHelper.getValueOrProp(mybatisMapperPackage);
        String mapperClassName = String.format("%sMapper", beanClass.getSimpleName());


        String script = CommonUtils.renderTemplate("template/mybatisDao.template", new JSONObject()
                .fluentPut("mapperPackage",mapperPackage)
                .fluentPut("mapperClassName",mapperClassName)
                .fluentPut("beanClass",beanClass)
        );
        System.out.println(script);
    }


}