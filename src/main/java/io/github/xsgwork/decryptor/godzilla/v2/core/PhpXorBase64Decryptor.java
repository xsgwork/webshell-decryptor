package io.github.xsgwork.decryptor.godzilla.v2.core;

import io.github.xsgwork.decryptor.godzilla.v2.core.base.AbstractBase64Decryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.nio.charset.StandardCharsets;

public class PhpXorBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] data, String key) {
        return DecodeUtil.xorDecode(data, key.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected byte[] doDecryptResponse(byte[] data, String key) {
        return DecodeUtil.xorDecode(data, key.getBytes(StandardCharsets.UTF_8));
    }
}