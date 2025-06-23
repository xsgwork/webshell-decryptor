package io.github.xsgwork.decryptor.godzilla.v3.core.base;

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