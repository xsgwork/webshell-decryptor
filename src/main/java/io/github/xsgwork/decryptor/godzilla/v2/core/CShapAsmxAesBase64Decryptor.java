package io.github.xsgwork.decryptor.godzilla.v2.core;

import cn.hutool.core.util.StrUtil;
import io.github.xsgwork.decryptor.godzilla.v2.core.base.AbstractBase64Decryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.StringExtractUtil;

public class CShapAsmxAesBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    protected String extractRequestData(String data) {
        String extractedData = StringExtractUtil.extractContent(data, "<pass>", "</pass>");
        if (StrUtil.isBlank(extractedData)) {
            throw new IllegalArgumentException("数据格式错误，未提取到关键信息");
        }
        return extractedData;
    }

    @Override
    protected String extractResponseData(String data) {
        String extractedData = StringExtractUtil.extractContent(data, "<passResult>", "</passResult>");
        if (StrUtil.isBlank(extractedData)) {
            throw new IllegalArgumentException("数据格式错误，未提取到关键信息");
        }
        // 去掉前16位和后16位内容
        return extractedData.substring(16, extractedData.length() - 16);
    }

    @Override
    protected byte[] doDecryptRequest(byte[] data, String key) {
        return DecodeUtil.aesCbcDecrypt(data, key, key);
    }

    @Override
    protected byte[] doDecryptResponse(byte[] data, String key) {
        return DecodeUtil.aesCbcDecrypt(data, key, key);
    }
}