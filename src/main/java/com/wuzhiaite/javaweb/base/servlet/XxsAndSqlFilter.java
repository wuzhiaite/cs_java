package com.wuzhiaite.javaweb.base.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * sql注入过滤和防止跨站脚本攻击
 */
public class XxsAndSqlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper( (HttpServletRequest) servletRequest);

        filterChain.doFilter(xssRequest,servletResponse);
    }













    @Override
    public void destroy() {

    }

}
