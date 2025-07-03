package io.github.xsgwork.decryptor.godzilla.v2;

import io.github.xsgwork.decryptor.godzilla.v2.core.base.GodzillaV2Decryptor;
import lombok.Getter;

/**
 * 哥斯拉3.x~4.x版本解密器类型枚举
 * 定义了支持的各种加密器类型
 *
 * @author xsg
 * @version 1.0.0
 */
@Getter
public enum GodzillaV2DecryptorType {

    JAVA_AES_BASE64("JavaAesBase64Decryptor"),

    JAVA_AES_RAW("JavaAesRawDecryptor"),

    PHP_EVAL_XOR_BASE64("PhpEvalXorBase64Decryptor"),

    PHP_XOR_BASE64("PhpXorBase64Decryptor"),

    PHP_XOR_RAW("PhpXorRawDecryptor"),

    CSHAP_AES_BASE64("CShapAesBase64Decryptor"),

    CSHAP_AES_RAW("CShapAesRawDecryptor"),

    CSHAP_EVAL_AES_BASE64("CShapEvalAesBase64Decryptor"),

    CSHAP_ASMX_AES_BASE64("CShapAsmxAesBase64Decryptor"),

    ASP_BASE64("AspBase64Decryptor"),

    ASP_EVAL_BASE64("AspEvalBase64Decryptor"),

    ASP_RAW("AspRawDecryptor"),

    ASP_XOR_BASE64("AspXorBase64Decryptor"),

    ASP_XOR_RAW("AspXorRawDecryptor");

    /**
     * 解密器实现类的类名
     */
    private final String className;

    /**
     * 构造方法
     *
     * @param className 解密器实现类的类名
     */
    GodzillaV2DecryptorType(String className) {
        this.className = className;
    }

    /**
     * 根据加密器类型获取对应的解密器实例
     *
     * 使用反射机制动态创建指定类型的解密器实例。
     * 所有解密器类都应该位于io.github.xsgwork.decryptor.godzilla.v2.core包下
     *
     * @param type 加密器类型枚举值
     * @return 对应的解密器实例
     * @throws IllegalArgumentException 如果加密器类型不支持或实例化失败
     */
    public static GodzillaV2Decryptor getDecryptor(GodzillaV2DecryptorType type) {
        try {
            String fullClassName = "io.github.xsgwork.decryptor.godzilla.v2.core." + type.getClassName();
            Class<?> clazz = Class.forName(fullClassName);
            return (GodzillaV2Decryptor) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("无法实例化解密器: " + type.getClassName(), e);
        }
    }
}