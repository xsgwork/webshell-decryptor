package io.github.xsgwork.decryptor.godzilla.v3.core.base;

import cn.hutool.core.util.ZipUtil;
import io.github.xsgwork.decryptor.util.ByteTypeUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public abstract class AbstractBase64Decryptor implements GodzillaV3Decryptor {

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
                return DecodeUtil.godzillaV3Deserialize(decompressedBytes);
            }
            return DecodeUtil.godzillaV3Deserialize(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }

    protected String extractRequestData(String data) {
        // 查找等号位置并提取后面的内容
        int equalIndex = data.indexOf('=');
        if (equalIndex == -1) {
            throw new IllegalArgumentException("数据格式错误，未找到等号分隔符");
        }
        return data.substring(equalIndex + 1);
    }

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

    protected String extractResponseData(String data) {
        // 去掉前16位和后16位内容
        return data.substring(16, data.length() - 16);
    }

    protected abstract byte[] doDecryptRequest(byte[] encryptedData, String password, String key);
    
    protected abstract byte[] doDecryptResponse(byte[] encryptedData, String password, String key);
}
