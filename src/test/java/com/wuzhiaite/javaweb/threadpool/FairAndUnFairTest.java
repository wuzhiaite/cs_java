package com.wuzhiaite.javaweb.threadpool;

import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnFairTest {

    private static ReentrantLock2 fair = new ReentrantLock2(true);
    private static ReentrantLock2 unfair = new ReentrantLock2(false);


    private static class Job extends Thread{
        private ReentrantLock2 lock;
        Job(ReentrantLock2 lock){
            this.lock = lock;
        }

        @SneakyThrows
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("==当前线程"+Thread.currentThread().getName());
                lock.getQueuedThreads().stream().forEach(thread -> {
                    System.out.println("======等待队列："+thread.getName());
                });
            } finally {
                lock.unlock();
            }

            lock.lock();
            try {
                System.out.println("**当前线程"+Thread.currentThread().getName());
                lock.getQueuedThreads().stream().forEach(thread -> {
                    System.out.println("*******等待队列："+thread.getName());
                });
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 增加队列
     * @param lock
     */
    public static void testLock(ReentrantLock2 lock){
        for (int i=0;i<5;i++){
            new Job(lock).start();
        }
    }

    /**
     * 重入锁的实现类
     */
    private  static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(Boolean fair){
            super(fair);
        }
        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<Thread>(super.
                    getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

    public static void main(String[] args) {
        testLock(unfair);
    }










}
