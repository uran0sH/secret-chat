package com.hwy.secretchat.utils;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-15
 */
public class DataUtils {

    public static String base64Encode(byte[] data) {
        return java.util.Base64.getEncoder().encodeToString(data);
    }

    public static byte[] base64Decode(String data) {
        return java.util.Base64.getMimeDecoder().decode(data);
    }

}
