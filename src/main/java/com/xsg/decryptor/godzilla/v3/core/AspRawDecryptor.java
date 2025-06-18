package com.xsg.decryptor.godzilla.v3.core;

import com.xsg.decryptor.godzilla.v3.core.base.AbstractRawDecryptor;

public class AspRawDecryptor extends AbstractRawDecryptor {

    @Override
    public String decryptResponse(String responseData, String password, String key) {
        // 响应直接就是明文
        return responseData;
    }

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return encryptedData;
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return encryptedData;
    }
}