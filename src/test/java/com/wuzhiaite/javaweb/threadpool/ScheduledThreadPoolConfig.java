package com.wuzhiaite.javaweb.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.*;

@Component
@Slf4j
public class ScheduledThreadPoolConfig {

    public static ConcurrentHashMap<String,ScheduledFuture>  map
                = new ConcurrentHashMap<>();
    private static ThreadPoolTaskScheduler scheduler;
    private static ScheduledThreadPoolExecutor executor;
    private ScheduledFuture future;
    private String cron ;

    static {
        scheduler = new ThreadPoolTaskScheduler() ;
        scheduler.setPoolSize(100);
        scheduler.setRemoveOnCancelPolicy(true);
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        scheduler.initialize();
    }

    public void startCorn(){
        cron = "0/1 * * * * ?";
        String name = Thread.currentThread().getName();
        future = scheduler.schedule(new MyTask(name), new CronTrigger(cron));
        map.put(name,future);
    }

    public void stop(){
        if(future != null){
            future.cancel(true);
        }
    }

    class MyTask implements  Runnable{
        private String name;
        MyTask(String name){
            this.name = name;
        }
        @Override
        public void run() {
            log.info("name:"+name);
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        ScheduledThreadPoolConfig scheduled = new ScheduledThreadPoolConfig();
//        scheduled.startCorn();

        executor = new ScheduledThreadPoolExecutor(100);
//        ScheduledFuture<?> test = executor.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("fixedRate");
//            }
//        }, 0, 10000, TimeUnit.MILLISECONDS);//initialDelay 标识第一次执行推迟时间
        // period表示延迟执行的时间
        //取消
//        test.cancel(true);

        String className = "com.wuzhiaite.javaweb.threadpool.TestTask";
        Class<?> clazz = Class.forName(className);
        Runnable obj = (Runnable) clazz.newInstance();
        ScheduledFuture<?> fixedDelay =
                executor.scheduleWithFixedDelay( obj ,
                        1000, 100, TimeUnit.MILLISECONDS);



    }








}
