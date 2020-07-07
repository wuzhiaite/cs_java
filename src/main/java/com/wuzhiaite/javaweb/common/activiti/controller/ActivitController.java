package com.wuzhiaite.javaweb.common.activiti.controller;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController("/activiti")
@Slf4j
public class ActivitController {

    @Autowired
    RepositoryService rep;
    @Autowired
    RuntimeService runservice;
    @Autowired
    FormService formservice;
    @Autowired
    IdentityService identityservice;
    @Autowired
    TaskService taskservice;
    @Autowired
    HistoryService histiryservice;
    @Autowired
    private RepositoryService repositoryService;

    /**
     *  创建工作流
     * @param uploadfile
     * @return
     */
    @PostMapping(value = "/uploadworkflow/{deploymentId}")
    public ResultObj fileupload(@RequestParam MultipartFile uploadfile,
                                @PathVariable("deploymentId") String deploymentId) {
        try {
            repositoryService.deleteDeployment(deploymentId);
            MultipartFile file = uploadfile;
            String filename = file.getOriginalFilename();
            InputStream is = file.getInputStream();
            rep.createDeployment().addInputStream(filename, is).deploy();
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



}
