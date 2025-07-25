package io.github.xsgwork.decryptor.godzilla.v2.core.base;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ZipUtil;
import io.github.xsgwork.decryptor.util.ByteTypeUtil;
import io.github.xsgwork.decryptor.util.GodzillaResultUtil;

import java.nio.charset.StandardCharsets;

public abstract class AbstractRawDecryptor implements GodzillaV2Decryptor {

    @Override
    public String decryptRequest(String data, String key) {
        try {
            // 十六进制解码：将十六进制字符串转换为字节数组
            byte[] decodeHexBytes = HexUtil.decodeHex(data);
            // 具体解密过程：调用子类实现的解密算法
            byte[] decryptedBytes = doDecryptRequest(decodeHexBytes, key);
            // GZIP解压缩：检查并处理压缩数据
            if (ByteTypeUtil.isGzipFormat(decryptedBytes)) {
                byte[] decompressedBytes = ZipUtil.unGzip(decryptedBytes);
                return GodzillaResultUtil.godzillaV3Deserialize(decompressedBytes);
            }
            return GodzillaResultUtil.godzillaV3Deserialize(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }

    @Override
    public String decryptResponse(String data, String key) {
        try {
            // 十六进制解码：将十六进制字符串转换为字节数组
            byte[] decodeHexBytes = HexUtil.decodeHex(data);
            // 具体解密过程：调用子类实现的解密算法
            byte[] decryptedBytes = doDecryptResponse(decodeHexBytes, key);
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

    protected abstract byte[] doDecryptRequest(byte[] data, String key);
    
    protected abstract byte[] doDecryptResponse(byte[] data, String key);
}
