package io.github.xsgwork.decryptor.godzilla.v2.core;

import io.github.xsgwork.decryptor.godzilla.v2.core.base.AbstractRawDecryptor;

public class AspRawDecryptor extends AbstractRawDecryptor {

    @Override
    public String decryptResponse(String data, String key) {
        // 响应直接就是明文
        return data;
    }

    @Override
    protected byte[] doDecryptRequest(byte[] data, String key) {
        return data;
    }

    @Override
    protected byte[] doDecryptResponse(byte[] data, String key) {
        return data;
    }
}