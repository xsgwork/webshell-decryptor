package io.github.xsgwork.decryptor.behinder.v1;

import cn.hutool.core.io.IoUtil;
import io.github.xsgwork.decryptor.WebshellDecryptorFacade;
import io.github.xsgwork.decryptor.behinder.v1.core.base.BehinderV1Decryptor;
import io.github.xsgwork.decryptor.behinder.v1.enums.BehinderV1DecryptorType;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public abstract class BaseDecryptorTest {

    protected static final String KEY = "e45e329feb5d925b";

    protected BehinderV1Decryptor DECRYPTOR = WebshellDecryptorFacade.decryptBehinderV1(getDecryptorType());

    protected abstract BehinderV1DecryptorType getDecryptorType();

    /**
     * 获取测试数据目录前缀，子类需要实现此方法
     * 例如："data/java-aes-raw"
     */
    protected abstract String getTestDataPrefix();

    protected String readData(String filePath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new RuntimeException("Resource not found: " + filePath);
        }
        return IoUtil.read(inputStream, StandardCharsets.UTF_8);
    }

    /**
     * 通用的请求解密测试方法
     *
     * @param testCase 测试用例名称，如 "request1"
     */
    protected void testDecrypt(String testCase) {
        String inputPath = getTestDataPrefix() + "/" + testCase + "/input.txt";
        String resultPath = getTestDataPrefix() + "/" + testCase + "/result.txt";

        String input = readData(inputPath);
        String expected = readData(resultPath);
        String response = DECRYPTOR.decrypt(input, KEY);
        assertEquals(expected, response);
    }
}
