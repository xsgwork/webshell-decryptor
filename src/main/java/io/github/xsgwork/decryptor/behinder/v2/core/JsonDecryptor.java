package io.github.xsgwork.decryptor.behinder.v2.core;

import io.github.xsgwork.decryptor.behinder.v2.core.base.BaseDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class JsonDecryptor extends BaseDecryptor {

    @Override
    protected byte[] doDecrypt(String data, String key) {
        // json解析
        String jsonDecryptedData = jsonDecrypt(data);
        // Base64解码
        return DecodeUtil.base64Decode(jsonDecryptedData);
    }

    private String jsonDecrypt(String data) {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(bytes, 26, bytes.length - 29);
        return bos.toString().replace("<", "+").replace(">", "/");
    }
}
