package io.github.xsgwork.decryptor.antsword;

import org.junit.Test;

public class JspDecryptorTest extends BaseDecryptorTest {

    @Override
    protected AntSwordDecryptorType getDecryptorType() {
        return AntSwordDecryptorType.JSP;
    }

    @Override
    protected String getTestDataPrefix() {
        return "antsword/jsp";
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
