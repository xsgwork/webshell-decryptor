package io.github.xsgwork.decryptor.behinder.v2.core;

import cn.hutool.core.util.HexUtil;
import io.github.xsgwork.decryptor.behinder.v2.core.base.BaseDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

public class AesDecryptor extends BaseDecryptor {

    @Override
    protected byte[] doDecrypt(String data, String key) {
        // 先判断数据是否为16进制
        if (HexUtil.isHexNumber(data)) {
            data = HexUtil.decodeHexStr(data);
        }
        // Base64解码
        byte[] base64DecodedData = DecodeUtil.base64Decode(data);
        // Aes解密
        return DecodeUtil.aesEcbDecrypt(base64DecodedData, key);
    }
}
