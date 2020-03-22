package com.wuzhiaite.javaweb.module.pagelistconfig.service.config;



import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.module.common.ComCrudServiceImpl;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.PageDetail;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.ConfigDetailMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigDetailService extends ComCrudServiceImpl<ConfigDetailMapper, Map<String,Object>> {


    public  List<Map<String, Object>> pageList(Map<String,Object> params) {
        Map<String, Object> obj = this.get(params);
        String sql = MapUtil.getString(obj, "SEARCH_SQL");


        return null ;
    }
}
