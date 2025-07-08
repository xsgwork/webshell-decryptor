package io.github.xsgwork.decryptor.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BehinderResultUtil {

    /**
     * 解析嵌套的JSON响应
     * 递归处理JSON中的所有元素，对base64编码的字符串进行解码
     *
     * @param jsonStr 原始JSON字符串
     * @return 解析后的JSON字符串
     */
    public static String parseNestedJsonResponse(String jsonStr) {
        try {
            if (!JSONUtil.isTypeJSON(jsonStr)) {
                return jsonStr;
            }

            JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
            JSONObject result = decodeJsonObject(jsonObject);
            return result.toString();
        } catch (Exception e) {
            return jsonStr;
        }
    }

    /**
     * 递归解析JSON对象，对所有base64编码的字符串进行解码
     *
     * @param jsonObject JSON对象
     * @return 解析后的JSONObject
     */
    private static JSONObject decodeJsonObject(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof String) {
                String strValue = (String) value;
                String decodedValue = tryBase64Decode(strValue);
                // 如果解码后的内容是JSON，则递归处理
                if (JSONUtil.isTypeJSON(decodedValue)) {
                    try {
                        JSONObject nestedJson = JSONUtil.parseObj(decodedValue);
                        result.set(key, decodeJsonObject(nestedJson));
                    } catch (Exception e) {
                        result.set(key, decodedValue);
                    }
                } else {
                    result.set(key, decodedValue);
                }
            } else if (value instanceof JSONObject) {
                // 如果值本身就是JSONObject，递归处理
                result.set(key, decodeJsonObject((JSONObject) value));
            } else {
                // 其他类型直接保留
                result.set(key, value);
            }
        }

        return result;
    }

    /**
     * 尝试对字符串进行base64解码，如果失败则返回原字符串
     *
     * @param str 待解码的字符串
     * @return 解码后的字符串或原字符串
     */
    private static String tryBase64Decode(String str) {
        try {
            return DecodeUtil.base64DecodeString(str);
        } catch (Exception e) {
            return str;
        }
    }
}
