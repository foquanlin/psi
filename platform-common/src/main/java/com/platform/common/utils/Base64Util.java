package com.platform.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 作者: @author Lipengjun <br>
 * 时间: 2017-08-11 16:17<br>
 * 描述: Base64 <br>
 */
public class Base64Util {
    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] b = str.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(b);
    }

    /**
     * 解密
     *
     * @param s
     * @return
     */
    public static String decode(String s) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(s), StandardCharsets.UTF_8);
    }
}
