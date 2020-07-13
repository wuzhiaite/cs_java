package com.wuzhiaite.javaweb.common.activiti.controller;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.common.activiti.service.IActivitiHistoricService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description  流程历史数据
 * @author lpf
 */
@Slf4j
@RestController
@RequestMapping("/api/activiti/historic")
public class ActivitiHistoricController {


    @Autowired
    private IActivitiHistoricService historicService;

    /**
     *
     * @return
     */
    @PostMapping("/pagelist")
    public ResultObj historicPageList(@RequestBody Map<String,Object> params){
        List<HistoricTaskInstance> list = null ;
         try {
               list = historicService.getHistoricPageList(params);
         } catch (Exception e) {
             log.error(e.getMessage());
             ResultObj.failObj(e.getMessage());
         }
         return ResultObj.successObj("流转成功");
     }
    /**
     *
     * @param params
     * @return
     */
     @PostMapping("/getHistoricDetail")
     public ResultObj getHistoricDetail(@RequestBody Map<String,Object> params){
         HistoricDetail detail = null ;
         try {
             detail = historicService.getHistoricDetail(params);
         } catch (Exception e) {
             log.error(e.getMessage());
             ResultObj.failObj(e.getMessage());
         }
         return ResultObj.successObj("流转成功");
     }




}
