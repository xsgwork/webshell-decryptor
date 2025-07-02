package io.github.xsgwork.decryptor.godzilla.v3.core;

import io.github.xsgwork.decryptor.godzilla.v3.core.base.AbstractBase64Decryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

public class CShapAesBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return DecodeUtil.aesCbcDecrypt(encryptedData, key, key);
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.aesCbcDecrypt(encryptedData, key, key);
    }
}
