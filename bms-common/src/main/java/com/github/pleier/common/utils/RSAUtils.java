package com.github.pleier.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : pleier
 * @date : 2018/3/9
 */
public class RSAUtils {
    private static final Logger logger = LoggerFactory.getLogger(RSAUtils.class);
    private static String KEY_RSA_TYPE = "RSA";
    /**
     * JDK方式RSA加密最大只有1024位
     */
    private static int KEY_SIZE = 1024;
    private static int ENCODE_PART_SIZE = KEY_SIZE / 8;
    private static final String PUBLIC_KEY_NAME = "public";
    private static final String PRIVATE_KEY_NAME = "private";

    /**
     * 创建公私秘钥
     *
     * @return
     */
    public static Map<String, String> createRSAKeys() {
        //里面存放公私秘钥的Base64位加密
        Map<String, String> keyPairMap = new HashMap<>(16);
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_RSA_TYPE);
            keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            //获取公私秘钥
            String publicKeyValue = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
            String privateKeyValue = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());

            //存入公私秘钥，以便以后获取
            keyPairMap.put(PUBLIC_KEY_NAME, publicKeyValue);
            keyPairMap.put(PRIVATE_KEY_NAME, privateKeyValue);
        } catch (NoSuchAlgorithmException e) {
            logger.error("当前JDK版本没找到RSA加密算法！");
            e.printStackTrace();
        }
        return keyPairMap;
    }

    /**
     * 公钥加密
     * 描述：
     * 1字节 = 8位；
     * 最大加密长度如 1024位私钥时，最大加密长度为 128-11 = 117字节，不管多长数据，加密出来都是 128 字节长度。
     *
     * @param sourceStr          需加密数据
     * @param publicKeyBase64Str 公钥
     * @return 加密后数据
     */
    public static String encode(String sourceStr, String publicKeyBase64Str) {
        byte[] publicBytes = Base64.decodeBase64(publicKeyBase64Str);
        //公钥加密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicBytes);
        List<byte[]> alreadyEncodeListData = new LinkedList<>();

        int maxEncodeSize = ENCODE_PART_SIZE - 11;
        String encodeBase64Result = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(KEY_RSA_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] sourceBytes = sourceStr.getBytes("utf-8");
            int sourceLen = sourceBytes.length;
            for (int i = 0; i < sourceLen; i += maxEncodeSize) {
                int curPosition = sourceLen - i;
                int tempLen = curPosition;
                if (curPosition > maxEncodeSize) {
                    tempLen = maxEncodeSize;
                }
                //待加密分段数据
                byte[] tempBytes = new byte[tempLen];
                System.arraycopy(sourceBytes, i, tempBytes, 0, tempLen);
                byte[] tempAlreadyEncodeData = cipher.doFinal(tempBytes);
                alreadyEncodeListData.add(tempAlreadyEncodeData);
            }
            //加密次数
            int partLen = alreadyEncodeListData.size();

            int allEncodeLen = partLen * ENCODE_PART_SIZE;
            //存放所有RSA分段加密数据
            byte[] encodeData = new byte[allEncodeLen];
            for (int i = 0; i < partLen; i++) {
                byte[] tempByteList = alreadyEncodeListData.get(i);
                System.arraycopy(tempByteList, 0, encodeData, i * ENCODE_PART_SIZE, ENCODE_PART_SIZE);
            }
            encodeBase64Result = Base64.encodeBase64String(encodeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeBase64Result;
    }

    /**
     * 私钥解密
     *
     * @param sourceBase64RSA     需加密数据
     * @param privateKeyBase64Str 私钥
     * @return 解密后数据
     */
    public static String decode(String sourceBase64RSA, String privateKeyBase64Str) {
        byte[] privateBytes = Base64.decodeBase64(privateKeyBase64Str);
        byte[] encodeSource = Base64.decodeBase64(sourceBase64RSA);
        int encodePartLen = encodeSource.length / ENCODE_PART_SIZE;
        //所有解密数据
        List<byte[]> decodeListData = new LinkedList<>();
        String decodeStrResult = null;
        //私钥解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(KEY_RSA_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //初始化所有被解密数据长度
            int allDecodeByteLen = 0;
            for (int i = 0; i < encodePartLen; i++) {
                byte[] tempEncodedData = new byte[ENCODE_PART_SIZE];
                System.arraycopy(encodeSource, i * ENCODE_PART_SIZE, tempEncodedData, 0, ENCODE_PART_SIZE);
                byte[] decodePartData = cipher.doFinal(tempEncodedData);
                decodeListData.add(decodePartData);
                allDecodeByteLen += decodePartData.length;
            }
            byte[] decodeResultBytes = new byte[allDecodeByteLen];
            for (int i = 0, curPosition = 0; i < encodePartLen; i++) {
                byte[] tempSorceBytes = decodeListData.get(i);
                int tempSourceBytesLen = tempSorceBytes.length;
                System.arraycopy(tempSorceBytes, 0, decodeResultBytes, curPosition, tempSourceBytesLen);
                curPosition += tempSourceBytesLen;
            }
            decodeStrResult = new String(decodeResultBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeStrResult;
    }

    public static void main(String[] args) {
        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALP2cR8kQytg0HvtL1bHjL0BGICK5M//BCI4HDKV0JRFAav/rtN2geRXmgXNqm8xC7Q02vACSkr5laxQLoWRmHZeMZ1KtywZHDzPGj9xmtXca5YKYTD9ULNgfNjh3ihQ7tCV+uLwJ1oQYxgmKp5NncSuBA2hTZO32h6ZQ8CRhec9AgMBAAECgYBgQSb+HhojIHRCZHlWdAMNsIFXd3Ks4VxnpRVH5SObulELmwT6K7+Lz2MdSYSoVlZJO6ACkWcFNPteH8DSRi6B83d1HU9UZxJ99ehlqgblqEn9dJci5BLHzQKYZgf1FQ8TieOSnwUGMqs3Uxms6NO1fAG3oxYSY3OTtXnWnJOIoQJBANrAcQ9MGn2HQ9Jw3Ij9gFDd+PAG+BcdvgEFh5ddig+mp1JAfsaB5lx8gSb7U3yKIYU8Ee4vMKVArE3WCCCflOkCQQDSmyegHoLbHupr4UB6Z6Dn4DcM+7NMvR8xVgTm6buR7iUBzRG2VLhzQ6Astopm+I4BAZ90PXOS+cv4d52e25s1AkEAkE0iR9UMJIJ2WvvYfwW6/51t+eie/6C/Fi1vpIov6OMmvTuOZaSLMbdv+ycPi7gTJLDboyO+E0T2ZOIrulmLAQJBAIZH+xnymkiQC7PBKVViYYu2wCL9ETNqmkrgp7t7Z+cByoK9d/+jQP/tLGGV+eTxsckeecapr9kUgGLqEYvKRckCQQDSL4Ax6W48PMdFAd/M4Fg9gY0dp1Rk7wNytY4C2wTWYx6j4dyN53/oYBR+wKSQpqtyDOkfPom9Bqmt4SZ+dA5T";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCz9nEfJEMrYNB77S9Wx4y9ARiAiuTP/wQiOBwyldCURQGr/67TdoHkV5oFzapvMQu0NNrwAkpK+ZWsUC6FkZh2XjGdSrcsGRw8zxo/cZrV3GuWCmEw/VCzYHzY4d4oUO7Qlfri8CdaEGMYJiqeTZ3ErgQNoU2Tt9oemUPAkYXnPQIDAQAB";
        Long a = System.currentTimeMillis();
        String value = "非对称加密";
        String encode = encode(value, publicKey);
        System.out.println("加密前：" + value);
        System.out.println("加密后：" + encode);
        String decode = decode(encode, privateKey);
        System.out.println("解密后：" + decode);
        Long b = System.currentTimeMillis();
        System.out.println("耗时：" + (b-a));
    }
}