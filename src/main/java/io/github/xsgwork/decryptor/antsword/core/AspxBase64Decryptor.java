package io.github.xsgwork.decryptor.antsword.core;

import cn.hutool.core.util.StrUtil;
import io.github.xsgwork.decryptor.antsword.core.base.AbstractSplitCodeDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.StringExtractUtil;

public class AspxBase64Decryptor extends AbstractSplitCodeDecryptor {

    @Override
    protected String doCodeDecrypt(String encryptedData) {
        String base64DecodedData = DecodeUtil.base64DecodeString(encryptedData);
        // 尝试提取bd函数中的内容
        String extractedData = StringExtractUtil.extractContent(base64DecodedData, "System.Convert.FromBase64String(\"", "\")),\"unsafe\");");
        if (StrUtil.isBlank(extractedData)) {
            return base64DecodedData;
        }
        return DecodeUtil.base64DecodeString(extractedData);
    }
}
