package org.lht.boot.lang.suanfa;

import java.util.Stack;


       /* 假设袋子里有编号为1,2,...,m这m个球。现在每次从袋子中取一个球几下编号，放回袋中再取，取n次作为一组，枚举所有可能的情况。
        分析：
        每一次取都有m种可能的情况，因此一共有种情况。
        这里我们取m=3,n=4，则有种不同的情况。
        代码：*/

public class Test {
    static int cnt = 0;
    static Stack<Integer> s = new Stack<Integer>();

    public static void main(String[] args) {
        kase1(1, 3, 0, 4);
        System.out.println(cnt);
    }


    /**
     * 递归方法，当实际选取的小球数目与要求选取的小球数目相同时，跳出递归
     *
     * @param minv   - 小球编号的最小值
     * @param maxv   - 小球编号的最大值
     * @param curnum - 当前已经确定的小球的个数
     * @param maxnum - 要选取的小球的数目
     */
    public static void kase1(int minv, int maxv, int curnum, int maxnum) {
        if (curnum == maxnum) {
            cnt++;
            System.out.println(s);
            return;
        }

        for (int i = minv; i <= maxv; i++) {
            s.push(i);
            kase1(minv, maxv, curnum + 1, maxnum);
            s.pop();
        }
    }
}
 