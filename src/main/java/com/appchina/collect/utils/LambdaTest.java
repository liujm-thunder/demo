package com.appchina.collect.utils;

import java.util.Arrays;
import java.util.List;

public class LambdaTest {

//    public static void main(String[] args) {
//        Thread t = new Thread(() ->  {
//            System.out.println("线程：" + Thread.currentThread().getName());
//        });
//        t.start();
//    }

    public static void printStr(String str){
        System.out.println(str);
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.forEach(str -> {
            LambdaTest.printStr(str);
        });

        list.forEach(LambdaTest::printStr);

    }


}
