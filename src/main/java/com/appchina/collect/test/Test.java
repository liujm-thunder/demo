package com.appchina.collect.test;


import com.appchina.check.ApkInfo;

import java.io.File;
import java.util.ArrayList;

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

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }


    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root==null)return null;
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if(root.left==null&&root.val==target){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(root.val);
            lists.add(list);
        }

        return  lists;
    }



}
