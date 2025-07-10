package io.github.xsgwork.decryptor.antsword.core;

import io.github.xsgwork.decryptor.antsword.core.base.AbstractNormalParamDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.JavaDecompileUtil;

import java.util.HashMap;
import java.util.Map;

public class JspDecryptor extends AbstractNormalParamDecryptor {

    @Override
    protected Map<String, String> convertPasswordParam(Map<String, String> paramMap, String password) {
        Map<String, String> result = new HashMap<>();
        String paramValue = paramMap.remove(password);
        // BASE64解码
        byte[] base64DecodedBytes = DecodeUtil.base64Decode(paramValue);
        // Java字节码处理
        String value = JavaDecompileUtil.decompileBytes(base64DecodedBytes);
        result.put(password, value);
        return result;
    }

}
