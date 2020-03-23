package com.hwy.secretchat.utils;

import java.util.UUID;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-15
 */
public class UserIdUtil {

    private static final String PREFIX = "brsmsg_";

    public static synchronized String generateUserId() {
        String unique = KeyUtil.genUniqueKey();
        return PREFIX + unique;
    }
}
