package io.github.xsgwork.decryptor.godzilla.v1.core;

import io.github.xsgwork.decryptor.godzilla.v1.core.base.AbstractRawDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

public class CShapAesRawDecryptor extends AbstractRawDecryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        return DecodeUtil.aesCbcDecrypt(encryptedData, key, key);
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.aesCbcDecrypt(encryptedData, key, key);
    }
}
