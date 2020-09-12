package org.lht.boot.lang.util;

import cn.hutool.core.util.RandomUtil;

/**
 * @author LiHaitao
 * @description RandomUtils: 随机工具类
 * @date 2020/3/11 14:36
 **/
public class RandomUtils extends RandomUtil {

    private static final String N = "朱秦尤许何吕施孔曹严华金魏陶姜鲁韦昌马苗凤花方俞任袁柳" +
            "酆鲍史唐费廉岑薛雷贺倪汤李刘赵王周陈许金钱孙郑李刘赵王周陈许金钱孙郑李刘赵王周陈许金钱孙郑";

    private static final String M1 = "强浩涛鹏雨青红伟旭刚桃倩庆娜珍家寒桃楠杉玲璟玲柏妮楠曼寒" +
            "美琳雅初家萱华彤冬玲俊花丽瑶琬璇薇春格璟玉月丽鹤阳珠璇馨蕾岚锦美雪紫心彩阳璇珠曼云" +
            "琬岚颖怡栀云琪雨琪柔雪璐杉欢璇韵歆蔚茹珊蔚冰曼薇薇雯格雅瑶欢枫芝可雅莉芳明雯香馨凌" +
            "珍欢琳柔婷帛芙春美丽紫梓柔玥玉弦";

    private static final String M2 = "桐鑫芝雨慧薇莉心雨蔚彤菡钰莲玲梅云梅彤雅可栀碧琳云桃楠芸" +
            "寒婷淑采雪璐杉欢璇韵歆蔚茹珊蔚冰曼薇薇雯格雅瑶欢枫芝可雅莉芳明雯香馨凌珍欢琳柔婷帛芙" +
            "春美丽紫梓柔玥玉弦";

    private static final String M21 = "珍家寒桃楠杉玲璟玲柏妮楠曼寒美琳雅初家萱华彤冬玲俊花丽瑶琬" +
            "璇薇春格璟玉月丽鹤阳珠璇馨蕾岚锦美雪紫心彩阳璇珠曼云琬岚颖怡栀云琪雨琪柔杉昭芳惠函旭蕾珊茜怡璇";


    private static final String G = "初高研博小";
    private static final String R = "一二三";
    private static final String A = "123456789";

    /**
     * 随机生成中文姓名
     *
     * @return
     */
    public static String randomName() {
        final StringBuilder sb = new StringBuilder();
        sb.append(randomString(N, 1));
        if (randomInt(3) % 2 == 0) {
            return sb.append(randomString(M2, 1))
                    .append(randomString(M21, 1))
                    .toString();
        }
        return sb.append(randomString(M1, 1))
                .toString();
    }

    /**
     * 随机生成一个初高研博小的班级
     * 格式：eg. 高三(8)班
     *
     * @return grade
     */
    public static String randomGrade() {
        final StringBuilder sb = new StringBuilder();
        return sb.append(randomString(G, 1))
                .append(randomString(R, 1))
                .append("(")
                .append(randomString(A, 1))
                .append(")班").toString();
    }

    /**
     * 随机返回男或女
     *
     * @return
     */
    public static String randomSex(boolean isChinese) {
        if (isChinese) {
            return randomString("男女", 1);
        }
        return String.valueOf(randomInt(2));
    }

}
