package com.xsg.decryptor.godzilla.v3.core.base;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ZipUtil;
import com.xsg.decryptor.util.ByteTypeUtil;
import com.xsg.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public abstract class AbstractRawDecryptor implements GodzillaV3Decryptor {

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
                return DecodeUtil.godzillaV3Deserialize(decompressedBytes);
            }
            return DecodeUtil.godzillaV3Deserialize(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }

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

    protected abstract byte[] doDecryptRequest(byte[] encryptedData, String password, String key);
    
    protected abstract byte[] doDecryptResponse(byte[] encryptedData, String password, String key);
}
