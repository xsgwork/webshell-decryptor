package io.github.xsgwork.decryptor.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

/**
 * 解码和解密工具类
 *
 * 提供各种常用的解码和解密功能，包括：
 * - URL解码
 * - Base64解码
 * - AES解密（ECB和CBC模式）
 * - XOR解密
 * - 十六进制解码
 * - 数据反序列化
 *
 * @author xsg
 * @version 1.0.0
 */
public class DecodeUtil {

    /**
     * URL解码
     *
     * 使用UTF-8字符集对URL编码的字符串进行解码
     *
     * @param data 需要解码的URL编码字符串
     * @return 解码后的字符串
     */
    public static String urlDecode(String data) {
        return URLUtil.decode(data, StandardCharsets.UTF_8);
    }

    /**
     * Base64解码
     *
     * 将Base64编码的字符串解码为字节数组
     *
     * @param data Base64编码的字符串
     * @return 解码后的字节数组
     */
    public static byte[] base64Decode(String data) {
        return Base64.decode(data);
    }

    /**
     * AES-ECB模式解密
     *
     * 使用AES算法的ECB模式和PKCS5填充进行解密
     *
     * @param base64Data 需要解密的字节数组
     * @param key        解密密钥
     * @return 解密后的字节数组
     */
    public static byte[] aesEcbDecrypt(byte[] base64Data, String key) {
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8));
        return aes.decrypt(base64Data);
    }

    /**
     * AES-CBC模式解密（C#风格）
     *
     * 使用AES算法的CBC模式和PKCS5填充进行解密，
     * 适用于C#生成的加密数据
     *
     * @param base64Data 需要解密的字节数组
     * @param key        解密密钥
     * @param iv         初始化向量
     * @return 解密后的字节数组
     */
    public static byte[] aesCbcDecrypt(byte[] base64Data, String key, String iv) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));
        return aes.decrypt(base64Data);
    }

    /**
     * XOR解密
     *
     * 使用XOR算法对加密数据进行解密。
     * 密钥循环使用，每16个字节重复一次。
     *
     * @param encryptedData 加密的字节数组
     * @param key           解密密钥字节数组
     * @return 解密后的字节数组
     */
    public static byte[] xorDecode(byte[] encryptedData, byte[] key) {
        int len = encryptedData.length;
        for (int i = 0; i < len; i++) {
            encryptedData[i] = (byte) (encryptedData[i] ^ key[(i + 1) & 15]);
        }
        return encryptedData;
    }
}
