package com.xsg.decryptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Webshell解密器主应用程序
 * 这是一个综合性的Webshell流量解密工具，支持多种加密算法和Webshell类型，
 * 包括但不限于Godzilla、冰蝎等主流Webshell工具的流量解密。
 *
 * @author xsg
 * @version 1.0.0
 */
@SpringBootApplication
public class WebshellDecryptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshellDecryptorApplication.class, args);
    }

}
