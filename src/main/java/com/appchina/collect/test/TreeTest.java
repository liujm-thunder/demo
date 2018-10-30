package com.appchina.collect.test;

public class TreeTest {

    public boolean VerifySquenceOfBST(int[] sequence){

        //TODO 日常判断是否为空
        if (sequence.length==0)return false;
        return judge(sequence,0,sequence.length-1);
    }


    public boolean judge(int[] sequence,int start,int len){
        //TODO 首尾碰撞 表示校验完毕
        if (start>=len)return true;
        int i = len-1;
        //TODO 确定左右子树分割点
        while (i>start&&sequence[i]>sequence[len]){
            i--;
        }

        //TODO 判断左子树是否大于根节点
        for (int j = start; j < i; j++) {
            if (sequence[j]>sequence[len]){
                return false;
            }
        }
        //TODO 递归校验左右子树是否合法
        return judge(sequence,start,i)&&judge(sequence,i+1,len-1);

    }

    public static void main(String[] args) {
        int [] a = {5,4,3,2,1};
        TreeTest t = new TreeTest();
        t.VerifySquenceOfBST(a);
    }
}
