package com.wuzhiaite.javaweb.module.pagelistconfig.service.search;

import com.github.pagehelper.PageHelper;
import com.wuzhiaite.javaweb.base.properties.BaseProperties;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.*;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.SearchMapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.SearchProvider;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.entity.CheckParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.util.*;

/**
 * 查询业务处理类
 * @author  lpf
 * @since 2019-11-08
 */
@Service
@Transactional
@Slf4j
public class SearchService {
    /**查询mapper */
    @Autowired
    private SearchMapper mapper;
    /**单表业务查询*/
    @Autowired
    private SingleTableOperService singleTableService;
    /**基础配置信息*/
    @Autowired
    private BaseProperties baseProperties;
    /**通用的SQL拼接*/
    @Autowired
    private SearchProvider provider;
    /**校验service*/
    @Autowired
    private SearchConfigService service;
    private static final String  LEVE = "LEVEL";

    /**
     * 进行参数校验
     * @param searchFiled
     * @throws Exception
     */
    private void checkParam(SearchFiled searchFiled) throws Exception {
        //获取表中列信息
        String tablename = searchFiled.getTablename();
        Table table = new Table();
        table.setName(tablename);
//        table.setSchema(baseProperties.getDatabaseName());
        table.setSchema("cmbi_report");
        singleTableService.getColumnInfo(table);
        //创建param过滤对象,并进行过滤
        CheckParam param = new CheckParam();
        param.setSearchFiled(searchFiled);
        param.setTable(table);
        service.doCheck(param);
    }


    /**
     * 进行数据搜索
     * @param searchFiled
     * @return
     */
    @Transactional
    public List<Map<String,Object>> search(SearchFiled searchFiled) throws Exception{
        //参数进行校验
        checkParam(searchFiled);
        //根据SQL查询数据
        //1.判断是否要进行分页
        Integer pageNum = searchFiled.getPageNum();
        Integer pageSize = searchFiled.getPageSize();
        List<Map<String,Object>> resultObj = null;
        if(pageNum == null || pageSize == null){
           resultObj = getResultObj(searchFiled);
        }else{
            PageHelper.startPage(pageNum,pageSize);
            String orderby = provider.getOrderby(searchFiled);
            PageHelper.orderBy(orderby);
            searchFiled.setOrder(null);
            resultObj = getResultObj(searchFiled);
        }
        return resultObj;
    }



    //查询方式分为三种：1.对列进行单独统计的，没有groupby相关的；
    //2.有分组的进行，相关查询拆分；
    /**
     * 数据搜索查找并进行数据处理
     * @param searchFiled
     * @return
     */
    private List<Map<String, Object>> getResultObj(SearchFiled searchFiled) throws  Exception{
        //对于不需要分组的数据
        List<String> group = searchFiled.getGroup();
        int size = 0;
        if(StringUtils.isEmpty(group) && (size = group.size()) < 1){
            return mapper.search(searchFiled);
        }

        //对于要分组的数据
        //1.拼接SQL，查找每个
        SearchFiled sf = (SearchFiled) searchFiled.deepClone();//克隆一份
        this.addLevel(sf,1);

        String sql = provider.search(searchFiled);
        List<Map<String, Object>> searchList = mapper.get(sql);
        Map<Integer,List<Map<String,Object>>> map = new HashMap<>();
        map.put(1,searchList);
        List<String> sfGroup = sf.getGroup();
        //对参数进行处理
        for(int i = 1 ;size > 1; i++){
            size = size -1;
            String groupStr = sfGroup.get(size);
            sf.removeGroup(groupStr);
            this.addLevel(sf,i+1);
            sql = provider.search(sf);
            searchList = mapper.get(sql);
            map.put(i,searchList);
        }
        log.info(sql.toString());
        List<Map<String,Object>> result = new ArrayList<>();
        formatData(map,result,searchFiled);
        return result;
    }
    /**对数据进行格式化**/
    private void formatData(Map<Integer, List<Map<String, Object>>> map, List<Map<String, Object>> result, SearchFiled searchFiled) {



    }

    /**
     * 增加等级及查询字段
     * @param sf
     * @param i
     */
    private void addLevel(SearchFiled sf, int i) {
        //select参数和orderby参数新增
        SelectField field = new SelectField();
        field.setFiled(i+"");
        field.setType(SelectField.SelectEnum.DEFAULT);
        field.setAlias(LEVE);
        List<SelectField> selects = sf.getSelect();
        selects.add(field);
        sf.setSelect(selects);
    }


    public static void main(String[] args) {
        SearchService ss = new SearchService();
        SearchFiled filed = getFiled();


    }

    public static SearchFiled getFiled(){
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



/**
 * 前端列表展示的要点：
 * 1.列的合并参数，合并行，合并列（rownum,value）
 * 2.逐行数据写入；
 * 存在的问题，逻辑编辑，在后端完成，前端只需要做数据回写就可以；
 * 不能数据处理的
 * 解决思路：
 * 1.多次数据库查询，再进行排列；
 * 2.拼接SQL；数据进行调整；
 * 3.原始SQL查询后，后台应用进行数据处理；
 */














