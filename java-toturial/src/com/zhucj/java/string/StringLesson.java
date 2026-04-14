package com.zhucj.java.string;

/**
 * =====================================================
 * Java String 字符串详解
 * =====================================================
 *
 * 【一、String的本质】
 * -------------------
 * 1. String是java.lang包下的类，用于表示字符串
 * 2. Java中所有字符串字面量（如"hello"）都是String类的实例
 * 3. String类实现了 Serializable, Comparable<String>, CharSequence 接口
 *
 * 【二、String的不可变性（Immutable）】
 * -----------------------------------
 * String对象一旦创建，其内容不可改变。这是Java设计中的重要特性。
 *
 * 为什么String要设计成不可变？
 * 1. 安全性：字符串常用于网络连接、文件路径、数据库连接等敏感场景
 *    不可变性可以防止被恶意修改
 * 2. 线程安全：不可变对象天然线程安全，无需同步开销
 * 3. 字符串常量池：不可变性使得字符串可以被缓存复用，节省内存
 * 4. 哈希码缓存：String的hashCode可以被缓存，因为值不会变
 *
 * 【三、字符串常量池（String Pool）】
 * ---------------------------------
 * 字符串常量池是Java堆内存中的一块特殊区域，用于存储字符串字面量。
 *
 * 工作原理：
 * - 当使用字面量创建字符串时，JVM会先在常量池中查找是否存在相同值的字符串
 * - 如果存在，直接返回该字符串的引用；如果不存在，则创建新对象并加入常量池
 * - 使用new创建的字符串对象则在堆中创建，不直接参与常量池（但intern()可以加入）
 *
 * =====================================================
 */
public class StringLesson {

    public static void main(String[] args) {
        // 运行各章节示例
        System.out.println("========== String基础 ==========");
        stringBasics();

        System.out.println("\n========== 创建方式对比 ==========");
        stringCreation();

        System.out.println("\n========== 常用方法 ==========");
        stringMethods();

        System.out.println("\n========== 字符串拼接 ==========");
        stringConcat();

        System.out.println("\n========== 注意事项 ==========");
        stringCautions();

        System.out.println("\n========== intern方法 ==========");
        stringIntern();

        System.out.println("\n========== 正则表达式 ==========");
        stringRegex();

        System.out.println("\n========== 格式化字符串 ==========");
        stringFormat();
    }

    // ================================================
    // 一、String基础
    // ================================================
    public static void stringBasics() {
        System.out.println("--- String本质 ---");

        // 1. String是引用类型，但可以直接用字面量赋值
        String s1 = "Hello";
        String s2 = "Hello";

        // 2. s1和s2指向常量池中的同一个对象
        System.out.println("s1 == s2: " + (s1 == s2));  // true，指向同一引用

        // 3. String实现了CharSequence接口
        CharSequence cs = "World";
        System.out.println("CharSequence length: " + cs.length());  // 5

        // 4. String的字符编码
        String chinese = "中文";
        System.out.println("中文字符串: " + chinese);
        System.out.println("字节长度(UTF-8): " + chinese.getBytes().length);
    }

    // ================================================
    // 二、String的创建方式
    // ================================================
    public static void stringCreation() {
        System.out.println("--- 创建方式 ---");

        // 【方式1】字面量创建（推荐）
        // 直接从字符串常量池获取
        String s1 = "Hello";

        // 【方式2】new String()创建
        // 在堆中创建新对象
        String s2 = new String("Hello");

        // 【方式3】字符数组创建
        char[] chars = {'H', 'e', 'l', 'l', 'o'};
        String s3 = new String(chars);

        // 【方式4】字节数组创建（常用于网络/文件读取）
        byte[] bytes = {72, 101, 108, 108, 111};  // "Hello"的ASCII码
        String s4 = new String(bytes);

        // 【方式5】StringBuilder/StringBuffer转String
        String s5 = new StringBuilder("Hello").toString();

        // 【方式6】字符数组部分创建
        String s6 = new String(chars, 1, 3);  // "ell"

        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("s3: " + s3);
        System.out.println("s4: " + s4);
        System.out.println("s5: " + s5);
        System.out.println("s6: " + s6);

        // 【重要】字面量 vs new 的区别
        System.out.println("\n--- 字面量 vs new ---");
        System.out.println("s1 == s2: " + (s1 == s2));  // false，s1在常量池，s2在堆
        System.out.println("s1.equals(s2): " + s1.equals(s2));  // true，内容相同
        System.out.println("s1.intern() == s2: " + s1.intern() == s2);  // false

        // intern()方法：将此字符串加入常量池，返回常量池中的引用
        // 所以 s1.intern() == s2 会是 false，因为 s2 是堆中的对象
        // 而 s1 == s1.intern() 是 true，因为 s1 本身就在常量池
    }

