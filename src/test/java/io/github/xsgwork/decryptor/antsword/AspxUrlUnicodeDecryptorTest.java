package io.github.xsgwork.decryptor.antsword;

import org.junit.Test;

public class AspxUrlUnicodeDecryptorTest extends BaseDecryptorTest {

    @Override
    protected AntSwordDecryptorType getDecryptorType() {
        return AntSwordDecryptorType.ASPX_URL_UNICODE;
    }

    @Override
    protected String getTestDataPrefix() {
        return "antsword/aspx_url_unicode";
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
