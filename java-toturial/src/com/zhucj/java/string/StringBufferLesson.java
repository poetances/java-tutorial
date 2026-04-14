package com.zhucj.java.string;

/**
 * =====================================================
 * Java StringBuffer 详解
 * =====================================================
 *
 * 【一、StringBuffer 是什么】
 * -------------------------
 * StringBuffer 是 Java 中的一个线程安全的可变字符序列，
 * 可以在字符串两端进行append、insert、delete等操作而不会创建新对象。
 *
 * 【二、为什么需要 StringBuffer】
 * ------------------------------
 * String 的问题：
 * - String 不可变，每次拼接都会创建新对象
 * - 循环中拼接字符串效率极低
 *
 * StringBuffer 的优势：
 * - 可变字符序列，在原对象上修改
 * - 避免了频繁创建对象带来的性能开销
 *
 * 【三、StringBuffer vs StringBuilder vs String】
 * ------------------------------------------------
 * | 特性      | String       | StringBuffer       | StringBuilder   |
 * |-----------|--------------|--------------------|-----------------|
 * | 可变性     | 不可变       | 可变               | 可变            |
 * | 线程安全   | 安全         | 安全（synchronized）| 不安全          |
 * | 性能      | 低           | 较低               | 高              |
 * | 使用场景   | 字符串固定   | 多线程环境         | 单线程环境      |
 *
 * =====================================================
 */
public class StringBufferLesson {

    public static void main(String[] args) {
        // 运行各章节示例
        System.out.println("========== StringBuffer基础 ==========");
        stringBufferBasics();

        System.out.println("\n========== 常用方法 ==========");
        stringBufferMethods();

        System.out.println("\n========== append方法详解 ==========");
        stringBufferAppend();

        System.out.println("\n========== insert方法 ==========");
        stringBufferInsert();

        System.out.println("\n========== delete/replace ==========");
        stringBufferDeleteReplace();

        System.out.println("\n========== reverse ==========");
        stringBufferReverse();

        System.out.println("\n========== capacity方法 ==========");
        stringBufferCapacity();

        System.out.println("\n========== 性能对比 ==========");
        stringBufferPerformance();

        System.out.println("\n========== 注意事项 ==========");
        stringBufferCautions();
    }

    // ================================================
    // 一、StringBuffer基础
    // ================================================
    public static void stringBufferBasics() {
        System.out.println("--- StringBuffer 创建方式 ---");

        // 【方式1】无参构造（默认容量16）
        StringBuffer sb1 = new StringBuffer();
        System.out.println("无参构造初始容量: " + sb1.capacity());  // 16

        // 【方式2】指定容量的构造
        StringBuffer sb2 = new StringBuffer(32);
        System.out.println("指定容量(32)初始容量: " + sb2.capacity());  // 32

        // 【方式3】从String创建（容量 = 字符串长度 + 16）
        StringBuffer sb3 = new StringBuffer("Hello");
        System.out.println("从String创建初始容量: " + sb3.capacity());  // 5 + 16 = 21
        System.out.println("内容: " + sb3.toString());  // "Hello"

        // 【方式4】从StringBuilder创建
        StringBuilder sbd = new StringBuilder("World");
        StringBuffer sb4 = new StringBuffer(sbd);
        System.out.println("从StringBuilder创建: " + sb4.toString());

        // 链式调用
        StringBuffer chained = new StringBuffer()
                .append("Java")
                .append(" ")
                .append("Programming");
        System.out.println("链式调用: " + chained.toString());
    }

