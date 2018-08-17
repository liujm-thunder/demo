package com.appchina.collect.test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
               for (int i=0;i<1000;i++){
                   count.incrementAndGet();
               }
            }
        };


        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++){
                    count.incrementAndGet();
                }
            }
        };


        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.printf("最后统计结果count: "+count);
    }

}
