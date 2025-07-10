package io.github.xsgwork.decryptor.antsword;

import org.junit.Test;

public class AspDecryptorTest extends BaseDecryptorTest {

    @Override
    protected AntSwordDecryptorType getDecryptorType() {
        return AntSwordDecryptorType.ASP;
    }

    @Override
    protected String getTestDataPrefix() {
        return "antsword/asp";
    }

    /**
     * 编码器default的请求数据
     */
    @Test
    public void request1() {
        testDecrypt("request1");
    }

    /**
     * 编码器xxxxdog的请求数据
     */
    @Test
    public void request2() {
        testDecrypt("request2");
    }

    /**
     * 编码器insert_percent的请求数据
     */
    @Test
    public void request3() {
        testDecrypt("request3");
    }
}
