package com.appchina.collect.test.send;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

public class TestApi {


    public static void main(String[] args) {
        read3();
    }

    public static void read3(){
        LineIterator iterator = null;

        String filePath = "/Users/liujianmeng/Downloads/history.log";

        String url = "http://103.231.68.141:8080/manage/crawler/entrance/api";
        try {
            iterator = FileUtils.lineIterator(new File(filePath),"UTF-8");
            while (iterator.hasNext()){
                String line = iterator.nextLine();
                line = line.substring(line.indexOf("{"));
                try {
                    String result = HttpRequest.sendPost(url,"param="+line);
                    Thread.sleep(3000);
                    System.out.println(result+",remember="+line);
                }catch (Exception e){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (iterator!=null){
                LineIterator.closeQuietly(iterator);
            }
        }

    }


}
