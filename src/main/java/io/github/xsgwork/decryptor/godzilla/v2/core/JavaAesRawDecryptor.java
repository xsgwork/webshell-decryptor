package io.github.xsgwork.decryptor.godzilla.v2.core;

import io.github.xsgwork.decryptor.godzilla.v2.core.base.AbstractRawDecryptor;
import io.github.xsgwork.decryptor.util.ByteTypeUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.JavaDecompileUtil;

import java.nio.charset.StandardCharsets;

public class JavaAesRawDecryptor extends AbstractRawDecryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        byte[] decryptedBytes = DecodeUtil.aesEcbDecrypt(encryptedData, key);
        // Java字节码处理
        if (ByteTypeUtil.isJavaClassFile(decryptedBytes)) {
            return JavaDecompileUtil.decompileBytes(decryptedBytes).getBytes(StandardCharsets.UTF_8);
        }
        return decryptedBytes;
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.aesEcbDecrypt(encryptedData, key);
    }
}