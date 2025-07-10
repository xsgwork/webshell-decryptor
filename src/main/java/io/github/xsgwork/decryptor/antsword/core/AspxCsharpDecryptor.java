package io.github.xsgwork.decryptor.antsword.core;

import io.github.xsgwork.decryptor.antsword.core.base.AbstractNormalParamDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.util.HashMap;
import java.util.Map;

public class AspxCsharpDecryptor extends AbstractNormalParamDecryptor {

    @Override
    protected Map<String, String> convertPasswordParam(Map<String, String> paramMap, String password) {
        Map<String, String> result = new HashMap<>();
        String paramValue = paramMap.remove(password);
        // BASE64解码
        String base64DecodedValue = DecodeUtil.base64DecodeString(paramValue);
        result.put(password, base64DecodedValue);
        return result;
    }
}