    // ================================================
    // 三、常用方法详解
    // ================================================
    public static void stringMethods() {
        String str = "Hello World";

        System.out.println("原字符串: \"" + str + "\"");

        // ===== 1. 获取相关方法 =====
        System.out.println("\n--- 获取相关 ---");

        // length() - 返回字符串长度（字符数）
        System.out.println("length(): " + str.length());  // 11

        // charAt(int index) - 返回指定索引的字符
        System.out.println("charAt(0): " + str.charAt(0));  // 'H'
        System.out.println("charAt(6): " + str.charAt(6));  // 'W'

        // indexOf/lastIndexOf - 查找字符或字符串的位置
        System.out.println("indexOf('o'): " + str.indexOf('o'));  // 4
        System.out.println("indexOf('o', 5): " + str.indexOf('o', 5));  // 7，从索引5开始找
        System.out.println("lastIndexOf('o'): " + str.lastIndexOf('o'));  // 7
        System.out.println("indexOf(\"World\"): " + str.indexOf("World"));  // 6

        // ===== 2. 判断相关方法 =====
        System.out.println("\n--- 判断相关 ---");

        String s1 = "Hello";
        String s2 = "hello";
        String s3 = "Hello";

        // equals() - 判断字符串内容是否相等（区分大小写）
        System.out.println("s1.equals(s2): " + s1.equals(s2));  // false
        System.out.println("s1.equals(s3): " + s1.equals(s3));  // true

        // equalsIgnoreCase() - 判断内容是否相等（不区分大小写）
        System.out.println("s1.equalsIgnoreCase(s2): " + s1.equalsIgnoreCase(s2));  // true

        // startsWith/endsWith - 判断前缀/后缀
        System.out.println("startsWith(\"He\"): " + s1.startsWith("He"));  // true
        System.out.println("endsWith(\"lo\"): " + s1.endsWith("lo"));  // true

        // contains - 判断是否包含子串
        System.out.println("contains(\"ell\"): " + s1.contains("ell"));  // true

        // isEmpty() - 判断是否为空字符串（length() == 0）
        System.out.println("\"\".isEmpty(): " + "".isEmpty());  // true
        System.out.println("\"  \".isEmpty(): " + "  ".isEmpty());  // false（有空格）

        // isBlank() - JDK11+，判断是否为空白（包含空格、制表符等）
        System.out.println("\"  \".isBlank(): " + "  ".isBlank());  // true
        System.out.println("\"\".isBlank(): " + "".isBlank());  // true

        // ===== 3. 转换相关方法 =====
        System.out.println("\n--- 转换相关 ---");

        String original = "Hello World";

        // toCharArray() - 转换为字符数组
        char[] charArray = original.toCharArray();
        System.out.print("toCharArray(): ");
        for (char c : charArray) System.out.print(c + " ");
        System.out.println();

        // getBytes() - 转换为字节数组
        byte[] byteArray = original.getBytes();
        System.out.print("getBytes(): ");
        for (byte b : byteArray) System.out.print(b + " ");
        System.out.println();

        // 大小写转换
        System.out.println("toUpperCase(): " + original.toUpperCase());  // HELLO WORLD
        System.out.println("toLowerCase(): " + original.toLowerCase());  // hello world

        // trim() - 去除首尾空格（不会去除中间空格）
        String withSpace = "  Hello World  ";
        System.out.println("trim(): \"" + withSpace.trim() + "\"");  // "Hello World"

        // strip() - JDK11+，更智能的去除空格（Unicode空格）
        String withUnicodeSpace = "\u2003Hello\u2003";  // 全角空格
        System.out.println("strip(): \"" + withUnicodeSpace.strip() + "\"");  // "Hello"

        // ===== 4. 替换/替换所有 =====
        System.out.println("\n--- 替换相关 ---");

        String text = "Hello World";

        // replace() - 替换所有匹配的字符或字符串
        System.out.println("replace('o', 'O'): " + text.replace('o', 'O'));  // HellO WOrld
        System.out.println("replace(\"World\", \"Java\"): " + text.replace("World", "Java"));  // Hello Java

        // replaceAll() - 使用正则表达式替换所有匹配的字符串
        System.out.println("replaceAll(\"[aeiou]\", \"*\"): " + text.replaceAll("[aeiou]", "*"));

        // replaceFirst() - 只替换第一个匹配的
        System.out.println("replaceFirst(\"[aeiou]\", \"*\"): " + text.replaceFirst("[aeiou]", "*"));

        // ===== 5. 截取相关方法 =====
        System.out.println("\n--- 截取相关 ---");

        String sub = "Hello World";

        // substring(int start) - 从start开始截取到末尾
        System.out.println("substring(6): " + sub.substring(6));  // "World"

        // substring(int start, int end) - 从start截取到end（不包含end）
        System.out.println("substring(0, 5): " + sub.substring(0, 5));  // "Hello"

        // subSequence(int start, int end) - 返回CharSequence
        System.out.println("subSequence(0, 5): " + sub.subSequence(0, 5));  // "Hello"

        // ===== 6. 分割相关方法 =====
        System.out.println("\n--- 分割相关 ---");

        String csv = "apple,banana,orange";

        // split(String regex) - 按正则表达式分割
        String[] fruits = csv.split(",");
        System.out.print("split(\",\"): ");
        for (String fruit : fruits) System.out.print(fruit + " ");
        System.out.println();

        // split带限制参数
        String[] limited = csv.split(",", 2);
        System.out.print("split(\",\", 2): ");
        for (String s : limited) System.out.print("\"" + s + "\" ");
        System.out.println();

        // 特殊字符分割注意：. | * + 等需要转义
        String ip = "192.168.1.1";
        String[] ipParts = ip.split("\\.");  // 点号需要转义
        System.out.print("split(\"\\\\.\"): ");
        for (String p : ipParts) System.out.print(p + " ");
        System.out.println();

        // ===== 7. 字符串拼接 =====
        System.out.println("\n--- 拼接相关 ---");

        String joined = String.join("-", "a", "b", "c");
        System.out.println("String.join(\"-\", \"a\", \"b\", \"c\"): " + joined);

        // repeat() - JDK11+，重复字符串
        System.out.println("\"ab\".repeat(3): " + "ab".repeat(3));  // "ababab"

        // lines() - JDK11+，按行分割
        String multiline = "line1\nline2\nline3";
        System.out.print("lines(): ");
        multiline.lines().forEach(System.out::print);
        System.out.println();

        // ===== 8. 静态方法 =====
        System.out.println("\n--- 静态方法 ---");

        // valueOf() - 将其他类型转为字符串
        System.out.println("String.valueOf(123): " + String.valueOf(123));
        System.out.println("String.valueOf(3.14): " + String.valueOf(3.14));
        System.out.println("String.valueOf(true): " + String.valueOf(true));

        // format() - 格式化字符串
        System.out.println("String.format(\"%s-%d\", \"Hello\", 123): " +
                String.format("%s-%d", "Hello", 123));
    }

