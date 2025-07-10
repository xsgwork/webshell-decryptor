package io.github.xsgwork.decryptor.antsword;

import org.junit.Test;

public class PhpChrDecryptorTest extends BaseDecryptorTest {

    @Override
    protected AntSwordDecryptorType getDecryptorType() {
        return AntSwordDecryptorType.PHP_CHR;
    }

    @Override
    protected String getTestDataPrefix() {
        return "antsword/php_chr";
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
    public void request3() {
        testDecrypt("request3");
    }

    @Test
    public void request4() {
        testDecrypt("request4");
    }

    @Test
    public void request5() {
        testDecrypt("request5");
    }

    @Test
    public void request6() {
        testDecrypt("request6");
    }
}
