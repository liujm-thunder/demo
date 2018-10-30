package com.appchina.collect.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class MysqlDemo {
//    public static void main(String[] args) throws Exception {
//        Connection conn = null;
//        String url = "jdbc:mysql://172.16.30.25:3306/mproduction?characterEncoding=UTF-8";
//        String driver = "com.mysql.jdbc.Driver";
//        String user = "mproduction";
//        String password = "16021incloud";
//        PrintWriter pw = null;
//        FileWriter fw = null;
//        File file = new File("/Users/liujianmeng/Desktop/dev.txt");
//        FileInputStream is = null;
//        BufferedReader in = null;
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        Date date = sdf.parse("2017-01-01 00:00:00");
//        Connection conn1 = null;
//        String url1 = "jdbc:mysql://172.16.30.25:3306/mproduction?characterEncoding=UTF-8";
//        String driver1 = "com.mysql.jdbc.Driver";
//        String user1 = "mproduction";
//        String password1 = "16021incloud";
//        Class.forName(driver1);
//        conn1 = DriverManager.getConnection(url1, user1, password1);
//
//        try {
//            Class.forName(driver);
//            System.out.println("成功加载MySQL驱动程序");
//            // 一个Connection代表一个数据库连接
//            conn = DriverManager.getConnection(url, user, password);
//            if (!conn.isClosed()) {
//                System.out.println("Succeed connecting to the Database!");
//                is = new FileInputStream(file);
//                InputStreamReader isr = new InputStreamReader(is);
//                in = new BufferedReader(isr);
//                String line = null;
//                int i = 0;
//                while ((line = in.readLine()) != null)
//                {
//                    System.out.println(++i);
//                    String sql = "select name,email,cellphone from developer_info where user_id = '"+line+"'";
//                    Statement ptmt = conn.createStatement();
//                    ResultSet resultSet = ptmt.executeQuery(sql);
//                    String name = "";
//                    String email = "";
//                    String cellphone = "";
//                    while (resultSet.next()) {
//                           name = resultSet.getString("name");
//                           email = resultSet.getString("email");
//                           cellphone = resultSet.getString("cellphone");
//                    }
//                    if (StringUtils.isEmpty(name)){
//                        String sql1 = "select name,devemail,devphone from userinfopool  where user_id='"+line+"'";
//                        Statement ptmt1 = conn.createStatement();
//                        ResultSet resultSet1 = ptmt1.executeQuery(sql1);
//                        while (resultSet1.next()) {
//                            name = resultSet1.getString("name");
//                            email = resultSet1.getString("devemail");
//                            cellphone = resultSet1.getString("devphone");
//                        }
//                        resultSet1.close();
//                    }
//                    getconnect(conn1,name,email,cellphone,line);
//
//                    resultSet.close();
//                }
//            }
//            conn1.close();
//            conn.close();
//            in.close();
//            is.close();
//
//        } catch (SQLException e) {
//            System.out.println("MySQL操作错误");
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    // 关闭数据库连接
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (pw != null) {
//                // 关闭IO流
//                pw.close();
//            }
//            if (fw != null) {
//                try {
//                    fw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }



    public static void getconnect(Connection conn,String name,String email,String phone,String line){
        try {
            if (!conn.isClosed()) {
                String sql = "select block_id,name,packagename from new_application where user_id ='"+line+"'and status=0";
                Statement ptmt = conn.createStatement();
                ResultSet resultSet = ptmt.executeQuery(sql);
                Set<String> packageNameSet = new HashSet<>();
                while (resultSet.next()) {
                    int block_id =resultSet.getInt("block_id");
                    if (!isSoftApp(conn,block_id)){
                        continue;
                    }
                    String appName =resultSet.getString("name");
                    String packagename =resultSet.getString("packagename");
                    if (packageNameSet.add(packagename)){
                        try {
                            FileWriter writer = new FileWriter("/Users/liujianmeng/Desktop/dev_app.txt",true);
                            writer.write(name+"\t"+appName+"\t"+packagename+"\t"+email+"\t"+phone+"\r");
                            writer.flush();//刷新内存，将内存中的数据立刻写出。
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isSoftApp(Connection conn,int blockId){
        try {
            if (!conn.isClosed()) {
                String sql = "select categoryid from categoryitem where blockid ='"+blockId+"'";
                Statement ptmt = conn.createStatement();
                ResultSet resultSet = ptmt.executeQuery(sql);
                System.out.println("size: "+resultSet.next());
                resultSet.previous();
                while (resultSet.next()) {
                    int categoryId =resultSet.getInt("categoryid");
                    if (categoryId>=400&&categoryId<=500){
                        return false;
                    }else {
                        return true;
                    }
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void update(Connection conn){
        Set<String> packages = new HashSet<>();
        try {
            if (!conn.isClosed()) {
                String sql = "select blockid from categoryitem where categoryid='315'";
                Statement ptmt = conn.createStatement();
                ResultSet resultSet = ptmt.executeQuery(sql);
                while (resultSet.next()) {
                    int blockid =resultSet.getInt("blockid");
                    String sql1 = "select packagename from packageblock_relation where block_id='"+blockid+"'";
                    Statement ptmt1 = conn.createStatement();
                    ResultSet resultSet1 = ptmt1.executeQuery(sql1);
                    while (resultSet1.next()){
                        String packagename = resultSet1.getString("packagename");
                        if (!packages.add(packagename)){break;}
                        String sql2 ="select block_id,downloadcount from block where block_id='"+blockid+"' and defaultappid!=-1";
                        Statement ptmt2 = conn.createStatement();
                        ResultSet resultSet2 = ptmt2.executeQuery(sql2);
                        while (resultSet2.next()){
                            int downloadcount = resultSet2.getInt("downloadcount");
                            System.out.println(packagename+"\t"+downloadcount);
                        }
                        resultSet2.close();
                    }
                    resultSet1.close();
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://172.16.30.25:3306/mproduction?characterEncoding=UTF-8";
        String driver = "com.mysql.jdbc.Driver";
        String user = "mproduction";
        String password = "16021incloud";
        try {
            Class.forName(driver);
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) {
                System.out.println("Succeed connecting to the Database!");
                update(conn);
            }
            conn.close();

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
        }
    }
}