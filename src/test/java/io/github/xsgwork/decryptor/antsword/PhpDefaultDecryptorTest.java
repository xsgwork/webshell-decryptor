package io.github.xsgwork.decryptor.antsword;

import org.junit.Test;

public class PhpDefaultDecryptorTest extends BaseDecryptorTest {

    @Override
    protected AntSwordDecryptorType getDecryptorType() {
        return AntSwordDecryptorType.PHP_DEFAULT;
    }

    @Override
    protected String getTestDataPrefix() {
        return "antsword/php_default";
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
}
