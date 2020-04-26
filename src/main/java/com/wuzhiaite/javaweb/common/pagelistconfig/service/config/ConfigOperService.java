package com.wuzhiaite.javaweb.common.pagelistconfig.service.config;


import com.wuzhiaite.javaweb.common.common.ComCrudServiceImpl;
import com.wuzhiaite.javaweb.common.pagelistconfig.mapper.ConfigOperMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @descript 业务查询表
 * @author lpf
 */
@Service
@Log4j2
public class ConfigOperService extends ComCrudServiceImpl<ConfigOperMapper,Map<String,Object>> {

    /**
     * 配置细节查询处理service
     */
    @Autowired
    private ConfigDetailService detailService ;


    /**
     * 插入和删除表单配置信息
     * @param params
     * @return
     */
    public int insertOrUpdate(Map<String, Object> params) throws RuntimeException{
        Map<String,Object> form = (Map<String, Object>) params.get("FORM");
        int pageCount = this.saveOrUpdate(form, "ID");
        Map<String,Object> detail = (Map<String, Object>) params.get("DETAIL");
        int detailCount = detailService.saveOrUpdate(detail, "ID");
        return pageCount + detailCount ;
    }









}
