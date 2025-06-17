package com.xsg.decryptor.godzilla.core;

import cn.hutool.core.util.StrUtil;
import com.xsg.decryptor.godzilla.core.base.AbstractAspBase64Decryptor;
import com.xsg.decryptor.util.DecodeUtil;
import com.xsg.decryptor.util.StringExtractUtil;

public class AspEvalBase64Decryptor extends AbstractAspBase64Decryptor {

    @Override
    public String decryptRequest(String encryptedData, String password, String key) {
        try {
            String encodedData = extractRequestData(encryptedData);
            // url解码
            String urlDecodedData = DecodeUtil.urlDecode(encodedData);
            // 提取关键信息
            String extractedData = StringExtractUtil.extractContent(urlDecodedData, "bd(\"\"\"\"", "\"\"\"\"))");
            if (StrUtil.isBlank(extractedData)) {
                throw new IllegalArgumentException("数据格式错误，未提取到关键信息");
            }
            // 16进制转换成字符串
            return DecodeUtil.decodeHex(extractedData);
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
        return encryptedData;
    }
}