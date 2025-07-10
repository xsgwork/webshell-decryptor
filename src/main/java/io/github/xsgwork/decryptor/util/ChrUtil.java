package io.github.xsgwork.decryptor.util;

public class ChrUtil {

    public static String decrypt(String encryptedText) {
        StringBuilder decrypted = new StringBuilder();
        int i = 0;

        while (i < encryptedText.length()) {
            // 检查是否是 chr( 开头
            if (i + 4 <= encryptedText.length() && encryptedText.startsWith("chr(", i)) {
                // 提取括号内的内容
                int start = i + 4;
                int end = encryptedText.indexOf(")", start);
                if (end != -1) {
                    String number = encryptedText.substring(start, end);
                    char ch;
                    try {
                        // 尝试解析为十六进制
                        if (number.startsWith("0x")) {
                            int hexValue = Integer.parseInt(number.substring(2), 16);
                            ch = (char) hexValue;
                        } else {
                            // 否则按十进制解析
                            int intValue = Integer.parseInt(number);
                            ch = (char) intValue;
                        }
                        decrypted.append(ch);
                        i = end + 1; // 跳过已处理的部分
                        continue;
                    } catch (NumberFormatException e) {
                        // 如果数字无效，直接忽略
                    }
                }
            }
            // 如果当前字符不是 chr(，并且不是点号 .，直接保留原字符
            if (encryptedText.charAt(i) != '.') {
                decrypted.append(encryptedText.charAt(i));
            }
            i++;
        }
        return decrypted.toString();
    }
}
