package com.appchina.collect.utils;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.sql.*;
import java.util.*;


public class MysqlForDev {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String url = "jdbc:mysql://172.16.30.25:3306/mproduction?characterEncoding=UTF-8";
        String driver = "com.mysql.jdbc.Driver";
        String user = "mproduction";
        String password = "16021incloud";
        PrintWriter pw = null;
        FileWriter fw = null;
        File file = new File("/Users/liujianmeng/Desktop/666.txt");
        FileInputStream is = null;
        BufferedReader in = null;
//        Set<String> idSet = new HashSet<>();
//        Set<String> editorUserIds = getEditorUserIds();
        List<String> whiteList = getWhiteList();
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
                int i = 0;
                while ((line = in.readLine()) != null)
                {
                    String[] lines = line.split("\t");
                    System.out.println(++i);
//                    String sql = "select downloadcount from `packageblock_relation` r,block b where r.`block_id`=b.block_id and r.`packagename`= '"+lines[0]+"'";
//                    Statement ptmt = conn.createStatement();
//                    ResultSet resultSet = ptmt.executeQuery(sql);
//                    if (!resultSet.next()){
//                        FileWriter writer = new FileWriter("/Users/liujianmeng/Desktop/result.txt",true);
//                        writer.write(line+"\r");
//                        writer.flush();//刷新内存，将内存中的数据立刻写出。
//                        writer.close();
//                    }
//                    resultSet.previous();
//                    while (resultSet.next()) {
//                          String downloadcount = resultSet.getString("downloadcount");
//                        try {
//                            FileWriter writer = new FileWriter("/Users/liujianmeng/Desktop/result.txt",true);
//                            writer.write(downloadcount+"\t"+line+"\r");
//                            writer.flush();//刷新内存，将内存中的数据立刻写出。
//                            writer.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    resultSet.close();
                    if (whiteList.contains(lines[1])){
                        continue;
                    }
                    try {
                        FileWriter writer = new FileWriter("/Users/liujianmeng/Desktop/result.txt",true);
                        writer.write(line+"\r");
                        writer.flush();//刷新内存，将内存中的数据立刻写出。
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                System.out.println("size: "+idSet.size());
//                Iterator<String> iterator = idSet.iterator();
//                while (iterator.hasNext()){
//                    String userId = iterator.next();
//                    System.out.println(userId);
//                    try {
//                        FileWriter writer = new FileWriter("/Users/liujianmeng/Desktop/result.txt",true);
//                        writer.write(userId+"\r");
//                        writer.flush();//刷新内存，将内存中的数据立刻写出。
//                        writer.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }



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


    public static Set<String> getEditorUserIds(){
        PrintWriter pw = null;
        FileWriter fw = null;
        File file = new File("/Users/liujianmeng/Desktop/editor_manage.txt");
        FileInputStream is = null;
        BufferedReader in = null;
        Set<String> editorUserId = new HashSet<>();
        try {
                is = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(is);
                in = new BufferedReader(isr);
                String line = null;
                while ((line = in.readLine()) != null)
                {
                    editorUserId.add(line);
                }
            in.close();
            is.close();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {

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

    return editorUserId;

    }


    public static List<String> getWhiteList(){
        PrintWriter pw = null;
        FileWriter fw = null;
        File file = new File("/Users/liujianmeng/Desktop/whitePackage.txt");
        FileInputStream is = null;
        BufferedReader in = null;
        List<String> list = new ArrayList<>();
        try {
            is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            in = new BufferedReader(isr);
            String line = null;
            while ((line = in.readLine()) != null)
            {
                list.add(line);
            }
            in.close();
            is.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                // 关闭IO流
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {

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

        return list;

    }



}