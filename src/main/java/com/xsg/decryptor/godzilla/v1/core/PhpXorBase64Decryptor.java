package com.xsg.decryptor.godzilla.v1.core;

import com.xsg.decryptor.godzilla.v1.core.base.AbstractBase64Decryptor;
import com.xsg.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class PhpXorBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        byte[] data = DecodeUtil.xorDecode(encryptedData, key.getBytes(StandardCharsets.UTF_8));
        return DecodeUtil.base64Decode(new String(data, StandardCharsets.UTF_8));
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        byte[] data = DecodeUtil.xorDecode(encryptedData, key.getBytes(StandardCharsets.UTF_8));
        return DecodeUtil.base64Decode(new String(data, StandardCharsets.UTF_8));
    }
}