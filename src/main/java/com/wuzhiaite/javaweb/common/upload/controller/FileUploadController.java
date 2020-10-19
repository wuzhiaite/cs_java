package com.wuzhiaite.javaweb.common.upload.controller;

import cn.hutool.core.util.RandomUtil;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.utils.QiniuUpload;
import com.wuzhiaite.javaweb.base.properties.VariableName;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description 上传文件到七牛
 * @author lpf
 */
@RestController
@RequestMapping("/upload/")
public class FileUploadController {

    /**
     * 七牛上传文件的token
     * @return
     */
    @GetMapping("/qiniu/token")
    public ResultObj getToken(){
        long expireSeconds = 600;   //过期时间
        StringMap putPolicy = new StringMap();
        Auth auth = Auth.create(VariableName.accessKey, VariableName.secretKey);
        String upToken = auth.uploadToken(VariableName.bucket,null, expireSeconds,putPolicy);
        return ResultObj.successObj(upToken);
    }

    /**
     * 七牛上传图片
     * @return
     */
    @PostMapping("/imgupload")
    public ResultObj imgUpload(@RequestParam("image")  MultipartFile image) throws Exception {
        String pictureUrl = QiniuUpload.updateFile(image, RandomUtil.randomString(10));
        return ResultObj.successObj(pictureUrl);
    }

    /**
     * 七牛上传图片
     * @return
     */
    @PostMapping("/deleteUploadFile/{fileName}")
    public ResultObj deleteFile(@PathVariable("fileName") String fileName) throws Exception {
        int count = QiniuUpload.deleteFile(fileName);
        return ResultObj.successObj(count);
    }




}
