package io.github.xsgwork.decryptor.antsword.core.base;

import cn.hutool.core.util.StrUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * pass=eval(System.Text.Encoding.GetEncoding(936).GetString(System.Convert.FromBase64String(Request.Item%5B%22fb25f469c1a6f4%22%5D))%2C%22unsafe%22)%3B
 * 这种请求将具体代码独立成一个参数，例如fb25f469c1a6f4，在具体的eval函数中引用这个fb25f469c1a6f4
 * 需要特殊处理，先查询password参数的值，再根据这个值去找到对应的代码参数
 */
public abstract class AbstractSplitCodeDecryptor extends AbstractNormalParamDecryptor {

    @Override
    protected Map<String, String> convertPasswordParam(Map<String, String> paramMap, String password) {
        Map<String, String> result = new HashMap<>();
        // 去除password参数的值
        String passwordParamValue = paramMap.remove(password);
        result.put(password, DecodeUtil.urlDecode(passwordParamValue));
        // 通过password参数的值去寻找具体代码参数
        String codeParam = paramMap.keySet().stream()
                .filter(item -> passwordParamValue.contains(item))
                .findFirst()
                .orElse("");
        if (StrUtil.isBlank(codeParam)) {
            return result;
        }
        String codeValue = paramMap.remove(codeParam);
        String decryptData = doCodeDecrypt(codeValue);
        result.put(codeParam, decryptData);
        return result;
    }

    protected abstract String doCodeDecrypt(String encryptedData);
}
