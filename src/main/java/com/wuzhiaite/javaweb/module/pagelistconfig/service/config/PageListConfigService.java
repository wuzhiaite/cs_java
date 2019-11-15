package com.wuzhiaite.javaweb.module.pagelistconfig.service.config;

import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.module.pagelistconfig.enums.IgnoreSQLJoinEnum;
import com.wuzhiaite.javaweb.module.pagelistconfig.enums.SQLJoinEnum;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.config.entity.SelectFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.config.impl.SQLOperationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.thymeleaf.util.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置页面
 */
@Service
@Transactional
@Slf4j
public class PageListConfigService {

//    @Autowired
    private SQLOperation sqlOper = new SQLOperationImpl();

    public void getTableInfo(String sql){
        //1.过滤掉无关紧要的字符；
        for(IgnoreSQLJoinEnum value :IgnoreSQLJoinEnum.values()){
           sql = sql.replace(value.name(),"");
        }
        sql = sql.trim();
        //获取所有的表及对应的字段
        List<Map<String,Object>> list = new ArrayList<>();
        getTableFiled(sql,list);

    }

    /**
     * 值存取结构
     * 获取表格字段信息
     * @param sql
     * @param list
     */
    private List<Map<String, Object>> getTableFiled(String sql, List<Map<String, Object>> list) {
        Map<String, Object> selectFiledsMap = sqlOper.getSelectFiledScript(sql, 0);
        String selectScript = MapUtil.getString(selectFiledsMap, "script");
        String[] selectFileds = selectScript.split(",");
        for(String selectFiled : selectFileds){
             //先获取别名
            Map<String, String> selectAlias = sqlOper.splitAliasFiled(selectFiled);
            String selectFiledValue = MapUtil.getString(selectAlias, "value");
            if(selectFiledValue.contains(SQLJoinEnum.SELECT.name())){//可能性比较小，先忽略
                //这里对表进行处理
                List<Map<String,Object>> childFiled = new ArrayList<>();
                 this.getTableFiled(selectFiled,childFiled);
            }else{
                Map<String, String> stringStringMap = sqlOper.splitFiled(selectFiledValue);


            }

        }

        //获取主表


        //




        return list;
    }


    /**
     * 现在主要的是数据结构的问题：
     *  核心点：表及表字段；
     *  主查询，字段名称
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */


    /**
     * 要对sql进行拆分，找出sql中的要查找的表格，以及表格中的字段
     * 主要功能点：1.找到要查找的表，通过表找到要查找列的备注信息
     *            2.将表中列的别名和中文备注对应起来；
     *            3.用于搜索框条件配置，根据配置字段进行数据查询；
     *            4.用于高级查询，字段进行查询
     * @param sql
     * @return
     */
    public Map<String, List<String>> getSelects(String sql){

        //1.对sql进行拆分，以join 进行拆分
        //2.对join拆分后的代码块进行拆分，存在两种情况：1.单表连接；2.多表生成临时表，对临时表处理
        //临时表处理里面又会涉及到表的拆分问题；
        //3.拆分后的表，获取表中要查询的表名称，和表中的字段；


        //1.查找需要的字段
        /*
         *  几种需要特殊处理的格式：
         *  eg1: SELECT USER_ID,(SELECT USERNAME FROM USER U WHERE U.USER_ID = U1.USER_ID ) USERNAME FROM USER U1
         *  eg2:SELECT  JBXX.XH LADJXH , JBXX.*,LCXX.BZMC , CASE WHEN LCXX.YWZT ='Finished' THEN '已结束' WHEN LCXX.YWZT ='Underway' THEN '正在办理' END YWZT
         *		FROM T_XZCF_JBXX JBXX
         */
        sql = sql.toUpperCase().trim();
        sqlOper = new SQLOperationImpl();//测试使用
        Map<String, Object> script = sqlOper.getSelectFiledScript(sql, 0);
        String select = MapUtil.getString(script,"script");
        int startIndex = (int) script.get("index");


        String[] selectFileds = select.split(",");

        //存储表别名对应的表字段
        Map<String,List<SelectFiled>> info = new HashMap<>();
        for(String filed : selectFileds){
            String conditionName = "";
            String alias = "";
            String name = "";
            String tableAlias = "";

            //1.类型为 (SELECT USERNAME FROM USER U WHERE U.USER_ID = U1.USER_ID )
            if( filed.indexOf(SQLJoinEnum.SELECT.name())!= -1){continue;}

            //2.类型为: JBXX.XH  AS LADJXH,CASE WHEN LCXX.YWZT ='Finished' THEN '已结束' WHEN LCXX.YWZT ='Underway' THEN '正在办理' END YWZT

            //没有点号分割的时候
            int index = filed.lastIndexOf(SQLJoinEnum.AS.name());
            if(index == -1){
                conditionName = filed;
                name = filed;
                alias = filed;
            }else{
                conditionName = filed.substring(0, index).trim();
                log.info("conditonName:"+conditionName);
                alias = filed.substring(index + SQLJoinEnum.AS.length(), filed.length()).trim();
            }

            if(conditionName.indexOf(".") == -1){
                name = conditionName;
            } else if(conditionName.indexOf(".") == conditionName.lastIndexOf(".") ){
                index = conditionName.indexOf(".");
                tableAlias = conditionName.substring(0, index);
                name = conditionName.substring(index+1,conditionName.length());
            }else{
                index= conditionName.indexOf(".");
                String preStr = conditionName.substring(0, index).trim();
                String subStr = conditionName.substring(index + 1, conditionName.length()).trim();
                int blank = preStr.lastIndexOf(" ");
                preStr = preStr.substring(blank,preStr.length()-1);
                blank = subStr.indexOf(" ");
                subStr = subStr.substring(0,blank);
                tableAlias = preStr ;
                name = subStr ;
            }
            ArrayList<SelectFiled> arr = new ArrayList<SelectFiled>();
            if(info.get(tableAlias) != null){
                arr = (ArrayList<SelectFiled>) info.get(tableAlias);
            }
            SelectFiled selectFiled = new SelectFiled();
            selectFiled.setName(name);
            selectFiled.setConditionName(conditionName);
            selectFiled.setAlias(alias);
            arr.add(selectFiled);
            info.put(tableAlias,arr);
        }

        System.out.println(info);
        String temptSQL = sql.substring(startIndex,sql.length()-1);
        List<String> joinList = new ArrayList<String>();
        sqlOper.getJoinScript(joinList,temptSQL,0);








        return null;
    }

    public static void main(String[] args) {


        PageListConfigService service = new PageListConfigService();
        String sql = " SELECT USER_ID AS ID,USERNAME AS USERNAME,(SELECT USERNAME FROM USER U WHERE U.USER_ID = U1.USER_ID ) AS USERNAME,CASE WHEN LCXX.YWZT ='Finished' THEN '已结束' WHEN LCXX.YWZT ='Underway' THEN '正在办理' END AS YWZT FROM USER U1 ";
        Map<String, List<String>> listMap = service.getSelects(sql);
        System.out.println(listMap);


    }




}
