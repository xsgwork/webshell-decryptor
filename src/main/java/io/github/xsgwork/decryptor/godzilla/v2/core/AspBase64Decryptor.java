package io.github.xsgwork.decryptor.godzilla.v2.core;

import io.github.xsgwork.decryptor.godzilla.v2.core.base.AbstractAspBase64Decryptor;

public class AspBase64Decryptor extends AbstractAspBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] data, String key) {
        return data;
    }

    @Override
    protected byte[] doDecryptResponse(byte[] data, String key) {
        return data;
    }
}