    // ================================================
    // 四、字符串拼接详解
    // ================================================
    public static void stringConcat() {
        System.out.println("--- 字符串拼接方式对比 ---");

        // 【方式1】+ 运算符拼接
        String s1 = "Hello" + " " + "World";
        System.out.println("+ 拼接: " + s1);

        // 底层原理：编译器优化，简单的字符串拼接会被优化为StringBuilder
        // 但循环中的+拼接，每次都会创建新的StringBuilder，效率低
        String loop = "";
        for (int i = 0; i < 5; i++) {
            loop += "a";  // 每次循环都创建新的StringBuilder对象！
        }
        System.out.println("循环+拼接: " + loop);

        // 【方式2】StringBuilder拼接（推荐，线程不安全，效率高）
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append("a");
        }
        System.out.println("StringBuilder: " + sb.toString());

        // 【方式3】StringBuffer拼接（线程安全，但效率较低）
        StringBuffer sbf = new StringBuffer();
        sbf.append("Hello").append(" ").append("World");
        System.out.println("StringBuffer: " + sbf.toString());

        // 【方式4】String.concat()方法
        String con = "Hello".concat(" ").concat("World");
        System.out.println("concat(): " + con);

        // 【方式5】String.join() - JDK8+
        String join = String.join("-", "A", "B", "C");
        System.out.println("String.join(): " + join);

