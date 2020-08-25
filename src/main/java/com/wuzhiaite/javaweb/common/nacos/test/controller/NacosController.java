package com.wuzhiaite.javaweb.common.nacos.test.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("config")
@Slf4j
public class NacosController {

    @NacosValue(value = "${uploadFilePath}", autoRefreshed = true)
    private Object useLocalCache;

    @GetMapping(value = "/get")
    @ResponseBody
    public Object get() {
        log.info(String.valueOf(useLocalCache));
        return useLocalCache;
    }

    @NacosInjected
    private NamingService namingService;

    @GetMapping(value = "/getService")
    @ResponseBody
    public List<Instance> getService(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }



}
