package com.appchina.collect.design.daili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client2 {
    public static void main(String[] args) {
        Person zhangsan = new Student("张三");
        InvocationHandler studentInvocation = new StudentInvocationHandler<Person>(zhangsan);
        Person proxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),new Class[]{Person.class},studentInvocation);
        proxy.giveMoney();

    }
}
