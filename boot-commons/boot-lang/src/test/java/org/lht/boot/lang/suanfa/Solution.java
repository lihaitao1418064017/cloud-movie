package org.lht.boot.lang.suanfa;

/**
 * 思路分析:总次数=最后一步走了一步的次数+最后一步走两步的次数
 * 阶            次数
 * 1            1
 * 2            2
 * 3            3最后走一步(2阶上)2次+最后走两步(1阶上)1次
 * 4            5 最后走一步3阶上的次数+最后走两步2阶上的次数=5
 */
class Solution {
    public static int climbStairs(int n) {

        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }


    private static int climbStairs1(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int num1 = 1;
        int num2 = 2;
        for (int i = 2; i < n; i++) {
            int tmp = num1 + num2;
            num1 = num2;
            num2 = tmp;
        }
        return num2;
    }

    private static int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }


    public static void main(String[] args) {
        System.out.println(climbStairs(6));
        System.out.println(climbStairs1(6));
        System.out.println(climbStairs2(5));

    }
}