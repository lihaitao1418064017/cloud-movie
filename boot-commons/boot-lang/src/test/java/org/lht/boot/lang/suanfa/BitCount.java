package org.lht.boot.lang.suanfa;

/**
 * @author LiHaitao
 * @description
 * @date 2020/10/14 13:13
 **/
public class BitCount {

    /**
     * 通过位移逐位去判断是否为1
     */
    public static int bitCount(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;//最后一位与1运算n=7(111) 111&001=1
            n >>>= 1;//将最后一位位移出 111>>>1=011
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(bitCount(3));
    }
}
