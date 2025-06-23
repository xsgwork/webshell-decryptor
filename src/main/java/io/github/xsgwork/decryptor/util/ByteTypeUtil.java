package io.github.xsgwork.decryptor.util;

/**
 * 字节类型检测工具类
 * 
 * 提供各种字节数组格式检测功能，用于识别不同类型的二进制数据。
 * 主要用于在解密过程中判断数据格式，以便进行相应的处理。
 * 
 * 支持的格式检测：
 * - GZIP压缩格式
 * - Java字节码文件格式
 * 
 * @author xsg
 * @version 1.0.0
 */
public class ByteTypeUtil {

    /**
     * 检查字节数组是否为GZIP压缩格式
     * 
     * GZIP文件的前两个字节固定为0x1F和0x8B，这是GZIP格式的魔数。
     * 在Webshell解密过程中，解密后的数据可能是GZIP压缩的，
     * 需要进一步解压缩才能获得最终的明文数据。
     *
     * @param data 需要检测的字节数组
     * @return 如果是GZIP格式返回true，否则返回false
     */
    public static boolean isGzipFormat(byte[] data) {
        if (data == null || data.length < 2) {
            return false;
        }
        // 检查前两个字节是否为GZIP的魔数：0x1F 0x8B
        return data[0] == (byte) 0x1F && data[1] == (byte) 0x8B;
    }

    /**
     * 检查字节数组是否为Java字节码文件格式
     * 
     * Java class文件的前四个字节固定为0xCAFEBABE，这是Java字节码的魔数。
     * 在某些Webshell中可能会传输Java字节码，需要识别这种格式。
     *
     * @param data 需要检测的字节数组
     * @return 如果是Java字节码格式返回true，否则返回false
     */
    public static boolean isJavaClassFile(byte[] data) {
        if (data == null || data.length < 4) {
            return false;
        }
        // 检查前四个字节是否为Java class文件的魔数：0xCAFEBABE
        return data[0] == (byte) 0xCA && data[1] == (byte) 0xFE &&
                data[2] == (byte) 0xBA && data[3] == (byte) 0xBE;
    }
}
