package com.wuzhiaite.javaweb.common.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * @description 流程任务处理类
 * @author  lpf
 */
public interface IActivitiTaskService {


    ProcessInstance startProcess(Map<String, Object> params);


    List<Task> getUserTaskList(Map<String, Object> params);
}
