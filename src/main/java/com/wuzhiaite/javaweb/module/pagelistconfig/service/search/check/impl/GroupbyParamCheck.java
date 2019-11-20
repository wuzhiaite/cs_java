package com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.impl;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.OrderField;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SelectField;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.CheckChain;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.Param;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.CheckFilter;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.entity.CheckParam;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 对groupby的参数进行过滤
 */
public class GroupbyParamCheck implements CheckFilter<CheckParam> {

    @Override
    public void checkParam(Param<CheckParam> param, CheckChain chain) {
        CheckParam checkParam = param.get();
        List<String> group = checkParam.getGroup();
        List<SelectField> selects =  checkParam.getSelect();
        List<OrderField> order = checkParam.getOrder();
        List<String> columnList = checkParam.getColumnList();
        //1.将select的字段不在groupby的字段进行移除
        ListIterator<SelectField> iterator = selects.listIterator();
        for(;iterator.hasNext();){
            SelectField next = iterator.next();
            if((next.getType().equals(SelectField.SelectEnum.DEFAULT) || next == null )
                    && !group.contains(next.getFiled())){
                    iterator.remove();
            }
        }
        //2.查询字段重新增加排序
        AtomicInteger index = new AtomicInteger();
        group.forEach( g -> {
            if(!columnList.contains(g)) throw new RuntimeException("分组列中"+g+"不能存在，请确定是否存在问题！");
            //将配置的字段删除，按照组合的顺序，重新进行添加
            List<SelectField>  field = getSelectFiled(selects,g);
            if(field.size() > 0){
                selects.remove(field);
                SelectField selectField = selects.get(0);
                selectField.setType(SelectField.SelectEnum.DEFAULT);
                selects.add(index.intValue(),selectField);
                index.incrementAndGet();
            }
        });
        //将orderby中非groupby列进行删除
        ListIterator<OrderField> orderIterator = order.listIterator();
        for(;orderIterator.hasNext();){
            OrderField orderField = orderIterator.next();
            String field = orderField.getField();
            if(group.contains(field)){
                orderIterator.remove();
            }
        }

    }
    /**获取属性名查询参数*/
    private List<SelectField> getSelectFiled(List<SelectField> selects, String g) {
        Predicate<SelectField> predicate = selectField -> {
          return selectField.getFiled().equals(g)
                  && (selectField.getType().equals(SelectField.SelectEnum.DEFAULT) || selectField.getType() == null);
        };
        List<SelectField> collect = selects.stream().filter(predicate).collect(Collectors.toList());
        return collect;
    }


}
