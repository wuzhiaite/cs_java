package com.wuzhiaite.javaweb.test;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionTest {







    @Test
    public void listTest(){
        List<Book> bookList = new ArrayList<Book>();
        Book b1 = new Book("红楼梦", "曹雪芹");
        Book b2 = new Book("西游记", "吴承恩");
        Book b3 = new Book("啦啦啦", "嘻嘻嘻");
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        System.out.println("before:"+bookList);
        bookList.forEach(book -> {
            String name = book.getName();
            if("啦啦啦".equals(name)){
                book.setAuthor("拉你个大头鬼");
            }
        });
        System.out.println("after:"+bookList);
    }





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
        int i = 1;
        list.forEach(l -> {
            l = "666";
        });

        System.out.println(list.toString());
//        String collect = list.stream().collect(Collectors.joining(","));
//        System.out.println(collect);

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
