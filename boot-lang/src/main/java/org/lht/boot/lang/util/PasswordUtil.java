package org.lht.boot.lang.util;


import cn.hutool.core.util.RandomUtil;

import java.util.regex.Pattern;

/**
 * @author LiHaitao
 * @description PasswordUtil:密码规则校验工具类
 * @date 2019/11/12 11:15
 **/
public class PasswordUtil {

    private static final Pattern COMPLEX_PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*])[\\da-zA-Z~!@#$%^&*]{8,15}$");

    private static final Pattern SIMPLE_PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{8,15}$");
    private static final char[] SPECIAL_CHARACTER = {'!', '@', '#', '$', '%', '^', '&', '*'};

    public static final Integer SIMPLE_ROLE = 3;
    public static final Integer COMPLEX_ROLE = 4;

    /**
     * 根据密码强度匹配对应密码规则
     *
     * @param password
     * @param role
     * @return
     */
    public static boolean match(String password, Integer role) {
        if (SIMPLE_ROLE.equals(role)) {
            return SIMPLE_PASSWORD_PATTERN.matcher(password).find();
        }
        if (COMPLEX_ROLE.equals(role)) {
            return COMPLEX_PASSWORD_PATTERN.matcher(password).find();
        }
        return false;
    }


    /**
     * 密码生成
     *
     * @param length 密码长度
     * @return password
     */
    private static String makePassword(int length, Integer role) {
        int range = role;
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int r = RandomUtil.randomInt(range);
            switch (r) {
                case 0:
                    password.append((char) ('A' + Math.random() * 26));
                    break;
                case 1:
                    password.append((char) ('a' + Math.random() * 26));
                    break;
                case 2:
                    password.append(RandomUtil.randomInt(0, 9));
                    break;
                case 3:
                    password.append(SPECIAL_CHARACTER[RandomUtil.randomInt(0, SPECIAL_CHARACTER.length)]);
                    break;
                default:
                    return "";
            }
        }
        return password.toString();
    }

}
