package io.github.xsgwork.decryptor.antsword.core;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import io.github.xsgwork.decryptor.antsword.core.base.AbstractSplitCodeDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.StringExtractUtil;

public class AspxHexDecryptor extends AbstractSplitCodeDecryptor {

    @Override
    protected String doCodeDecrypt(String encryptedData) {
        String hexDecodedData = HexUtil.decodeHexStr(encryptedData);
        // 尝试提取bd函数中的内容
        String extractedData = StringExtractUtil.extractContent(hexDecodedData, "System.Convert.FromBase64String(\"", "\")),\"unsafe\");");
        if (StrUtil.isBlank(extractedData)) {
            return hexDecodedData;
        }
        return DecodeUtil.base64DecodeString(extractedData);
    }
}
