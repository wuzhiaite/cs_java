package com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check;

import org.springframework.stereotype.Service;

/**
 * 对参数进行校验
 * @author lpf
 */
@Service
public interface CheckFilter<T extends Param> {

    /**
     * 参数校验方法
     * @param chain
     * @return
     */
    public abstract void checkParam(Param<T> param, CheckChain chain) throws Exception;


}
















