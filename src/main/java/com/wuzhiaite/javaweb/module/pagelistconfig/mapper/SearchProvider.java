package com.wuzhiaite.javaweb.module.pagelistconfig.mapper;

import com.wuzhiaite.javaweb.base.utils.StringUtil;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.ConditionField;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.OrderField;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SelectField;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 方法查询
 */
@Slf4j
public class SearchProvider {

    private static final String AND = "AND";

    /**
     * 注意点：1.orderby 如果分页的话去掉
     *
     * @param filed
     * @return
     */
    public String search(SearchFiled filed){
        try{
            List<SelectField> select = filed.getSelect();
            Assert.notNull(select,"需要查询的列不能为空！");
            //select查询字段
            StringBuilder selStr = new StringBuilder();
            select.forEach(column->{
                selStr.append(",");
                column.appendCol(selStr);
            });
            String columns = selStr.toString().substring(1);
            String tableName = filed.getTablename();
            Assert.notNull(tableName,"表明不能为空");

            //where条件
            List<ConditionField> conditions = filed.getCondition();
            StringBuilder conStr = new StringBuilder();
            conditions.forEach(condition -> {
                conStr.append(AND+" \t ");
                condition.appendConditon(conStr);
            });
            String where = conStr.toString().substring(3);

            //groupby条件
            List<String> group = filed.getGroup();
            String groupby = "";
            if(!StringUtils.isEmpty(group)){
                groupby = group.stream().collect(Collectors.joining(","));
            }
            String finalGroupby = groupby;
            //orderby条件
            List<OrderField> orderList = filed.getOrder();
            StringBuilder orderStr = new StringBuilder();
            orderList.forEach(order -> {
                orderStr.append(",");
                order.appendOrder(orderStr);
            });
            String orderby = orderStr.toString().substring(1);

            //拼接SQL
            String sql = new SQL(){{
                SELECT(columns);
                FROM(tableName);
                if(StringUtil.isNotBlank(where)){
                    WHERE(where);
                }
                if(StringUtil.isNotBlank(finalGroupby)){
                    GROUP_BY(finalGroupby);
                }
                if(StringUtil.isNotBlank(orderby)){
                    ORDER_BY(orderby);
                }

            }}.toString();

            log.info(sql);
            return sql;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null ;
    }





}
