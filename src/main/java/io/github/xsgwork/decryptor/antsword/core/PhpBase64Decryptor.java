package io.github.xsgwork.decryptor.antsword.core;

import io.github.xsgwork.decryptor.antsword.core.base.AbstractSplitCodeDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

public class PhpBase64Decryptor extends AbstractSplitCodeDecryptor {

    @Override
    protected String doCodeDecrypt(String encryptedData) {
        return DecodeUtil.base64DecodeString(encryptedData);
    }

}
