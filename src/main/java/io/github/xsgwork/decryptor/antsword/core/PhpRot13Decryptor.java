package io.github.xsgwork.decryptor.antsword.core;

import io.github.xsgwork.decryptor.antsword.core.base.AbstractSplitCodeDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

public class PhpRot13Decryptor extends AbstractSplitCodeDecryptor {

    @Override
    protected String doCodeDecrypt(String encryptedData) {
        return DecodeUtil.rot13(encryptedData);
    }
}
