package com.wuzhiaite.javaweb.threadpool;

import lombok.SneakyThrows;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicTest {

    private static CyclicBarrier c = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("================over=========");
        }
    });


    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @SneakyThrows
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"—————————————————— 1 ");
//                c.await(100,TimeUnit.MILLISECONDS);
                c.await();
                c.await();
            }
        };
        t1.start();


        new Thread(){
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                }
                System.out.println(Thread.currentThread().getName()+"—————————————————— 2 ");
            }
        }.start();


        try {
//            Thread.sleep(1000);
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"—————————————————— 3 ");

    }





}
