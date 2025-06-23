package io.github.xsgwork.decryptor.godzilla.v3.core.base;

/**
 * 哥斯拉3.x~4.x流量解密器接口
 * 
 * 定义了哥斯拉流量解密的核心方法，所有具体的解密实现都需要实现此接口。
 * 该接口支持对请求包和响应包的独立解密处理。
 * 
 * @author xsg
 * @version 1.0.0
 */
public interface GodzillaV3Decryptor {

    /**
     * 解密请求包数据
     * 
     * @param encryptedData 加密的请求数据
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密后的明文数据
     * @throws RuntimeException 当解密失败时抛出异常
     */
    String decryptRequest(String encryptedData, String password, String key);

    /**
     * 解密响应包数据
     * 
     * @param encryptedData 加密的响应数据
     * @param password 解密密码
     * @param key 解密密钥
     * @return 解密后的明文数据
     * @throws RuntimeException 当解密失败时抛出异常
     */
    String decryptResponse(String encryptedData, String password, String key);
}
