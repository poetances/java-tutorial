package com.zhucj.tutorial;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 一个简单的 JUnit 5 测试示例。
 * 测试 Apache Commons Lang3 的 StringUtils 工具类。
 *
 * 运行方式（IntelliJ）：
 *   右键 -> Run 'StringUtilsTest'
 * 运行方式（Maven 命令行）：
 *   mvn test
 */
public class StringUtilsTest {

    @Test
    void testIsBlank() {
        // "" 和 "   " 都算空白
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank("   "));
        // 普通字符串不算空白
        assertTrue(!StringUtils.isBlank("hello"));
    }

    @Test
    void testCapitalize() {
        // 首字母大写
        assertEquals("Hello", StringUtils.capitalize("hello"));
        assertEquals("Java", StringUtils.capitalize("java"));
    }

    @Test
    void testReverse() {
        // 字符串反转
        assertEquals("avaJ", StringUtils.reverse("Java"));
        assertEquals("olleh", StringUtils.reverse("hello"));
    }

    @Test
    void testJoin() {
        // 拼接数组，类似 iOS 的 componentsJoinedByString
        String[] words = {"Java", "is", "fun"};
        assertEquals("Java-is-fun", StringUtils.join(words, "-"));
        assertEquals("Java is fun", StringUtils.join(words, " "));
    }
}
