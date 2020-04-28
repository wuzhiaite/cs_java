package com.wuzhiaite.javaweb.module.file.file.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.wuzhiaite.javaweb.module.file.file.service.IFileUploadInfoService;
import com.wuzhiaite.javaweb.module.file.file.entity.FileUploadInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
* <p>
* 
* </p>
* @author lpf
* @since 2020-04-28
*/
@RestController
@RequestMapping("/api/file/file-upload-info")
@Slf4j
public class FileUploadInfoController {

    /**
    * 业务处理类
    */
    @Autowired
    private IFileUploadInfoService service;
    /**
     * 默认文件上传路径
     */
    @Value("uploadFilePath")
    private String filePath ;

    /**
     * 文件上传
     * @param request
     * @return
     */
    @PostMapping("/multiUpload")
    @ResponseBody
    public ResultObj multiUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return ResultObj.failObj("上传第" + (i++) + "个文件失败");
            }
            String fileName = file.getOriginalFilename();
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                FileUploadInfo info  = new FileUploadInfo();
                info.setFileName(fileName);
                info.setFilePath(dest.getPath());
                info.setFileSuffix(fileName.substring(fileName.indexOf(".")+1));
                info.setFileSize(String.valueOf(file.getSize()));
                service.save(info);
                log.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                log.error(e.toString(), e);
                return ResultObj.failObj("上传第" + (i++) + "个文件失败");
            }
        }
        return ResultObj.successObj("上传成功");
    }


    /**
    * 查找列表数据
    * @param page entity
    * @return
    */
    @PostMapping("/getPageList")
    public ResultObj getPageList(Page page, FileUploadInfo entity){
        Page<FileUploadInfo> pageList = null;
        try {
            pageList = service.page(page,new QueryWrapper<FileUploadInfo>(entity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(pageList);
    }

    /**
    * 查找列表
    * @param entity
    * @return
    */
    @PostMapping("/getList")
    public ResultObj getList(@RequestBody(required = false) FileUploadInfo entity){
        List<FileUploadInfo> list = null;
        try {
            list = service.list(new QueryWrapper<FileUploadInfo>(entity));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(list);
    }



    /**
    * 通过ID获取
    * @param id
    * @return
    */
    @PostMapping("/getPageById/{id}")
    public ResultObj getPageById(@PathVariable String id){
        FileUploadInfo result = null;
        try {
            result = service.getById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(result);
    }

    /**
    * 保存或修改数据
    * @param entity
    * @return
    */
    @PostMapping("/addOrUpdatePage")
    public ResultObj addOrUpdatePage( FileUploadInfo entity){
        boolean flag = false;
        try {
            flag = service.saveOrUpdate(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(flag);
    }

    /**
    * 通过ID移除
    * @param id
    * @return
    */
    @PostMapping("/removeById/{id}")
    public ResultObj removeById(@PathVariable String id){
        boolean flag = false ;
        try {
            flag = service.removeById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(flag);
    }

 }
