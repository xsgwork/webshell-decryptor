package io.github.xsgwork.decryptor.behinder.v2;

import io.github.xsgwork.decryptor.behinder.v2.core.base.BehinderV2Decryptor;
import lombok.Getter;

@Getter
public enum BehinderV2DecryptorType {

    XOR("XorDecryptor"),

    XOR_BASE64("XorBase64Decryptor"),

    AES("AesDecryptor"),

    IMAGE("ImageDecryptor"),

    JSON("JsonDecryptor"),

    AES_WITH_MAGIC("AesWithMagicDecryptor"),

    ;

    /**
     * 解密器实现类的类名
     */
    private final String className;

    /**
     * 构造方法
     *
     * @param className 解密器实现类的类名
     */
    BehinderV2DecryptorType(String className) {
        this.className = className;
    }

    public static BehinderV2Decryptor getDecryptor(BehinderV2DecryptorType type) {
        try {
            String fullClassName = "io.github.xsgwork.decryptor.behinder.v2.core." + type.getClassName();
            Class<?> clazz = Class.forName(fullClassName);
            return (BehinderV2Decryptor) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("无法实例化解密器: " + type.getClassName(), e);
        }
    }
}