package com.hwy.secretchat.utils.encryption;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class SHA1EncryptUtilTest {

    @Test
    void encryptStr() {
        String hello = new String("IamWhatamI1101");
        String result1 = SHA1EncryptUtil.encryptStr(hello);
        String result2 = SHA1EncryptUtil.encryptStr(hello);
        System.out.println(result1);
        boolean isEqual = result1.equals(result2);
        Assert.assertEquals(true, isEqual);
    }
}