    // ================================================
    // 二、常用方法详解
    // ================================================
    public static void stringBufferMethods() {
        StringBuffer sb = new StringBuffer("Hello");

        System.out.println("初始内容: \"" + sb + "\"");
        System.out.println("初始长度: " + sb.length());
        System.out.println("初始容量: " + sb.capacity());

        // ===== 1. append() - 最常用 =====
        System.out.println("\n--- append() ---");

        StringBuffer sb1 = new StringBuffer("Hello");
        sb1.append(" World");
        System.out.println("append后: " + sb1);  // "Hello World"

        // append 支持多种类型
        StringBuffer sb2 = new StringBuffer();
        sb2.append(true)              // boolean
          .append(123)               // int
          .append(3.14)              // double
          .append('A')               // char
          .append("String");         // String
        System.out.println("append各种类型: " + sb2);

        // ===== 2. toString() =====
        System.out.println("\n--- toString() ---");
        StringBuffer sb3 = new StringBuffer("Hello");
        String str = sb3.toString();
        System.out.println("toString: " + str + ", 类型: " + str.getClass().getSimpleName());

        // ===== 3. length() vs capacity() =====
        System.out.println("\n--- length() vs capacity() ---");
        StringBuffer sb4 = new StringBuffer();
        System.out.println("空SB - length: " + sb4.length() + ", capacity: " + sb4.capacity());

        sb4.append("ABC");
        System.out.println("添加ABC后 - length: " + sb4.length() + ", capacity: " + sb4.capacity());

        sb4.append("DEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println("继续添加后 - length: " + sb4.length() + ", capacity: " + sb4.capacity());

        // ===== 4. charAt() =====
        System.out.println("\n--- charAt() ---");
        StringBuffer sb5 = new StringBuffer("Hello");
        System.out.println("charAt(0): " + sb5.charAt(0));  // 'H'
        System.out.println("charAt(4): " + sb5.charAt(4));  // 'o'

        // ===== 5. setCharAt() =====
        System.out.println("\n--- setCharAt() ---");
        StringBuffer sb6 = new StringBuffer("Hello");
        sb6.setCharAt(0, 'J');
        System.out.println("setCharAt(0, 'J')后: " + sb6);  // "Jello"

        // ===== 6. getChars() =====
        System.out.println("\n--- getChars() ---");
        StringBuffer sb7 = new StringBuffer("Hello");
        char[] dest = new char[5];
        sb7.getChars(0, 5, dest, 0);
        System.out.print("getChars到数组: ");
        for (char c : dest) System.out.print(c);
        System.out.println();
    }

    // ================================================
    // 三、append方法详解
    // ================================================
    public static void stringBufferAppend() {
        System.out.println("--- append 方法详解 ---");

        // append 可以接受多种参数类型
        StringBuffer sb = new StringBuffer();

        // 1. append(String str)
        sb.append("Hello");
        System.out.println("append(String): " + sb);

        // 2. append(char[] chars)
        sb.append(new char[]{' ', 'W', 'o', 'r', 'l', 'd'});
        System.out.println("append(char[]): " + sb);

        // 3. append(char[] chars, int offset, int len)
        StringBuffer sb2 = new StringBuffer();
        char[] chars = {'A', 'B', 'C', 'D', 'E'};
        sb2.append(chars, 1, 3);  // 从索引1开始，取3个字符: "BCD"
        System.out.println("append(char[], offset, len): " + sb2);

        // 4. append(CharSequence cs)
        sb2.append((CharSequence) "FGH");
        System.out.println("append(CharSequence): " + sb2);

        // 5. append(CharSequence cs, int start, int end)
        sb2.append((CharSequence) "IJKLM", 0, 2);  // "IJ"
        System.out.println("append(CharSequence, start, end): " + sb2);

        // 6. append(boolean/number/char等)
        StringBuffer sb3 = new StringBuffer("数字: ");
        sb3.append(100).append(", 浮点: ").append(3.14).append(", 布尔: ").append(true);
        System.out.println("append各种数值类型: " + sb3);

        // 7. appendNull - 如果传入null
        StringBuffer sb4 = new StringBuffer("Hello");
        sb4.append((String) null);  // 变成 "Hellonull"
        System.out.println("append(null String): " + sb4);

        StringBuffer sb5 = new StringBuffer("Hello");
        sb5.append((StringBuffer) null);  // NullPointerException!
        // System.out.println(sb5);
        System.out.println("注意：append(null StringBuffer)会抛NullPointerException");

        // 8. appendCodePoint - 追加Unicode码点
        StringBuffer sb6 = new StringBuffer("Hello");
        sb6.appendCodePoint(0x1F600);  // 添加emoji 😀
        System.out.println("appendCodePoint(0x1F600): " + sb6);
    }

    // ================================================
    // 四、insert方法
    // ================================================
    public static void stringBufferInsert() {
        System.out.println("--- insert 方法 ---");

        // insert(int offset, String str) - 在指定位置插入
        StringBuffer sb = new StringBuffer("HelloWorld");
        sb.insert(5, " ");  // 在索引5处插入空格
        System.out.println("insert(5, \" \"): " + sb);  // "Hello World"

        // insert 支持多种类型
        StringBuffer sb2 = new StringBuffer("ABC");
        sb2.insert(1, 123);           // 插入数字
        System.out.println("insert(int): " + sb2);  // "A123BC"

        StringBuffer sb3 = new StringBuffer("ABC");
        sb3.insert(1, 3.14);         // 插入浮点数
        System.out.println("insert(double): " + sb3);  // "A3.14BC"

        StringBuffer sb4 = new StringBuffer("ABC");
        sb4.insert(1, new char[]{'X', 'Y', 'Z'});  // 插入字符数组
        System.out.println("insert(char[]): " + sb4);  // "AXYZBC"

        // insert 超出范围会抛异常
        try {
            StringBuffer sb5 = new StringBuffer("ABC");
            sb5.insert(10, "X");  // offset > length()
            System.out.println(sb5);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("insert越界抛出异常: StringIndexOutOfBoundsException");
        }

        // append 和 insert 的对比
        StringBuffer sb6 = new StringBuffer("ABC");
        sb6.append("D");       // 末尾追加
        System.out.println("append(\"D\"): " + sb6);

        StringBuffer sb7 = new StringBuffer("ABC");
        sb7.insert(0, "0");    // 头部插入
        System.out.println("insert(0, \"0\"): " + sb7);
    }

    // ================================================
    // 五、delete和replace
    // ================================================
    public static void stringBufferDeleteReplace() {
        System.out.println("--- delete 和 replace ---");

        // 【delete】
        StringBuffer sb1 = new StringBuffer("HelloWorld");

        // delete(int start, int end) - 删除 [start, end) 范围的字符
        sb1.delete(5, 11);  // 删除索引5到末尾
        System.out.println("delete(5, 11): " + sb1);  // "Hello"

        // deleteCharAt(int index) - 删除指定索引的字符
        StringBuffer sb2 = new StringBuffer("Hello");
        sb2.deleteCharAt(1);  // 删除索引1的字符 'e'
        System.out.println("deleteCharAt(1): " + sb2);  // "Hllo"

        // 删除整个内容
        StringBuffer sb3 = new StringBuffer("Hello");
        sb3.delete(0, sb3.length());
        System.out.println("清空内容: \"" + sb3 + "\"");

        // 【replace】
        StringBuffer sb4 = new StringBuffer("HelloWorld");

        // replace(int start, int end, String str) - 替换 [start, end) 范围
        sb4.replace(5, 11, " Java");
        System.out.println("replace(5, 11, \" Java\"): " + sb4);  // "Hello Java"

        // 【setLength】 - 设置长度
        StringBuffer sb5 = new StringBuffer("HelloWorld");
        sb5.setLength(5);  // 截断到前5个字符
        System.out.println("setLength(5): " + sb5);  // "Hello"

        // setLength 可以扩大长度（用null填充）
        StringBuffer sb6 = new StringBuffer("Hello");
        sb6.setLength(10);  // 扩大长度
        System.out.println("setLength(10)后内容: \"" + sb6 + "\"");
        System.out.println("setLength(10)后length: " + sb6.length());
    }

    // ================================================
    // 六、reverse
    // ================================================
    public static void stringBufferReverse() {
        System.out.println("--- reverse ---");

        StringBuffer sb1 = new StringBuffer("Hello");
        sb1.reverse();
        System.out.println("\"Hello\" reverse: " + sb1);  // "olleH"

        // 回文判断
        String str = "上海自来水来自海上";
        StringBuffer sb2 = new StringBuffer(str);
        sb2.reverse();
        boolean isPalindrome = str.equals(sb2.toString());
        System.out.println("\"" + str + "\" 是回文: " + isPalindrome);
    }

    // ================================================
    // 七、capacity相关方法
    // ================================================
    public static void stringBufferCapacity() {
        System.out.println("--- capacity 相关方法 ---");

        // StringBuffer 的容量策略
        // 初始容量 = 16 或 字符串长度 + 16（取较大值）
        // 当容量不足时，扩容公式：newCapacity = (oldCapacity * 2) + 2

        StringBuffer sb = new StringBuffer();
        System.out.println("初始状态:");
        System.out.println("  length: " + sb.length());   // 0
        System.out.println("  capacity: " + sb.capacity());  // 16

        // 添加字符直到扩容
        System.out.println("\n逐步添加观察扩容:");
        for (int i = 0; i <= 20; i++) {
            if (i == 0 || i == 16 || i == 17 || i == 20) {
                System.out.println("  添加第" + i + "个字符后: length=" + sb.length() + ", capacity=" + sb.capacity());
            }
            sb.append("a");
        }

        // ensureCapacity - 确保容量至少达到指定值
        StringBuffer sb2 = new StringBuffer();
        sb2.ensureCapacity(100);
        System.out.println("\nensureCapacity(100)后容量: " + sb2.capacity());  // 至少100

        // trimToSize - 调整容量到实际长度
        StringBuffer sb3 = new StringBuffer(100);
        sb3.append("Hello");
        System.out.println("\ntrimToSize前: capacity=" + sb3.capacity());
        sb3.trimToSize();
        System.out.println("trimToSize后: capacity=" + sb3.capacity());
    }

    // ================================================
    // 八、性能对比
    // ================================================
    public static void stringBufferPerformance() {
        System.out.println("--- 性能对比 ---");

        int count = 10000;

        // 1. String += （不推荐）
        long start = System.nanoTime();
        String str = "";
        for (int i = 0; i < count; i++) {
            str += "a";
        }
        long end = System.nanoTime();
        System.out.println("String += 耗时: " + (end - start) / 1000000 + " ms");

        // 2. StringBuffer（线程安全）
        start = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sb.append("a");
        }
        end = System.nanoTime();
        System.out.println("StringBuffer 耗时: " + (end - start) / 1000000 + " ms");

        // 3. StringBuilder（更推荐，单线程）
        start = System.nanoTime();
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sbd.append("a");
        }
        end = System.nanoTime();
        System.out.println("StringBuilder 耗时: " + (end - start) / 1000000 + " ms");

