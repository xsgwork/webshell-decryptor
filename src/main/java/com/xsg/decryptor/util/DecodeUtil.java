package com.xsg.decryptor.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;
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
    public static byte[] aesDecrypt(byte[] base64Data, String key) {
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
    public static byte[] cshapAesDecrypt(byte[] base64Data, String key, String iv) {
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

    /**
     * 十六进制字符串解码
     *
     * 将十六进制字符串解码为普通字符串
     *
     * @param hexStr 十六进制字符串
     * @return 解码后的字符串
     */
    public static String decodeHex(String hexStr) {
        return HexUtil.decodeHexStr(hexStr);
    }

    /**
     * 哥斯拉V3版本数据反序列化
     *
     * 将序列化的字节数组反序列化为可读的字符串格式。
     * 数据格式：key + 分隔符(2) + value长度(4字节) + value内容
     *
     * 解析规则：
     * 1. 读取key直到遇到字节值2（分隔符）
     * 2. 读取4字节的value长度信息
     * 3. 根据长度读取value内容
     * 4. 重复上述过程直到数据结束
     *
     * @param data 需要反序列化的字节数组
     * @return 反序列化后的字符串，格式为"key=value\n"
     */
    public static String godzillaV3Deserialize(byte[] data) {
        try {
            StringBuilder result = new StringBuilder();
            int offset = 0;

            while (offset < data.length) {
                // 读取key：一个字节一个字节读取直到遇到字节值2（分隔符）
                int keyStart = offset;
                while (offset < data.length && data[offset] != 2) {
                    offset++;
                }
                if (offset >= data.length) {
                    // 没有找到分隔符2，结束解析
                    break;
                }

                // 提取key的内容（2之前的内容）
                byte[] keyBytes = new byte[offset - keyStart];
                System.arraycopy(data, keyStart, keyBytes, 0, offset - keyStart);
                String key = new String(keyBytes, StandardCharsets.UTF_8);
                // 跳过分隔符2
                offset++;
                // 检查是否还有足够的字节来读取value长度（4字节）
                if (offset + 4 > data.length) {
                    break;
                }

                // 读取4个字节来计算value的长度（小端字节序）
                int valueLength = (data[offset] & 0xFF) |
                        ((data[offset + 1] & 0xFF) << 8) |
                        ((data[offset + 2] & 0xFF) << 16) |
                        ((data[offset + 3] & 0xFF) << 24);
                offset += 4;
                // 检查是否还有足够的字节来读取value内容
                if (offset + valueLength > data.length) {
                    break;
                }

                // 读取value的内容
                byte[] valueBytes = new byte[valueLength];
                System.arraycopy(data, offset, valueBytes, 0, valueLength);
                String value = new String(valueBytes, StandardCharsets.UTF_8);
                offset += valueLength;
                // 添加到结果中，格式为 key=value
                if (result.length() > 0) {
                    result.append("\n");
                }
                result.append(key).append("=").append(value);
            }
            if (result.length() > 0) {
                return result.toString();
            }
            return new String(data, StandardCharsets.UTF_8);
        } catch (Exception e) {
            // 如果解析失败，返回原始字符串
            return new String(data, StandardCharsets.UTF_8);
        }
    }

    /**
     * 哥斯拉V1版本数据反序列化
     *
     * @param data 需要反序列化的字节数组
     * @return 反序列化后的字符串
     */
    public static String godzillaV1Deserialize(byte[] data) {
        String result = new String(data, StandardCharsets.UTF_8);
        // 检查是否包含methodName=
        if (!result.contains("methodName=")) {
            return result;
        }

        try {
            // 获取methodName=之后的所有内容
            String value = result.replace("methodName=", "");
            byte[] decodedBytes = base64Decode(value);
            String decodedValue = new String(decodedBytes, StandardCharsets.UTF_8);
            return "methodName=" + decodedValue;
        } catch (Exception e) {
            // 如果解码失败，返回原始字符串
            return result;
        }
    }
}
