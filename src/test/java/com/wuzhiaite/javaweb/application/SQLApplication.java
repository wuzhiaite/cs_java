package com.wuzhiaite.javaweb.application;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.ConditionField;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.OrderField;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SelectField;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.SearchMapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.entity.CheckParam;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.DefaultCheckChain;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.entity.ParamCheckWapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.impl.SelectParamCheck;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class SQLApplication {


    @Autowired
    private SearchMapper mapper;

    public void test(){







    }


    @Test
    public void paramTest() throws Exception {

        SearchFiled sf = getFiled();
        CheckParam cp = new CheckParam();
        cp.setSearchFiled(sf);

        DefaultCheckChain checkChain = new DefaultCheckChain();
        ParamCheckWapper wapper = new ParamCheckWapper(new SelectParamCheck(),"SelectParamCheck");

        checkChain.addCheck(wapper);
        checkChain.doCheck(cp);

    }

    @Test
    public void SQLTest(){
        SearchFiled searchFiled = getFiled();
        List<Map<String, Object>> search = mapper.search(searchFiled);
        System.out.println(search);
    }



    public SearchFiled getFiled(){
        SearchFiled searchFiled = new SearchFiled();
        //查询字段
        SelectField select1 = new SelectField();
        select1.setType(SelectField.SelectEnum.DEFAULT);
        select1.setFiled("fd_month");
        select1.setAlias("fd_month");

        SelectField select2 = new  SelectField();
        select2.setFiled("fd_aecode");
        select2.setAlias("fd_aecode");
        select2.setType(SelectField.SelectEnum.DEFAULT);

        SelectField select3 = new  SelectField();
        select3.setFiled("fd_account");
        select3.setAlias("fd_account");
        select3.setType(SelectField.SelectEnum.DEFAULT);

        SelectField select4 = new  SelectField();
        select4.setFiled("fd_money");
        select4.setAlias("fd_money");
        select4.setType(SelectField.SelectEnum.DEFAULT);

        List<SelectField> selectField = new ArrayList<SelectField>();
        selectField.add(select1);
        selectField.add(select2);
        selectField.add(select3);
        selectField.add(select4);

        //查询条件
        ConditionField c1 = new ConditionField();
        c1.setFiled("fd_month");
        c1.setCondition(ConditionField.ConditionEnum.EQUAR);
        List<String> value = new ArrayList<String>();
        value.add("2019-06");
        c1.setValue(value);
        ArrayList<ConditionField> conditionFields = new ArrayList<>();
        conditionFields.add(c1);
        //groupby  条件
        ArrayList<String> groupby = new ArrayList<>();
        groupby.add("fd_month");

        OrderField order = new OrderField();
        order.setField("fd_month");
        order.setOrderEnum(OrderField.OrderEnum.DESC);
        List<OrderField> o = new ArrayList<OrderField>();
        o.add(order);


        searchFiled.setSelect(selectField);
        searchFiled.setTablename("REPORT_TEST");
        searchFiled.setCondition(conditionFields);
//          searchFiled.setGroup(groupby);
        searchFiled.setOrder(o);
        return searchFiled ;
    }


























}
