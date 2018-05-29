package com.appchina.collect.utils;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class MysqlDemo {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String url = "jdbc:mysql://172.16.30.25:3306/mproduction?characterEncoding=UTF-8";
        String driver = "com.mysql.jdbc.Driver";
        String user = "mproduction";
        String password = "16021incloud";
        PrintWriter pw = null;
        FileWriter fw = null;
        File file = new File("/Users/liujianmeng/Desktop/333");
        FileInputStream is = null;
        BufferedReader in = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-01-01 00:00:00");
        try {
            Class.forName(driver);
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) {
                System.out.println("Succeed connecting to the Database!");
                is = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(is);
                in = new BufferedReader(isr);
                String line = null;
                while ((line = in.readLine()) != null)
                {
//                    String sql = "select name,lastmodifiedtime from new_application where application_id = '"+line+"' and status=0";
                    String sql = "select defaultappid,downloadcount,lastmodifiedtime from block where block_id = '"+line+"' and defaultappid!=-1 and lastmodifiedtime>'2017-01-01 00:00:00'";
//                    String sql = "select block_id from packageblock_relation where packagename = '"+line+"'";
                    Statement ptmt = conn.createStatement();
                    ResultSet resultSet = ptmt.executeQuery(sql);
                    while (resultSet.next()) {
//                        String defaultappid = resultSet.getString("defaultappid");
//                        System.out.println(defaultappid);
//                        String block_id = resultSet.getString("block_id");
//                        String downloadcount = resultSet.getString("downloadcount");
//                        String name = resultSet.getString("name");
                        Date lastmodifiedtime = resultSet.getTimestamp("lastmodifiedtime");
                        String str = sdf.format(lastmodifiedtime);
//                        Date lastmodifiedtime = resultSet.getTimestamp("lastmodifiedtime");
//                        String str = sdf.format(new Date(Long.valueOf(time)));
//                        if (lastmodifiedtime.after(date)){
                            System.out.println(str);
//                        }
                    }
                    resultSet.close();
                }
            }
            conn.close();
            in.close();
            is.close();

        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    // 关闭数据库连接
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pw != null) {
                // 关闭IO流
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}