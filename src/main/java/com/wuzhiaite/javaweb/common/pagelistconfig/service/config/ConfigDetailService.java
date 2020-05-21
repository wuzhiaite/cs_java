package com.wuzhiaite.javaweb.common.pagelistconfig.service.config;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.StringUtil;
import com.wuzhiaite.javaweb.common.common.ComCrudServiceImpl;
import com.wuzhiaite.javaweb.common.pagelistconfig.enums.QueryEnum;
import com.wuzhiaite.javaweb.common.pagelistconfig.mapper.ConfigDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description 配置细节处理类
 * @author lpf
 */
@Service
@Slf4j
public class ConfigDetailService extends ComCrudServiceImpl<ConfigDetailMapper, Map<String,Object>> {
    @Autowired
    private ConfigOperService service;


    /**
     * 通用列表查询
     * @param params
     * @return
     */
    public  PageInfo<Map<String, Object>> pageList(Map<String,Object> params) {
        String sql = getQuerySql(params);
        Integer pageNum = MapUtil.getInteger(params, "pageNum");
        Integer pageSize = MapUtil.getInteger(params, "pageSize");
        pageNum = pageNum != null ? pageNum : 1 ;
        pageSize = pageSize != null ? pageSize : 10 ;
        PageHelper.startPage(pageNum, pageSize);
        String orders = MapUtil.getString(params,"orders");
        if (!StringUtil.isBlank(orders)) {
            PageHelper.orderBy(orders);
        }
        List<Map<String, Object>> list = mapper.getBySQL(sql);
        return new PageInfo<Map<String,Object>>(list) ;
    }

    /**
     * sql进行拼接
     * @param params
     * @return
     */
    private String getQuerySql(Map<String,Object> params){
        Map<String, Object> obj = this.get(params);
        String searchSql = MapUtil.getString(obj, "SEARCH_SQL");
        String conditionFileds = MapUtil.getString(obj, "CONDITION_FILEDS");
        List<Map<String,Object>> conditions = (List<Map<String, Object>>) JSONObject.parse(conditionFileds);
        Set<String> queries = params.keySet();
        StringBuilder conditionSQL = new StringBuilder();
        for(Map<String,Object> condition : conditions){
            String prop = MapUtil.getString(condition, "prop");
            String type = MapUtil.getString(condition, "type");
            if(queries.contains(prop)){
                for(QueryEnum value : QueryEnum.values()){
                    if( type .equals(value.type()) ){
                        value.appendStr(conditionSQL,prop,params.get(prop));
                    }
                }
            }
        }
        //模糊查询部分
        if(queries.contains("search")
                && StringUtil.isNotBlank((String) params.get("search"))){
            String search = MapUtil.getString(params, "search");
            String fileds = MapUtil.getString(obj, "SEARCH_FILEDS");
            if (conditionSQL.length() > 5) {
                conditionSQL.append(" AND  (");
            } else {
                conditionSQL.append(" ");
            }
            JSONArray arr = JSONObject.parseArray(fileds);
            int len = arr.size() ;
            for(int i = 0 ; i < len  ; i++){
                conditionSQL.append(arr.get(i))
                        .append("  LIKE ")
                        .append(" '%").append(search).append("%' ");
                conditionSQL.append(i < (len -1) ? " OR " : " ");
            }
        }

        String sql = new SQL() {{
            SELECT("*");
            FROM("( "+searchSql+" ) AS TB ");
            if(!StringUtils.isEmpty(conditionSQL.toString())){
                 WHERE(conditionSQL.toString());
            }
        }}.toString();
        log.info(sql);
        return sql  ;
    }

    /**
     * 查询所有
     * @param params
     * @return
     */
    public List<Map<String, Object>> getList(Map<String, Object> params) {
        String sql = getQuerySql(params);
        return mapper.getBySQL(sql);
    }

    /**
     * 通用的查找excel导出格式的数据
     * @param params
     * @return
     */
    public Map<String, Object> getExcelFormatData(Map<String, Object> params) {
        List<Map<String, Object>> list = getList(params);

        if(StringUtils.isEmpty(list) || list.size() < 1 ) {return null; };
        Map<String, Object> tempMap = list.get(0);
        Set<String> keysTemp = tempMap.keySet();
        List<List<String>> head = new ArrayList<>();
        for(String key : keysTemp){
            List<String> temp = new ArrayList<>();
            temp.add(key);
            head.add(temp);
        }
        //展示列数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        for (int i = 0; i < list.size(); i++) {
            List<Object> data = new ArrayList<Object>();
            Map<String, Object> temp = list.get(i);
            for(String key : keysTemp){
                Object value = temp.get(key);
                value = ! StringUtils.isEmpty(value) ? value : "";
                data.add(value);
            }
            dataList.add(data);
        }
        Map<String, Object> obj = this.get(params);
        Map<String,Object> map = new HashMap<>();
        map.put("head",head);
        map.put("dataList",dataList);
        String date = new SimpleDateFormat("yyyyMMddHHssmm").format(new Date());
        map.put("fileName",MapUtil.getString(obj,"CONFIG_NAME")+ ""+date );
        map.put("sheetName",MapUtil.getString(obj,"CONFIG_NAME"));
        return map;
    }
}
