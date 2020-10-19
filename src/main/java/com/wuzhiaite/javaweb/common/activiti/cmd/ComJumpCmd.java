package com.wuzhiaite.javaweb.common.activiti.cmd;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.task.IdentityLinkType;

import java.io.Serializable;
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