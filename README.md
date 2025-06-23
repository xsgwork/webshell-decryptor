## 项目简介

一个功能强大的Webshell流量解密工具，专门用于解密各种类型的Webshell通信数据，为安全分析人员提供便捷的流量分析能力。

目前支持哥斯拉(Godzilla) 1.x-2.x版本和3.x-4.x版本的流量解密。

## 支持的解密器类型

目前已实现全部的哥斯拉解密器，涵盖以下类型，每个类型都有对应的单元测试类与测试数据：

## 哥斯拉1.x~2.x版本解密器（6种）

### Java类型
- **JAVA_AES_BASE64**: Java AES Base64解密器
- **JAVA_AES_RAW**: Java AES原始数据解密器

### PHP类型
- **PHP_XOR_BASE64**: PHP XOR Base64解密器
- **PHP_XOR_RAW**: PHP XOR原始数据解密器

### C#类型
- **CSHAP_AES_BASE64**: C# AES Base64解密器
- **CSHAP_AES_RAW**: C# AES原始数据解密器

## 哥斯拉3.x~4.x版本解密器（14种）

### Java类型
- **JAVA_AES_BASE64**: Java AES Base64解密器
- **JAVA_AES_RAW**: Java AES原始数据解密器

### PHP类型
- **PHP_XOR_BASE64**: PHP XOR Base64解密器
- **PHP_XOR_RAW**: PHP XOR原始数据解密器
- **PHP_EVAL_XOR_BASE64**: PHP Eval XOR Base64解密器

### C#类型
- **CSHAP_AES_BASE64**: C# AES Base64解密器
- **CSHAP_AES_RAW**: C# AES原始数据解密器
- **CSHAP_EVAL_AES_BASE64**: C# Eval AES Base64解密器
- **CSHAP_ASMX_AES_BASE64**: C# ASMX Web服务 AES Base64解密器

### ASP类型
- **ASP_BASE64**: ASP Base64解密器
- **ASP_RAW**: ASP原始数据解密器
- **ASP_XOR_BASE64**: ASP XOR Base64解密器
- **ASP_XOR_RAW**: ASP XOR原始数据解密器
- **ASP_EVAL_BASE64**: ASP Eval Base64解密器

## 快速使用

### Maven依赖

在你的项目中添加以下Maven依赖：

```xml
<dependency>
    <groupId>io.github.xsgwork</groupId>
    <artifactId>webshell-decryptor</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 使用示例

```java
public class DecryptExample {
    public static void main(String[] args) {
        // 示例：解密哥斯拉3.x版本的PHP XOR Base64加密数据
        String encryptedData = "your_encrypted_data_here";
        String password = "your_password_here";
        String key = "your_key_here";
        
        GodzillaV3Decryptor decryptor = WebshellDecryptorFacade.decryptGodzillaV3(GodzillaV3DecryptorType.PHP_XOR_BASE64);
            
        // 解密请求包
        String requestResult = decryptor.decryptRequest(encryptedData, password, key);
        System.out.println("请求包解密结果: " + requestResult);
            
        // 解密响应包
        String responseResult = decryptor.decryptResponse(encryptedData, password, key);
        System.out.println("响应包解密结果: " + responseResult);
    }
}
```

## 项目结构

```
src/main/java/com/xsg/decryptor/
├── WebshellDecryptorFacade.java              # 统一入口类
├── godzilla/
│   ├── v1/                                   # 哥斯拉1.x~2.x版本
│   │   ├── core/                             # 核心解密器实现
│   │   │   ├── base/                         # 抽象基类
│   │   │   └── ...                           # 具体解密器实现
│   │   └── enums/
│   │       └── GodzillaV1DecryptorType.java  # 解密器类型枚举
│   └── v3/                                   # 哥斯拉3.x~4.x版本
│       ├── core/                             # 核心解密器实现
│       │   ├── base/                         # 抽象基类
│       │   └── ...                           # 具体解密器实现
│       └── enums/
│           └── GodzillaV3DecryptorType.java  # 解密器类型枚举
└── util/                                     # 工具类
    ├── DecodeUtil.java                       # 解码工具
    ├── StringExtractUtil.java                # 字符串提取工具
    └── ByteTypeUtil.java                     # 字节类型检测工具
```

## 未来规划

- **扩展支持**: 计划支持冰蝎(Behinder)等其他主流Webshell工具

---

**注意**: 本工具仅用于合法的安全研究和渗透测试，请勿用于非法用途。使用者需要对使用本工具的行为承担相应的法律责任。
