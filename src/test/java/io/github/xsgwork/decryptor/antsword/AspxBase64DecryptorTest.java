package io.github.xsgwork.decryptor.antsword;

import org.junit.Test;

public class AspxBase64DecryptorTest extends BaseDecryptorTest {

    @Override
    protected AntSwordDecryptorType getDecryptorType() {
        return AntSwordDecryptorType.ASPX_BASE64;
    }

    @Override
    protected String getTestDataPrefix() {
        return "antsword/aspx_base64";
    }
    
    @Test
    public void request1() {
        testDecrypt("request1");
    }

    @Test
    public void request2() {
        testDecrypt("request2");
    }
}
