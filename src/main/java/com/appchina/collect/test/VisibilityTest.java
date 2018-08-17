package com.appchina.collect.test;

public class VisibilityTest {

    private static boolean ready;

    private static int number;


    static class ReaderTHread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (!ready){
                System.out.println(ready);
            }

            System.out.println(number);

        }
    }


    static class WriterThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            number = 100;
            ready=true;
        }
    }


    public static void main(String[] args) {
        new WriterThread().start();
        new ReaderTHread().start();
    }

}
