package com.xsg.decryptor.godzilla.core;

import com.xsg.decryptor.godzilla.core.base.AbstractRawDecryptor;
import com.xsg.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class PhpXorRawDecryptor extends AbstractRawDecryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return DecodeUtil.xorDecode(encryptedData, key.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.xorDecode(encryptedData, key.getBytes(StandardCharsets.UTF_8));
    }
}