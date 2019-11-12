package com.wuzhiaite.javaweb.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRandomTest {

    private static Date randomDate(String beginDate, String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }





    public static void main(String[] args) {
        for(int i = 0; i < 100;){
//            Date date = randomDate("1990-01", "2019-12");
//            System.out.println(new SimpleDateFormat("yyyy-MM").format(date));
            long random = random(0, 10);
            System.out.println(random);

        }

    }





}
