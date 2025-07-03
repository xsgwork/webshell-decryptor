package io.github.xsgwork.decryptor.behinder.v2;

import org.junit.Test;

public class AesDecryptorTest extends BaseDecryptorTest {

    @Override
    protected BehinderV2DecryptorType getDecryptorType() {
        return BehinderV2DecryptorType.AES;
    }

    @Override
    protected String getTestDataPrefix() {
        return "behinder/v2/aes";
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

    @Test
    public void request7() {
        testDecrypt("request7");
    }

    @Test
    public void request8() {
        testDecrypt("request8");
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

    @Test
    public void response5() {
        testDecrypt("response5");
    }

    @Test
    public void response6() {
        testDecrypt("response6");
    }

    @Test
    public void response7() {
        testDecrypt("response7");
    }

    @Test
    public void response8() {
        testDecrypt("response8");
    }
}
