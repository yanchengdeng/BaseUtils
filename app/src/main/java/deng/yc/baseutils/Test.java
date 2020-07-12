package deng.yc.baseutils;


import com.blankj.utilcode.util.LogUtils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
 

public class Test {
 

    //公钥加密
    public static String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQiFQ68jaoj9gWgo8JN9bFVaiA\n" +
            "jpfEJ/mCalMpnRYcqwNM0JNpqLpCAY4uoFd2cP8c3yqZbh8e3+nkDHOGnndeaGO/\n" +
            "284uIuLlwNxnYT+/EJdcaB9z8UR2tLSzeH4EXce3VfT8h2GzcoVj1OdT4kPvr9VG\n" +
            "VgpVhgW4YUriyxZWxQIDAQAB";
    //私钥解密
    public  static String privateKeyString = "MIICXQIBAAKBgQDQiFQ68jaoj9gWgo8JN9bFVaiAjpfEJ/mCalMpnRYcqwNM0JNp\n" +
            "qLpCAY4uoFd2cP8c3yqZbh8e3+nkDHOGnndeaGO/284uIuLlwNxnYT+/EJdcaB9z\n" +
            "8UR2tLSzeH4EXce3VfT8h2GzcoVj1OdT4kPvr9VGVgpVhgW4YUriyxZWxQIDAQAB\n" +
            "AoGAIVsdNgyWZ6ISq48YuB3BcfFAscedSRgn1g+R298vsUg9j+TxH36IxJQhHR4y\n" +
            "v1RVylV8J+ywd6zTadIADLF+YEaWc+mmo+wfE13F9aZj/enxb6nuUWJqPsg6Rqby\n" +
            "In5gp9SomNZaYkxTQTCWPd/MkMugZC5YTZe5s1CwTPBkjoECQQDvKgxX1DuP6vgv\n" +
            "WPz7rO3yD+tE2iUtDiFRQKd8o32pes9BzDTgRv3uN8zoub3JiQvRSKvDFWJFqoQ0\n" +
            "mkYujlDhAkEA3zZFKCRNmFdNcmsIm8u1jNPvv8H3cf9bs6xWx0HQatDFqaBtnZlv\n" +
            "fGbaJ/Lqwu4cGuNAnYsjQunLR3CQ32guZQJBAIiqlIcT5j1lXhFgXqBKv2YVprGf\n" +
            "nqLSckOGGK9mlYZlgU3uLUEEEFMyW8uZaFRkFfav+kbuT0vUFtwgVH6CIMECQQDR\n" +
            "2qUUQ2VMd6/Rhb23M8NBXrRF9aedXrYpazq+5Sp8ckGT48eK5wmAzPYHnwOGNvTn\n" +
            "doZ2V6zUKRg71yHtWHZdAkByvOFMw9Oq3jKyXS0sFz07XVsuQ+sGpVVwLl1A5n45\n" +
            "GNu5kD/3UE1EJrFXUEVVWPuvGKR2rCMYRRRAqliQgcyh";
 
//    public static void main(String[] args) throws Exception {
//        // TODO Auto-generated method stub
//
//        // 用于封装随机产生的公钥与私钥
//        { // 生成公钥和私钥
//            genKeyPair();
//            // 加密字符串
//            String message = "df723820";
//            System.out.println("随机生成的公钥为:" +publicKey);
//            System.out.println("随机生成的私钥为:" + privateKey);
//            String messageEn = encrypt(message,publicKey);
//            System.out.println(message + "\t加密后的字符串为:" + messageEn);
//            String messageDe = decrypt(messageEn, privateKey);
//            System.out.println("还原后的字符串为:" + messageDe);
//        }
//    }

    /**
     * 生成 公钥  私钥方法
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException { // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 得到公钥
        publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到
        LogUtils.w("dyc","公钥："+publicKeyString);
        LogUtils.w("dyc","私钥："+privateKeyString);
//        keyMap.put(0, publicKeyString);
        // 0表示公钥
//        keyMap.put(1, privateKeyString);
        // 1表示私钥
    }
 
    public static String encrypt(String str, String publicKey) throws Exception {
        // base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA", new BouncyCastleProvider())
                .generatePublic(new X509EncodedKeySpec(decoded));
        // RSA加密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }
 
    public static String decrypt(String str, String privateKey) throws Exception {
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        // base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA", new BouncyCastleProvider())
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // RSA解密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    public static String base64Encode(String str){
      return   new String(Base64.encodeBase64(str.getBytes()));
    }

    public static String base64decode(String str){
        return   new String(Base64.decodeBase64(str.getBytes()));
    }
 
}