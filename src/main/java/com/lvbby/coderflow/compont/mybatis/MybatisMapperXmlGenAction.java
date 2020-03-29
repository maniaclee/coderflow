package com.lvbby.coderflow.compont.mybatis;
import com.alibaba.fastjson.JSONObject;
import com.lvbby.coderflow.flow.CoderFlowKeys;
import com.lvbby.coderflow.model.SqlTable;
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
public class MybatisMapperXmlGenAction extends AbstractFlowAction {

    public void invoke(FlowContext context) throws Exception {
        SqlTable table = FlowHelper.getValueOrProp(CoderFlowKeys.dbTable);
        Class beanClass = FlowHelper.getValueOrProp(CoderFlowKeys.beanClass);
        String mapperPackage = FlowHelper.getValueOrProp(MybatisDaoGenAction.mybatisMapperPackage);
        String mapperFullName = String.format("%s.%sMapper", mapperPackage,beanClass.getSimpleName());


        String script = CommonUtils.renderTemplate("template/mapper.xml", new JSONObject()
                .fluentPut("mapperClassName",mapperFullName)
                .fluentPut("table",table)
                .fluentPut("beanClass",beanClass)
        );

        System.out.println(script);
    }
}