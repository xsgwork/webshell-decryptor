package io.github.xsgwork.decryptor.antsword.core.base;

import io.github.xsgwork.decryptor.util.DecodeUtil;

public abstract class AbstractNormalParamDecryptor extends AbstractDecryptor {

    @Override
    protected boolean isSubstringNormalParams() {
        return true;
    }

    @Override
    protected String doNormalDecrypt(String encryptedData) {
        // URL解码
        String urlDecodedValue = DecodeUtil.urlDecode(encryptedData);
        // base64解码
        return DecodeUtil.base64DecodeString(urlDecodedValue);
    }
}
