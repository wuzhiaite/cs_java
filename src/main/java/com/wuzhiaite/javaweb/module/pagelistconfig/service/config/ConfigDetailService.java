package com.wuzhiaite.javaweb.module.pagelistconfig.service.config;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.module.common.ComCrudServiceImpl;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.PageDetail;
import com.wuzhiaite.javaweb.module.pagelistconfig.enums.QueryEnum;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.ConfigDetailMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ConfigDetailService extends ComCrudServiceImpl<ConfigDetailMapper, Map<String,Object>> {


    public  List<Map<String, Object>> pageList(Map<String,Object> params) {
        Map<String, Object> obj = this.get(params);
        String sql = MapUtil.getString(obj, "SEARCH_SQL");
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

        if(queries.contains("search")){
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

        return null ;
    }
}
