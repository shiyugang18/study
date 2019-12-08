package com.syg.utils;

import java.util.Base64;

public class Base64Util {
    /**
     * BASE64解密
     *
     * @param key 文本
     * @return 内容
     */
    public static String decryptBase64(String key) {
        try {
            return new String(Base64.getDecoder().decode(key.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * BASE64加密
     *
     * @param key 文本
     * @return 内容
     */
    public static String encryptBase64(String key) {
        return new String(Base64.getEncoder().encode(key.getBytes()));
    }
}
