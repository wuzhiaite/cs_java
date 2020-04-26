package com.wuzhiaite.javaweb.common.pagelistconfig.enums;

/**
 * sql关联表字符
 * @author lpf
 * @since 2019-11-14
 */
public enum SQLJoinEnum {

        SELECT,
        AS,
        FROM,
        ON,
        JOIN;

    /**
     * 连接字符的长度
     * @return
     */
    public int length(){
            return this.name().length();
        }




}
