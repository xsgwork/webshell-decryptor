package io.github.xsgwork.decryptor.behinder.v1;

import org.junit.Test;

public class JspDecryptorTest extends BaseDecryptorTest{

    @Override
    protected BehinderV1DecryptorType getDecryptorType() {
        return BehinderV1DecryptorType.JSP;
    }

    @Override
    protected String getTestDataPrefix() {
        return "behinder/v1/jsp";
    }

    @Test
    public void request1() {
        testDecrypt("request1");
    }

    @Test
    public void request2() {
        testDecrypt("request2");
    }

    @Test
    public void response1() {
        testDecrypt("response1");
    }

    @Test
    public void response2() {
        testDecrypt("response2");
    }
}
