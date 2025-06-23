package io.github.xsgwork.decryptor.godzilla.v3.core;

import io.github.xsgwork.decryptor.godzilla.v3.core.base.AbstractAspBase64Decryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class AspXorBase64Decryptor extends AbstractAspBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return DecodeUtil.xorDecode(encryptedData, key.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.xorDecode(encryptedData, key.getBytes(StandardCharsets.UTF_8));
    }
}