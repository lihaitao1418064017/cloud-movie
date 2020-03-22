package org.lht.boot.lang.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * @author LiHaitao
 * @description SecureUtils:
 * @date 2019/8/30 10:51
 **/
@Slf4j
public class SecureUtils {

    /**
     * key和iv的补全参数，一般情况下key和iv不会太少，这里最大补全15位。 注：key和iv要满足16位
     */
    private static final String[] ZEROPADDINGS = {"", "0", "00", "000", "0000", "00000", "000000", "0000000", "00000000", "000000000", "0000000000", "00000000000", "000000000000", "0000000000000", "00000000000000", "0000000000000000"};
    /**
     * blockSize 为 AES/CBC/NoPadding 的 size
     */
    private static final int BLOCK_SIZE = 16;
    private static final int OFFSET = 16;


    private static final String RSA = "RSA";

    /**
     * @param serRsaModulus  RSA公钥模数
     * @param serRsaExponent RSA公钥指数
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String serRsaModulus, String serRsaExponent) throws Exception {
        // decode
        byte[] serRsaExponentDecode = Base64.decodeBase64(serRsaExponent);
        byte[] serRsaModulusDecode = Base64.decodeBase64(serRsaModulus);
        // generate publicKey
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        BigInteger modulus = new BigInteger(serRsaModulusDecode);
        BigInteger publicExponent = new BigInteger(serRsaExponentDecode);
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
        PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
        return publicKey;
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(str.getBytes());
        return Base64.encodeBase64String(bytes);
    }

    /**
     * RSA解密
     *
     * @param str        要解密字符串
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));

        // base64编码的私钥
        byte[] encPriKey = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(encPriKey);

        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // RSA解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    /**
     * 构建AES加密密钥和偏移量对象
     *
     * @param key
     * @param iv
     * @param charset
     * @return AES
     */
    public static AES aes(String key, String iv, Charset charset) {
        /**密钥key为16位通过Latin1编码，不满16位前位补0;*/
        /**密钥偏移量iv为16位通过Latin1编码，不满16位后位补0;*/
        int keyLength = key.length();
        if (keyLength < OFFSET && keyLength > 0) {
            key = ZEROPADDINGS[OFFSET - keyLength] + key;
        }
        int ivLength = iv.length();
        if (ivLength < OFFSET && ivLength > 0) {
            iv = iv + ZEROPADDINGS[OFFSET - ivLength];
        }
        /** 编码 */
        byte[] keyBytes = StrUtil.bytes(key, charset);
        byte[] ivBytes = StrUtil.bytes(iv, charset);
        return new AES(Mode.CBC, Padding.NoPadding, keyBytes, ivBytes);
    }


    /**
     * Aes加密 NoPadding自定义的 ZeroPadding模式
     *
     * @param source  需要加密的内容
     * @param key     密钥
     * @param iv      偏移量
     * @param charset key 和 iv的编码方式
     * @return base64编码的字符串
     */
    public static String aesEncrypt(String source, String key, String iv, Charset charset) {
        /** source 必须长度为16位 */
        byte[] sourceBytes = source.getBytes();
        int length = sourceBytes.length;
        /** blockSize 在 AES/CBC/NoPadding 默认为 16, length 必须为16的倍数 */
        if (length % BLOCK_SIZE != 0) {
            length = length + (BLOCK_SIZE - (length % BLOCK_SIZE));
        }
        byte[] copyBytes = new byte[length];
        System.arraycopy(sourceBytes, 0, copyBytes, 0, sourceBytes.length);
        return aes(key, iv, charset).encryptBase64(copyBytes);
    }

    /**
     * AES 解密
     *
     * @param source  需要解密的内容，必须是Base64编码的字符串
     * @param key     密钥
     * @param iv      偏移量
     * @param charset key 和 iv的编码方式
     * @return base64编码的字符串
     */
    public static String aesDecrypt(String source, String key, String iv, Charset charset) {
        byte[] decrypt = aes(key, iv, charset).decryptFromBase64(source);
        return new String(decrypt).trim();
    }


    /**
     * AES 解密
     *
     * @param source 需要解密的内容，必须是Base64编码的字符串
     * @param key    密钥
     * @param iv     偏移量
     * @return base64编码的字符串
     */
    public static String aesDecryptKLatin1(String source, String key, String iv) {
        return aesDecrypt(source, key, iv, CharsetUtil.CHARSET_ISO_8859_1);
    }


    /**
     * Aes加密 NoPadding自定义的 ZeroPadding模式
     * key 和 iv通过UTF-8编码
     *
     * @param source 需要加密的内容
     * @param key    密钥
     * @param iv     偏移量
     * @return string
     */
    public static String aesEncryptKUtf(String source, String key, String iv) {
        return aesEncrypt(source, key, iv, Charset.defaultCharset());
    }

    /**
     * Aes加密 NoPadding自定义的 ZeroPadding模式
     * key 和 iv通过Latin1编码
     *
     * @param source 需要加密的内容
     * @param key    密钥
     * @param iv     偏移量
     * @return string
     */
    public static String aesEncryptKLatin1(String source, String key, String iv) {
        return aesEncrypt(source, key, iv, CharsetUtil.CHARSET_ISO_8859_1);
    }

    /**
     * Aes加密 NoPadding自定义的 ZeroPadding模式
     * key 和 iv不编码
     *
     * @param source 需要加密的内容
     * @param key    密钥
     * @param iv     偏移量
     * @return string
     */
    public static String aesCBCEncrypt(String source, String key, String iv) {
        return aesEncrypt(source, key, iv, null);
    }


    /**
     * MD5加密 ，获得加密后的16进制形式口令
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String md5Encrypt(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithm：{}", e);
            return null;
        }
        try {
            md.update(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("NoSuchAlgorithm：{}", e);
            return null;
        }
        byte[] digest = md.digest();
        return byteToHexString(digest);
    }


    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    private static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

}
