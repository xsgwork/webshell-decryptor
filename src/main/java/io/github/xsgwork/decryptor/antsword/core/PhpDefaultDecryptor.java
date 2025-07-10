package io.github.xsgwork.decryptor.antsword.core;

import io.github.xsgwork.decryptor.antsword.core.base.AbstractNormalParamDecryptor;

import java.util.HashMap;
import java.util.Map;

public class PhpDefaultDecryptor extends AbstractNormalParamDecryptor {

    @Override
    protected Map<String, String> convertPasswordParam(Map<String, String> paramMap, String password) {
        Map<String, String> result = new HashMap<>();
        String paramValue = paramMap.remove(password);
        result.put(password, paramValue);
        return result;
    }

}
