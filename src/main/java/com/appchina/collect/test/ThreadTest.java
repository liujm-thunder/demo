package com.appchina.collect.test;

public class ThreadTest extends Thread {

    public ThreadTest() {
    }

    @Override
    public void run() {
        TestLock testLock = new TestLock();
        testLock.printString();
    }
}