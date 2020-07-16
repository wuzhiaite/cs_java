package com.wuzhiaite.javaweb.threadpool;

import org.junit.Test;

import java.util.Arrays;

public class BaseTest {

    @Test
    public void test(){
        String[] str = {"a","b","c"};

        for(int i = 0 ; i < str.length ; i ++){
            for(int j = 0 ; j < str.length-1  ; j ++ ){
                String temp = "";
               if(i != j){
                  temp = str[j];
                  str[j] = str[j + 1];
                  str[j+1] = temp;
                   System.out.println(Arrays.toString(str));
                   str[j+1] = str[j];
                   str[j] = temp;
                   System.out.println("=================="+Arrays.toString(str)+"=====================");
               }
            }
        }




    }



}
