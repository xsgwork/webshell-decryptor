package io.github.xsgwork.decryptor.godzilla.v2;

import org.junit.Test;

public class PhpEvalXorBase64DecryptorTest extends BaseDecryptorTest {

    @Override
    protected GodzillaV2DecryptorType getDecryptorType() {
        return GodzillaV2DecryptorType.PHP_EVAL_XOR_BASE64;
    }

    @Override
    protected String getTestDataPrefix() {
        return "godzilla/v2/php_eval_xor_base64";
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
