package org.lht.boot.lang.suanfa;

public class PrimeNumber {


    public static void main(String[] args) {
        int N = 100;

        for (int i = 1; i < N; i++) {
            if (judgePrime(i))
                System.out.print(i + "   ");
        }
        System.err.println(judgePrime(5));
    }


    /**
     * 判断一个数是否为素数
     * Math.sqrt可以改成n，也可以改成n/2  但Math.sqrt是最好的
     *
     * @param n
     * @return
     */
    public static boolean judgePrime(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }


}