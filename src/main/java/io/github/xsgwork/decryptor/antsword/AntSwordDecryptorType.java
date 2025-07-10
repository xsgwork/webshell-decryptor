package io.github.xsgwork.decryptor.antsword;

import io.github.xsgwork.decryptor.antsword.core.base.AntSwordDecryptor;
import lombok.Getter;

@Getter
public enum AntSwordDecryptorType {

    ASP("AspDecryptor"),

    ASPX_DEFAULT("AspxDefaultDecryptor"),

    ASPX_BASE64("AspxBase64Decryptor"),

    ASPX_HEX("AspxHexDecryptor"),

    ASPX_URL_UNICODE("AspxUrlUnicodeDecryptor"),

    ASPX_CSHARP("AspxCsharpDecryptor"),

    PHP_DEFAULT("PhpDefaultDecryptor"),

    PHP_BASE64("PhpBase64Decryptor"),

    PHP_CHR("PhpChrDecryptor"),

    PHP_ROT13("PhpRot13Decryptor"),

    JSP("JspDecryptor"),
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
    AntSwordDecryptorType(String className) {
        this.className = className;
    }

    public static AntSwordDecryptor getDecryptor(AntSwordDecryptorType type) {
        try {
            String fullClassName = "io.github.xsgwork.decryptor.antsword.core." + type.getClassName();
            Class<?> clazz = Class.forName(fullClassName);
            return (AntSwordDecryptor) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("无法实例化解密器: " + type.getClassName(), e);
        }
    }
}