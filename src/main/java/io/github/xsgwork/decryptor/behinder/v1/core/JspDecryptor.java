package io.github.xsgwork.decryptor.behinder.v1.core;

import io.github.xsgwork.decryptor.behinder.v1.core.base.BehinderV1Decryptor;
import io.github.xsgwork.decryptor.util.BehinderResultUtil;
import io.github.xsgwork.decryptor.util.ByteTypeUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.JavaDecompileUtil;

import java.nio.charset.StandardCharsets;

public class JspDecryptor implements BehinderV1Decryptor {

    @Override
    public String decrypt(String encryptedData, String key) {
        try {
            // Base64解码
            byte[] base64DecodedData = DecodeUtil.base64Decode(encryptedData);
            // aes ecb解密
            byte[] decryptedBytes = DecodeUtil.aesEcbDecrypt(base64DecodedData, key);
            // Java字节码处理
            if (ByteTypeUtil.isJavaClassFile(decryptedBytes)) {
                return JavaDecompileUtil.decompileBytes(decryptedBytes);
            }
            String result = new String(decryptedBytes, StandardCharsets.UTF_8);
            return BehinderResultUtil.parseNestedJsonResponse(result);
        } catch (Exception e) {
            throw new RuntimeException("请求包解密失败", e);
        }
    }
}
