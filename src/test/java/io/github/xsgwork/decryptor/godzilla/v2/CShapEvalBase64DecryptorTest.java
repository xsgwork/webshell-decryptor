package io.github.xsgwork.decryptor.godzilla.v2;

import org.junit.Test;

public class CShapEvalBase64DecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV2DecryptorType getDecryptorType() {
        return GodzillaV2DecryptorType.CSHAP_EVAL_AES_BASE64;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v2/cshap_eval_base64";
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
