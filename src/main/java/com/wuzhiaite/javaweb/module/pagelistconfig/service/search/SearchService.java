package com.wuzhiaite.javaweb.module.pagelistconfig.service.search;

import com.github.pagehelper.PageHelper;
import com.wuzhiaite.javaweb.base.properties.BaseProperties;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Table;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.SearchMapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.SearchProvider;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.entity.CheckParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Map;

/**
 * 查询业务处理类
 * @author  lpf
 * @since 2019-11-08
 */
@Service
@Transactional
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
        table.setSchema(baseProperties.getDatabaseName());
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
        return null;
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
        if(StringUtils.isEmpty(group)){
            return mapper.search(searchFiled);
        }

        //对于要分组的数据
        //1.拼接SQL，查找每个

















        return null;
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
















