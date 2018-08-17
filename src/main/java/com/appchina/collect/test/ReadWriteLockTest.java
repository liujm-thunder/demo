package com.appchina.collect.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public void write(){
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getId()+"获取到了写锁，时间戳："+System.currentTimeMillis());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

    public void read(){
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getId()+"获取到了读锁，时间戳："+System.currentTimeMillis());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }


    public static void main(String[] args) {
        final ReadWriteLockTest test = new ReadWriteLockTest();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                test.read();
            }
        };
        for (int i=0;i<100;i++){
            Thread t1 = new Thread(runnable);
            Thread t2 = new Thread(runnable);
            t1.start();
            t2.start();
        }

    }

}
