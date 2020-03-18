package com.wuzhiaite.javaweb.module.pagelistconfig.service.config.impl;

import com.wuzhiaite.javaweb.module.pagelistconfig.enums.SQLJoinEnum;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.config.SQLOperation;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLOperationImpl implements SQLOperation {



    /**
     *  切割SELECT 和 FROM 之间的代码
     * @param sql
     * @param startIndex
     * @return
     */
    @Override
    public Map<String,Object> getSelectFiledScript(String sql, int startIndex) {
        String temp = sql.substring(startIndex,sql.length()-1);
        int index = temp.indexOf(SQLJoinEnum.FROM.name());
        Assert.notNull(index,"不存在FROM关键字，请确认SQL是否正确；");
        String sqlStr = temp.substring(0, index);
        int selectIndex = sqlStr.lastIndexOf(SQLJoinEnum.SELECT.name());
        if(selectIndex != 0 && selectIndex != -1 ){
            return  this.getSelectFiledScript(sql,index+SQLJoinEnum.FROM.length());
        }
        index = index + startIndex;
        String script = sql.substring(SQLJoinEnum.SELECT.length(), index).trim();
        HashMap<String, Object> map = new HashMap<>();
        map.put("index",index);
        map.put("script",script);
        return  map;
    }

    /**
     * 切割join片段,不需要在这里判断是否有关键字
     * @param sql
     * @param startIndex
     * @return
     */
    @Override
    public List<String> getJoinScript(String sql, int startIndex) {
        String temp = sql.substring(startIndex,sql.length()-1);
        int joinIndex = sql.indexOf(SQLJoinEnum.JOIN.name());
        int onIndex = sql.indexOf(SQLJoinEnum.ON.name());
        String str = temp.substring(joinIndex + 1, onIndex);





        return null;
    }






    public static void main(String[] args) {
        SQLOperation oper = new SQLOperationImpl();
        String sql = "SELECT USER_ID,(SELECT USERNAME FROM USER U WHERE U.USER_ID = U1.USER_ID ) USERNAME FROM USER U1";
        Map<String, Object> str = oper.getSelectFiledScript(sql, 0);
        System.out.println(str+"*");
    }



}
