package io.github.xsgwork.decryptor.godzilla.v1;

import org.junit.Test;

public class PhpXorBase64DecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV1DecryptorType getDecryptorType() {
        return GodzillaV1DecryptorType.PHP_XOR_BASE64;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v1/php-xor-base64";
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
