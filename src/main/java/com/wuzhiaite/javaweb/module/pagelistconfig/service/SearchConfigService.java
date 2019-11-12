package com.wuzhiaite.javaweb.module.pagelistconfig.service;

import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.DefaultCheckChain;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.Param;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity.ParamCheckWapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.impl.ConditionParamCheck;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.impl.GroupbyParamCheck;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.impl.OrderbyParamCheck;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.impl.SelectParamCheck;
import org.springframework.stereotype.Service;

/**
 *
 * 主要用于参数校验链路配置
 * @author  lpf
 */
@Service
public class SearchConfigService {

    /**默认检查链*/
    private static DefaultCheckChain checkChain ;
    /**过滤链路表配置*/
    static{
        checkChain = new DefaultCheckChain();
        //参数检查器
        ParamCheckWapper selectParamCheck = new ParamCheckWapper(new SelectParamCheck(),"SelectParamCheck");
        ParamCheckWapper groupParamCheck = new ParamCheckWapper(new GroupbyParamCheck(), "groupParamCheck");
        ParamCheckWapper conditionParamCheck = new ParamCheckWapper(new ConditionParamCheck(), "conditionParamCheck");
        ParamCheckWapper orderbyParamCheck = new ParamCheckWapper(new OrderbyParamCheck(), "orderbyParamCheck");

        //参数链表增加过滤类
        checkChain.addCheck(selectParamCheck);
        checkChain.addCheck(groupParamCheck);
        checkChain.addCheck(conditionParamCheck);
        checkChain.addCheck(orderbyParamCheck);
    }

    /**
     * 参数校验
     */
    public void doCheck(Param param) throws Exception {
        checkChain.doCheck(param);
    }




}
