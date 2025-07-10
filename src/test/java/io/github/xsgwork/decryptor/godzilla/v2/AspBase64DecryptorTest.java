package io.github.xsgwork.decryptor.godzilla.v2;

import org.junit.Test;

public class AspBase64DecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV2DecryptorType getDecryptorType() {
        return GodzillaV2DecryptorType.ASP_BASE64;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v2/asp_base64";
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
