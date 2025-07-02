package io.github.xsgwork.decryptor.behinder.v1;

import io.github.xsgwork.decryptor.behinder.v1.enums.BehinderV1DecryptorType;
import org.junit.Test;

public class AspDecryptorTest extends BaseDecryptorTest{

    @Override
    protected BehinderV1DecryptorType getDecryptorType() {
        return BehinderV1DecryptorType.ASP;
    }

    @Override
    protected String getTestDataPrefix() {
        return "behinder/v1/asp";
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
