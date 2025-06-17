package com.xsg.decryptor;

import com.xsg.decryptor.godzilla.enums.DecryptorType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JavaAesRawDecryptorTest extends BaseDecryptorTest {

    @Override
    protected DecryptorType getDecryptorType() {
        return DecryptorType.JAVA_AES_RAW;
    }

    @Override
    protected String getTestDataPrefix() {
        return "data/java-aes-raw";
    }

    @Test
    public void request1() {
        testDecryptRequest("request1");
    }

    @Test
    public void request2() {
        testDecryptRequest("request2");
    }

    @Test
    public void request3() {
        testDecryptRequest("request3");
    }

    @Test
    public void response1() {
        testDecryptResponse("response1");
    }

    @Test
    public void response2() {
        testDecryptResponse("response2");
    }
}
