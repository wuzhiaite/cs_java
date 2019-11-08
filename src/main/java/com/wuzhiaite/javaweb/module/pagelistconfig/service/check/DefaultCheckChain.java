package com.wuzhiaite.javaweb.module.pagelistconfig.service.check;

import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity.ParamCheckWapper;
import org.springframework.util.Assert;

/**
 * 默认链式检查
 */
public class DefaultCheckChain implements CheckChain {
    /**
     *
     */
    private ParamCheckWapper[] wappers = new ParamCheckWapper[0];

    private static final int INCREMENT = 10;

    private int n = 0;

    private int pos = 0;

    //进行链式检查
    @Override
    public void doCheck(Param filed) throws Exception {
        if(pos < n){
            ParamCheckWapper wapper = wappers[pos++];
            CheckFilter paramCheck = wapper.getParamCheck();
            Assert.notNull(paramCheck);
            paramCheck.checkParam(filed,this);
        }
    }

    /**
     * 增加要进行过滤处理的类
     * @param checkWapper
     */
    public void addCheck(ParamCheckWapper checkWapper){

        for(ParamCheckWapper wapper : wappers){
            if(wapper == checkWapper)return ;
        }

        if(n == wappers.length){
            ParamCheckWapper[] newWappers = new ParamCheckWapper[n + INCREMENT];
            System.arraycopy(wappers, 0, newWappers, 0, n);
            wappers = newWappers;
        }
        wappers[n++] = checkWapper;


    }

}
