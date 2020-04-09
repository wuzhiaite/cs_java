package com.wuzhiaite.javaweb.threadpool;

import java.util.concurrent.RecursiveTask;

public class RecursiveTest extends RecursiveTask<Integer> {

    private Integer start;
    private Integer end;
    private static final Integer INTERVAL_LEVEL = 2;

    public RecursiveTest(Integer start,Integer end){
        this.start = start ;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0 ;
        if(this.start - this.end == INTERVAL_LEVEL){
            for(int i = start ; i <= this.end ; i ++){
                sum += i;
            }
            return sum ;
        }else{
            int middle = (this.end - this.start)/2;
            RecursiveTest r1 = new RecursiveTest(this.start, middle);
            RecursiveTest r2 = new RecursiveTest(middle, this.end);
            r1.fork();
            r2.fork();
            Integer result1 = r1.join();
            Integer result2 = r2.join();
            sum = result1 + result2;
        }
        return sum;
    }



}
