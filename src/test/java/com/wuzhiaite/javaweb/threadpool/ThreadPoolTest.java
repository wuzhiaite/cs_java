package com.wuzhiaite.javaweb.threadpool;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.*;

public class ThreadPoolTest {

    ThreadPoolExecutor threadPool;

    @Before
   public void init (){
       threadPool = new ThreadPoolExecutor(10,
               100,
               100,
               TimeUnit.SECONDS,
               new ArrayBlockingQueue(100));

        ExecutorService executor = Executors.newCachedThreadPool();


    }

  @Test
  public void Test(){
      Future<?> submit = threadPool.submit(new TestTask());

      try {
          Object o = submit.get();

      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      }finally{
          threadPool.shutdown();
      }


  }









}






