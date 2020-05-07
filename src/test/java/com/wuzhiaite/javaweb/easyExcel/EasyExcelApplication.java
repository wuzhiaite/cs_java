package com.wuzhiaite.javaweb.easyExcel;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuzhiaite.javaweb.common.authority.entity.Menus;
import com.wuzhiaite.javaweb.common.authority.service.IMenusService;
import com.wuzhiaite.javaweb.common.pagelistconfig.easyexcel.read.UploadEntityListener;
import com.wuzhiaite.javaweb.common.pagelistconfig.easyexcel.read.UploadMapLinstener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class EasyExcelApplication {
    
    @Autowired
    private IMenusService service ;

    @Test
    public void uploadTest(){
        String pathName = "E:\\Desktop\\demo.xlsx";
        EasyExcel.read(pathName,new UploadEntityListener(service)).sheet().doRead();
    }

    @Test
    public void getTest(){
        BaseMapper<Menus> baseMapper = service.getBaseMapper();
    }


}
