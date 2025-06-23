package io.github.xsgwork.decryptor.godzilla.v3;

import io.github.xsgwork.decryptor.godzilla.v3.enums.GodzillaV3DecryptorType;
import org.junit.Test;

public class AspXorRawDecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV3DecryptorType getDecryptorType() {
        return GodzillaV3DecryptorType.ASP_XOR_RAW;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v3/asp-xor-raw";
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
