package io.github.xsgwork.decryptor.util;

import java.nio.charset.StandardCharsets;

public class GodzillaResultUtil {

    /**
     * 哥斯拉V1版本数据反序列化
     *
     * @param data 需要反序列化的字节数组
     * @return 反序列化后的字符串
     */
    public static String godzillaV1Deserialize(byte[] data) {
        String result = new String(data, StandardCharsets.UTF_8);
        // 检查是否包含methodName=
        if (!result.contains("methodName=")) {
            return result;
        }

        try {
            // 获取methodName=之后的所有内容
            String value = result.replace("methodName=", "");
            byte[] decodedBytes = DecodeUtil.base64Decode(value);
            String decodedValue = new String(decodedBytes, StandardCharsets.UTF_8);
            return "methodName=" + decodedValue;
        } catch (Exception e) {
            // 如果解码失败，返回原始字符串
            return result;
        }
    }

    /**
     * 哥斯拉V3版本数据反序列化
     * <p>
     * 将序列化的字节数组反序列化为可读的字符串格式。
     * 数据格式：key + 分隔符(2) + value长度(4字节) + value内容
     * <p>
     * 解析规则：
     * 1. 读取key直到遇到字节值2（分隔符）
     * 2. 读取4字节的value长度信息
     * 3. 根据长度读取value内容
     * 4. 重复上述过程直到数据结束
     *
     * @param data 需要反序列化的字节数组
     * @return 反序列化后的字符串，格式为"key=value\n"
     */
    public static String godzillaV3Deserialize(byte[] data) {
        try {
            StringBuilder result = new StringBuilder();
            int offset = 0;

            while (offset < data.length) {
                // 读取key：一个字节一个字节读取直到遇到字节值2（分隔符）
                int keyStart = offset;
                while (offset < data.length && data[offset] != 2) {
                    offset++;
                }
                if (offset >= data.length) {
                    // 没有找到分隔符2，结束解析
                    break;
                }

                // 提取key的内容（2之前的内容）
                byte[] keyBytes = new byte[offset - keyStart];
                System.arraycopy(data, keyStart, keyBytes, 0, offset - keyStart);
                String key = new String(keyBytes, StandardCharsets.UTF_8);
                // 跳过分隔符2
                offset++;
                // 检查是否还有足够的字节来读取value长度（4字节）
                if (offset + 4 > data.length) {
                    break;
                }

                // 读取4个字节来计算value的长度（小端字节序）
                int valueLength = (data[offset] & 0xFF) |
                        ((data[offset + 1] & 0xFF) << 8) |
                        ((data[offset + 2] & 0xFF) << 16) |
                        ((data[offset + 3] & 0xFF) << 24);
                offset += 4;
                // 检查是否还有足够的字节来读取value内容
                if (offset + valueLength > data.length) {
                    break;
                }

                // 读取value的内容
                byte[] valueBytes = new byte[valueLength];
                System.arraycopy(data, offset, valueBytes, 0, valueLength);
                String value = new String(valueBytes, StandardCharsets.UTF_8);
                offset += valueLength;
                // 添加到结果中，格式为 key=value
                if (result.length() > 0) {
                    result.append("\n");
                }
                result.append(key).append("=").append(value);
            }
            if (result.length() > 0) {
                return result.toString();
            }
            return new String(data, StandardCharsets.UTF_8);
        } catch (Exception e) {
            // 如果解析失败，返回原始字符串
            return new String(data, StandardCharsets.UTF_8);
        }
    }
}
