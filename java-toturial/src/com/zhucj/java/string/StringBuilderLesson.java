package com.zhucj.java.string;

/**
 * =====================================================
 * Java StringBuilder 详解
 * =====================================================
 *
 * 【一、StringBuilder 是什么】
 * ---------------------------
 * StringBuilder 是 Java 中的一个非线程安全的可变字符序列，
 * 功能与 StringBuffer 完全相同，但去掉了 synchronized 修饰符，
 * 因此性能更高，是单线程环境下的首选。
 *
 * 【二、StringBuilder vs StringBuffer】
 * ------------------------------------
 * | 特性      | StringBuilder          | StringBuffer          |
 * |-----------|-------------------------|-----------------------|
 * | 线程安全   | 否（无synchronized）    | 是（有synchronized）  |
 * | 性能      | 高                      | 较低                  |
 * | JDK版本   | JDK 1.5+                | JDK 1.0+              |
 * | 使用场景  | 单线程环境              | 多线程环境            |
 *
 * 【三、StringBuilder 的适用场景】
 * -------------------------------
 * 1. 字符串拼接（尤其是循环中）
 * 2. 需要频繁修改字符串内容的场景
 * 3. 单线程或已同步的场景
 * 4. 构建较长的字符串
 *
 * =====================================================
 */
public class StringBuilderLesson {

    public static void main(String[] args) {
        // 运行各章节示例
        System.out.println("========== StringBuilder基础 ==========");
        stringBuilderBasics();

        System.out.println("\n========== 常用方法 ==========");
        stringBuilderMethods();

        System.out.println("\n========== append方法详解 ==========");
        stringBuilderAppend();

        System.out.println("\n========== insert方法 ==========");
        stringBuilderInsert();

        System.out.println("\n========== delete/replace ==========");
        stringBuilderDeleteReplace();

        System.out.println("\n========== reverse ==========");
        stringBuilderReverse();

        System.out.println("\n========== capacity方法 ==========");
        stringBuilderCapacity();

        System.out.println("\n========== 与StringBuffer对比 ==========");
        stringBuilderVsStringBuffer();

        System.out.println("\n========== 实战技巧 ==========");
        stringBuilderTips();

        System.out.println("\n========== 注意事项 ==========");
        stringBuilderCautions();
    }

    // ================================================
    // 一、StringBuilder基础
    // ================================================
    public static void stringBuilderBasics() {
        System.out.println("--- StringBuilder 创建方式 ---");

        // 【方式1】无参构造（默认容量16）
        StringBuilder sb1 = new StringBuilder();
        System.out.println("无参构造初始容量: " + sb1.capacity());  // 16

        // 【方式2】指定容量的构造
        StringBuilder sb2 = new StringBuilder(32);
        System.out.println("指定容量(32)初始容量: " + sb2.capacity());  // 32

        // 【方式3】从String创建（容量 = 字符串长度 + 16）
        StringBuilder sb3 = new StringBuilder("Hello");
        System.out.println("从String创建初始容量: " + sb3.capacity());  // 5 + 16 = 21
        System.out.println("内容: " + sb3.toString());  // "Hello"

        // 【方式4】从StringBuffer创建
        StringBuffer sbf = new StringBuffer("World");
        StringBuilder sbd4 = new StringBuilder(sbf);
        System.out.println("从StringBuffer创建: " + sbd4.toString());

        // 【方式5】从CharSequence创建
        StringBuilder sb5 = new StringBuilder((CharSequence) "Java");
        System.out.println("从CharSequence创建: " + sb5);

        // 链式调用
        StringBuilder chained = new StringBuilder()
                .append("Java")
                .append(" ")
                .append(8)  // JDK7+
                .append(" 新特性");
        System.out.println("链式调用: " + chained.toString());
    }

    // ================================================
    // 二、常用方法详解
    // ================================================
    public static void stringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello");

        System.out.println("初始内容: \"" + sb + "\"");
        System.out.println("初始长度: " + sb.length());
        System.out.println("初始容量: " + sb.capacity());

        // ===== 1. append() =====
        System.out.println("\n--- append() ---");
        StringBuilder sb1 = new StringBuilder("Hello");
        sb1.append(" World");
        System.out.println("append后: " + sb1);  // "Hello World"

        // ===== 2. toString() =====
        System.out.println("\n--- toString() ---");
        StringBuilder sb2 = new StringBuilder("Hello");
        String str = sb2.toString();
        System.out.println("toString: " + str + ", 类型: " + str.getClass().getSimpleName());

        // ===== 3. length() vs capacity() =====
        System.out.println("\n--- length() vs capacity() ---");
        StringBuilder sb3 = new StringBuilder();
        System.out.println("空SB - length: " + sb3.length() + ", capacity: " + sb3.capacity());

