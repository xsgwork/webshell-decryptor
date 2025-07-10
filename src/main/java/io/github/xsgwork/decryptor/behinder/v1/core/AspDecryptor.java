package io.github.xsgwork.decryptor.behinder.v1.core;

import cn.hutool.core.util.HexUtil;
import io.github.xsgwork.decryptor.behinder.v1.core.base.BehinderV1Decryptor;
import io.github.xsgwork.decryptor.util.BehinderResultUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class AspDecryptor implements BehinderV1Decryptor {

    @Override
    public String decrypt(String data, String key) {
        try {
            // 十六进制解码：将十六进制字符串转换为字节数组
            byte[] decodeHexBytes = HexUtil.decodeHex(data);
            // Xor解密
            String result = DecodeUtil.xorDecodeString(decodeHexBytes, key.getBytes(StandardCharsets.UTF_8));
            return BehinderResultUtil.parseNestedJsonResponse(result);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }
}
