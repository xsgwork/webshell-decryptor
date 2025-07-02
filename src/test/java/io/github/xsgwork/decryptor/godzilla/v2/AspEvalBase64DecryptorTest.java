package io.github.xsgwork.decryptor.godzilla.v2;

import io.github.xsgwork.decryptor.godzilla.v2.enums.GodzillaV2DecryptorType;
import org.junit.Test;

public class AspEvalBase64DecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV2DecryptorType getDecryptorType() {
        return GodzillaV2DecryptorType.ASP_EVAL_BASE64;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v2/asp-eval-base64";
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