        sb3.append("ABC");
        System.out.println("添加ABC后 - length: " + sb3.length() + ", capacity: " + sb3.capacity());

        // ===== 4. charAt() =====
        System.out.println("\n--- charAt() ---");
        StringBuilder sb4 = new StringBuilder("Hello");
        System.out.println("charAt(0): " + sb4.charAt(0));  // 'H'

        // ===== 5. setCharAt() =====
        System.out.println("\n--- setCharAt() ---");
        StringBuilder sb5 = new StringBuilder("Hello");
        sb5.setCharAt(0, 'J');
        System.out.println("setCharAt(0, 'J')后: " + sb5);  // "Jello"

        // ===== 6. indexOf() / lastIndexOf() - JDK1.5新增 =====
        System.out.println("\n--- indexOf() / lastIndexOf() ---");
        StringBuilder sb6 = new StringBuilder("Hello World");
        System.out.println("indexOf(\"World\"): " + sb6.indexOf("World"));  // 6
        System.out.println("indexOf(\"o\"): " + sb6.indexOf("o"));  // 4
        System.out.println("lastIndexOf(\"o\"): " + sb6.lastIndexOf("o"));  // 7

        // ===== 7. substring() =====
        System.out.println("\n--- substring() ---");
        StringBuilder sb7 = new StringBuilder("Hello World");
        System.out.println("substring(6): " + sb7.substring(6));  // "World"
        System.out.println("substring(0, 5): " + sb7.substring(0, 5));  // "Hello"

