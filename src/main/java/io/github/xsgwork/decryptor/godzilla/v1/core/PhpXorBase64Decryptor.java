package io.github.xsgwork.decryptor.godzilla.v1.core;

import io.github.xsgwork.decryptor.godzilla.v1.core.base.AbstractBase64Decryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class PhpXorBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        String data = DecodeUtil.xorDecodeString(encryptedData, key.getBytes(StandardCharsets.UTF_8));
        return DecodeUtil.base64Decode(data);
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        String data = DecodeUtil.xorDecodeString(encryptedData, key.getBytes(StandardCharsets.UTF_8));
        return DecodeUtil.base64Decode(data);
    }
}