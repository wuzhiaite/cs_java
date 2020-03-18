package com.wuzhiaite.javaweb.module.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseConfigController {

    @GetMapping("indexPage")
    public String indexPage(){
        return "indexPage";
    }









}
