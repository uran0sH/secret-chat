package com.hwy.secretchat.utils.encryption;

import com.hwy.secretchat.utils.DataUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @program secret-chat
 * @author huangwenyu
 * @create 2020-03-15
 */
public class SHA1EncryptUtil {

    public static byte[] encrypt(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(data);
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String encryptStr(String data) {
        try {
            byte[] result = encrypt(data.getBytes("UTF-8"));
            return DataUtils.base64Encode(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
