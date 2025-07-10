package io.github.xsgwork.decryptor.godzilla.v2;

import org.junit.Test;

public class AspRawDecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV2DecryptorType getDecryptorType() {
        return GodzillaV2DecryptorType.ASP_RAW;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v2/asp_raw";
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
