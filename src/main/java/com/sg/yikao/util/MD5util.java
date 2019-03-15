package com.sg.yikao.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Ssssg
 * @Description MD5加密
 * @Date 2019/3/14 22:23
 **/
public class MD5util {


    private static final String HEX_NUMS_STR="0123456789ABCDEF";
    private static final Integer SALT_LENGTH = 32;


    /**
     * 将字符串转换成byte数组
     * @param hex
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4
                    | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }

    /**
     * 将指定byte数组转换成字符串
     * @param b
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        String hex;
        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     * 获得加密后的口令
     * @param password
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getEncryptedPwd(String password, String salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //声明加密后的口令数组变量
        byte[] pwd = null;
        //声明盐数组变量
        byte[] saltByte = new byte[SALT_LENGTH];
        // 将salt字符串转化成数组传递给数组
        System.arraycopy(hexStringToByte(salt),0, saltByte, 0, hexStringToByte(salt).length);
        //声明消息摘要对象
        MessageDigest md = null;
        //创建消息摘要
        md = MessageDigest.getInstance("MD5");
        //将盐数据传入消息摘要对象
        md.update(saltByte);
        //将口令的数据传给消息摘要对象
        md.update(password.getBytes("UTF-8"));
        //获得消息摘要的字节数组
        byte[] digest = md.digest();
        //因为要在口令的字节数组中存放盐，所以加上盐的字节长度
        pwd = new byte[digest.length + SALT_LENGTH];
        //将盐的字节拷贝到生成的加密口令字节数组的前12个字节
        System.arraycopy(saltByte, 0, pwd, 0, SALT_LENGTH);
        //将消息摘要拷贝到加密口令字节数组从第13个字节开始的字节
        System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
        //将字节数组格式加密后的口令转化为16进制字符串格式的口令
        return byteToHexString(pwd);
    }
}