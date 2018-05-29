package com.appchina.collect.design;

public class TestDecorateDesign {

    /**
     * 基于多态
     * @param args
     */
    public static void main(String[] args) {
        ShouZhuaBing shouZhuaBing = new ShouZhuaBing();
        //手抓饼基础价
        System.out.println(String.format("%s ¥ %s",shouZhuaBing.getDesc(),shouZhuaBing.getPrice()));
        Bing jianBing = new JianBing();
        System.out.println(String.format("%s ¥ %s",jianBing.getDesc(),jianBing.getPrice()));
        jianBing = new FireEgg(jianBing);
        jianBing = new Ham(jianBing);
        System.out.println(String.format("%s ¥ %s",jianBing.getDesc(),jianBing.getPrice()));

    }


}
