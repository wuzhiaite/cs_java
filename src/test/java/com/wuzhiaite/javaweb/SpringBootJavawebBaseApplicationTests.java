package com.wuzhiaite.javaweb;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.config.IDbQuery;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.wuzhiaite.javaweb.base.utils.CodeGeneratorUtil;
import com.wuzhiaite.javaweb.common.authority.entity.Menus;
import com.wuzhiaite.javaweb.common.authority.service.IMenusService;
import com.wuzhiaite.javaweb.spring.serveice.AopServiceTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootJavawebBaseApplicationTests {

    @Autowired
    private AopServiceTest service;

    @Test
    public void aopTest1(){
        service.testMethod();
        new AopServiceTest.Inner().innerMethod();//静态内部类并不会生成代理类
    }

   @Test
   public void databaseTest(){
//       service.baseTest01();
       service.bastTest02();
//       service.baseTest03();
   }

   @Test
   public void generatorTest(){
       Map<String,Object> params = new HashMap<String,Object>();
       params.put("packageName","file");
       params.put("author","lpf");
       params.put("module","file");
       params.put("tablenames","file_upload_info");
       CodeGeneratorUtil.generatorCode(params);
   }

    private IDbQuery dbQuery;
   @Test
    public void getTableList(){
       dbQuery= new MySqlQuery();
       String tablesSql = dbQuery.tablesSql();
   }


    @Autowired
    private IMenusService menusService;
    @Test
    public void serviceTest(){
        try {
            Page<Menus> page = new Page<Menus>();
            page.setSize(10);
            page.setCurrent(1);
            Menus menus = new Menus();
            Page<Menus> pageList = menusService.page(page,new QueryWrapper<Menus>(menus));
            System.out.println(pageList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMenuList(){
        menusService.list(null);
    }







}
