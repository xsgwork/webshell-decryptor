package io.github.xsgwork.decryptor.godzilla.v1.core.base;

import cn.hutool.core.util.HexUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.GodzillaResultUtil;

import java.nio.charset.StandardCharsets;

public abstract class AbstractBase64Decryptor implements GodzillaV1Decryptor {

    @Override
    public String decryptRequest(String data, String key) {
        try {
            // 提取请求数据
            String requestData = extractRequestData(data);
            // URL解码
            String urlDecodedData = DecodeUtil.urlDecode(requestData);
            // Base64解码
            byte[] base64DecodedData = DecodeUtil.base64Decode(urlDecodedData);
            // 执行具体的解密算法
            byte[] decryptedBytes = doDecryptRequest(base64DecodedData, key);
            // 反序列化
            return GodzillaResultUtil.godzillaV1Deserialize(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }

    protected String extractRequestData(String data) {
        // 先判断数据是否为16进制
        if (HexUtil.isHexNumber(data)) {
            data = HexUtil.decodeHexStr(data);
        }
        // 查找等号位置并提取后面的内容
        int equalIndex = data.indexOf('=');
        if (equalIndex == -1) {
            throw new IllegalArgumentException("数据格式错误，未找到等号分隔符");
        }
        return data.substring(equalIndex + 1);
    }

    @Override
    public String decryptResponse(String data, String key) {
        try {
            // 提取响应数据
            String responseData = extractResponseData(data);
            // Base64解码
            byte[] base64DecodedData = DecodeUtil.base64Decode(responseData);
            // 执行具体的解密算法
            byte[] decryptedBytes = doDecryptResponse(base64DecodedData, key);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("响应包解密失败", e);
        }
    }

    protected String extractResponseData(String data) {
        // 去掉前16位和后16位内容
        return data.substring(16, data.length() - 16);
    }

    protected abstract byte[] doDecryptRequest(byte[] data, String key);

    protected abstract byte[] doDecryptResponse(byte[] data, String key);
}
