package com.platform.common.utils;

import java.security.MessageDigest;
import java.util.Objects;

/**
 * MD5加密工具类
 */
public class MD5Util {
    private static final String DEFAULT_MD_5_SALT = "fjdsl321312kf349832&*^*903294[JNLIUIK]%fsdjfkl";//加盐md5盐值

    /**
     * 获得字符串的md5值
     *
     * @return md5加密后的字符串
     */
    public static String getMd5(String s) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验字符串的md5值
     *
     * @param str 目标字符串
     * @param md5 基准md5
     * @return 校验结果
     */
    public static boolean checkMd5(String str, String md5) {
        return Objects.requireNonNull(getMd5(str)).equalsIgnoreCase(md5);
    }

    /**
     * 获得加盐md5，算法过程是原字符串md5后连接加盐字符串后再进行md5
     *
     * @param str  待加密的字符串
     * @param salt 盐
     * @return 加盐md5
     */
    public static String getMd5AndSalt(String str, String salt) {
        return getMd5(Objects.requireNonNull(getMd5(str)).concat(salt));
    }

    /**
     * 获得加盐md5，算法过程是原字符串md5后连接加盐字符串后再进行md5
     * 使用默认盐值
     *
     * @param str 待加密的字符串
     * @return 加盐md5
     */
    public static String getMd5AndSalt(String str) {
        return getMd5(Objects.requireNonNull(getMd5(str)).concat(DEFAULT_MD_5_SALT));
    }
}
