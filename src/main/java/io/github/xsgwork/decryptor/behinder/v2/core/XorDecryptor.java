package io.github.xsgwork.decryptor.behinder.v2.core;

import cn.hutool.core.util.HexUtil;
import io.github.xsgwork.decryptor.behinder.v2.core.base.BaseDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class XorDecryptor extends BaseDecryptor {

    @Override
    protected byte[] doDecrypt(String data, String key) {
        // 十六进制解码：将十六进制字符串转换为字节数组
        byte[] decodeHexBytes = HexUtil.decodeHex(data);
        // Xor解密
        return DecodeUtil.xorDecode(decodeHexBytes, key.getBytes(StandardCharsets.UTF_8));
    }
}
