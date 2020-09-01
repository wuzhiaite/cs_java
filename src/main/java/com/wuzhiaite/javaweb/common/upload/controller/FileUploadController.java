package com.wuzhiaite.javaweb.common.upload.controller;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.qiniu.VariableName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload/")
public class FileUploadController {

    /**
     * 七牛上传文件的token
     * @return
     */
    @GetMapping("/qiniu/token")
    public ResultObj getToken(){
        String upToken = "";
        try {
            long expireSeconds = 600;   //过期时间
            StringMap putPolicy = new StringMap();
            Auth auth = Auth.create(VariableName.accessKey, VariableName.secretKey);
            upToken = auth.uploadToken(VariableName.bucket,null, expireSeconds,putPolicy);
        } catch (Exception e) {
            ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(upToken);
    }








}
