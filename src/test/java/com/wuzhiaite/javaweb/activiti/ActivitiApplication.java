package com.wuzhiaite.javaweb.activiti;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuzhiaite.javaweb.base.activiti.cmd.JumpDeleteTaskCmd;
import com.wuzhiaite.javaweb.base.activiti.cmd.SetFLowNodeAndGoCmd;
import com.wuzhiaite.javaweb.base.utils.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
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

import java.util.*;

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

    /**
     *
     */
    @Test
    public void listTaskTest() {
        // 获取当前任务
        TaskService taskService = processEngine.getTaskService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ManagementService managementService = processEngine.getManagementService();


        Task currentTask = taskService.createTaskQuery().taskId("132502").singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(currentTask.getProcessDefinitionId());
        // 获取流程定义
        Process process = bpmnModel.getMainProcess();
        // 获取目标节点定义
        FlowNode targetNode = (FlowNode) process.getFlowElement("sid-C24BA4F5-F744-4DD7-8D51-03C3698044D2");

        // 删除当前运行任务，同时返回执行id，该id在并发情况下也是唯一的
        String executionEntityId = managementService.executeCommand(new JumpDeleteTaskCmd(currentTask.getId()));

        // 流程执行到来源节点
        managementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
    }

    /**
     * 获取配置的流程节点
     */
    @Test
    public void getProcessNodes(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        ProcessDefinition processDefinition = list.get(2);
        String id = processDefinition.getId();
        repositoryService.createProcessDefinitionQuery().deploymentId(id).latestVersion();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(id);
        Process mainProcess = bpmnModel.getMainProcess();
        log.info("===========",mainProcess.getName());
        Collection<FlowElement> fe = mainProcess.getFlowElements();
        Iterator<FlowElement> it = fe.iterator();
        for(;it.hasNext();){
            FlowElement next = it.next();
            log.info("******************* name:{},id:{},class:{}****************",
                    next.getName(),next.getId(),next.getClass().toString());
        }

        List<Process> processes = bpmnModel.getProcesses();
        Process process = processes.get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        Iterator<FlowElement> iterator = flowElements.iterator();
        for(;iterator.hasNext();){
            FlowElement el = iterator.next();
            log.info("============== name:{},id:{}",el.getName(),el.getId());
        }
    }








}
