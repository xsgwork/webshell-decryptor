package com.xsg.decryptor;

import cn.hutool.core.io.IoUtil;
import com.xsg.decryptor.godzilla.core.base.Decryptor;
import com.xsg.decryptor.godzilla.enums.DecryptorType;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BaseDecryptorTest {

    protected static final String PASSWORD = "pass";
    protected static final String KEY = "3c6e0b8a9c15224a";

    protected Decryptor DECRYPTOR = DecryptorType.getDecryptor(getDecryptorType());

    protected abstract DecryptorType getDecryptorType();

    /**
     * 获取测试数据目录前缀，子类需要实现此方法
     * 例如："data/java-aes-raw"
     */
    protected abstract String getTestDataPrefix();

    protected String readData(String filePath) {
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            InputStream inputStream = resource.getInputStream();
            return IoUtil.read(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test input from: " + filePath, e);
        }
    }

    /**
     * 通用的请求解密测试方法
     *
     * @param testCase 测试用例名称，如 "request1"
     */
    protected void testDecryptRequest(String testCase) {
        String inputPath = getTestDataPrefix() + "/" + testCase + "/input.txt";
        String resultPath = getTestDataPrefix() + "/" + testCase + "/result.txt";

        String input = readData(inputPath);
        String expected = readData(resultPath);
        String response = DECRYPTOR.decryptRequest(input, PASSWORD, KEY);
        assertEquals(expected, response);
    }

    /**
     * 通用的响应解密测试方法
     *
     * @param testCase 测试用例名称，如 "response1"
     */
    protected void testDecryptResponse(String testCase) {
        String inputPath = getTestDataPrefix() + "/" + testCase + "/input.txt";
        String resultPath = getTestDataPrefix() + "/" + testCase + "/result.txt";

        String input = readData(inputPath);
        String expected = readData(resultPath);
        String response = DECRYPTOR.decryptResponse(input, PASSWORD, KEY);
        assertEquals(expected, response);
    }
}
