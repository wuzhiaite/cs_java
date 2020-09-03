package com.wuzhiaite.javaweb.common.activiti.controller;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.common.activiti.service.IActivitiTaskService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lpf
 * @description  任务执行
 * @since 20200710
 */
@Slf4j
@RestController
@RequestMapping("/activiti/task")
public class ActivitiTaskController {
    /**
     * 业务处理类
     */
    @Autowired
    private IActivitiTaskService taskService ;

    /**
     *  发起流程
     * @param params
     * @return
     */
    @PostMapping("/startprocess")
    public ResultObj startProcess(@RequestBody Map<String,Object> params){
        ProcessInstance instance = null ;
        try {
            instance  = taskService.startProcess(params);
        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(instance.getId());
    }

    /**
     * 获取当前用户的任务信息
     * @param params
     * @return
     */
    @PostMapping("/getUserTaskList")
    public ResultObj getUserTaskList(@RequestBody Map<String,Object> params){
        List<Task> list = null ;
        try {
            list = taskService.getUserTaskList(params);

        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(list);
    }

    /**
     * 完成任务
     * @param params
     * @return
     */
    @PostMapping("/completeTask")
    public ResultObj complateTask(@RequestBody Map<String,Object> params){
        try {
            taskService.complateTask(params);

        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj("流转成功");
    }

    @PostMapping("/fallbackTask")
    public ResultObj  fallbackTask(@RequestBody Map<String,Object> params){
        try {
            taskService.fallbackTask(params);
        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj("流转成功");
    }









}
