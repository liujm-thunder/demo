package com.appchina.collect.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/***
 * java 高效读取大文件
 */
public class FileUtil {

    /***
     *  1、在内存中读取
     *
     *  读取文件行的标准方式是在内存中读取，Guava 和Apache Commons IO都提供了如下所示快速读取文件行的方法：
     *
     *  这种方法带来的问题是文件的所有行都被存放在内存中，当文件足够大时很快就会导致程序抛出OutOfMemoryError 异常。
     *
     * @param filePath
     */
    public void read1(String filePath) throws IOException {
        Files.readLines(new File(filePath), Charsets.UTF_8);
    }


    /**
     * 2、文件流
     *  使用java.util.Scanner类扫描文件的内容，一行一行连续地读取
     *
     *  这种方案将会遍历文件中的所有行——允许对每一行进行处理，而不保持对它的引用。总之没有把它们存放在内存中
     *
     * @param filePath
     */
    public void read2(String filePath) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;

        try {


        inputStream = new FileInputStream(filePath);

        sc = new Scanner(inputStream,"utf-8");

        while (sc.hasNextLine()){
            String line = sc.nextLine();

            //TODO do something

        }
        // note that Scanner suppresses exceptions
        if (sc.ioException()!=null){
            throw sc.ioException();
        }
        }finally {
            if (inputStream!=null){
                inputStream.close();
            }

            if (sc!=null){
                sc.close();
            }
        }
    }


    /**
     * Apache Commons IO流
     *  同样也可以使用Commons IO库实现，利用该库提供的自定义LineIterator:
     *  由于整个文件不是全部存放在内存中，这也就导致相当保守的内存消耗
     *
     * @param filePath
     */
    public void read3(String filePath){
        LineIterator iterator = null;
        try {
            iterator = FileUtils.lineIterator(new File(filePath),"UTF-8");
            while (iterator.hasNext()){
                String line = iterator.nextLine();
                //TODO do something

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
