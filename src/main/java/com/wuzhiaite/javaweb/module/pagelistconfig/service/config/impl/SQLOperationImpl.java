package com.wuzhiaite.javaweb.module.pagelistconfig.service.config.impl;

import com.wuzhiaite.javaweb.module.pagelistconfig.enums.SQLJoinEnum;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.config.SQLOperation;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对SQL按照关键字进行切割
 * @author  lpf
 * @since 2019-11-15
 */
@Service
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
     * 获取join片段
     * @param list
     * @param sql
     * @param startIndex
     * @return List<String>
     */
    @Override
    public List<String> getJoinScript(List<String> list,String sql, int startIndex) {
        sql = sql.substring(startIndex);
        if(sql.contains(SQLJoinEnum.JOIN.name())){
            int index = sql.indexOf(SQLJoinEnum.JOIN.name());
            sql = sql.substring(index + SQLJoinEnum.JOIN.length()).trim();
        }
        return getJoinListScript(list,sql,0);
    }

    /**
     * 切割join片段,不需要在这里判断是否有关键字
     * 第一步：找到第一个join和on之间的片段
     * 第二步：判断片段中有join么，如果有，在on之后的片段中载找on标识，在进行判断两个on中间有join么，如果有就继续循环
     * 第三步：直到两个on中不再包含join,截取最后一个on和第一个join之间的片段；加入list中
     * 第四步：将on之后的代码进行截取，判断是否有JOIN，如果有则重新进入循环，直到ON后面没有join为止，返回list
     * @param sql
     * @param startIndex
     * @return List<String>
     */
    private List<String> getJoinListScript(List<String> list, String sql, int startIndex) {
        String temp = sql.substring(startIndex);
        int onIndex = temp.indexOf(SQLJoinEnum.ON.name());
         temp = temp.substring(0,onIndex);
        if(temp.contains(SQLJoinEnum.JOIN.name())){
           return this.getJoinListScript(list,sql,startIndex + onIndex + SQLJoinEnum.ON.length());
        }

        temp = sql.substring(0,startIndex + onIndex).trim();
        list.add(temp);
        String middleStr = sql.substring(startIndex + onIndex + SQLJoinEnum.ON.length());
        if(middleStr.contains(SQLJoinEnum.JOIN.name())){
            return this.getJoinScript(list,middleStr,0);
        }
        return list;
    }

    /**
     *  对传入的字段进行切割
     *  两个功能点：1.以AS为切割点，分为
     * @param filed
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> splitFiled(String filed) {
        HashMap<String, String> map = new HashMap<>();
        int index = filed.lastIndexOf(SQLJoinEnum.AS.name());
        String value = "";
        String alias = "";
        if( index != -1 ){
              value = filed.substring(0,index).trim();
              alias = filed.substring(index + SQLJoinEnum.AS.length()).trim();
        }else{
            value = filed.trim();
            alias = filed.trim();
        }
        map.put("value",value);
        map.put("alias",alias);
        return map;
    }






























    public static void main(String[] args) {

        SQLOperation oper = new SQLOperationImpl();
//        String sql = "SELECT USER_ID,(SELECT USERNAME FROM USER U WHERE U.USER_ID = U1.USER_ID ) USERNAME FROM USER U1";
//        Map<String, Object> str = oper.getSelectFiledScript(sql, 0);
//        System.out.println(str+"*");
        String sql = "JOIN (SELECT distinct SL.BH\n" +
                "\t\t\t\t\t\t,SL.B_DWMC,SL.YWZT,BZDY.BZMC,RWQX \n" +
                "\t\t\t\t\t\tFROM T_WORKFLOW_GZLCSL SL\n" +
                "\t\t\t\t\t\tLEFT JOIN (SELECT LCSLBH,BZDYBH FROM T_WORKFLOW_DQBZ WHERE SFZB != 'READER' GROUP BY LCSLBH,BZDYBH) DQBZ ON DQBZ.LCSLBH = SL.BH\n" +
                "\t\t\t\t\t\tLEFT JOIN T_WORKFLOW_LCBZDY BZDY ON BZDY.BZBH = DQBZ.BZDYBH\n" +
                "\t\t\t\t\t\tJOIN (SELECT LCSLBH FROM V_WORKFLOW_BZXX V_BZXX \n" +
                "\t\t\t\t\t\tWHERE  ###clr### GROUP BY LCSLBH) LSBZ ON LSBZ.LCSLBH = SL.BH ) LCXX ON  LCXX.BH = JBXX.BWBH  \n" +
                "\t\t\t\tLEFT JOIN T_PLATFORM_XZQHDM XZQH ON  XZQH.XZQHDM = JBXX.XZQHDM \n" +
                "\t\t\t\tLEFT JOIN T_PLATFORM_HYLX HYLX ON HYLX.HYLXDM = JBXX.HYDM ";
        List<String> list = new ArrayList<String>();
        list = oper.getJoinScript(list, sql, 0);
        System.out.println(list);


    }



}
