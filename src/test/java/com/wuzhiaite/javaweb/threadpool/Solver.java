package com.wuzhiaite.javaweb.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

class Solver {
    int result = 0;
    final ConcurrentHashMap<String,Object> data = new ConcurrentHashMap<>();
    final CyclicBarrier barrier;

    class Worker implements Runnable {
        int myRow;
        boolean flag = false;
        Worker(int row) { myRow = row; }
        public void run() {
            while (!done()) {
                processRow(myRow);
                try {
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private void processRow(int myRow) {
            data.put(Thread.currentThread().getName(),myRow);
            flag = true;
        }
        private boolean done() {
            return flag;
        }
    }



    public Solver(int N) throws InterruptedException {
        Runnable barrierAction =
                new Runnable() { public void run() { mergeRows(); }};
        barrier = new CyclicBarrier(N, barrierAction);

        List<Thread> threads = new ArrayList<Thread>(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            threads.add(thread);
            thread.start();
        }

        // wait until done
//        for (Thread thread : threads)
//            thread.join();
    }

    private void mergeRows() {
        this.data.entrySet().stream().forEach(entry ->{
            this.result += (Integer)this.data.get(entry.getKey());
        });
        System.out.println("result:"+result);
    }

    public static void main(String[] args) throws InterruptedException {
        Solver solver = new Solver(5);

    }
    
}