package com.zhucj.java.numbers;

/**
 * Java 数值处理
 *
 * <p>本类涵盖 Java 数值相关的核心知识点：</p>
 * <ul>
 *   <li>基本数据类型 vs 包装类型</li>
 *   <li>自动装箱与拆箱</li>
 *   <li>数值转换</li>
 *   <li>常用数值方法</li>
 *   <li>Math 工具类</li>
 *   <li>大数值处理</li>
 * </ul>
 *
 * <p>基本数据类型 vs 包装类型：</p>
 * <table border="1">
 *   <tr><th>基本类型</th><th>包装类型</th><th>字节数</th></tr>
 *   <tr><td>byte</td><td>Byte</td><td>1</td></tr>
 *   <tr><td>short</td><td>Short</td><td>2</td></tr>
 *   <tr><td>int</td><td>Integer</td><td>4</td></tr>
 *   <tr><td>long</td><td>Long</td><td>8</td></tr>
 *   <tr><td>float</td><td>Float</td><td>4</td></tr>
 *   <tr><td>double</td><td>Double</td><td>8</td></tr>
 *   <tr><td>char</td><td>Character</td><td>2</td></tr>
 *   <tr><td>boolean</td><td>Boolean</td><td>1</td></tr>
 * </table>
 *
 *  继承关系：
 *  Numbers ： Object
 *  ---Byte: Numbers
 *  ---Short: Numbers
 *  ---Integer: Numbers
 *  ---Long: Numbers
 *  ---Float: Numbers
 *  ---Double: Numbers
 *  Boolean: Object
 *  Character: Object
 *
 * @author zhucj
 * @version 1.0
 * @since 2026-04-06
 * @see <a href="https://docs.oracle.com/javase/tutorial/java/data/index.html">Oracle: 数字和字符串</a>
 */
public class NumbersLesson {

    public static void main(String[] args) {
        // 基本类型 vs 包装类型
        basicVsWrapper();

        // 自动装箱与拆箱
        autoBoxingUnboxing();

        // 数值转换
        numberConversion();

        // 常用数值方法
        integerMethods();

        // Math 工具类
        mathDemo();

        // 大数值处理
        bigNumberDemo();
    }

    /**
     * 基本类型与包装类型对比
     */
    public static void basicVsWrapper() {
        System.out.println("=== 基本类型 vs 包装类型 ===");

        // 基本类型：直接存储值
        int a = 100;
        double b = 3.14;

        // 包装类型：是对象，有方法
        Integer objA = 100;
        Double objB = 3.14;

        // 包装类型的常用方法
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("Double.isNaN(0.0/0.0) = " + Double.isNaN(0.0/0.0));
        System.out.println("Double.isInfinite(1.0/0.0) = " + Double.isInfinite(1.0/0.0));

        System.out.println();
    }

    /**
     * 自动装箱与拆箱
     *
     * <ul>
     *   <li>装箱( Boxing)：基本类型 → 包装类型</li>
     *   <li>拆箱(Unboxing)：包装类型 → 基本类型</li>
     * </ul>
     */
    public static void autoBoxingUnboxing() {
        System.out.println("=== 自动装箱与拆箱 ===");

        // 装箱：基本类型 → 包装类型
        Integer obj1 = 100;          // 自动装箱 (Integer.valueOf(100))
        Double obj2 = 3.14;           // 自动装箱

        // 拆箱：包装类型 → 基本类型
        int num = obj1;              // 自动拆箱 (obj1.intValue())

        // 运算时自动拆箱
        int sum = obj1 + 200;        // obj1 自动拆箱为 int

        System.out.println("obj1 = " + obj1);
        System.out.println("num = " + num);
        System.out.println("sum = " + sum);

        // 注意事项：空指针异常
        Integer nullObj = null;
        // int n = nullObj;          // ❌ NullPointerException！拆箱 null

        System.out.println();
    }

    /**
     * 数值转换
     *
     * <p>类型转换优先级（从低到高）：</p>
     * <ol>
     *   <li>byte, short, char → int → long → float → double</li>
     * </ol>
     */
    public static void numberConversion() {
        System.out.println("=== 数值转换 ===");

        // 自动类型转换（小 → 大）
        int i = 100;
        long l = i;          // int → long
        double d = l;         // long → double
        System.out.println("自动转换: int→long→double = " + d);

        // 强制类型转换（大 → 小）
        double big = 3.99;
        int small = (int) big; // double → int，结果是 3（小数部分丢失）
        System.out.println("强制转换: double→int = " + small);

        // 字符串与数值互转
        String str = "123";
        int parsed = Integer.parseInt(str);      // String → int
        Integer wrapper = Integer.valueOf(str);   // String → Integer
        String toStr = String.valueOf(456);       // int → String
        String toStr2 = Integer.toString(789);    // int → String
        System.out.println("parseInt(\"123\") = " + parsed);
        System.out.println("String.valueOf(456) = " + toStr);

        System.out.println();
    }

    /**
     * Integer 常用方法
     */
    public static void integerMethods() {
        System.out.println("=== Integer 常用方法 ===");

        String s = "100";
        int i = 50;

        System.out.println("Integer.parseInt(\"100\") = " + Integer.parseInt(s));
        System.out.println("Integer.valueOf(\"100\") = " + Integer.valueOf(s));
        System.out.println("Integer.toBinaryString(100) = " + Integer.toBinaryString(100));
        System.out.println("Integer.toHexString(255) = " + Integer.toHexString(255));
        System.out.println("Integer.compare(10, 20) = " + Integer.compare(10, 20));
        System.out.println("Integer.max(10, 20) = " + Integer.max(10, 20));
        System.out.println("Integer.min(10, 20) = " + Integer.min(10, 20));

        System.out.println();
    }

    public static void longMethods() {
        System.out.println("=== Long 常用方法 ===");
        String s = "100";
        long l = 50L;

        System.out.println("Long.parseLong(\"100\") = " + Long.parseLong(s));
    }

    /**
     * Math 工具类
     */
    public static void mathDemo() {
        System.out.println("=== Math 工具类 ===");

        System.out.println("Math.abs(-10) = " + Math.abs(-10));
        System.out.println("Math.max(10, 20) = " + Math.max(10, 20));
        System.out.println("Math.min(10, 20) = " + Math.min(10, 20));
        System.out.println("Math.ceil(3.2) = " + Math.ceil(3.2));
        System.out.println("Math.floor(3.8) = " + Math.floor(3.8));
        System.out.println("Math.round(3.5) = " + Math.round(3.5));
        System.out.println("Math.sqrt(16) = " + Math.sqrt(16));
        System.out.println("Math.pow(2, 3) = " + Math.pow(2, 3));
        System.out.println("Math.random() = " + Math.random());
        System.out.println("Math.PI = " + Math.PI);
        System.out.println("Math.E = " + Math.E);

        System.out.println();
    }

    /**
     * 大数值处理
     *
     * <p>当数值超过基本类型范围时，使用 BigInteger 和 BigDecimal：</p>
     */
    public static void bigNumberDemo() {
        System.out.println("=== 大数值处理 ===");

        java.math.BigInteger bigInt = new java.math.BigInteger("12345678901234567890");
        java.math.BigDecimal bigDec = new java.math.BigDecimal("3.1415926535897932384626");

        System.out.println("BigInteger: " + bigInt);
        System.out.println("BigDecimal: " + bigDec);

        // 大数值运算
        java.math.BigInteger a = new java.math.BigInteger("11111111111111111111");
        java.math.BigInteger b = new java.math.BigInteger("22222222222222222222");
        System.out.println("BigInteger + : " + a.add(b));

        System.out.println();
    }
}
