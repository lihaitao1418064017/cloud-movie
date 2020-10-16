package org.lht.boot.lang.suanfa;

/**
 * @author LiHaitao
 * @description 冒泡排序
 * @date 2020/10/14 16:44
 **/
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 8, 3, 9, 4, 7, 8, 6, 5, 3};


        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }

    }
}
