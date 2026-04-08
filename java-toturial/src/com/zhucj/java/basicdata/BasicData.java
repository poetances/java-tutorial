package com.zhucj.java.basicdata;

/**
 * Java 基本数据类型教学资料
 *
 * ==================== 一、注释 ====================
 * Java 中有三种注释方式：
 *
 * 1. 单行注释：// 注释内容
 * 2. 多行注释：斜杠星号 注释内容 星号斜杠
 * 3. 文档注释：斜杠星号星号 文档内容 星号斜杠 （可被 javadoc 提取生成文档）
 *
 * 注意：/// 在标准 Java 中等同于 //，没有特殊含义
 *       注释颜色是 IDE 语法高亮功能，与语言本身无关
 */
public class BasicData {
    // 定义常量。常量通常以final修饰
    final double PI = 3.14;

    int age;
    String name;

    public static void main(String[] args) {
        printLiterals();       // 字面量示例
        printIntegerTypes();   // 整数类型
        printFloatTypes();     // 浮点类型
        printCharType();       // 字符类型
        printBooleanType();    // 布尔类型
        printZeroValue();      // 零值
    }

    /**
     * 字面量示例
     *
     * 字面量是代码中直接写出的固定值
     */
    public static void printLiterals() {
        System.out.println("=== 字面量 ===");

        // 1. 整数字面量
        int decimal = 100;           // 十进制
        int binary = 0b1100;         // 二进制（0b开头）
        int octal = 0144;            // 八进制（0开头）
        int hex = 0x64;              // 十六进制（0x开头）

        // Java 7+ 可用下划线增强可读性
        int withUnderscore = 1_000_000;

        System.out.println("十进制: " + decimal);
        System.out.println("二进制: " + binary);
        System.out.println("八进制: " + octal);
        System.out.println("十六进制: " + hex);
        System.out.println("下划线: " + withUnderscore);

        // 2. 浮点字面量
        double d1 = 3.14;
        double d2 = 3.14e2;          // 科学计数法 = 314.0
        float f1 = 3.14f;            // float 类型需加 f 后缀

        System.out.println("浮点: " + d1 + ", 科学计数: " + d2 + ", float: " + f1);

        // 3. 字符字面量
        char c1 = 'A';
        char c2 = '\n';              // 转义字符
        char c3 = '\u0041';          // Unicode 编码

        System.out.println("字符: " + c1 + ", Unicode: " + c3);

        // 4. 字符串字面量
        String str = "Hello World";
        System.out.println("字符串: " + str);

        // 5. 布尔字面量
        boolean flag = true;
        System.out.println("布尔: " + flag);

        // 6. 空字面量
        String nullStr = null;
        System.out.println("空: " + nullStr);

        System.out.println();
    }

    /**
     * 整数类型
     *
     * byte:  1 字节, 8 位,  -128 ~ 127
     * short: 2 字节, 16 位, -32,768 ~ 32,767
     * int:   4 字节, 32 位, -2,147,483,648 ~ 2,147,483,647（最常用）
     * long:  8 字节, 64 位, 需要加 L 后缀
     */
    public static void printIntegerTypes() {
        System.out.println("=== 整数类型 ===");

        byte b = 100;
        short s = 1000;
        int i = 100000;
        long l = 1000000L;  // 必须加 L 后缀

        System.out.println("byte: " + b + " (1字节)");
        System.out.println("short: " + s + " (2字节)");
        System.out.println("int: " + i + " (4字节, 默认)");
        System.out.println("long: " + l + " (8字节)");
        System.out.println();
    }

    /**
     * 浮点类型
     *
     * float:  4 字节, 单精度, 需要加 f 后缀
     * double: 8 字节, 双精度, 默认类型
     */
    public static void printFloatTypes() {
        System.out.println("=== 浮点类型 ===");

        float f = 3.14f;
        double d = 3.14;

        System.out.println("float: " + f + " (4字节)");
        System.out.println("double: " + d + " (8字节, 默认)");
        System.out.println();
    }

    /**
     * 字符类型
     *
     * char: 2 字节, 16 位, 使用 UTF-16 编码
     *
     * 为什么是 2 字节？
     * Java 设计时 Unicode 还未扩展，基本平面（BMP）有 65535 个字符，
     * 所以用 2 字节存储。超出范围的字符需要两个 char 表示。
     *
     * 与 C 语言 char（1字节）的区别：
     * - C 的 char 是历史遗留，1 字节只能存 0-255
     * - Java 的 char 直接支持 Unicode 基本平面
     * - 现代语言（如 Swift）使用 UTF-8 字符串，内部自动处理编码
     */
    public static void printCharType() {
        System.out.println("=== 字符类型 ===");

        char c1 = 'A';
        char c2 = '中';
        char c3 = '\u0041';  // Unicode 编码

        System.out.println("字符 A: " + c1);
        System.out.println("字符 中: " + c2);
        System.out.println("Unicode \\u0041: " + c3);
        System.out.println("char 字节数: 2");
        System.out.println();
    }

    /**
     * 布尔类型
     *
     * boolean: 存储 true 或 false
     * 注意：boolean 在 JVM 中通常是 1 个字节，但具体实现由 JVM 决定
     */
    public static void printBooleanType() {
        System.out.println("=== 布尔类型 ===");

        boolean flag = true;
        boolean flag2 = false;

        System.out.println("true: " + flag);
        System.out.println("false: " + flag2);
        System.out.println();
    }

    /*
    Go vs Java 对比
    特性	Go	Java
    局部变量	✅ 零值可用	❌ 必须初始化
    成员变量	✅ 零值可用	✅ 零值可用
    * **/
    public static void printZeroValue() {
        // java中成员变量的零值可用。局部变量不行
        BasicData basicData = new BasicData();
        System.out.println(basicData.age);  // 0
        System.out.println(basicData.name); // null
    }
}