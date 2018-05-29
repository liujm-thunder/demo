package com.appchina.collect.design;

public abstract class Bing {
    public String desc = "我不是一个具体的饼";

    public String getDesc() {
        return desc;
    }

    public abstract Double getPrice();
}
