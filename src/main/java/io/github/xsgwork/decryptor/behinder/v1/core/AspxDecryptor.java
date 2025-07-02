package io.github.xsgwork.decryptor.behinder.v1.core;

import cn.hutool.core.util.HexUtil;
import io.github.xsgwork.decryptor.behinder.v1.core.base.BehinderV1Decryptor;
import io.github.xsgwork.decryptor.util.BehinderResultUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class AspxDecryptor implements BehinderV1Decryptor {

    @Override
    public String decrypt(String encryptedData, String key) {
        try {
            // 十六进制解码：将十六进制字符串转换为字节数组
            byte[] decodeHexBytes = HexUtil.decodeHex(encryptedData);
            // aes cbc解密，iv与key相同
            byte[] decryptedBytes = DecodeUtil.aesCbcDecrypt(decodeHexBytes, key, key);
            String result = new String(decryptedBytes, StandardCharsets.UTF_8);
            return BehinderResultUtil.parseNestedJsonResponse(result);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }
}
