package com.wuzhiaite.javaweb.base.initializer;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;
import java.util.Arrays;

/**
 * 用于注册springsecurity的校验Filter
 */
@Configuration
public class SecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        super.beforeSpringSecurityFilterChain(servletContext);
        insertFilters(servletContext,new MultipartFilter());
    }

    /**
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean multipartFilter(){
        return new FilterRegistrationBean(new MultipartFilter());
    }



}
