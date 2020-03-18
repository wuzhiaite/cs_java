package com.wuzhiaite.javaweb.module.pagelistconfig.service.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.StringUtil;
import com.wuzhiaite.javaweb.module.common.BaseService;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.PageConf;
import com.wuzhiaite.javaweb.module.pagelistconfig.enums.SQLJoinEnum;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.ConfigOperMapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.config.impl.SQLOperationImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 业务查询表
 */
@Service
@Log4j2
public class ConfigOperService extends BaseService {

    @Autowired
    private ConfigOperMapper mapper;

    @Autowired
    private SQLOperation sqlOper;

    //获取列表数据
    public PageInfo<Map<String, Object>> getPageList(Map<String, Object> params) throws Exception{
        Integer pageNum = MapUtil.getInteger(params, "pageNum");
        Integer pageSize = MapUtil.getInteger(params, "pageSize");
        pageNum = pageNum != null ? pageNum : 1 ;
        pageSize = pageSize != null ? pageSize : 10 ;
        PageHelper.startPage(pageNum,pageSize);
        String orders = MapUtil.getString(params,"orders");
        orders = StringUtil.isBlank(orders) ? " DETAIL.CREATE_TIME  DESC " : orders ;
        PageHelper.orderBy( orders );
        List<Map<String, Object>> pageList = mapper.getPageList(params);
        return new PageInfo<Map<String, Object>>(pageList);
    }

    /**
     * 插入和删除表单配置信息
     * @param params
     * @return
     */
    public int insertOrUpdate(Map<String, Object> params) throws RuntimeException{
        params.put( "UUID" , UUID.randomUUID() );
        Map<String,Object> details = (Map<String, Object>) params.get("detail");
        int count = insertOrUpdate(params, "id", "PAGE_LIST_CONFIG_FORM");
        details.put( "UUID" , UUID.randomUUID() );
        int countDetail = insertOrUpdate(details, "id", "PAGE_LIST_CONFIG_DETAIL");
        return count + countDetail;
    }

    /**
     * 查找配置的信息
     * @param id
     * @return
     */
    public PageConf getConf(String id) throws RuntimeException{
        return mapper.getConf(id) ;
    }
}
