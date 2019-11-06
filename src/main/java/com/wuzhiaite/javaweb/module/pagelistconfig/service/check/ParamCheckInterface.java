package com.wuzhiaite.javaweb.module.pagelistconfig.service.check;

import org.springframework.stereotype.Service;

@Service
public interface ParamCheckInterface <T>{


    public abstract boolean checkParam(T t);



}
