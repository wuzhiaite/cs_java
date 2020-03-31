package com.wuzhiaite.javaweb.module.pagelistconfig.controller;

import com.github.pagehelper.PageInfo;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.StringUtil;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.config.ConfigDetailService;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.config.ConfigOperService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/api/pagelist")
@Log4j2
public class ConfigController {
    /**
     *
     */
    @Autowired
    private ConfigOperService configOperService;
    /**
     *
     */
    @Autowired
    private ConfigDetailService detailService ;
    /**
     *
     */
    @Autowired
    private RedisTemplate redisTemplate ;
    /**
     * 查询台账列表数据
     * @param params
     * @return
     */
    @PostMapping("/getpagelist")
    public  ResultObj getPagelist(@RequestBody Map<String,Object> params){
        PageInfo<Map<String,Object>> resObj = null;
        try {
            String orders = MapUtil.getString(params,"orders");
            orders = StringUtil.isBlank(orders) ? " DETAILS.CREATE_TIME  DESC " : orders ;
            resObj = detailService.findListByPage( params , orders );
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        log.info(resObj);
        return ResultObj.successObj(resObj);
    }


    /**
     * 更新和修改
     * @param params
     * @return
     */
    @PostMapping("/savePageList")
    public ResultObj modifyOrAddPageConf(@RequestBody Map<String,Object> params){
        int count = 0;
        try {
            count = configOperService.insertOrUpdate(params);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }

        return ResultObj.successObj(count,"操作成功");
    }


    /**
     * 查找台账对应配置信息
     * @Param
     */
    @PostMapping("/pageconfig/{id}")
    @CachePut(key="#id",value="config")
    public ResultObj pageconfig(@PathVariable("id") String id){
        Map<String,Object> conf = new HashMap<String,Object>();
        try {
            conf.put("ID",id);
            conf = configOperService.get(conf);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return ResultObj.successObj(conf) ;
    }

    /**
     * 通用列表查询
     * @param id
     * @param params
     * @return
     */
    @PostMapping("/commonpage/{id}")
    public ResultObj commonpage(@PathVariable("id") String id,
                                @RequestBody Map<String,Object> params){
        params.put("ID",id);
        PageInfo<Map<String,Object>> list = null ;
        try {
            list = detailService.pageList(params);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(list) ;
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @PostMapping("/deletepage/{id}")
    public ResultObj deletepage(@PathVariable("id") String id){
        int count = 0;
        try {
            count = detailService.delete(id);
            count += configOperService.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(count) ;
    }




}
