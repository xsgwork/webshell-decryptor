package com.xsg.decryptor.godzilla.v1;

import com.xsg.decryptor.godzilla.v1.enums.GodzillaV1DecryptorType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JavaAesBase64DecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV1DecryptorType getDecryptorType() {
        return GodzillaV1DecryptorType.JAVA_AES_BASE64;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v1/java-aes-base64";
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
