package io.github.xsgwork.decryptor.behinder.v1.core;

import cn.hutool.core.util.HexUtil;
import io.github.xsgwork.decryptor.behinder.v1.core.base.BehinderV1Decryptor;
import io.github.xsgwork.decryptor.util.BehinderResultUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class AspDecryptor implements BehinderV1Decryptor {

    @Override
    public String decrypt(String encryptedData, String key) {
        try {
            // 十六进制解码：将十六进制字符串转换为字节数组
            byte[] decodeHexBytes = HexUtil.decodeHex(encryptedData);
            // Xor解密
            byte[] decryptedBytes = DecodeUtil.xorDecode(decodeHexBytes, key.getBytes(StandardCharsets.UTF_8));
            String result = new String(decryptedBytes, StandardCharsets.UTF_8);
            return BehinderResultUtil.parseNestedJsonResponse(result);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }
}
