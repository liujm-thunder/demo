package com.appchina.collect.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/****
 * 调用本地command方法
 */
public class NativeCommand {

    public static void main(String[] args) {
//        String command = IMAGE_RESIZE + " -h " + height + " -w " + width + " " + fromPath + " " + targetPath;
        String command = "";
        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
            System.out.println("command : "+command);

            String line;
            StringBuffer  error = new StringBuffer();
            StringBuffer  info = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line=bufferedReader.readLine())!=null){
                System.out.println("error:"+line);
                error.append(line);
            }

            BufferedReader stdoutReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            while ((line = stdoutReader.readLine()) != null) {
                System.out.println("debug:"+line);
                info.append(line);
            }

            if (process.waitFor()!=0){
                throw new RuntimeException("error:"+error+",info:"+info);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
