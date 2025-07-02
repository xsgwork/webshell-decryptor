package io.github.xsgwork.decryptor.godzilla.v1.core.base;

import cn.hutool.core.util.HexUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.GodzillaResultUtil;

import java.nio.charset.StandardCharsets;

public abstract class AbstractRawDecryptor implements GodzillaV1Decryptor {

    @Override
    public String decryptRequest(String encryptedData, String password, String key) {
        try {
            // 十六进制解码：将十六进制字符串转换为字节数组
            byte[] decodeHexBytes = HexUtil.decodeHex(encryptedData);
            // 具体解密过程：调用子类实现的解密算法
            byte[] decryptedBytes = doDecryptRequest(decodeHexBytes, password, key);
            return GodzillaResultUtil.godzillaV1Deserialize(decryptedBytes);
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
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("响应包解密失败", e);
        }
    }

    protected abstract byte[] doDecryptRequest(byte[] encryptedData, String password, String key);

    protected abstract byte[] doDecryptResponse(byte[] encryptedData, String password, String key);
}
