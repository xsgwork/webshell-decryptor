package com.xsg.decryptor.godzilla.core.base;

/**
 * ASP类型Base64解密器抽象基类
 * 
 * 该类专门为ASP类型的Webshell提供Base64解密功能的通用框架。
 * ASP类型的Webshell在响应数据中通常包含固定的头部和尾部标识符，
 * 需要在解密前进行特殊的数据提取处理。
 * 
 * 支持的ASP Webshell类型：
 * - AspXorBase64：使用XOR算法加密，固定头部"11cd6a"，固定尾部"ac826a"
 * - AspBase64：使用标准Base64编码，固定头部"11cd6a"，固定尾部"ac826a"
 * - AspEvalBase64：使用Eval执行，固定头部"828130"，固定尾部"20ebbc"
 * 
 * @author xsg
 * @version 1.0.0
 */
public abstract class AbstractAspBase64Decryptor extends AbstractBase64Decryptor {

    /**
     * 从ASP响应数据中提取加密内容
     * 
     * ASP类型的Webshell响应数据格式通常为：
     * [固定头部6位] + [实际加密数据] + [固定尾部6位]
     * 
     * 不同ASP Webshell类型的固定标识符：
     * - AspXorBase64/AspBase64：头部"11cd6a"，尾部"ac826a"
     * - AspEvalBase64：头部"828130"，尾部"20ebbc"
     * 
     * @param data 原始响应数据
     * @return 去除固定头尾后的加密数据
     */
    @Override
    protected String extractResponseData(String data) {
        // 去除ASP Webshell响应数据的固定头部（前6位）和固定尾部（后6位）
        return data.substring(6, data.length() - 6);
    }
}