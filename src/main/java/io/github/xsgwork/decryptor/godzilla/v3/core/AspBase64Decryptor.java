package io.github.xsgwork.decryptor.godzilla.v3.core;

import io.github.xsgwork.decryptor.godzilla.v3.core.base.AbstractAspBase64Decryptor;

public class AspBase64Decryptor extends AbstractAspBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return encryptedData;
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return encryptedData;
    }
}