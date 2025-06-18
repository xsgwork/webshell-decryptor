package com.xsg.decryptor.godzilla.v3;

import com.xsg.decryptor.godzilla.v3.enums.GodzillaV3DecryptorType;
import org.junit.Test;

public class PhpEvalXorBase64DecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV3DecryptorType getDecryptorType() {
        return GodzillaV3DecryptorType.PHP_EVAL_XOR_BASE64;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v3/php-eval-xor-base64";
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