        System.out.println("\n性能结论:");
        System.out.println("  String +=   : 最慢，循环中应避免");
        System.out.println("  StringBuffer: 线程安全，有同步开销");
        System.out.println("  StringBuilder: 最快，单线程首选");
    }

    // ================================================
    // 九、注意事项
    // ================================================
    public static void stringBufferCautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】线程安全
        System.out.println("\n1. 线程安全:");
        System.out.println("   StringBuffer所有方法都用synchronized修饰");
        System.out.println("   是线程安全的，但性能比StringBuilder低");

        // 【2】与String相互转换
        System.out.println("\n2. 与String相互转换:");
        StringBuffer sb1 = new StringBuffer("Hello");
        String str = sb1.toString();  // StringBuffer -> String

        StringBuffer sb2 = new StringBuffer(str);  // String -> StringBuffer
        System.out.println("转换成功: " + str + " <-> " + sb2);

        // 【3】链式调用
        System.out.println("\n3. 链式调用:");
        String result = new StringBuffer()
                .append("A").append("B").append("C")
                .insert(1, "X")
                .delete(2, 3)
                .reverse()
                .toString();
        System.out.println("链式调用结果: " + result);

        // 【4】空StringBuffer判断
        System.out.println("\n4. 空StringBuffer判断:");
        StringBuffer empty = new StringBuffer();
        System.out.println("length() == 0: " + (empty.length() == 0));

        // 【5】索引越界
        System.out.println("\n5. 索引越界:");
        try {
            StringBuffer sb = new StringBuffer("Hello");
            sb.charAt(10);  // 越界
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("charAt越界抛出: StringIndexOutOfBoundsException");
        }

        // 【6】StringBuffer的hashCode
        System.out.println("\n6. StringBuffer的hashCode:");
        StringBuffer sb1h = new StringBuffer("Hello");
        StringBuffer sb2h = new StringBuffer("Hello");
        System.out.println("两个内容相同的SB，hashCode相等: " + (sb1h.hashCode() == sb2h.hashCode()));
        System.out.println("但sb1 == sb2: " + (sb1h == sb2h));  // false，比较引用

        // 【7】修改后原引用仍有效
        System.out.println("\n7. 修改后原引用仍有效:");
        StringBuffer sbRef = new StringBuffer("Hello");
        StringBuffer sbRef2 = sbRef;  // 引用赋值
        sbRef.append(" World");
        System.out.println("sbRef: " + sbRef);      // "Hello World"
        System.out.println("sbRef2: " + sbRef2);   // "Hello World"（同一对象）
        System.out.println("sbRef == sbRef2: " + (sbRef == sbRef2));  // true
    }
}
