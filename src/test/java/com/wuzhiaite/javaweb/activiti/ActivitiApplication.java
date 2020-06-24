package com.wuzhiaite.javaweb.activiti;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuzhiaite.javaweb.base.utils.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ActivitiApplication {

    private static  ProcessEngine processEngine;
    @Before
    public void init(){
         processEngine
                = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJdbcUrl("jdbc:mysql://localhost:3307/activiti?serverTimezone=GMT%2B8")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setJdbcUsername("root")
                .setJdbcPassword("root")
                .setJdbcMaxActiveConnections(100)
                .setAsyncExecutorActivate(false)
                .buildProcessEngine();
    }

    @Test
    public void deployProcess(){
//        this.init();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("org/activiti/test/my-process.bpmn20.xml")
                .deploy();

        log.info("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
    }


    @Test
    public void startProcess(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);

        log.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
    }

    @Test
    public void endProcess(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            log.info("Task available: " + task.getName());
        }
        //任务完成操作
        Task task = tasks.get(0);
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("vacationApproved", "false");
        taskVariables.put("managerMotivation", "We have a tight deadline!");
        taskService.complete(task.getId(), taskVariables);
    }
    @Test
    public void getTaskList() throws JsonProcessingException {
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee("kermit")
//                .processVariableValueEquals("orderId", "0815")
                .orderByDueDateNullsFirst().asc()
                .list();
        log.info("=================={}=============", JsonMapperUtil.toString(tasks));

    }
    
    @Test
    public void getTaskList2() throws JsonProcessingException {
        ManagementService managementService = processEngine.getManagementService();
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createNativeTaskQuery()
                .sql("SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T ")
//                .parameter("taskName", "gonzoTask")
                .list();
        log.info("========{}==============",JsonMapperUtil.toString(tasks));
    }


















}
