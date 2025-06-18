package com.xsg.decryptor.godzilla.v1.core;

import com.xsg.decryptor.godzilla.v1.core.base.AbstractRawDecryptor;
import com.xsg.decryptor.util.DecodeUtil;

public class CShapAesRawDecryptor extends AbstractRawDecryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return DecodeUtil.cshapAesDecrypt(encryptedData, key, key);
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.cshapAesDecrypt(encryptedData, key, key);
    }
}
