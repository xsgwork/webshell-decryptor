package io.github.xsgwork.decryptor.behinder.v2;

import org.junit.Test;

public class XorDecryptorTest extends BaseDecryptorTest {

    @Override
    protected BehinderV2DecryptorType getDecryptorType() {
        return BehinderV2DecryptorType.XOR;
    }

    @Override
    protected String getTestDataPrefix() {
        return "behinder/v2/xor";
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
    public void response1() {
        testDecrypt("response1");
    }

    @Test
    public void response2() {
        testDecrypt("response2");
    }

    @Test
    public void response3() {
        testDecrypt("response3");
    }

    @Test
    public void response4() {
        testDecrypt("response4");
    }
}
