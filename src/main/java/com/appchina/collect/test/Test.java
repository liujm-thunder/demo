package com.appchina.collect.test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
//        System.out.println(System.getProperty("java.library.path"));
//        File file1= new File("/Users/liujianmeng/Desktop/app_icon_adaptive.webp");
//        File file2= new File("/Users/liujianmeng/Desktop/app_icon_adaptive.png");
//        try {
//            BufferedImage im = ImageIO.read(file1);
//            ImageIO.write(im, "png", file2);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-15);
        Date before = calendar.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(before));
    }
}
