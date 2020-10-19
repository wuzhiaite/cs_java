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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description 流程发布处理类
 * @author lpf
 * @since 20200710
 */
@RestController
@RequestMapping("/activiti/deployment")
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
        String modelXml = MapUtil.getString(params, "modelXml");
        String workflowName = MapUtil.getString(params, "workflowName");
        String modelImage = MapUtil.getString(params, "modelImage");
        String modelKey = MapUtil.getString(params, "modelKey");
        workflowName = StringUtil.isEmpty(workflowName)
                ? new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".bpmn"
                : workflowName;
        Deployment deploy = repositoryService.createDeployment()
                .addString(workflowName, modelXml)
                .deploy();
        if(StringUtils.isEmpty(deploy)){
            throw new RuntimeException("创建失败");
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
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                     .listPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
        return ResultObj.successObj(list);
    }

    /**
     *  删除部署的流程
     * @param deploymentId
     * @return
     */
    @PostMapping("/removeprocessbyid/{deploymentId}")
    public ResultObj removeProcessById(@PathVariable("deploymentId") String deploymentId){
        repositoryService.deleteDeployment(deploymentId);
        return ResultObj.successObj("删除成功");
    }
    @Autowired
    private ManagementService managementService;
    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/getDeployWorkflow/{key}/{id}")
    public ResultObj getDeployWorkflow(@PathVariable("key") String key ,
                                       @PathVariable("id") String id){
        StringBuffer res = new StringBuffer();
        try {
            Deployment deployment = repositoryService.createDeploymentQuery()
                    .processDefinitionKey(key)
                    .deploymentId(id)
                    .singleResult();
            String deploymentId = deployment.getId();
            List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
            String sourceName = names.stream()
                    .filter(name -> name.indexOf(".bpmn") != -1)
                    .findFirst().get();
            InputStream in = repositoryService.getResourceAsStream(deploymentId, sourceName);
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            try {
                String line;
                line = read.readLine();
                while (line != null) {
                    res.append(line);
                    line = read.readLine();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (null != read) {
                        read.close();
                        read = null;
                    }
                    if (null != in) {
                        in.close();
                        in = null;
                    }
                } catch (IOException e) {
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(res.toString());
    }








}
