package com.ruoyi.common.utils.security;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.*;

public class RSAUtils {

    private static final Logger log = LoggerFactory.getLogger(RSAUtils.class);
    private static KeyPair keyPair = null;

    // 生成秘钥对
    static {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048, new SecureRandom());
            keyPair = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            log.error("No such algorithm.");
        }
    }

    /**
     * 获取公钥
     *
     * @return 公钥内容
     */
    public static String generateBase64PublicKey() {
        PublicKey publicKey = keyPair.getPublic();
        return new String(Base64.encodeBase64(publicKey.getEncoded()));
    }

    /**
     * 传入字符串用私钥进行解密
     *
     * @param string string
     * @return 解密后的字符串
     */
    public static String decryptBase64(String string) {
        return new String(decrypt(Base64.decodeBase64(string.getBytes())));
    }

    private static byte[] decrypt(byte[] byteArray) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            PrivateKey privateKey = keyPair.getPrivate();
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(byteArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

