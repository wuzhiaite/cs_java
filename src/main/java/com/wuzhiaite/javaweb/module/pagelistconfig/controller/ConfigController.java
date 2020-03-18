package com.wuzhiaite.javaweb.module.pagelistconfig.controller;

import com.github.pagehelper.PageInfo;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.PageConf;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.config.ConfigOperService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/pagelist")
@Log4j2
public class ConfigController {

    @Autowired
    private ConfigOperService configOperService;

    /**
     * 查询台账列表数据
     * @param params
     * @return
     */
    @PostMapping("/getpagelist")
    public  ResultObj getPagelist(@RequestBody Map<String,Object> params){
        PageInfo<Map<String, Object>> resObj = null;
        try {
            resObj = configOperService.getPageList(params);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResultObj.failObj(resObj,"查询失败");
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
            return ResultObj.failObj();
        }

        return ResultObj.successObj(count,"操作成功");
    }


    /**
     * 查找台账对应配置信息
     * @Param
     */
    @PostMapping("/pageconfig/{id}")
    public ResultObj pageconfig(@PathVariable("id") String id){
        PageConf conf = null ;
        try {
            conf = configOperService.getConf(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return ResultObj.successObj(conf) ;
    }



}
