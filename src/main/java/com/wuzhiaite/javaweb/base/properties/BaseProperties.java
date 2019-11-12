package com.wuzhiaite.javaweb.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties(prefix="baseconfig")
@Configuration
@Data
public class BaseProperties {

    private String  sqlScriptPath;//sql脚本路径
    private String  baseFilepath;//文件存储地址
    private String  initscriptname;//初始化sql脚本名称
    private List<String> filterUrl;//过滤地址
    private String  databaseName;//数据库名称



}
