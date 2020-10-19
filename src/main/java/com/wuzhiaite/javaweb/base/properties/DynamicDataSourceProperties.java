package com.wuzhiaite.javaweb.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix="spring")
@Configuration
@Data
public class DynamicDataSourceProperties {

    public static final String DEFAULT_DATASOURCE = "master";
    Map<String, Map<String,String>> dbsource = new HashMap<String, Map<String,String>>();



}
