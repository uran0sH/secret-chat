package com.hwy.secretchat.utils;

import org.junit.jupiter.api.Test;

class KeyUtilTest {

    @Test
    void genUniqueKey() {
       String abc =  KeyUtil.genUniqueKey();
       System.out.println(abc);
    }
}