package com.wuzhiaite.javaweb.module.pagelistconfig.service.config;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.StringUtil;
import com.wuzhiaite.javaweb.module.common.ComCrudServiceImpl;
import com.wuzhiaite.javaweb.module.pagelistconfig.enums.QueryEnum;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.ConfigDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description 配置细节处理类
 * @author lpf
 */
@Service
@Slf4j
public class ConfigDetailService extends ComCrudServiceImpl<ConfigDetailMapper, Map<String,Object>> {

    /**
     *
     */
    @Autowired
    private ConfigDetailMapper mapper ;

    /**
     * 通用列表查询
     * @param params
     * @return
     */
    public  PageInfo<Map<String, Object>> pageList(Map<String,Object> params) {
        Map<String, Object> obj = this.get(params);
        String searchSql = MapUtil.getString(obj, "SEARCH_SQL");
        String conditionFileds = MapUtil.getString(obj, "CONDITION_FILEDS");
        List<Map<String,Object>> conditions = (List<Map<String, Object>>) JSONObject.parse(conditionFileds);
        Set<String> queries = params.keySet();
        StringBuilder conditionSQL = new StringBuilder(" 1=1  ");
        for(Map<String,Object> condition : conditions){
            String prop = MapUtil.getString(condition, "prop");
            String type = MapUtil.getString(condition, "type");
            if(queries.contains(prop)){
                for(QueryEnum value : QueryEnum.values()){
                    if( type .equals(value.type()) ){
                        conditionSQL.append(" AND  ");
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
            conditionSQL.append(" AND  (");
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
            WHERE(conditionSQL.toString());
        }}.toString();
        log.info(sql);
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
}
