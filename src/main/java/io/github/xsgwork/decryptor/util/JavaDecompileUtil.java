package io.github.xsgwork.decryptor.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.benf.cfr.reader.api.CfrDriver;
import org.benf.cfr.reader.api.OutputSinkFactory;

import java.io.File;
import java.util.*;

/**
 * Java类文件反编译工具类
 * 使用CFR（Class File Reader）作为反编译引擎，
 * CFR是一个优秀的Java反编译器，支持现代Java特性。
 *
 * @author xsg
 * @version 1.0.0
 */
public class JavaDecompileUtil {

    /**
     * 反编译字节数组为Java源代码
     * 将Java字节码字节数组反编译为可读的Java源代码。
     * 适用于从网络传输或内存中获取的字节码数据。
     *
     * @param classBytes Java字节码字节数组
     * @return 反编译后的Java源代码字符串
     */
    public static String decompileBytes(byte[] classBytes) {
        if (classBytes == null || classBytes.length == 0) {
            throw new IllegalArgumentException("字节数组不能为空");
        }
        // 检查是否为有效的Java字节码
        if (!ByteTypeUtil.isJavaClassFile(classBytes)) {
            throw new IllegalArgumentException("提供的字节数组不是有效的Java字节码文件");
        }

        // 创建临时文件
        File tempFile = new File(FileUtil.getTmpDir(), IdUtil.fastSimpleUUID() + ".class");
        try {
            // 将字节数组写入临时文件
            FileUtil.writeBytes(classBytes, tempFile);
            // 反编译临时文件
            return decompileFile(tempFile.getPath());
        } finally {
            FileUtil.del(tempFile);
        }
    }

    /**
     * 反编译指定的.class文件
     * 读取指定路径的.class文件并反编译为Java源代码。
     *
     * @param classFilePath .class文件的完整路径
     * @return 反编译后的Java源代码字符串
     */
    public static String decompileFile(String classFilePath) {
        if (StrUtil.isBlank(classFilePath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        }

        File classFile = new File(classFilePath);
        if (!classFile.exists()) {
            throw new IllegalArgumentException("文件不存在: " + classFilePath);
        }
        if (!classFile.isFile()) {
            throw new IllegalArgumentException("指定路径不是文件: " + classFilePath);
        }

        // 使用CFR进行反编译
        StringBuilder result = new StringBuilder();

        // 配置CFR选项
        Map<String, String> options = new HashMap<>();
        options.put("showversion", "false");
        options.put("hidelangimports", "false");
        options.put("hideutf", "false");
        options.put("innerclasses", "true");
        options.put("skipbatchinnerclasses", "false");

        // 创建输出收集器
        OutputSinkFactory outputSinkFactory = new OutputSinkFactory() {
            @Override
            public List<SinkClass> getSupportedSinks(SinkType sinkType, Collection<SinkClass> available) {
                return available.stream()
                        .filter(sinkClass -> sinkClass == SinkClass.STRING)
                        .collect(java.util.stream.Collectors.toList());
            }

            @Override
            public <T> Sink<T> getSink(SinkType sinkType, SinkClass sinkClass) {
                if (sinkType == SinkType.JAVA && sinkClass == SinkClass.STRING) {
                    return content -> result.append(content.toString());
                }
                return content -> {
                    // 忽略其他类型的输出
                };
            }
        };

        // 执行反编译
        CfrDriver driver = new CfrDriver.Builder()
                .withOptions(options)
                .withOutputSink(outputSinkFactory)
                .build();

        driver.analyse(Collections.singletonList(classFilePath));

        return result.toString();
    }
}