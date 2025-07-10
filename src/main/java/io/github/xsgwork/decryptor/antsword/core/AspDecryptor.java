package io.github.xsgwork.decryptor.antsword.core;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import io.github.xsgwork.decryptor.antsword.core.base.AbstractDecryptor;
import io.github.xsgwork.decryptor.util.DecodeUtil;
import io.github.xsgwork.decryptor.util.StringExtractUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 存在三个官方编码器default、insert_percent（增加了很多%，例如eval方法变成e%v%a%l）、xxxxdog（把eval方法替换成了xxxx）
 * 实际解码过程均一致
 */
public class AspDecryptor extends AbstractDecryptor {

    @Override
    protected boolean isSubstringNormalParams() {
        return false;
    }

    @Override
    protected String doNormalDecrypt(String encryptedData) {
        // URL解码
        String urlDecodedValue = DecodeUtil.urlDecode(encryptedData);
        // 16进制解码
        return HexUtil.decodeHexStr(urlDecodedValue);
    }

    @Override
    protected Map<String, String> convertPasswordParam(Map<String, String> paramMap, String password) {
        Map<String, String> result = new HashMap<>();
        String paramValue = paramMap.remove(password);
        // 尝试提取bd函数中的内容
        String extractedData = StringExtractUtil.extractContent(paramValue, "bd(\"\"\"\"", "\"\"\"\"))");
        if (StrUtil.isNotBlank(extractedData)) {
            // 16进制解码
            String hexDecodedData = HexUtil.decodeHexStr(extractedData);
            result.put(password, hexDecodedData);
        } else {
            result.put(password, paramValue);
        }
        return result;
    }

}