        // 【性能对比】
        System.out.println("\n--- 性能考虑 ---");
        System.out.println("1. 少量固定拼接：用 + 或 String.join() 即可");
        System.out.println("2. 循环中拼接：用 StringBuilder");
        System.out.println("3. 多线程共享：用 StringBuffer");
        System.out.println("4. String.concat()：适合拼接少量字符串，会创建新对象");
    }

    // ================================================
    // 五、注意事项
    // ================================================
    public static void stringCautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】字符串比较：== vs equals
        String a = "hello";
        String b = new String("hello");
        String c = "hello";

        System.out.println("\n字符串比较：");
        System.out.println("a == b: " + (a == b));  // false，比较引用
        System.out.println("a == c: " + (a == c));  // true，常量池复用
        System.out.println("a.equals(b): " + a.equals(b));  // true，比较内容

        // 【2】空字符串 vs null
        String empty = "";
        String nullStr = null;

        System.out.println("\n空字符串 vs null：");
        System.out.println("\"\".length(): " + empty.length());  // 0
        // System.out.println("null.length(): " + nullStr.length());  // NullPointerException!
        System.out.println("\"\".isEmpty(): " + empty.isEmpty());  // true
        // System.out.println("null.isEmpty(): " + nullStr.isEmpty());  // NullPointerException!

        // 安全判断
        System.out.println("empty == null || empty.isEmpty(): " + (empty == null || empty.isEmpty()));
        System.out.println("nullStr == null || nullStr.isEmpty(): " + (nullStr == null || nullStr.isEmpty()));

        // 【3】字符串不可变性
        String immutable = "Hello";
        System.out.println("\n字符串不可变性：");
        System.out.println("原字符串: " + immutable);
        immutable.replace("H", "J");  // 这行没有实际效果！
        System.out.println("replace后: " + immutable);  // 还是"Hello"！
        System.out.println("需要接收返回值: " + immutable.replace("H", "J"));  // 才是"Jello"

        // 【4】字符串常量池的特殊情况
        String pool = "abc";
        String newStr = new String("abc");
        String intern = newStr.intern();  // 返回常量池中的引用

        System.out.println("\n字符串常量池：");
        System.out.println("pool == intern: " + (pool == intern));  // true
        System.out.println("pool == newStr: " + (pool == newStr));  // false

        // 【5】字符串长度陷阱
        String emoji = "😀";
        System.out.println("\n字符长度问题：");
        System.out.println("emoji.length(): " + emoji.length());  // 2！（Java用UTF-16）
        System.out.println("emoji.codePointCount(0, emoji.length()): " +
                emoji.codePointCount(0, emoji.length()));  // 1，这才是正确的字符数
    }

    // ================================================
    // 六、intern方法详解
    // ================================================
    public static void stringIntern() {
        System.out.println("--- intern() 方法 ---");

        // intern()方法的作用：
        // 1. 如果常量池中已经存在当前字符串，返回常量池中的引用
        // 2. 如果常量池中不存在，将此字符串添加到常量池并返回引用

        // 示例1：字面量
        String s1 = "hello";
        String s2 = "hello";
        System.out.println("s1 == s2: " + (s1 == s2));  // true

        // 示例2：new String
        String s3 = new String("hello");
        String s4 = new String("hello");
        System.out.println("s3 == s4: " + (s3 == s4));  // false

        // 示例3：intern
        String s5 = s3.intern();
        String s6 = s4.intern();
        System.out.println("s5 == s6: " + (s5 == s6));  // true
        System.out.println("s1 == s5: " + (s1 == s5));  // true

        // 应用场景：大量重复字符串
        String a = new String("test").intern();
        String b = "test";
        System.out.println("a == b: " + (a == b));  // true，节省内存
    }

    // ================================================
    // 七、正则表达式在String中的应用
    // ================================================
    public static void stringRegex() {
        System.out.println("--- 正则表达式 ---");

        String text = "Hello123World456";

        // matches() - 整个字符串是否匹配正则
        System.out.println("\"123\".matches(\"\\\\d+\"): " + "123".matches("\\d+"));  // true

        // split() - 按正则分割
        System.out.print("split(\"[0-9]+\"): ");
        for (String s : text.split("[0-9]+")) {
            System.out.print("\"" + s + "\" ");
        }
        System.out.println();

        // replaceAll() - 正则替换
        System.out.println("replaceAll(\"[0-9]+\", \"#\"): " +
                text.replaceAll("[0-9]+", "#"));  // Hello#World#

        // replaceFirst() - 只替换第一个
        System.out.println("replaceFirst(\"[0-9]+\", \"#\"): " +
                text.replaceFirst("[0-9]+", "#"));  // Hello#World456

        // 常用正则示例
        System.out.println("\n--- 常用正则 ---");
        String email = "test@example.com";

        // 邮箱验证
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        System.out.println("邮箱匹配: " + email.matches(emailRegex));

        // 手机号验证（中国大陆）
        String phone = "13812345678";
        String phoneRegex = "^1[3-9]\\d{9}$";
        System.out.println("手机号匹配: " + phone.matches(phoneRegex));

        // 常见正则符号
        System.out.println("\n--- 正则符号速查 ---");
        System.out.println("\\d - 数字 [0-9]");
        System.out.println("\\D - 非数字 [^0-9]");
        System.out.println("\\w - 单词字符 [a-zA-Z0-9_]");
        System.out.println("\\s - 空白字符 [空格\\t\\n\\r]");
        System.out.println(".  - 任意字符（换行除外）");
        System.out.println("+  - 一次或多次");
        System.out.println("*  - 零次或多次");
        System.out.println("?  - 零次或一次");
        System.out.println("{n} - 恰好n次");
        System.out.println("{n,} - 至少n次");
        System.out.println("{n,m} - n到m次");
    }

    // ================================================
    // 八、格式化字符串
    // ================================================
    public static void stringFormat() {
        System.out.println("--- 格式化字符串 ---");

        // String.format() 用法
        String name = "张三";
        int age = 25;
        double height = 175.5;

        // 基本格式
        System.out.println("String.format(\"%s, %d岁, %.1fcm\", name, age, height):");
        System.out.println(String.format("%s, %d岁, %.1fcm", name, age, height));

        // 对齐
        System.out.println("\n左对齐:");
        System.out.println(String.format("|%10s|", "右对齐"));    // |        右对齐|
        System.out.println(String.format("|%-10s|", "左对齐"));    // |左对齐        |

        // 数字格式化
        System.out.println("\n数字格式化:");
        System.out.println(String.format("|%05d|", 42));           // |00042|
        System.out.println(String.format("|%.2f|", 3.14159));      // |3.14|
        System.out.println(String.format("|%,d|", 1234567));      // |1,234,567|

        // printf 也使用相同的格式化语法
        System.out.println("\nprintf格式化:");
        System.out.printf("年龄: %d, 身高: %.1f%n", age, height);
    }
}
