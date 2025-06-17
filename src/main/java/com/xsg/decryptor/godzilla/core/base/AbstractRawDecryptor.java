package com.xsg.decryptor.godzilla.core.base;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ZipUtil;
import com.xsg.decryptor.util.ByteTypeUtil;
import com.xsg.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

/**
 * 原始数据解密器抽象基类
 * 
 * 该类为处理十六进制编码的Webshell流量提供通用的解密框架。
 * 与Base64解密器不同，原始数据解密器直接处理十六进制编码的数据，
 * 适用于某些特定类型的Webshell通信协议。
 * 
 * 解密流程：
 * 1. 十六进制解码：将十六进制字符串转换为字节数组
 * 2. 具体解密算法：由子类实现的特定解密逻辑
 * 3. GZIP解压缩：如果解密后的数据是GZIP格式，进行解压缩
 * 4. 数据处理：请求数据进行反序列化，响应数据转换为字符串
 * 
 * @author xsg
 * @version 1.0.0
 */
public abstract class AbstractRawDecryptor implements Decryptor {

    /**
     * 解密请求数据的模板方法
     * 
     * 该方法定义了原始数据请求解密的标准流程：
     * 1. 将十六进制字符串解码为字节数组
     * 2. 调用子类实现的具体解密算法
     * 3. 检查解密结果是否为GZIP格式，如果是则进行解压缩
     * 4. 对最终数据进行反序列化处理
     * 
     * @param encryptedData 十六进制编码的加密数据
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密并反序列化后的字符串数据
     * @throws RuntimeException 当解密过程中发生任何异常时抛出
     */
    @Override
    public String decryptRequest(String encryptedData, String password, String key) {
        try {
            // 十六进制解码：将十六进制字符串转换为字节数组
            byte[] decodeHexBytes = HexUtil.decodeHex(encryptedData);
            // 具体解密过程：调用子类实现的解密算法
            byte[] decryptedBytes = doDecryptRequest(decodeHexBytes, password, key);
            // GZIP解压缩：检查并处理压缩数据
            if (ByteTypeUtil.isGzipFormat(decryptedBytes)) {
                byte[] decompressedBytes = ZipUtil.unGzip(decryptedBytes);
                return DecodeUtil.deserialize(decompressedBytes);
            }
            return DecodeUtil.deserialize(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }

    /**
     * 解密响应数据的模板方法
     * 
     * 该方法定义了原始数据响应解密的标准流程：
     * 1. 将十六进制字符串解码为字节数组
     * 2. 调用子类实现的具体解密算法
     * 3. 检查解密结果是否为GZIP格式，如果是则进行解压缩
     * 4. 将最终数据转换为UTF-8字符串
     * 
     * @param responseData 十六进制编码的加密响应数据
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密后的响应字符串
     * @throws RuntimeException 当解密过程中发生任何异常时抛出
     */
    @Override
    public String decryptResponse(String responseData, String password, String key) {
        try {
            // 十六进制解码：将十六进制字符串转换为字节数组
            byte[] decodeHexBytes = HexUtil.decodeHex(responseData);
            // 具体解密过程：调用子类实现的解密算法
            byte[] decryptedBytes = doDecryptResponse(decodeHexBytes, password, key);
            // GZIP解压缩：检查并处理压缩数据
            if (ByteTypeUtil.isGzipFormat(decryptedBytes)) {
                byte[] decompressedBytes = ZipUtil.unGzip(decryptedBytes);
                return new String(decompressedBytes, StandardCharsets.UTF_8);
            }
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("响应包解密失败", e);
        }
    }

    /**
     * 执行请求数据的具体解密算法
     * 
     * 子类必须实现此方法来提供特定的解密逻辑。
     * 该方法接收已经进行十六进制解码的字节数组，
     * 并返回解密后的原始字节数据。
     * 
     * @param encryptedData 已解码的加密字节数组
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密后的字节数组
     */
    protected abstract byte[] doDecryptRequest(byte[] encryptedData, String password, String key);

    /**
     * 执行响应数据的具体解密算法
     * 
     * 子类必须实现此方法来提供特定的解密逻辑。
     * 该方法接收已经进行十六进制解码的字节数组，
     * 并返回解密后的原始字节数据。
     * 
     * @param encryptedData 已解码的加密字节数组
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密后的字节数组
     */
    protected abstract byte[] doDecryptResponse(byte[] encryptedData, String password, String key);
}
