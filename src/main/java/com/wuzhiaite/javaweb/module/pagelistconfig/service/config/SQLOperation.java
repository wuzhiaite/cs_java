package com.wuzhiaite.javaweb.module.pagelistconfig.service.config;

import java.util.List;
import java.util.Map;

/**
 * SQL检索处理类
 *
 */
public interface SQLOperation {
    //
    public Map<String,Object> getSelectFiledScript(String sql, int startIndex);


    public List<String> getJoinScript(String sql, int startIndex);

}
