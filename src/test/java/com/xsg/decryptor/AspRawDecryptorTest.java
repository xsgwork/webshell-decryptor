package com.xsg.decryptor;

import com.xsg.decryptor.godzilla.enums.DecryptorType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AspRawDecryptorTest extends BaseDecryptorTest {

    @Override
    protected DecryptorType getDecryptorType() {
        return DecryptorType.ASP_RAW;
    }

    @Override
    protected String getTestDataPrefix() {
        return "data/asp-raw";
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
}
