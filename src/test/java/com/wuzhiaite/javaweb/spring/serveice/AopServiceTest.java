package com.wuzhiaite.javaweb.spring.serveice;



import com.wuzhiaite.javaweb.base.multidatabase.DynamciDb;
import com.wuzhiaite.javaweb.common.common.ComCrudServiceImpl;
import com.wuzhiaite.javaweb.spring.mapper.AopMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@DynamciDb
@Transactional(readOnly = false)
public class AopServiceTest extends ComCrudServiceImpl<AopMapper, Map<String,Object>> {

    @Autowired
    private AopMapper aopMapper;

    public void testMethod(){
        new Inner().innerMethod();
        System.out.println("testMethod");
        this.testMethod2();
    }
    public void testMethod2(){
        System.out.println("testMethod22222222");
    }

    public static class Inner{
        public void innerMethod(){
            System.out.println("innerMethod");
        }
    }

//    @DataSource(name="master")
    @DynamciDb(name="slave")
    public void baseTest01(){
        String sql = "SELECT * FROM page_list_config_form ";
        List<Map<String, Object>> list = aopMapper.getBySQL(sql);
        log.info("master:"+String.valueOf(list));
    }

    @DynamciDb(name="slave")
    public void bastTest02(){
//        DynamicDataSourceContextHolder.setDataSourceKey("slave");
        String sql = "SELECT * FROM boss_role";
        List<Map<String, Object>> list = aopMapper.getBySQL(sql);
        log.info("slave:"+String.valueOf(list));
    }

    public void baseTest03(){
        String sql = "SELECT * FROM page_list_config_form";
        List<Map<String, Object>> list = aopMapper.getBySQL(sql);
        log.info("normal:"+String.valueOf(list));
    }

}
