package com.appchina.collect.design.daili;

public class Student implements Person {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void giveMoney() {
        System.out.println(name+"最近进步非常明显");
        System.out.println(name+":上交班费50元");
    }
}
