## 项目简介

一个功能强大的Webshell流量解密工具，专门用于解密各种类型的Webshell通信数据，为安全分析人员提供便捷的流量分析能力。

目前支持哥斯拉(Godzilla) 全版本、冰蝎(Behinder) 全版本的流量解密。

## 支持的解密器类型

目前已实现全部的哥斯拉解密器和冰蝎解密器，涵盖以下类型，每个类型都有对应的单元测试类与测试数据：

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

## 冰蝎1.x~3.x版本解密器（4种）

### 脚本类型
- **JSP**: JSP脚本解密器
- **PHP**: PHP脚本解密器
- **ASP**: ASP脚本解密器
- **ASPX**: ASPX脚本解密器

## 冰蝎4.x版本解密器（6种）

### 加密类型
- **XOR**: XOR异或解密器
- **XOR_BASE64**: XOR Base64解密器
- **AES**: AES加密解密器
- **IMAGE**: 图片隐写解密器
- **JSON**: JSON格式解密器
- **AES_WITH_MAGIC**: 带魔术头的AES解密器

## 快速使用

### Maven依赖

在你的项目中添加以下Maven依赖：

```xml
<dependency>
    <groupId>io.github.xsgwork</groupId>
    <artifactId>webshell-decryptor</artifactId>
    <version>1.1.0</version>
</dependency>
```

### 使用示例

```java
public class DecryptExample {
    public static void main(String[] args) {
        // 示例1：解密哥斯拉3.x版本的PHP XOR Base64加密数据
        String godzillaData = "your_godzilla_encrypted_data_here";
        String godzillaPassword = "your_godzilla_password_here";
        String godzillaKey = "your_godzilla_key_here";

        GodzillaV2Decryptor godzillaDecryptor = WebshellDecryptorFacade.godzillaV2(GodzillaV2DecryptorType.PHP_XOR_BASE64);
        String godzillaResult = godzillaDecryptor.decryptRequest(godzillaData, godzillaPassword, godzillaKey);
        System.out.println("哥斯拉解密结果: " + godzillaResult);

        // 示例2：解密冰蝎4.x版本的AES加密数据
        String behinderData = "your_behinder_encrypted_data_here";
        String behinderPassword = "your_behinder_password_here";

        BehinderV2Decryptor behinderDecryptor = WebshellDecryptorFacade.behinderV2(BehinderV2DecryptorType.AES);
        String behinderResult = behinderDecryptor.decrypt(behinderData, behinderPassword);
        System.out.println("冰蝎解密结果: " + behinderResult);
    }
}
```

## 项目结构

```
src/main/java/io/github/xsgwork/decryptor/
├── WebshellDecryptorFacade.java              # 统一入口类
├── godzilla/
│   ├── v1/                                   # 哥斯拉1.x~2.x版本
│   │   ├── core/                             # 核心解密器实现
│   │   │   ├── base/                         # 抽象基类
│   │   │   └── ...                           # 具体解密器实现
│   │   └── GodzillaV1DecryptorType.java      # 解密器类型枚举
│   └── v2/                                   # 哥斯拉3.x~4.x版本
│       ├── core/                             # 核心解密器实现
│       │   ├── base/                         # 抽象基类
│       │   └── ...                           # 具体解密器实现
│       └── GodzillaV2DecryptorType.java      # 解密器类型枚举
├── behinder/
│   ├── v1/                                   # 冰蝎1.x~3.x版本
│   │   ├── core/                             # 核心解密器实现
│   │   │   ├── base/                         # 抽象基类
│   │   │   └── ...                           # 具体解密器实现
│   │   └── BehinderV1DecryptorType.java      # 解密器类型枚举
│   └── v2/                                   # 冰蝎4.x版本
│       ├── core/                             # 核心解密器实现
│       │   ├── base/                         # 抽象基类
│       │   └── ...                           # 具体解密器实现
│       └── BehinderV2DecryptorType.java      # 解密器类型枚举
└── util/                                     # 工具类
    ├── DecodeUtil.java                       # 解码工具
    ├── StringExtractUtil.java                # 字符串提取工具
    ├── ByteTypeUtil.java                     # 字节类型检测工具
    ├── GodzillaResultUtil.java               # 哥斯拉结果处理工具
    ├── BehinderResultUtil.java               # 冰蝎结果处理工具
    └── JavaDecompileUtil.java                # Java反编译工具
```

## 未来规划

- **扩展支持**: 计划支持蚁剑(AntSword)等其他主流Webshell工具

---

**注意**: 本工具仅用于合法的安全研究和渗透测试，请勿用于非法用途。使用者需要对使用本工具的行为承担相应的法律责任。
