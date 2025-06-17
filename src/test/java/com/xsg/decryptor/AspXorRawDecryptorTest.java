package com.xsg.decryptor;

import com.xsg.decryptor.godzilla.enums.DecryptorType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AspXorRawDecryptorTest extends BaseDecryptorTest {

    @Override
    protected DecryptorType getDecryptorType() {
        return DecryptorType.ASP_XOR_RAW;
    }

    @Override
    protected String getTestDataPrefix() {
        return "data/asp-xor-raw";
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
