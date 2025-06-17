package com.xsg.decryptor.godzilla.core.base;

import cn.hutool.core.util.ZipUtil;
import com.xsg.decryptor.util.ByteTypeUtil;
import com.xsg.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

/**
 * Base64编码类型的Webshell解密器抽象基类
 * 
 * 该抽象类实现了Base64编码类型Webshell的通用解密流程：
 * 1. 提取加密数据
 * 2. URL解码
 * 3. Base64解码
 * 4. 执行具体的解密算法
 * 5. GZIP解压缩（如果需要）
 * 6. 数据反序列化
 * 
 * 子类只需要实现具体的数据提取和解密算法即可。
 * 
 * @author xsg
 * @version 1.0.0
 */
public abstract class AbstractBase64Decryptor implements Decryptor {

    /**
     * 解密请求包数据的模板方法
     * 
     * 执行标准的Base64类型请求包解密流程：
     * 1. 提取请求数据
     * 2. URL解码
     * 3. Base64解码
     * 4. 执行具体解密算法
     * 5. GZIP解压缩（如果数据是GZIP格式）
     * 6. 数据反序列化
     * 
     * @param encryptedData 加密的请求数据
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密后的明文数据
     * @throws RuntimeException 当解密过程中发生错误时抛出
     */
    @Override
    public String decryptRequest(String encryptedData, String password, String key) {
        try {
            // 提取请求数据
            String encodedData = extractRequestData(encryptedData);
            // URL解码
            String urlDecodedData = DecodeUtil.urlDecode(encodedData);
            // Base64解码
            byte[] base64DecodedData = DecodeUtil.base64Decode(urlDecodedData);
            // 执行具体的解密算法
            byte[] decryptedBytes = doDecryptRequest(base64DecodedData, password, key);
            // 检查是否为GZIP格式并进行解压缩
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
     * 从请求数据中提取加密内容
     * 
     * 默认实现：查找等号位置并提取后面的内容
     * 子类可以重写此方法以适应不同的数据格式
     * 
     * @param data 原始请求数据
     * @return 提取出的加密数据
     * @throws IllegalArgumentException 当数据格式不正确时抛出
     */
    protected String extractRequestData(String data) {
        // 查找等号位置并提取后面的内容
        int equalIndex = data.indexOf('=');
        if (equalIndex == -1) {
            throw new IllegalArgumentException("数据格式错误，未找到等号分隔符");
        }
        return data.substring(equalIndex + 1);
    }

    /**
     * 解密响应包数据的模板方法
     * 
     * 执行标准的Base64类型响应包解密流程：
     * 1. 提取响应数据
     * 2. Base64解码
     * 3. 执行具体解密算法
     * 4. GZIP解压缩（如果数据是GZIP格式）
     * 5. 转换为字符串
     * 
     * @param responseData 加密的响应数据
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密后的明文数据
     * @throws RuntimeException 当解密过程中发生错误时抛出
     */
    @Override
    public String decryptResponse(String responseData, String password, String key) {
        try {
            // 提取响应数据
            String encryptedData = extractResponseData(responseData);
            // Base64解码
            byte[] base64DecodedData = DecodeUtil.base64Decode(encryptedData);
            // 执行具体的解密算法
            byte[] decryptedBytes = doDecryptResponse(base64DecodedData, password, key);
            // 检查是否为GZIP格式并进行解压缩
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
     * 从响应数据中提取加密内容
     * 
     * 默认实现：去掉前16位和后16位内容
     * 子类可以重写此方法以适应不同的数据格式
     * 
     * @param data 原始响应数据
     * @return 提取出的加密数据
     */
    protected String extractResponseData(String data) {
        // 去掉前16位和后16位内容
        return data.substring(16, data.length() - 16);
    }

    /**
     * 执行请求包的具体解密算法
     * 
     * 子类必须实现此方法以提供具体的解密逻辑
     * 
     * @param encryptedData 经过Base64解码后的加密数据
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密后的字节数组
     */
    protected abstract byte[] doDecryptRequest(byte[] encryptedData, String password, String key);

    /**
     * 执行响应包的具体解密算法
     * 
     * 子类必须实现此方法以提供具体的解密逻辑
     * 
     * @param encryptedData 经过Base64解码后的加密数据
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密后的字节数组
     */
    protected abstract byte[] doDecryptResponse(byte[] encryptedData, String password, String key);
}
