package io.github.xsgwork.decryptor.godzilla.v2.core;

import cn.hutool.core.util.StrUtil;
import io.github.xsgwork.decryptor.godzilla.v2.core.base.AbstractBase64Decryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.StringExtractUtil;

import java.nio.charset.StandardCharsets;

public class PhpEvalXorBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    public String decryptRequest(String encryptedData, String password, String key) {
        try {
            String encodedData = extractRequestData(encryptedData);
            // url解码
            String urlDecodedData = DecodeUtil.urlDecode(encodedData);
            // 提取关键信息
            String extractedData = StringExtractUtil.extractContent(urlDecodedData, "urldecode('", "'))))");
            if (StrUtil.isBlank(extractedData)) {
                throw new IllegalArgumentException("数据格式错误，未提取到关键信息");
            }
            // 反转字符串
            String reversedData = StrUtil.reverse(extractedData);
            // base64解码
            return DecodeUtil.base64DecodeString(reversedData);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return encryptedData;
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.xorDecode(encryptedData, key.getBytes(StandardCharsets.UTF_8));
    }
}