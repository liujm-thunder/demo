package com.appchina.collect.design.daili;

public class MonitorUtil {
    private static ThreadLocal<Long> t = new ThreadLocal<>();

    public static void start(){
        t.set(System.currentTimeMillis());
    }

    public static void finish(String method){
        long finishTime = System.currentTimeMillis();
        System.out.println(method+" 方法耗时:"+(finishTime-t.get())+" ms");
    }
}
