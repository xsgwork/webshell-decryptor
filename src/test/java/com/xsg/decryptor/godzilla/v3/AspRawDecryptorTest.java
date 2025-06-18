package com.xsg.decryptor.godzilla.v3;

import com.xsg.decryptor.godzilla.v3.enums.GodzillaV3DecryptorType;
import org.junit.Test;

public class AspRawDecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV3DecryptorType getDecryptorType() {
        return GodzillaV3DecryptorType.ASP_RAW;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v3/asp-raw";
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
