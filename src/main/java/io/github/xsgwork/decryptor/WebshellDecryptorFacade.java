package io.github.xsgwork.decryptor;

import io.github.xsgwork.decryptor.behinder.v1.core.base.BehinderV1Decryptor;
import io.github.xsgwork.decryptor.behinder.v1.enums.BehinderV1DecryptorType;
import io.github.xsgwork.decryptor.godzilla.v1.core.base.GodzillaV1Decryptor;
import io.github.xsgwork.decryptor.godzilla.v1.enums.GodzillaV1DecryptorType;
import io.github.xsgwork.decryptor.godzilla.v2.core.base.GodzillaV2Decryptor;
import io.github.xsgwork.decryptor.godzilla.v2.enums.GodzillaV2DecryptorType;

/**
 * Webshell解密工具主入口类
 * 提供简化的API供外部使用
 */
public class WebshellDecryptorFacade {

    /**
     * 哥斯拉1.x~2.x版本流量解密
     */
    public static GodzillaV1Decryptor decryptGodzillaV1(GodzillaV1DecryptorType type) {
        return GodzillaV1DecryptorType.getDecryptor(type);
    }

    /**
     * 哥斯拉3.x~4.x版本流量解密
     */
    public static GodzillaV2Decryptor decryptGodzillaV2(GodzillaV2DecryptorType type) {
        return GodzillaV2DecryptorType.getDecryptor(type);
    }

    /**
     * 冰蝎1.x~3.x版本流量解密
     */
    public static BehinderV1Decryptor decryptBehinderV1(BehinderV1DecryptorType type) {
        return BehinderV1DecryptorType.getDecryptor(type);
    }
}