package org.lht.boot.lang.suanfa;

/**
 * @author LiHaitao
 * @description 快速排序
 * @date 2020/10/14 17:02
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {2, 1, 5, 8, 3, 9, 4, 7, 8, 6, 5, 3};

        sort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void sort(int[] a, int f, int e) {
        if (f < e) {
            int mid = div(a, f, e);
            sort(a, f, mid);
            sort(a, mid + 1, e);
        }
    }

    public static int div(int[] a, int f, int e) {

        int i = f, j = e;
        int x = a[f];
        while (i < j) {


            while (i < j && a[j] >= x) {
                j--;
            }
            if (i < j) {
                a[i] = a[j];
                i++;
            }


            while (i < j && a[i] < x) {
                i++;
            }
            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        a[i] = x;
        return i;
    }
}
