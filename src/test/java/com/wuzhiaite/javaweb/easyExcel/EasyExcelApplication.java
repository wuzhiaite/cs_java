package com.wuzhiaite.javaweb.easyExcel;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuzhiaite.javaweb.common.authority.entity.Menus;
import com.wuzhiaite.javaweb.common.authority.service.IMenusService;
import com.wuzhiaite.javaweb.base.easyexcel.read.UploadEntityListener;
import com.wuzhiaite.javaweb.base.easyexcel.write.DownloadEntity;
import com.wuzhiaite.javaweb.easyExcel.write.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void downloadTest(){
        Class clazz = Book.class ;
        String fileName = "E:\\Desktop\\write.xlsx";
        List<Book> list = new ArrayList<Book>();
        Book b1 = Book.builder().author("曹雪芹").country("china").name("红楼梦").publishDate(new Date()).build();
        Book b2 = Book.builder().author("张爱玲").country("china").name("倾城之恋").publishDate(new Date()).build();
        Book b3 = Book.builder().author("余华").country("china").name("活着").publishDate(new Date()).build();
        Book b4 = Book.builder().author("三毛").country("china").name("撒哈拉沙漠").publishDate(new Date()).build();
        Book b5 = Book.builder().author("沈从文").country("china").name("边城").publishDate(new Date()).build();
        Book b6 = Book.builder().author("老舍").country("china").name("四世同堂").publishDate(new Date()).build();
        Book b7 = Book.builder().author("李碧华").country("china").name("霸王别姬").publishDate(new Date()).build();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        list.add(b5);
        list.add(b6);
        list.add(b7);
        DownloadEntity<Book> de =  DownloadEntity.builder().clazz(clazz)
                .fileName(fileName)
                .list(list)
                .sheetName("近代作品")
                .build();

            EasyExcel.write(de.getFileName(),de.getClazz()).sheet(de.getSheetName()).doWrite(list);
    }




    @Test
    public void getTest(){
        BaseMapper<Menus> baseMapper = service.getBaseMapper();
    }


}
