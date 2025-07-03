package io.github.xsgwork.decryptor.behinder.v2;

import org.junit.Test;

public class ImageDecryptorTest extends BaseDecryptorTest {

    @Override
    protected BehinderV2DecryptorType getDecryptorType() {
        return BehinderV2DecryptorType.IMAGE;
    }

    @Override
    protected String getTestDataPrefix() {
        return "behinder/v2/image";
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
