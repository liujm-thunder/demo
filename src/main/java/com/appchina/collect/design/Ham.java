package com.appchina.collect.design;

public class Ham extends Decorate{

    private Bing bing;

    public Ham(Bing bing) {
        this.bing = bing;
    }

    @Override
    public String getDesc() {
        return bing.getDesc()+",加火腿";
    }

    @Override
    public Double getPrice() {
        return bing.getPrice()+3;
    }
}
