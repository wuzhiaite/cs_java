package com.wuzhiaite.javaweb.spring.serveice;

import org.springframework.stereotype.Service;

@Service
public class AopServiceTest {
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

}
