package com.xsg.decryptor.godzilla.v1.core;

import com.xsg.decryptor.godzilla.v1.core.base.AbstractBase64Decryptor;
import com.xsg.decryptor.util.ByteTypeUtil;
import com.xsg.decryptor.util.DecodeUtil;
import com.xsg.decryptor.util.JavaDecompileUtil;

import java.nio.charset.StandardCharsets;

public class JavaAesBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        byte[] decryptedBytes = DecodeUtil.aesDecrypt(encryptedData, key);
        // Java字节码处理
        if (ByteTypeUtil.isJavaClassFile(decryptedBytes)) {
            return JavaDecompileUtil.decompileBytes(decryptedBytes).getBytes(StandardCharsets.UTF_8);
        }
        return decryptedBytes;
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.aesDecrypt(encryptedData, key);
    }
}