package com.wuzhiaite.javaweb.base.securingweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Slf4j
//@Component
public class MyFilteSecurityInterceptor extends FilterSecurityInterceptor {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("MyFilteSecurityInterceptor");
        super.doFilter(request, response, chain);
    }

}
