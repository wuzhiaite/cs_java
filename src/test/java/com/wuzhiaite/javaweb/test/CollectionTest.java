package com.wuzhiaite.javaweb.test;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionTest {


    @Test
    public void stringTest(){


        String str = new String(",123456");
        str = str.substring(1);
        System.out.println(str);




    }

    @Test
    public void collectionTest(){
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        String collect = list.stream().collect(Collectors.joining(","));

        System.out.println(collect);

    }


    @Test
    public void listCollect(){
        String[] str = {"one","two","three","four","five"};
        String valueStr = str.toString().replace(",", "','");
        StringBuilder builder = new StringBuilder();
        for(String s : str){
            builder.append(s).append(",");
        }
        System.out.println(builder.substring(0,builder.length()-2));
        System.out.println(valueStr);

    }
















}
