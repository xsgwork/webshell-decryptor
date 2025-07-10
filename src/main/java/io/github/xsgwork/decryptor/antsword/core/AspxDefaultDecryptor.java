package io.github.xsgwork.decryptor.antsword.core;

import cn.hutool.core.util.StrUtil;
import io.github.xsgwork.decryptor.antsword.core.base.AbstractNormalParamDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.StringExtractUtil;

import java.util.HashMap;
import java.util.Map;

public class AspxDefaultDecryptor extends AbstractNormalParamDecryptor {

    @Override
    protected Map<String, String> convertPasswordParam(Map<String, String> paramMap, String password) {
        Map<String, String> result = new HashMap<>();
        String paramValue = paramMap.remove(password);
        // 尝试提取bd函数中的内容
        String extractedData = StringExtractUtil.extractContent(paramValue, "System.Convert.FromBase64String(\"", "\")),\"unsafe\");");
        if (StrUtil.isBlank(extractedData)) {
            result.put(password, paramValue);
        } else {
            result.put(password, DecodeUtil.base64DecodeString(extractedData));
        }
        return result;
    }
}
