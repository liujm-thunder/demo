package com.appchina.collect.design;

public class FireEgg extends Decorate{
    private Bing bing;


    public FireEgg(Bing bing) {
        this.bing = bing;
    }

    @Override
    public String getDesc() {
        return bing.getDesc()+",加煎鸡蛋";
    }

    @Override
    public Double getPrice() {
        return bing.getPrice()+2;
    }
}
