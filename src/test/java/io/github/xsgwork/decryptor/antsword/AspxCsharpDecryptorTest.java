
package io.github.xsgwork.decryptor.antsword;

import org.junit.Test;

public class AspxCsharpDecryptorTest extends BaseDecryptorTest {

    @Override
    protected AntSwordDecryptorType getDecryptorType() {
        return AntSwordDecryptorType.ASPX_CSHARP;
    }

    @Override
    protected String getTestDataPrefix() {
        return "antsword/aspx_csharp";
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
