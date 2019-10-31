package com.wuzhiaite.javaweb.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="baseconfig")
@Configuration
@Data
public class BaseProperties {

    private String  sqlScriptPath;
    private String  baseFilepath;





}
