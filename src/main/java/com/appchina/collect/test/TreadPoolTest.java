package com.appchina.collect.test;

import java.util.concurrent.*;

public class TreadPoolTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //生产普通的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(6);
        //第一种执行线程的方式
        threadPool.submit(new Thread1());
        //第二种执行线程的方式，有返回值
        Future<String> result1 = threadPool.submit(new Thread1(),"返回值1");
        System.out.println(result1.get());
        //第三种执行线程的方式，传入Callable对象，有返回值
        Future<String> result2 = threadPool.submit(new Thread2());
        System.out.println(result2.get());


        //关闭线程池，不再接受新的任务，会将之前所有提交的任务执行完成，所有的任务完成后，所有的线程死亡
        threadPool.shutdown();
        //调用shutdownNow可以立马体制所有的线程
//        threadPool.shutdownNow();
        System.out.println("------------");

        //生产可以延迟的执行的线程池
        ScheduledExecutorService threadPool2 = Executors.newScheduledThreadPool(6);
        //延迟1秒执行
        threadPool2.schedule(new Thread1(),1,TimeUnit.SECONDS);
        //延迟2秒后执行，每一秒循环执行一次
        //是以上一个任务开始时间计时，period时间过去后，检测上一个任务是否执行完毕，如果上一个任务执行完毕，
        //则当前任务立即执行，如果上一个任务没有执行，则需要等上一个任务执行完毕后立即执行。
        threadPool2.scheduleWithFixedDelay(new Thread1(),2,1,TimeUnit.SECONDS);

        Thread.sleep(3000);
        threadPool2.shutdown();


    }


    static class Thread1 extends Thread{
        @Override
        public void run() {
            System.out.println("线程 "+Thread.currentThread().getName());
        }
    }

    static class Thread2 implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("线程 "+Thread.currentThread().getName());
            return "返回值2";
        }
    }

}
