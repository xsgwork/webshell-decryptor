package com.xsg.decryptor.godzilla.core;

import com.xsg.decryptor.godzilla.core.base.AbstractBase64Decryptor;
import com.xsg.decryptor.util.ByteTypeUtil;
import com.xsg.decryptor.util.DecodeUtil;

public class JavaAesBase64Decryptor extends AbstractBase64Decryptor {

    @Override
    protected byte[] doDecryptRequest(byte[] encryptedData, String password, String key) {
        byte[] decryptedBytes = DecodeUtil.aesDecrypt(encryptedData, key);
        // Java字节码处理
        if (ByteTypeUtil.isJavaClassFile(decryptedBytes)) {
            // todo 输出反编译后的结果
        }
        return decryptedBytes;
    }

    @Override
    protected byte[] doDecryptResponse(byte[] encryptedData, String password, String key) {
        return DecodeUtil.aesDecrypt(encryptedData, key);
    }
}