package com.appchina.collect.test;


import com.appchina.check.ApkChecker;
import com.appchina.check.ApkInfo;
import com.appchina.check.Result;
import com.appchina.check.ResultItem;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import static com.appchina.check.UtilsKt.getWorkspacePath;

public class Test {


//    public static void main(String[] args) {
//        System.out.println(1<<8);
//    }

    /**
     * 输出一个int的二进制数
     * @param num
     */
    private static void printInfo(int num){
        int i = 1<<8;
        System.out.println(i);

    }


    private static final int getUTF8Length(byte[] array, int offset) {
        int len;
        int value = array[offset/4];
        if ((offset%4)/2!=0) {
            value = (value >> 16);
        }
        int hVal = value&0xFF;
        int lVal = value&0xFF00;
        if((hVal&0x80) != 0) {
            len = ((hVal&0x7F)<<8) | lVal;
        } else {
            len = hVal;
        }
        return len;
    }

    public static void main(String[] args) {

        ApkInfo apkInfo = new ApkInfo(new File("~/Downloads/Triporg_7.apk"));



    }



}
