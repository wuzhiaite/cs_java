package com.wuzhiaite.javaweb.activiti;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuzhiaite.javaweb.base.utils.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("processes/VacationRequest.bpmn20.xml")
                .deploy();
//        repositoryService.createDeployment().
        log.info("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
    }

    @Test
    public void getDeployProcess() throws JsonProcessingException {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        int firstResult=1;
        int maxResults = 10;
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().listPage(firstResult,maxResults);
        list.stream().forEach(p->{
            log.info("============  process//id:{},name:{},key:{},deploymentId:{} ============",
                    p.getId(),p.getName(),p.getKey(),p.getDeploymentId());
        });

    }

    @Test
    public void getStartedProcess(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        list.stream().forEach(p->{
            try {
                log.info("================= id:{},name:{} ==============", p.getDeploymentId(),p.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void startProcess(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");
        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequestUser", variables);

        log.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
    }



    @Test
    public void getTaskList() throws JsonProcessingException {
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateGroup("management")
                .list();
        for (Task task : tasks) {
            log.info("Task available: " + task.getName());
        }
        ManagementService managementService = processEngine.getManagementService();
    }
    @Test
    public void doTheTask(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        Task task = tasks.get(1);
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("vacationApproved", "false");
        taskVariables.put("managerMotivation", "We have a tight deadline!");
        taskService.complete(task.getId(), taskVariables);
    }

    /**
     *
     */
    @Test
    public void doNextProcess(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("Kermit").list();
        for (Task task : tasks) {
            log.info("=============================Task available: " + task.getName());
            log.info("============================= Task variable:" + task.getDescription());
        }
    }

    /**
     * 结束任务
     */
    @Test
    public void doEndProcess(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("Kermit").list();
        Task task = tasks.get(0);
        HashMap<String, Object> map = new HashMap<>();
        map.put("resendRequest", false);
        log.info("======================{}complete==============",task.getName());
        taskService.claim(task.getId(),"");
        taskService.complete(task.getId(),map);
    }
    @Autowired
    private IdentityService identityService;
    @Test
    public void manageTest(){
        ManagementService managementService = processEngine.getManagementService();

    }













}
