package com.xsg.decryptor.godzilla.v3.core;

import com.xsg.decryptor.godzilla.v3.core.base.AbstractRawDecryptor;
import com.xsg.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class AspXorRawDecryptor extends AbstractRawDecryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return DecodeUtil.xorDecode(encryptedData, key.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.xorDecode(encryptedData, key.getBytes(StandardCharsets.UTF_8));
    }
}