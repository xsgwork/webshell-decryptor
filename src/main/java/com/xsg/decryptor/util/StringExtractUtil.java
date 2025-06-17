package com.xsg.decryptor.util;

import cn.hutool.core.util.StrUtil;

/**
 * 字符串内容提取工具类
 * 
 * 提供从字符串中提取特定模式内容的功能，
 * 主要用于从Webshell流量中提取加密数据。
 * 
 * @author xsg
 * @version 1.0.0
 */
public class StringExtractUtil {

    /**
     * 从字符串中提取指定模式之间的内容
     * 
     * 根据开始模式和结束模式从源字符串中提取中间的内容。
     * 常用于从XML、HTML或其他结构化文本中提取特定标签内的数据。
     * 
     * 示例：
     * - 提取XML标签内容：extractContent(data, "<tag>", "</tag>")
     * - 提取函数参数：extractContent(data, "func(", ")")
     * 
     * @param data 源字符串
     * @param startPattern 开始模式字符串
     * @param endPattern 结束模式字符串
     * @return 提取出的内容，如果未找到匹配模式则返回null
     */
    public static String extractContent(String data, String startPattern, String endPattern) {
        if (StrUtil.isBlank(data)) {
            return null;
        }
        // 查找开始模式的位置
        int startIndex = data.indexOf(startPattern);
        if (startIndex == -1) {
            return null;
        }
        // 移动到内容开始位置（跳过开始模式）
        startIndex += startPattern.length();
        // 查找结束模式的位置
        int endIndex = data.indexOf(endPattern, startIndex);
        if (endIndex == -1) {
            return null;
        }
        // 提取开始模式和结束模式之间的内容
        return data.substring(startIndex, endIndex);
    }
}
