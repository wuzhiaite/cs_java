package com.wuzhiaite.javaweb.common.activiti.service.impl;

import cn.hutool.core.lang.Assert;
import com.github.pagehelper.util.StringUtil;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.SpringSecurityUtil;
import com.wuzhiaite.javaweb.common.activiti.service.IActivitiTaskService;
import org.activiti.engine.*;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author lpf
 * @description 流程任务处理类
 * @since 20200710
 */
public class ActivitiTaskServiceImpl implements IActivitiTaskService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeservice;
    @Autowired
    private FormService formService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService histiryService;

    /**
     *  发起流程：
     *  1.发送表单数据的时候同时传送发送人（这个字段设置成固定字段即可${employee}）
     *  2.流程发起后获取当前流程实例的任务，将任务发送给指定的人员
     *
     *
     * @param params
     * @return
     */
    @Override
    public ProcessInstance startProcess(Map<String, Object> params) {
        Map<String,Object> process = (Map<String, Object>) params.get("process");
        Assert.notNull(process);
        Map<String,Object> form = (Map<String, Object>) params.get("form");
        Assert.notNull(form);
        String assigne = MapUtils.getString(params, "assigne");
        Assert.notNull(assigne);
        String processDefinitionId = MapUtil.getString(process, "ID_");
        //设置流程发起人为当前用户
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        form.put("employee",name);
        ProcessInstance instance = null;
        if(!StringUtil.isEmpty(processDefinitionId)){
            instance = runtimeservice.startProcessInstanceById(processDefinitionId, form);
        }else if(!StringUtil.isEmpty(MapUtil.getString(process,"KEY_"))){
            instance =  runtimeservice.startProcessInstanceByKey(MapUtil.getString(params,"KEY_"),form);
        }else{
            throw new RuntimeException("没有传入发起流程的key值，请重新确认！");
        }

        String id = instance.getId();
        Task task = taskService.createTaskQuery().processInstanceId(id).singleResult();
        taskService.claim(task.getId(),assigne);
        return instance;
    }

    /**
     *  获取当前用户待审批任务列表
     * @param params
     * @return
     */
    @Override
    public List<Task> getUserTaskList(Map<String, Object> params) {
        Integer pageNum = MapUtil.getInteger(params, "pageNum");
        Integer pageSize = MapUtil.getInteger(params, "pageSize");


        String username = SpringSecurityUtil.getCurrentUserName();
        TaskQuery taskQuery = taskService.createTaskQuery();


        taskQuery.taskAssignee(username);
        String processDefinitionKey = null;
        if(StringUtils.isEmpty(processDefinitionKey = MapUtil.getString(params,"processDefinitionKey"))){
            taskQuery.processDefinitionKey(processDefinitionKey);
        }

        List<Task> tasks = taskQuery.orderByProcessDefinitionId()
                .listPage(pageNum, pageSize);

        return tasks;
    }


}
