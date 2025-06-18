package com.xsg.decryptor.godzilla.v1.enums;

import com.xsg.decryptor.godzilla.v1.core.base.GodzillaV1Decryptor;
import lombok.Getter;

/**
 * 哥斯拉1.x~2.x版本解密器类型枚举
 * 定义了支持的各种加密器类型
 *
 * @author xsg
 * @version 1.0.0
 */
@Getter
public enum GodzillaV1DecryptorType {

    JAVA_AES_BASE64("JavaAesBase64Decryptor"),

    JAVA_AES_RAW("JavaAesRawDecryptor"),

    PHP_XOR_BASE64("PhpXorBase64Decryptor"),

    PHP_XOR_RAW("PhpXorRawDecryptor"),

    CSHAP_AES_BASE64("CShapAesBase64Decryptor"),

    CSHAP_AES_RAW("CShapAesRawDecryptor");

    /**
     * 解密器实现类的类名
     */
    private final String className;

    /**
     * 构造方法
     *
     * @param className 解密器实现类的类名
     */
    GodzillaV1DecryptorType(String className) {
        this.className = className;
    }

    /**
     * 根据加密器类型获取对应的解密器实例
     *
     * 使用反射机制动态创建指定类型的解密器实例。
     * 所有解密器类都应该位于com.xsg.decryptor.godzilla.v1.core包下
     *
     * @param type 加密器类型枚举值
     * @return 对应的解密器实例
     * @throws IllegalArgumentException 如果加密器类型不支持或实例化失败
     */
    public static GodzillaV1Decryptor getDecryptor(GodzillaV1DecryptorType type) {
        try {
            String fullClassName = "com.xsg.decryptor.godzilla.v1.core." + type.getClassName();
            Class<?> clazz = Class.forName(fullClassName);
            return (GodzillaV1Decryptor) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("无法实例化解密器: " + type.getClassName(), e);
        }
    }
}