package com.king.service.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;

public class DEScode {
    /**
     * 全局数组
     */
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    // KeyGenerator 提供对称密钥生成器的功能，支持各种算�?
    private SecretKeyFactory keygen;
    // SecretKey 负责保存对称密钥
    private SecretKey deskey;
    // Cipher负责完成加密或解密工�?
    private Cipher c;
    // 该字节数组负责保存加密的结果
    private byte[] cipherByte;



    public DEScode() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, UnsupportedEncodingException {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        // 实例化支持DES算法的密钥生成器(算法名称命名�?按规定，否则抛出异常)
        keygen = SecretKeyFactory.getInstance("DES");
        // 生成密钥
        byte[] DESkey = "comkingdomwww".getBytes("UTF-8");// 设置密钥
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        deskey = keygen.generateSecret(keySpec);
        // 生成Cipher对象,指定其支持的DES算法
        c = Cipher.getInstance("DES");
    }

    /**
     * 对字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] Encrytor(String str) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] src = str.getBytes();
        // 加密，结果保存进cipherByte
        cipherByte = c.doFinal(src);
        return cipherByte;
    }

    /**
     * 对字符串解密
     *
     * @param buff
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] Decryptor(byte[] buff) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
        c.init(Cipher.DECRYPT_MODE, deskey);
        cipherByte = c.doFinal(buff);
        return cipherByte;
    }

    /**
     * @param args
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public static void main(String[] args) throws Exception {
        GetProcessID getProcessID = new GetProcessID();
        System.out.println("ProcessID: " + getProcessID.getid());

        DEScode desCode = new DEScode();
        //String inMsg = "郭XX-搞笑相声全集";
        String inMsg = "BFEBFBFF000306D4"+"20200202"+"123456";
        System.out.println("加密前字符串: " + inMsg);

        byte[] encodeByteArr = desCode.Encrytor(inMsg);
        String encodeStr = desCode.byteArrayToHexString(encodeByteArr);
        System.out.println("加密后十六进制字符串: " + encodeStr);     //存数据库

        byte[] decodeByteArr = desCode.hexString2Bytes(encodeStr);
        byte[] outMsgByteArr = desCode.Decryptor(decodeByteArr);
        String outMsg = new String(outMsgByteArr);
        System.out.println("解密后字符串: " + outMsg);

    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     *
     * @param b
     *            字节数组
     * @return 字符�?
     */
    private String byteToHexString(byte b) {
        int ret = b;
        // System.out.println("ret = " + ret);
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }

    /**
     * 转换字节数组为十六进制字符串
     *
     * @param bytes
     *            字节数组
     * @return 十六进制字符�?
     */
    public String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 转换十六进制字符串为字节数组
     *
     * @param hexstr
     *            十六进制字符�?
     * @return
     */
    public byte[] hexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     * 转换字符类型数据为整型数�?
     *
     * @param c
     *            字符
     * @return
     */
    private int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

}
