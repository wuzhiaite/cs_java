package com.wuzhiaite.javaweb.common.pagelistconfig.controller;

import com.github.pagehelper.PageInfo;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.RedisUtil;
import com.wuzhiaite.javaweb.base.utils.StringUtil;
import com.wuzhiaite.javaweb.common.pagelistconfig.easyexcel.write.DownloadEntity;
import com.wuzhiaite.javaweb.common.pagelistconfig.service.config.ConfigDetailService;
import com.wuzhiaite.javaweb.common.pagelistconfig.service.config.ConfigOperService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    private RedisUtil redisUtil ;


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
            String id = MapUtil.getString(params, "ID");
            if( redisUtil.hget("pagelist", id) != null){
                redisUtil.hdel("pagelist",id);
            };
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
            if( redisUtil.hget("pagelist", id) != null ){
                conf = (Map<String, Object>) redisUtil.hget("pagelist", id);
            }else{
                conf.put("ID",id);
                conf = configOperService.get(conf);
                redisUtil.hset("pagelist",id,conf);
            }
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
            redisUtil.hdel("pagelist",id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(count) ;
    }

    /**
     * 通用文件下载方法
     * @param response
     * @param id
     */
    @PostMapping("/downloadFile/{id}")
    public void downloadFile(HttpServletResponse response,
                             @PathVariable String id,
                             @RequestBody Map<String,Object> params){
        try {
            params.put("ID",id);
            Map<String,Object> data = detailService.getExcelFormatData(params);
            DownloadEntity.builder()
                    .head((List<List<String>>) data.get("head"))
                    .sheetName((String) data.get("sheetName"))
                    .fileName((String) data.get("fileName"))
                    .list((List<Object>) data.get("dataList"))
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }


}
