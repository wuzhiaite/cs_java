package com.wuzhiaite.javaweb.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class ReferenceCountinGC {

    public Object instance = null;

    private static  final int _1MB = 1024 * 1024;

    private  byte[]  bigSize = new byte[2 * _1MB];


    public  static void testGC(){
        ReferenceCountinGC objA = new ReferenceCountinGC() ;
        ReferenceCountinGC objB = new ReferenceCountinGC() ;

        objA.instance = objB ;
        objB.instance = objA ;

         System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }







}
