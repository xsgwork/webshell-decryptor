package io.github.xsgwork.decryptor.antsword.core.base;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDecryptor implements AntSwordDecryptor {

    @Override
    public String decrypt(String encryptedData) {
        try {
            // 将请求的url参数进行url decode后，转化成map存储
            Map<String, String> paramMap = HttpUtil.decodeParamMap(encryptedData, StandardCharsets.UTF_8);
            String password = getPasswordParamName(paramMap);
            if (StrUtil.isBlank(password)) {
                throw new RuntimeException("未找到连接密码对应的字段");
            }
            Map<String, String> passwordResult = convertPasswordParam(paramMap, password);
            Map<String, String> normalResult = convertNormalParams(paramMap);
            StringBuilder stringBuilder = new StringBuilder();
            passwordResult.forEach((key, value) -> stringBuilder.append(key).append("=").append(value).append("\n"));
            normalResult.forEach((key, value) -> stringBuilder.append(key).append("=").append(value).append("\n"));
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException("解密失败", e);
        }
    }

    /**
     * 判断这么多参数中，哪个是password的参数
     */
    private String getPasswordParamName(Map<String, String> paramMap) {
        String maxKey = "";
        String maxValue = "";
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (entry.getValue().contains("eval") || entry.getValue().contains("cHr") || entry.getValue().contains("ini_set")) {
                return entry.getKey();
            }
            if (entry.getValue().length() > maxValue.length()) {
                maxKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        // 以上规则匹配不上，就找value最长的key
        return maxKey;
    }

    /**
     * 普通字段内容转换
     */
    private Map<String, String> convertNormalParams(Map<String, String> paramMap) {
        Map<String, String> result = new HashMap<>();
        paramMap.forEach((key, value) -> {
            if (isSubstringNormalParams()) {
                value = value.substring(2);
            }
            result.put(key, doNormalDecrypt(value));
        });
        return result;
    }

    /**
     * 是否需要分割普通字段的长度
     */
    protected abstract boolean isSubstringNormalParams();

    /**
     * 普通字段内容的具体转换逻辑
     */
    protected abstract String doNormalDecrypt(String encryptedData);

    /**
     * password字段内容转换
     */
    protected abstract Map<String, String> convertPasswordParam(Map<String, String> paramMap, String password);
}
