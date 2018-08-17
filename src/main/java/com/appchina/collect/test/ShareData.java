package com.appchina.collect.test;

public class ShareData {

    public static int count=0;

    public static void main(String[] args) {
        final ShareData shareData = new ShareData();

        for (int i = 0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //进入的时候暂停10毫秒，增加并发问题出现的几率
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j=0;j<100;j++){
                        shareData.addCount();
                    }
                    System.out.print(count+"    ");
                }
            }).start();
        }

        try {
            //暂停主线程3秒，以保证上面的程序执行完成
            Thread.sleep(3000);
        }catch (Exception e){

        }


        System.out.println("count= "+count);
    }

    public synchronized void addCount(){
        count++;
    }

}
