package com.appchina.collect.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BarWorker implements Runnable {

    private static AtomicBoolean exists = new AtomicBoolean(false);

    private String name;

    public BarWorker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if(exists.compareAndSet(false,true)){
            System.out.println(name+" enter");
            try {
                System.out.println(name + " working");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                //do nothing
            }
            System.out.println(name + " exit");
            exists.set(false);
        } else {
            System.out.println(name + " do nothing");
        }

    }

//    public static void main(String[] args) {
//        BarWorker barWorker1 = new BarWorker("bar1");
//        BarWorker barWorker2 = new BarWorker("bar2");
//
//        new Thread(barWorker1).start();
//        new Thread(barWorker2).start();
//    }

    public static void main(String[] args) {
        Set<String> idSet = new HashSet<>();
        idSet.add("359");
        idSet.add("349");
        idSet.add("233");
        idSet.add("3d");
        idSet.add("35f9");
        System.out.println("size: "+idSet.size());
        Iterator<String> iterator = idSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
