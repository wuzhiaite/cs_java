package com.wuzhiaite.javaweb.common.activiti.controller;

import com.github.pagehelper.util.StringUtil;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description 流程发布处理类
 * @author lpf
 * @since 20200710
 */
@RestController
@RequestMapping("/api/activiti/deployment")
@Slf4j
public class ActivitDeploymentController {
    /**
     *
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     *  创建工作流
     * @param params
     * @return
     */
    @PostMapping(value="/deployWorkflow")
    public ResultObj deployWorkflow(@RequestBody Map<String,Object> params) {
        try {
            String modelXml = MapUtil.getString(params, "modelXml");
            String workflowName = MapUtil.getString(params, "workflowName");
            String modelImage = MapUtil.getString(params, "modelImage");
            String modelKey = MapUtil.getString(params, "modelKey");
            workflowName = StringUtil.isEmpty(workflowName)
                    ? new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".bpmn"
                    : workflowName;
            Deployment deploy = repositoryService.createDeployment()
                    .addString(workflowName, modelXml)
//                    .addString(workflowName,modelImage)
                    .deploy();
            if(StringUtils.isEmpty(deploy)){
                throw new RuntimeException("创建失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj("创建成功");
    }

    /**
     *  获取发布的流程列表
     * @param params
     * @return
     */
    @PostMapping("/getprocesslist")
    public ResultObj getProcessList(@RequestBody Map<String,Object> params){
        List<ProcessDefinition> list = null;
        try {
             list = repositoryService.createProcessDefinitionQuery()
                     .listPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(list);
    }

    /**
     *  删除部署的流程
     * @param deploymentId
     * @return
     */
    @PostMapping("/removeprocessbyid/{deploymentId}")
    public ResultObj removeProcessById(@PathVariable("deploymentId") String deploymentId){
        try {
            repositoryService.deleteDeployment(deploymentId);
        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj("删除成功");
    }
    @Autowired
    private ManagementService managementService;
    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/getDeployWorkflow/{id}")
    public ResultObj getDeployWorkflow(@PathVariable("id") String id){
        try {
            Deployment deployment = repositoryService.createDeploymentQuery()
                    .processDefinitionKey(id)
                    .singleResult();
            String deploymentId = deployment.getId();
//            byte[] modelEditorSource = repositoryService.getDeploymentResourceNames();

        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj();
    }








}
