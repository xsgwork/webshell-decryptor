package io.github.xsgwork.decryptor.godzilla.v2.core;

import cn.hutool.core.util.StrUtil;
import io.github.xsgwork.decryptor.godzilla.v2.core.base.AbstractBase64Decryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.StringExtractUtil;

public class CShapEvalAesBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    public String decryptRequest(String data, String key) {
        try {
            String requestData = extractRequestData(data);
            // url解码
            String urlDecodedData = DecodeUtil.urlDecode(requestData);
            // 提取关键信息
            String extractedData = StringExtractUtil.extractContent(urlDecodedData, "HttpUtility.UrlDecode('", "')))");
            if (StrUtil.isBlank(extractedData)) {
                throw new IllegalArgumentException("数据格式错误，未提取到关键信息");
            }
            // base64解码
            return DecodeUtil.base64DecodeString(extractedData);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }

    @Override
    protected byte[] doDecryptRequest(byte[] data, String key) {
        return data;
    }

    @Override
    protected byte[] doDecryptResponse(byte[] data, String key) {
        return DecodeUtil.aesCbcDecrypt(data, key, key);
    }
}