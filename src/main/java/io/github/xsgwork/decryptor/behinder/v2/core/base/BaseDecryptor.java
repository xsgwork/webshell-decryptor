package io.github.xsgwork.decryptor.behinder.v2.core.base;

import io.github.xsgwork.decryptor.util.BehinderResultUtil;
import io.github.xsgwork.decryptor.util.ByteTypeUtil;
import io.github.xsgwork.decryptor.util.JavaDecompileUtil;

import java.nio.charset.StandardCharsets;

public abstract class BaseDecryptor implements BehinderV2Decryptor {

    @Override
    public String decrypt(String encryptedData, String key) {
        try {
            byte[] decryptedBytes = doDecrypt(encryptedData, key);
            // Java字节码处理
            if (ByteTypeUtil.isJavaClassFile(decryptedBytes)) {
                return JavaDecompileUtil.decompileBytes(decryptedBytes);
            }
            String result = new String(decryptedBytes, StandardCharsets.UTF_8);
            return BehinderResultUtil.parseNestedJsonResponse(result);
        } catch (Exception e) {
            throw new RuntimeException("解密失败", e);
        }
    }

    protected abstract byte[] doDecrypt(String data, String key);
}
