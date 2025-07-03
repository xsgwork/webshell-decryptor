package io.github.xsgwork.decryptor.behinder.v2.core;

import cn.hutool.core.util.HexUtil;
import io.github.xsgwork.decryptor.behinder.v2.core.base.BaseDecryptor;

import java.io.ByteArrayOutputStream;

public class ImageDecryptor extends BaseDecryptor {

    @Override
    protected byte[] doDecrypt(String data, String key) {
        // 十六进制解码：将十六进制字符串转换为字节数组
        byte[] decodeHexBytes = HexUtil.decodeHex(data);
        // 图片解密，从数据的第966字节开始提取到末尾
        return imageDecrypt(decodeHexBytes);
    }

    private byte[] imageDecrypt(byte[] data) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(data, 966, data.length - 966);
        return bos.toByteArray();
    }
}
