package com.xsg.decryptor.godzilla.core;

import com.xsg.decryptor.godzilla.core.base.AbstractRawDecryptor;
import com.xsg.decryptor.util.DecodeUtil;

public class JavaAesRawDecryptor extends AbstractRawDecryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return DecodeUtil.aesDecrypt(encryptedData, key);
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.aesDecrypt(encryptedData, key);
    }
}