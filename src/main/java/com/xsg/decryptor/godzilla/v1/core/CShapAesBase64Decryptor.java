package com.xsg.decryptor.godzilla.v1.core;

import com.xsg.decryptor.godzilla.v1.core.base.AbstractBase64Decryptor;
import com.xsg.decryptor.util.DecodeUtil;

public class CShapAesBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return DecodeUtil.cshapAesDecrypt(encryptedData, key, key);
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.cshapAesDecrypt(encryptedData, key, key);
    }
}
