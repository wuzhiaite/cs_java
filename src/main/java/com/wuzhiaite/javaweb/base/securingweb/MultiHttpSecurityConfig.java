package com.wuzhiaite.javaweb.base.securingweb;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 */
@EnableWebSecurity
@Configuration
public class MultiHttpSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private SpringDataUserDetailsService userDetailsService ;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter ;

    @Autowired
    private SelfAuthenticationProvider selfAuthenticationProvider;


    /**身份验证*/
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new SpringDataUserDetailsService();
    }
    /**自定义密码如何编码*/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
        @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .httpBasic()
                .authenticationEntryPoint(new AuthenticationEntryPoint(){
                        @Override
                        public void commence(HttpServletRequest request, HttpServletResponse response,
                                             AuthenticationException authException) throws IOException, ServletException {
                            response.setCharacterEncoding("UTF-8");
                            response.setStatus(403);
                            response.setContentType("application/json; charset=utf-8");
                            response.getWriter().write(JSON.toJSONString(ResultObj.failObj("无访问权限，请先登录")));
                        }
                    })
                .and()
                .authorizeRequests()
                .antMatchers("/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .antMatchers("/user/login","/user/logout").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
                .and()
                .headers()
                .xssProtection()
                .block(false);
        // 记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService).tokenValiditySeconds(3000);

        //使用 JWT，关闭token
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // JWT Filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 无权访问 JSON 格式的数据
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler(){
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response,
                               AccessDeniedException accessDeniedException) throws IOException, ServletException {
                ResultObj resultObj = ResultObj.successObj(null, "无效访问");
                response.setStatus(400);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSON.toJSONString(resultObj));
                printWriter.flush();
            }
        });
        // 开启允许iframe 嵌套
        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        ProviderManager providerManager
                = new ProviderManager(Collections.singletonList(selfAuthenticationProvider));
        return providerManager;
//        return super.authenticationManagerBean();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }





}
