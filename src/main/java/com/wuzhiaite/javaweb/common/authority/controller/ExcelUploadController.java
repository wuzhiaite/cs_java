package com.wuzhiaite.javaweb.common.authority.controller;


import com.wuzhiaite.javaweb.base.entity.ResultObj;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description 用户信息上传
 * @author lpf
 */
@RestController("/excel/upload/")
public class ExcelUploadController {


    /**
     * 上传用户信息
     * @return
     */
    @PostMapping("uploadInfo")
    public ResultObj uploadInfo(@RequestParam("file") MultipartFile file){


        return null;
    }






}
