package com.xsg.decryptor.godzilla.enums;

import com.xsg.decryptor.godzilla.core.base.Decryptor;
import lombok.Getter;

/**
 * Webshell解密器类型枚举
 * 
 * 定义了支持的各种Webshell解密器类型，包括不同编程语言、
 * 不同加密算法和不同编码格式的组合。
 * 
 * 命名规则：
 * - 语言前缀：Java、PHP、CShap（C#）、Asp
 * - 加密算法：Aes、Xor、Eval
 * - 编码格式：Base64、Raw
 * - 特殊类型：Asmx（ASMX Web服务）
 * 
 * @author xsg
 * @version 1.0.0
 */
@Getter
public enum DecryptorType {

    /** Java语言 + AES加密 + Base64编码 */
    JAVA_AES_BASE64("JavaAesBase64Decryptor"),
    
    /** Java语言 + AES加密 + 原始字节 */
    JAVA_AES_RAW("JavaAesRawDecryptor"),
    
    /** PHP语言 + Eval函数 + XOR加密 + Base64编码 */
    PHP_EVAL_XOR_BASE64("PhpEvalXorBase64Decryptor"),
    
    /** PHP语言 + XOR加密 + Base64编码 */
    PHP_XOR_BASE64("PhpXorBase64Decryptor"),
    
    /** PHP语言 + XOR加密 + 原始字节 */
    PHP_XOR_RAW("PhpXorRawDecryptor"),
    
    /** C#语言 + AES加密 + Base64编码 */
    CSHAP_AES_BASE64("CShapAesBase64Decryptor"),
    
    /** C#语言 + AES加密 + 原始字节 */
    CSHAP_AES_RAW("CShapAesRawDecryptor"),
    
    /** C#语言 + Eval函数 + AES加密 + Base64编码 */
    CSHAP_EVAL_AES_BASE64("CShapEvalAesBase64Decryptor"),
    
    /** C#语言 + ASMX Web服务 + AES加密 + Base64编码 */
    CSHAP_ASMX_AES_BASE64("CShapAsmxAesBase64Decryptor"),
    
    /** ASP语言 + Base64编码 */
    ASP_BASE64("AspBase64Decryptor"),
    
    /** ASP语言 + Eval函数 + Base64编码 */
    ASP_EVAL_BASE64("AspEvalBase64Decryptor"),
    
    /** ASP语言 + 原始字节 */
    ASP_RAW("AspRawDecryptor"),
    
    /** ASP语言 + XOR加密 + Base64编码 */
    ASP_XOR_BASE64("AspXorBase64Decryptor"),
    
    /** ASP语言 + XOR加密 + 原始字节 */
    ASP_XOR_RAW("AspXorRawDecryptor");

    /** 解密器实现类的类名 */
    private final String className;

    /**
     * 构造方法
     * 
     * @param className 解密器实现类的类名
     */
    DecryptorType(String className) {
        this.className = className;
    }

    /**
     * 根据解密器类型获取对应的Decryptor实例
     * 
     * 使用反射机制动态创建指定类型的解密器实例。
     * 所有解密器类都应该位于com.xsg.decryptor.godzilla.core包下，
     * 并且具有无参构造方法。
     *
     * @param type 解密器类型枚举值
     * @return 对应的Decryptor实例
     * @throws IllegalArgumentException 如果解密器类型不支持或实例化失败
     */
    public static Decryptor getDecryptor(DecryptorType type) {
        try {
            String fullClassName = "com.xsg.decryptor.godzilla.core." + type.getClassName();
            Class<?> clazz = Class.forName(fullClassName);
            return (Decryptor) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("无法实例化解密器: " + type.getClassName(), e);
        }
    }
}