        // 注意：substring返回的是新的String，不是StringBuilder
        String sub = sb7.substring(0, 5);
        System.out.println("substring类型: " + sub.getClass().getSimpleName());
    }

    // ================================================
    // 三、append方法详解
    // ================================================
    public static void stringBuilderAppend() {
        System.out.println("--- append 方法详解 ---");

        StringBuilder sb = new StringBuilder();

        // 1. append(String str)
        sb.append("Hello");
        System.out.println("append(String): " + sb);

        // 2. append(char[] chars)
        sb.append(new char[]{' ', 'W', 'o', 'r', 'l', 'd'});
        System.out.println("append(char[]): " + sb);

        // 3. append(char[] chars, int offset, int len)
        StringBuilder sb2 = new StringBuilder();
        char[] chars = {'A', 'B', 'C', 'D', 'E'};
        sb2.append(chars, 1, 3);  // "BCD"
        System.out.println("append(char[], offset, len): " + sb2);

        // 4. append(CharSequence cs)
        sb2.append((CharSequence) "FGH");
        System.out.println("append(CharSequence): " + sb2);

        // 5. append(CharSequence cs, int start, int end)
        sb2.append((CharSequence) "IJKLM", 0, 2);  // "IJ"
        System.out.println("append(CharSequence, start, end): " + sb2);

        // 6. append(boolean/int/long/float/double/char)
        StringBuilder sb3 = new StringBuilder("值: ");
        sb3.append(true)
           .append(", ")
           .append(123)
           .append(", ")
           .append(3.14)
           .append(", ")
           .append('A');
        System.out.println("append各种类型: " + sb3);

        // 7. append(StringBuilder) - JDK11新增
        StringBuilder sb4 = new StringBuilder("Hello");
        StringBuilder sb5 = new StringBuilder(" World");
        sb4.append(sb5);
        System.out.println("append(StringBuilder): " + sb4);

        // 8. appendNull - append null
        StringBuilder sb6 = new StringBuilder("Hello");
        sb6.append((String) null);
        System.out.println("append(null String): " + sb6);  // "Hellonull"

        // 9. appendCodePoint
        StringBuilder sb7 = new StringBuilder("Hello");
        sb7.appendCodePoint(0x1F600);  // 😀
        System.out.println("appendCodePoint(0x1F600): " + sb7);
    }

    // ================================================
    // 四、insert方法
    // ================================================
    public static void stringBuilderInsert() {
        System.out.println("--- insert 方法 ---");

        // insert(int offset, String str)
        StringBuilder sb = new StringBuilder("HelloWorld");
        sb.insert(5, " ");  // "Hello World"
        System.out.println("insert(5, \" \"): " + sb);

        // 支持多种类型
        StringBuilder sb2 = new StringBuilder("ABC");
        sb2.insert(1, 123);
        System.out.println("insert(int): " + sb2);  // "A123BC"

        StringBuilder sb3 = new StringBuilder("ABC");
        sb3.insert(1, new char[]{'X', 'Y', 'Z'});
        System.out.println("insert(char[]): " + sb3);  // "AXYZBC"

        // insert(offset, CharSequence) - JDK5
        StringBuilder sb4 = new StringBuilder("ABC");
        sb4.insert(1, (CharSequence) "XYZ");
        System.out.println("insert(CharSequence): " + sb4);  // "AXYZBC"

        // insert(offset, CharSequence, start, end) - JDK5
        StringBuilder sb5 = new StringBuilder("ABC");
        sb5.insert(1, (CharSequence) "XYZ", 0, 2);  // "AXYBC"
        System.out.println("insert(CharSequence, start, end): " + sb5);
    }

    // ================================================
    // 五、delete和replace
    // ================================================
    public static void stringBuilderDeleteReplace() {
        System.out.println("--- delete 和 replace ---");

        // 【delete】
        StringBuilder sb1 = new StringBuilder("HelloWorld");

        // delete(int start, int end) - 删除 [start, end)
        sb1.delete(5, 11);
        System.out.println("delete(5, 11): " + sb1);  // "Hello"

        // deleteCharAt(int index)
        StringBuilder sb2 = new StringBuilder("Hello");
        sb2.deleteCharAt(1);
        System.out.println("deleteCharAt(1): " + sb2);  // "Hllo"

        // 清空内容
        StringBuilder sb3 = new StringBuilder("Hello");
        sb3.delete(0, sb3.length());
        System.out.println("清空: \"" + sb3 + "\"");

        // 【replace】
        StringBuilder sb4 = new StringBuilder("HelloWorld");
        sb4.replace(5, 11, " Java");
        System.out.println("replace(5, 11, \" Java\"): " + sb4);  // "Hello Java"

        // 【setLength】
        StringBuilder sb5 = new StringBuilder("HelloWorld");
        sb5.setLength(5);
        System.out.println("setLength(5): " + sb5);  // "Hello"

        // 【setCharAt】
        StringBuilder sb6 = new StringBuilder("Hello");
        sb6.setCharAt(1, 'a');
        System.out.println("setCharAt(1, 'a'): " + sb6);  // "Hallo"
    }

    // ================================================
    // 六、reverse
    // ================================================
    public static void stringBuilderReverse() {
        System.out.println("--- reverse ---");

        StringBuilder sb1 = new StringBuilder("Hello");
        sb1.reverse();
        System.out.println("\"Hello\" reverse: " + sb1);  // "olleH"

        // 回文判断
        String str = "上海自来水来自海上";
        StringBuilder sb2 = new StringBuilder(str);
        sb2.reverse();
        System.out.println("\"" + str + "\" reverse: \"" + sb2 + "\"");
        System.out.println("是回文: " + str.equals(sb2.toString()));
    }

    // ================================================
    // 七、capacity相关方法
    // ================================================
    public static void stringBuilderCapacity() {
        System.out.println("--- capacity 相关 ---");

        // 初始容量
        StringBuilder sb = new StringBuilder();
        System.out.println("无参构造: length=" + sb.length() + ", capacity=" + sb.capacity());

        StringBuilder sb2 = new StringBuilder("Hello");
        System.out.println("String参数: length=" + sb2.length() + ", capacity=" + sb2.capacity());

        // ensureCapacity
        StringBuilder sb3 = new StringBuilder();
        sb3.ensureCapacity(100);
        System.out.println("ensureCapacity(100): " + sb3.capacity());  // 至少100

        // 扩容过程
        System.out.println("\n--- 扩容过程 ---");
        StringBuilder sb4 = new StringBuilder();
        System.out.println("初始: length=" + sb4.length() + ", capacity=" + sb4.capacity());

        for (int i = 0; i <= 17; i++) {
            sb4.append("a");
            if (i == 16 || i == 17) {
                System.out.println("添加第" + (i + 1) + "个: length=" + sb4.length() + ", capacity=" + sb4.capacity());
            }
        }

        // trimToSize
        StringBuilder sb5 = new StringBuilder(100);
        sb5.append("Hi");
        System.out.println("\ntrimToSize前: capacity=" + sb5.capacity());
        sb5.trimToSize();
        System.out.println("trimToSize后: capacity=" + sb5.capacity());
    }

    // ================================================
    // 八、StringBuilder vs StringBuffer
    // ================================================
    public static void stringBuilderVsStringBuffer() {
        System.out.println("--- StringBuilder vs StringBuffer ---");

        System.out.println("\n【相同点】");
        System.out.println("  1. 都是可变字符序列");
        System.out.println("  2. API几乎完全相同");
        System.out.println("  3. 容量管理策略相同");

        System.out.println("\n【不同点】");
        System.out.println("  | 特性     | StringBuilder | StringBuffer |");
        System.out.println("  |----------|---------------|--------------|");
        System.out.println("  | 线程安全  | 否            | 是           |");
        System.out.println("  | 性能     | 高            | 较低         |");
        System.out.println("  | JDK版本  | 1.5+          | 1.0+         |");

        System.out.println("\n【性能对比】");
        int count = 100000;

        // StringBuilder
        long start = System.nanoTime();
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sbd.append("a");
        }
        long sbTime = System.nanoTime() - start;

        // StringBuffer
        start = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sbf.append("a");
        }
        long sbfTime = System.nanoTime() - start;

        System.out.println("StringBuilder: " + sbTime / 1000000 + " ms");
        System.out.println("StringBuffer:  " + sbfTime / 1000000 + " ms");
        System.out.println("性能差异: 约 " + String.format("%.1f", (double) sbfTime / sbTime) + " 倍");

        System.out.println("\n【选择建议】");
        System.out.println("  单线程环境: StringBuilder（推荐）");
        System.out.println("  多线程环境: StringBuffer 或自行同步");
    }

    // ================================================
    // 九、实战技巧
    // ================================================
    public static void stringBuilderTips() {
        System.out.println("--- 实战技巧 ---");

        // 【技巧1】构建SQL语句
        System.out.println("\n1. 构建SQL语句:");
        String[] names = {"Alice", "Bob", "Charlie"};
        StringBuilder sql = new StringBuilder("SELECT * FROM users WHERE name IN (");
        for (int i = 0; i < names.length; i++) {
            sql.append("'").append(names[i]).append("'");
            if (i < names.length - 1) sql.append(", ");
        }
        sql.append(")");
        System.out.println(sql);

        // 【技巧2】JSON字符串构建
        System.out.println("\n2. 构建JSON字符串:");
        StringBuilder json = new StringBuilder("{");
        json.append("\"name\":").append("\"Tom\",");
        json.append("\"age\":").append(25);
        json.append("}");
        System.out.println(json);

        // 【技巧3】文件路径拼接
        System.out.println("\n3. 路径拼接:");
        String[] parts = {"home", "user", "documents", "file.txt"};
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            path.append(parts[i]);
            if (i < parts.length - 1) path.append("/");
        }
        System.out.println(path);

        // 【技巧4】StringBuilder预分配容量
        System.out.println("\n4. 预分配容量（性能优化）:");
        int expectedLength = 1000;
        StringBuilder sb = new StringBuilder(expectedLength);
        System.out.println("预分配后capacity: " + sb.capacity());

        // 【技巧5】链式调用实现复杂逻辑
        System.out.println("\n5. 链式调用:");
        String result = new StringBuilder()
                .append("Hello")
                .append(" ")
                .append("World")
                .replace(0, 5, "Hi")
                .insert(2, ",")
                .delete(3, 4)
                .toString()
                .toUpperCase();
        System.out.println("链式调用结果: " + result);

        // 【技巧6】字符串反转（回文检测）
        System.out.println("\n6. 回文检测:");
        String word = "racecar";
        boolean isPalindrome = new StringBuilder(word).reverse().toString().equals(word);
        System.out.println("\"" + word + "\" 是回文: " + isPalindrome);
    }

    // ================================================
    // 十、注意事项
    // ================================================
    public static void stringBuilderCautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】非线程安全
        System.out.println("\n1. 非线程安全:");
        System.out.println("   StringBuilder没有synchronized修饰");
        System.out.println("   多线程环境下不要使用StringBuilder");
        System.out.println("   多线程共享StringBuilder需要额外同步");

        // 【2】与String相互转换
        System.out.println("\n2. 与String相互转换:");
        StringBuilder sb1 = new StringBuilder("Hello");
        String str = sb1.toString();  // SB -> String

        StringBuilder sb2 = new StringBuilder(str);  // String -> SB
        System.out.println("转换: " + str + " <-> " + sb2);

        // 【3】线程安全性验证
        System.out.println("\n3. 安全性对比:");

        // 【4】容量预分配
        System.out.println("\n4. 容量预分配建议:");
        System.out.println("   如果知道最终长度，预先分配容量");
        System.out.println("   可以避免多次扩容带来的性能开销");

        // 【5】空判断
        System.out.println("\n5. 空判断:");
        StringBuilder empty = new StringBuilder();
        System.out.println("length() == 0: " + (empty.length() == 0));
        System.out.println("isEmpty(): " + empty.toString().isEmpty());

        // 【6】索引越界
        System.out.println("\n6. 索引越界:");
        try {
            StringBuilder sb = new StringBuilder("Hello");
            sb.charAt(10);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("越界抛出: StringIndexOutOfBoundsException");
        }

        // 【7】不要滥用StringBuilder
        System.out.println("\n7. 不要滥用:");
        System.out.println("   String str = \"Hello\" + \" World\";  // 简单拼接用+即可");
        System.out.println("   编译器会优化为StringBuilder");
        System.out.println("   只有循环或复杂拼接才需要显式使用StringBuilder");
    }
}
