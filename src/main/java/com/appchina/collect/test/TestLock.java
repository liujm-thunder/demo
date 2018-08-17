package com.appchina.collect.test;


public class TestLock {


    public static Integer a = 0;

    public synchronized void printString(){
        for (int i=0;i<10000;i++){
            System.out.println(++a);
        }
    }

    public static void main(String[] args) {
        ThreadTest t1 = new ThreadTest();
        ThreadTest t2 = new ThreadTest();
        ThreadTest t3 = new ThreadTest();
        ThreadTest t4 = new ThreadTest();
        ThreadTest t5 = new ThreadTest();

        t2.start();
        t3.start();
        t1.start();
        t4.start();
        t5.start();
    }

}
