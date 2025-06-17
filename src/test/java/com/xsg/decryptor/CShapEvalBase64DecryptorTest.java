package com.xsg.decryptor;

import com.xsg.decryptor.godzilla.enums.DecryptorType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CShapEvalBase64DecryptorTest extends BaseDecryptorTest {

    @Override
    protected DecryptorType getDecryptorType() {
        return DecryptorType.CSHAP_EVAL_AES_BASE64;
    }

    @Override
    protected String getTestDataPrefix() {
        return "data/cshap-eval-base64";
    }

    @Test
    public void request1() {
        testDecryptRequest("request1");
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
