package io.github.xsgwork.decryptor.behinder.v1.enums;

import io.github.xsgwork.decryptor.behinder.v1.core.base.BehinderV1Decryptor;
import lombok.Getter;

@Getter
public enum BehinderV1DecryptorType {

    JSP("JspDecryptor"),

    PHP("PhpDecryptor"),

    ASP("AspDecryptor"),

    ASPX("AspxDecryptor"),
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
    BehinderV1DecryptorType(String className) {
        this.className = className;
    }

    public static BehinderV1Decryptor getDecryptor(BehinderV1DecryptorType type) {
        try {
            String fullClassName = "io.github.xsgwork.decryptor.behinder.v1.core." + type.getClassName();
            Class<?> clazz = Class.forName(fullClassName);
            return (BehinderV1Decryptor) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("无法实例化解密器: " + type.getClassName(), e);
        }
    }
}