package com.appchina.collect.test;

import com.appchina.collect.utils.UserInfoContext;

public class ThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    UserInfoContext.setGUID("liujianmeng");
                    UserInfoContext.setIp("localhost");
                    printSomething();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                    UserInfoContext.setGUID("teenage dream");
                    UserInfoContext.setIp("remote host");
                    printSomething();
            }
        });

        t1.start();
        t1.join();
        t2.start();
    }


    private static void printSomething(){
        System.out.println("current Thread name ="+Thread.currentThread().getName()+",guid = "+UserInfoContext.getGUID());
        System.out.println("current Thread name ="+Thread.currentThread().getName()+",ip = "+UserInfoContext.getIp());
    }

}
