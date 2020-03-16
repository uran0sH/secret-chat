package com.hwy.secretchat.utils;

import java.util.Random;

/**
 * @program: secret-chat
 * @author: huangwenyu
 * @create: 2020-03-15
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer a = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(a);
    }
}
