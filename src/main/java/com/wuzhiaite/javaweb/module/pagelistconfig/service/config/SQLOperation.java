package com.wuzhiaite.javaweb.module.pagelistconfig.service.config;

import java.util.List;
import java.util.Map;

/**
 * SQL检索处理类
 *
 */
public interface SQLOperation {
    /**
     * 获取查询字段SQL片段
     * @param sql
     * @param startIndex
     * @return
     */
    public Map<String,Object> getSelectFiledScript(String sql, int startIndex);

    /**
     * 获取join连接sql片段
     * @param list
     * @param sql
     * @param startIndex
     * @return
     */
    public List<String> getJoinScript(List<String> list,String sql, int startIndex);

    /**
     * 切分片段的别名和值
     * @param filed
     * @return
     */
    public Map<String,String> splitAliasFiled(String filed);

    /**
     * 切割多表联查select参数
     * @param filed
     * @return
     */
    public Map<String,String> splitFiled(String filed);

}
