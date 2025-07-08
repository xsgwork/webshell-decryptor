package io.github.xsgwork.decryptor.behinder.v2.core;

import cn.hutool.core.util.HexUtil;
import io.github.xsgwork.decryptor.behinder.v2.core.base.BaseDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AesWithMagicDecryptor extends BaseDecryptor {

    @Override
    protected byte[] doDecrypt(String data, String key) {
        byte[] decodeHexBytes = HexUtil.decodeHex(data);
        // 处理magic
        int magicNum = Integer.parseInt(key.substring(0, 2), 16) % 16;
        byte[] bytesData = Arrays.copyOfRange(decodeHexBytes, 0, decodeHexBytes.length - magicNum);
        // Base64解码
        byte[] base64DecodedData = DecodeUtil.base64Decode(new String(bytesData, StandardCharsets.UTF_8));
        // Aes解密
        return DecodeUtil.aesEcbDecrypt(base64DecodedData, key);
    }
}
