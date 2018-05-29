package com.appchina.collect.test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestThread {

    public static void startTestCountDownLatch(){
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            final int finalI = i+1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread "+finalI+" start");
                    Random random = new Random();

                    try {
                        Thread.sleep(random.nextInt(10000)+1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("thread "+finalI+" finish");
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(threadNum+" thread finish");

    }


    public static void main(String[] args) {
        startTestCountDownLatch();
    }
}
