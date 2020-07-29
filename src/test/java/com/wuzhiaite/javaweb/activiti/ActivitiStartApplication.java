package com.wuzhiaite.javaweb.activiti;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.common.activiti.service.IActivitiTaskService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ActivitiStartApplication {


    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void getProcessList(){
        List<ProcessDefinition> list = null;
        try {
            list = repositoryService.createProcessDefinitionQuery()
                    .list();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        for (ProcessDefinition p : list) {
            log.info("==================={},{}==================",
                    p.getDeploymentId(),
                    p.getKey());
        }

    }

    /**
     * 业务处理类
     */
    @Autowired
    private IActivitiTaskService taskService ;
    @Autowired
    private RuntimeService runtimeService;
    //===================15005,workflow_1a84b95==================
    //===================15001,workflow_1a84b95==================
    @Test
    public void startProcess(){
        ProcessInstance instance = null ;
        try {
//            Map<String,Object> params = new HashMap<String,Object>();
//            params.put("assigne","root");
//            Map<String,Object> form = new HashMap<>();
//            form.put("type","年假");
//            form.put("days",10);
//            form.put("reason","想请假了");
//            params.put("form",form);
//            Map<String,Object> process = new HashMap<String,Object>();
////            process.put("ID_","15005");
//            process.put("KEY_","workflow_1a84b95");
//            params.put("process",process);

            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("employeeName", "root");
            variables.put("numberOfDays", new Integer(4));
            variables.put("vacationMotivation", "I'm really tired!");

            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("workflow_1a84b95", variables);
            log.info("=========={}==========",processInstance.getProcessInstanceId());
            log.info("=========={}==========",processInstance.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }

    }
    @Autowired
    private TaskService ts;
    //20001
    @Test
    public void addClaim(){
        String id="20001";
        Task task = ts.createTaskQuery().processInstanceId(id).singleResult();
        log.info("=========={}==========",task.getId());
        ts.claim(task.getId(),"root");
    }

    @Test
    public void getTaskList(){
        TaskQuery taskQuery = ts.createTaskQuery();
        taskQuery.taskAssignee("root");
        List<Task> list = taskQuery.list();
        list.stream().forEach(l->{
            log.info("=============={}============",l.getProcessInstanceId());
            log.info("=============={}============",l.getName());
            log.info("=============={}============",l.getId());
            log.info("=============={}============",l.getFormKey());
        });
    }

    @Test
    public void approveFlow(){
        TaskQuery taskQuery = ts.createTaskQuery();
        taskQuery.taskAssignee("root");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("approve",true);
        List<Task> list = taskQuery.list();
        list.stream().forEach(l->{
            String instanceId = l.getProcessInstanceId();
            ts.complete(l.getId(),params);
            Task task = ts.createTaskQuery().processInstanceId(instanceId).singleResult();
            log.info("=========={}==========");

        });
    }

    @Autowired
    private HistoryService historyService;

    @Test
    public void getHistory(){
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();
        list.stream().forEach(l->{
            log.info("=============={}============",l.getProcessInstanceId());
            log.info("=============={}============",l.getName());
            log.info("=============={}============",l.getId());
            log.info("=============={}============",l.getFormKey());
            log.info("=============={}============",l.getTaskDefinitionKey());
            log.info("=============={}============",l.getProcessVariables());
        });
        log.info("------------------------------------------------------------------------------------");
        List<HistoricProcessInstance> instances = historyService.createHistoricProcessInstanceQuery().list();
        instances.stream().forEach(instance->{
            log.info("=============={}============",instance.getBusinessKey());
            log.info("=============={}============",instance.getProcessDefinitionName());
            log.info("============={}=============",instance.getStartUserId());
        });
        log.info("------------------------------------------------------------------------------------");
        List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery().list();
        tasks.stream().forEach(task->{
            log.info("=============={}============",task.getTaskDefinitionKey());
            log.info("=============={}============",task.getAssignee());
            log.info("==========={}================",task.getProcessDefinitionId());
        });

    }

    @Test
    public void getSteps(){

        Process mainProcess = repositoryService.getBpmnModel("workflow_1a84b95:2:15008").getMainProcess();
        Collection<FlowElement> flowElements = mainProcess.getFlowElements();
        Iterator<FlowElement> iterator = flowElements.iterator();
        for(;iterator.hasNext();){
            FlowElement next = iterator.next();
            log.info("=============={}============",next.getName());
            log.info("=============={}============",next.getDocumentation());
        }


    }


    @Test
    public void getProcessDefintionId(){

        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        list.stream().forEach(l->{
            log.info("=============={}============",l.getId());
        });


    }




}
