package io.github.xsgwork.decryptor.behinder.v1.core;

import io.github.xsgwork.decryptor.behinder.v1.core.base.BehinderV1Decryptor;
import io.github.xsgwork.decryptor.util.BehinderResultUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.StringExtractUtil;

import java.nio.charset.StandardCharsets;

public class PhpDecryptor implements BehinderV1Decryptor {

    @Override
    public String decrypt(String encryptedData, String key) {
        try {
            // Base64解码
            byte[] base64DecodedData = DecodeUtil.base64Decode(encryptedData);
            // aes cbc解密，默认的IV值（固定值）为16个0x00
            byte[] decryptedBytes = DecodeUtil.aesCbcDecrypt(base64DecodedData, key, new String(new byte[16]));
            String result = new String(decryptedBytes, StandardCharsets.UTF_8);
            if (result.startsWith("assert|eval(base64_decode('")) {
                String extractedData = StringExtractUtil.extractContent(result, "assert|eval(base64_decode('", "'));");
                return DecodeUtil.base64DecodeString(extractedData);
            }
            return BehinderResultUtil.parseNestedJsonResponse(result);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }
}
