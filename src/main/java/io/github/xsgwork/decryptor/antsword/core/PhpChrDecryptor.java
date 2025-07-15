package io.github.xsgwork.decryptor.antsword.core;

import io.github.xsgwork.decryptor.antsword.core.base.AbstractNormalParamDecryptor;
import io.github.xsgwork.decryptor.util.ChrUtil;

import java.util.HashMap;
import java.util.Map;

public class PhpChrDecryptor extends AbstractNormalParamDecryptor {

    @Override
    protected Map<String, String> convertPasswordParam(Map<String, String> paramMap, String password) {
        Map<String, String> result = new HashMap<>();
        String paramValue = paramMap.remove(password);
        // 将urlDecodedValue全转化成小写
        paramValue = paramValue.toLowerCase();
        result.put(password, ChrUtil.decrypt(paramValue));
        return result;
    }
}
