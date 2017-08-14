package com.appchina.collect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by liujianmeng on 2017/8/14.
 */
@SpringBootApplication
@MapperScan("com.appchina.collect.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
