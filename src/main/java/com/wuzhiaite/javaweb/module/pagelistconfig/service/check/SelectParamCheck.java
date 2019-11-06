package com.wuzhiaite.javaweb.module.pagelistconfig.service.check;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SelectField;
import org.springframework.util.Assert;
import org.thymeleaf.util.StringUtils;

/**
 * select参数校验
 */
public class SelectParamCheck implements ParamCheckInterface<SelectField>{

    private String regex = "";

    @Override
    public boolean checkParam(SelectField selectField) {
        if(StringUtils.isEmpty(selectField.getFiled())) return false;
        if(StringUtils.isEmpty(selectField.getAlias())) selectField.setAlias(selectField.getFiled());
        if(selectField.getType() == null ) selectField.setType(SelectField.SelectEnum.DEFAULT);
        return true;
    }

}
