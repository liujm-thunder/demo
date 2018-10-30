package com.appchina.collect;

import com.appchina.collect.utils.QuickSort;

import java.util.Random;

public class FindMaxK {

    private static Random random = new Random();

    private  static int[] array = new int[20];

    static {
        for (int i = 0;i<20;i++){
            array[i]=random.nextInt(100)+1;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(array[i]);
        }
        System.out.println("*******************");
        int[] k = findMaxKData(4);
        for(int x :k){
            System.out.print(x+" ");
        }
    }


    public static int[] findMaxKData(int k){
        QuickSort.sort(array,0,array.length-1);
        int[] kArray = new int[k];
        int j = array.length-1;
        for (int i = 0; i  < k ; i++) {
            kArray[i] = array[j--];
        }
        return kArray;
    }
}
