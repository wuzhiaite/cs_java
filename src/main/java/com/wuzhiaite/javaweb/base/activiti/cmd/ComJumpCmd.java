package com.wuzhiaite.javaweb.base.activiti.cmd;

import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.hibernate.service.spi.ServiceException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 跨接点跳转
 */
@Slf4j
public class ComJumpCmd implements Command, Serializable {


    private String taskId;
    private Map<String, Object> variables;
    private String desActivitiId;

    public ComJumpCmd(String taskId, Map<String, Object> variables, String desActivitiId) {
        this.taskId = taskId;
        this.variables = variables;
        this.desActivitiId = desActivitiId;
    }

    @Override
    public Object execute(CommandContext commandContext) {


        TaskEntityManager taskEntityManager = commandContext.getTaskEntityManager();
        TaskEntity taskEntity = taskEntityManager.findById(taskId);
        ExecutionEntity executionEntity = taskEntity.getExecution();
        // 设置流程变量
        executionEntity.setVariables(variables);

        // 触发执行监听器complete事件
        taskEntity.setEventName(TaskListener.EVENTNAME_COMPLETE);

        // 触发全局事件转发器TASK_COMPLETED事件
        if (commandContext.getProcessEngineConfiguration().getEventDispatcher().isEnabled()) {
            commandContext.getProcessEngineConfiguration().getEventDispatcher().dispatchEvent(ActivitiEventBuilder
                    .createEntityWithVariablesEvent(ActivitiEventType.TASK_COMPLETED, this, variables, false));
        }

        // 添加act_ru_identitylink表记录。常规流程实例id与执行id相同
        if (Authentication.getAuthenticatedUserId() != null && executionEntity.getProcessInstanceId() != null) {
            ExecutionEntity processInstanceEntity = commandContext.getExecutionEntityManager().findById(taskEntity.getProcessInstanceId());
            commandContext.getIdentityLinkEntityManager().involveUser(processInstanceEntity, Authentication.getAuthenticatedUserId(), IdentityLinkType.PARTICIPANT);
        }

        // 删除任务
        taskEntityManager.deleteTask(taskEntity, null, false, false);




        return null;
    }



}