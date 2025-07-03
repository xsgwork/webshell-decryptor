package io.github.xsgwork.decryptor.behinder.v2.core;

import io.github.xsgwork.decryptor.behinder.v2.core.base.BaseDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class XorBase64Decryptor extends BaseDecryptor {

    @Override
    protected byte[] doDecrypt(String data, String key) {
        // Base64解码
        byte[] base64DecodedData = DecodeUtil.base64Decode(data);
        // Xor解密
        return DecodeUtil.xorDecode(base64DecodedData, key.getBytes(StandardCharsets.UTF_8));
    }
}
