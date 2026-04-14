package com.zhucj.java.function;

/**
 * =====================================================
 * Java 方法（Method）详解
 * =====================================================
 *
 * 【一、什么是方法】
 * -----------------
 * 方法是封装在类中的代码块，用于执行特定功能。
 * - 方法是类的成员之一（与属性、构造器并列）
 * - 方法定义在类体中，可以访问类的成员
 * - 通过对象或类名来调用
 *
 * 【二、方法的基本结构】
 * --------------------
 * 修饰符 返回类型 方法名(参数列表) {
 *     // 方法体
 *     return 返回值;
 * }
 *
 * =====================================================
 */
public class FunctionLesson {

    public static void main(String[] args) {
        System.out.println("========== 方法基础 ==========");
        methodBasics();

        System.out.println("\n========== 方法的组成要素 ==========");
        methodComponents();

        System.out.println("\n========== 方法的调用 ==========");
        methodInvocation();

        System.out.println("\n========== 构造方法 ==========");
        constructorMethod();

        System.out.println("\n========== 方法重载 ==========");
        methodOverload();

        System.out.println("\n========== this关键字 ==========");
        thisKeyword();

        System.out.println("\n========== 静态方法 ==========");
        staticMethod();

        System.out.println("\n========== 方法参数 ==========");
        methodParameters();

        System.out.println("\n========== 可变参数 ==========");
        varargs();

        System.out.println("\n========== 方法返回值 ==========");
        methodReturn();

        System.out.println("\n========== 递归方法 ==========");
        recursion();

        System.out.println("\n========== 注意事项 ==========");
        methodCautions();
    }

    // ================================================
    // 一、方法基础
    // ================================================
    public static void methodBasics() {
        System.out.println("--- 方法基础概念 ---");

        // 创建对象来调用实例方法
        Dog dog = new Dog("旺财", 3);

        // 调用无返回值的方法
        // 输出: 旺财正在叫: 汪汪汪！
        dog.bark();

        // 调用有返回值的方法
        // getAge() → 3
        int age = dog.getAge();
        System.out.println("狗狗年龄: " + age);

        // 调用静态方法（通过类名）
        // MathUtil.isEven(6) → true
        System.out.println("6是偶数吗: " + MathUtil.isEven(6));

        // 调用重载方法
        // getMax(3, 5) → 5
        System.out.println("最大值: " + getMax(3, 5));
        // getMax(3.0, 5.0) → 5.0
        System.out.println("最大值: " + getMax(3.0, 5.0));
    }

    // 【最简单的无参数无返回值方法】
    public void simpleMethod() {
        System.out.println("这是一个简单方法");
        // 调用结果: 这是一个简单方法
    }

    // 【带多个参数的方法】
    public static int calculate(int a, int b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: return 0;
        }
    }

    // ================================================
    // 二、方法的组成要素
    // ================================================
    public static void methodComponents() {
        System.out.println("--- 方法的组成要素 ---");

        // 【完整的方法结构】
        // public           - 访问修饰符
        // static           - 静态修饰符
        // int              - 返回类型
        // calculate        - 方法名
        // (int a, int b)   - 参数列表
        // { return a + b; } - 方法体

        // 【1】访问修饰符
        // public   - 公开访问
        // private  - 私有访问
        // protected - 受保护访问
        // (default) - 包级私有

        // 【2】返回类型
        // 基本类型: int, double, boolean等
        // 引用类型: String, Object, 自定义类
        // void     - 无返回值

        // 【3】方法名
        // 命名规范: 小驼峰命名法
        // 规则: 由字母、数字、下划线、$组成，不能以数字开头

        // 【4】参数列表
        // 可以有0个或多个参数
        // 每个参数需要指定类型

        // 示例
        System.out.println("\n--- 方法示例 ---");

        // 无参数无返回值
        printLine();  // 输出: ============

        // 有参数无返回值
        printMessage("Hello");  // 输出: Message: Hello

        // 有参数有返回值
        int result = multiply(6, 7);  // → 42
        System.out.println("6 * 7 = " + result);

        // 多个参数
        double avg = calculateAverage(10, 20, 30);  // → 20.0
        System.out.println("平均值: " + avg);
    }

    // 无参数无返回值方法
    public static void printLine() {
        System.out.println("============");
    }

    // 有参数无返回值
    public static void printMessage(String message) {
        System.out.println("Message: " + message);
    }

    // 有返回值
    public static int multiply(int a, int b) {
        return a * b;
    }

    // 多个参数
    public static double calculateAverage(int a, int b, int c) {
        return (a + b + c) / 3.0;
    }

    // ================================================
    // 三、方法的调用
    // ================================================
    public static void methodInvocation() {
        System.out.println("--- 方法的调用 ---");

        // 【1】调用静态方法 - 通过类名调用
        // MathUtil.isPrime(7) → true
        System.out.println("7是质数吗: " + MathUtil.isPrime(7));
        System.out.println("8是质数吗: " + MathUtil.isPrime(8));

        // 【2】调用实例方法 - 通过对象调用
        Dog dog = new Dog("小白", 2);

        // dog.getName() → "小白"
        System.out.println("狗狗名字: " + dog.getName());

        // dog.bark() → 小白正在叫: 汪汪汪！
        dog.bark();

        // 【3】同类中的方法可以直接调用
        // greet() → Hello!

        // 【4】同类中的静态方法可以直接调用
        // staticGreet() → Hi!
        staticGreet();  // 直接调用同类中的静态方法

        // 【5】链式调用（返回this）
        StringBuilder sb = new StringBuilder();
        sb.append("Hello").append(" ").append("World");
        // sb.toString() → "Hello World"
        System.out.println("链式调用: " + sb.toString());
    }

    public void greet() {
        System.out.println("Hello!");
    }

    public static void staticGreet() {
        System.out.println("Hi!");
    }

    // ================================================
    // 四、构造方法
    // ================================================
    public static void constructorMethod() {
        System.out.println("--- 构造方法 ---");

        // 【构造方法的特点】
        // 1. 方法名与类名相同
        // 2. 没有返回类型（连void都没有）
        // 3. 在创建对象时自动调用
        // 4. 用于初始化对象的属性

        // 【默认构造方法】
        // 如果不写构造方法，编译器会自动生成一个无参构造方法
        // Person p = new Person();

        // 【1】无参构造方法
        Person person1 = new Person();
        // person1.name → "Unknown"
        // person1.age → 0
        System.out.println("无参构造: " + person1.name + ", " + person1.age);

        // 【2】有参构造方法
        Person person2 = new Person("张三", 25);
        // person2.name → "张三"
        // person2.age → 25
        System.out.println("有参构造: " + person2.name + ", " + person2.age);

        // 【3】构造方法重载
        Person person3 = new Person("李四");
        // person3.name → "李四"
        // person3.age → 1
        System.out.println("只传名字: " + person3.name + ", " + person3.age);

        // 【4】this调用其他构造方法
        Person person4 = new Person(30);
        // person4.name → "Guest"
        // person4.age → 30
        System.out.println("只传年龄: " + person4.name + ", " + person4.age);

        // 【5】构造方法私有化
        // Singleton s = Singleton.getInstance();  // 通过静态方法获取实例

        // 【6】构造方法中的this用法
        Rectangle rect = new Rectangle(10, 5);
        // rect.width → 10
        // rect.height → 5
        // rect.getArea() → 50
        System.out.println("矩形面积: " + rect.getArea());

        // 【7】构造方法与创建对象的过程
        // Student stu = new Student("王五");
        // 1. 在堆内存中分配空间
        // 2. 属性赋默认值
        // 3. 执行构造方法
        // 4. 返回对象引用
    }

    // ================================================
    // 五、方法重载
    // ================================================
    public static void methodOverload() {
        System.out.println("--- 方法重载 ---");

        // 【什么是方法重载】
        // 在同一个类中，方法名相同但参数列表不同（参数个数、类型、顺序不同）
        // 返回类型不能作为重载的依据

        // 【重载示例】
        System.out.println("--- 重载示例 ---");

        // add(int, int)
        // add(10, 20) → 30
        System.out.println("int相加: " + add(10, 20));

        // add(double, double)
        // add(3.5, 2.5) → 6.0
        System.out.println("double相加: " + add(3.5, 2.5));

        // add(String, String)
        // add("Hello", "World") → "HelloWorld"
        System.out.println("字符串相加: " + add("Hello", "World"));

        // 【print方法的多种重载】
        print(100);      // 输出整数
        print(3.14);    // 输出小数
        print("Hi");     // 输出字符串
        print(true);     // 输出布尔

        // 【构造方法重载】
        System.out.println("\n--- 构造方法重载 ---");
        Calculator calc1 = new Calculator();           // 使用无参构造
        Calculator calc2 = new Calculator(10);          // 使用有参构造
        Calculator calc3 = new Calculator(10, 5);       // 使用两参数构造

        // calc1.getResult() → 0
        System.out.println("calc1结果: " + calc1.getResult());
        // calc2.getResult() → 10
        System.out.println("calc2初始值: " + calc2.getResult());
        // calc3.add() → 15
        System.out.println("calc3两数相加: " + calc3.add());
    }

    // 方法重载示例
    public static int add(int a, int b) {
        return a + b;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static String add(String a, String b) {
        return a + b;
    }

    public static void print(int num) {
        System.out.println("整数: " + num);
    }

    public static void print(double num) {
        System.out.println("小数: " + num);
    }

    public static void print(String str) {
        System.out.println("字符串: " + str);
    }

    public static void print(boolean flag) {
        System.out.println("布尔: " + flag);
    }

    // 获取最大值 - 重载
    public static int getMax(int a, int b) {
        return a > b ? a : b;
    }

    public static double getMax(double a, double b) {
        return a > b ? a : b;
    }

    public static int getMax(int a, int b, int c) {
        return getMax(getMax(a, b), c);
    }

    // ================================================
    // 六、this关键字
    // ================================================
    public static void thisKeyword() {
        System.out.println("--- this关键字 ---");

        // 【this的三个作用】
        // 1. 指向当前对象
        // 2. 调用本类的构造方法
        // 3. 作为参数传递

        // 【1】this指向当前对象
        PersonWithThis p1 = new PersonWithThis("小明", 20);
        PersonWithThis p2 = new PersonWithThis("小红", 22);

        // p1.introduce() → 我是小明, 今年20岁
        System.out.println("p1: " + p1.introduce());
        // p2.introduce() → 我是小红, 今年22岁
        System.out.println("p2: " + p2.introduce());

        // 【2】this调用本类的方法
        PersonWithThis person = new PersonWithThis("张三", 25);
        // person.show() → 姓名: 张三, 年龄: 25
        person.show();

        // 【3】this作为返回值（链式调用）
        PersonWithThis p = new PersonWithThis("", 0);
        p.setName("李四").setAge(30).setName("王五");
        // p.name → "王五"
        // p.age → 30
        System.out.println("链式调用后: 姓名=" + p.name + ", 年龄=" + p.age);

        // 【4】this在构造方法中调用其他构造方法
        // new StudentWithThis("小明") → name="小明", age=18, grade=1
        StudentWithThis s1 = new StudentWithThis("小明");
        System.out.println("学生1: " + s1.getInfo());

        // new StudentWithThis("小红", 20, 3) → name="小红", age=20, grade=3
        StudentWithThis s2 = new StudentWithThis("小红", 20, 3);
        System.out.println("学生2: " + s2.getInfo());
    }

    // ================================================
    // 七、静态方法
    // ================================================
    public static void staticMethod() {
        System.out.println("--- 静态方法 ---");

        // 【静态方法的特点】
        // 1. 用static修饰
        // 2. 属于类，不属于对象
        // 3. 可以通过类名直接调用
        // 4. 不能访问实例成员（实例属性、实例方法）
        // 5. 可以访问静态成员

        // 【1】调用静态方法
        // MathUtil.isPrime(17) → true
        System.out.println("17是质数: " + MathUtil.isPrime(17));
        // MathUtil.factorial(5) → 120
        System.out.println("5的阶乘: " + MathUtil.factorial(5));
        // MathUtil.gcd(12, 18) → 6
        System.out.println("12和18的最大公约数: " + MathUtil.gcd(12, 18));

        // 【2】静态方法与实例方法的区别
        System.out.println("\n--- 静态方法 vs 实例方法 ---");
        System.out.println("静态方法: 属于类，通过类名调用");
        System.out.println("实例方法: 属于对象，通过对象调用");

        // 【3】静态方法的应用场景
        // - 工具类方法（Math.random(), Arrays.sort()等）
        // -工厂方法
        // -单例模式的获取实例方法

        // 【4】静态方法不能直接访问实例成员
        // 下面的代码会编译错误：
        // public static void errorMethod() {
        //     System.out.println(this.name);  // 错误！
        // }

        // 【5】静态方法调用静态方法
        // 同一个类中可以直接调用
        staticMethodA();  // 输出: 静态方法A
        staticMethodB();  // 输出: 静态方法B（调用了A）

        // 【6】main方法是静态方法
        System.out.println("\nmain方法是程序的入口");
        System.out.println("main方法由JVM自动调用，不需要手动调用");
    }

    public static void staticMethodA() {
        System.out.println("静态方法A");
    }

    public static void staticMethodB() {
        staticMethodA();  // 静态方法可以直接调用同类中的静态方法
        System.out.println("静态方法B");
    }

    // ================================================
    // 八、方法参数
    // ================================================
    public static void methodParameters() {
        System.out.println("--- 方法参数 ---");

        // 【1】基本类型参数（值传递）
        int num = 10;
        changePrimitive(num);
        // num → 10 (未改变)
        System.out.println("基本类型参数: " + num);

        // 【2】引用类型参数（引用传递）
        int[] arr = {1, 2, 3};
        changeArray(arr);
        // arr[0] → 100
        System.out.println("引用类型参数: arr[0] = " + arr[0]);

        // 【3】对象参数
        Person p = new Person("原名", 20);
        changePerson(p);
        // p.name → "新名字"
        System.out.println("对象参数: " + p.name);

        // 【4】参数顺序
        printInfo("张三", 25, true);
        printInfo("李四", 30, false);

        // 【5】参数默认值（需要使用重载实现）
        greetUser();              // 输出: Hello, Guest!
        greetUser("小明");        // 输出: Hello, 小明!
        greetUser("小红", 18);     // 输出: Hello, 小红! 年龄: 18

        // 【6】参数类型转换
        // 较小的类型可以自动转换为较大的类型
        int i = 100;
        printDouble(i);  // int可以传给double参数 → 100.0

        // 强制类型转换
        double d = 3.14;
        printInt((int) d);  // 输出: 3

        // 【7】参数传递的内存分析
        System.out.println("\n--- 内存分析 ---");
        int x = 5;
        int y = 10;
        swap(x, y);
        // x → 5, y → 10 (未交换)
        System.out.println("交换后: x=" + x + ", y=" + y);
    }

    public static void changePrimitive(int num) {
        num = 999;
    }

    public static void changeArray(int[] arr) {
        arr[0] = 100;
    }

    public static void changePerson(Person p) {
        p.name = "新名字";
    }

    public static void printInfo(String name, int age, boolean isStudent) {
        System.out.println("姓名: " + name + ", 年龄: " + age + ", 是学生: " + isStudent);
    }

    public static void greetUser() {
        System.out.println("Hello, Guest!");
    }

    public static void greetUser(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public static void greetUser(String name, int age) {
        System.out.println("Hello, " + name + "! 年龄: " + age);
    }

    public static void printDouble(double d) {
        System.out.println("double: " + d);
    }

    public static void printInt(int i) {
        System.out.println("int: " + i);
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    // ================================================
    // 九、可变参数
    // ================================================
    public static void varargs() {
        System.out.println("--- 可变参数 ---");

        // 【可变参数的特点】
        // 1. 使用 ... 表示
        // 2. 本质上是数组
        // 3. 可以传0个或多个参数
        // 4. 必须是参数列表的最后一个

        // 【1】基本用法
        // sum(1, 2, 3) → 6
        System.out.println("可变参数求和: " + sum(1, 2, 3));
        // sum(1, 2, 3, 4, 5) → 15
        System.out.println("5个数求和: " + sum(1, 2, 3, 4, 5));
        // sum() → 0
        System.out.println("无参数求和: " + sum());

        // 【2】混合参数
        // printAll("A", "B", "C") → A, B, C
        // printAll("Numbers:", 1, 2, 3) → Numbers: 1, 2, 3
        printAll("Numbers:", 1, 2, 3);

        // 【3】与数组参数的区别
        int[] nums = {1, 2, 3, 4, 5};
        // sumArray(nums) → 15
        System.out.println("数组参数求和: " + sumArray(nums));
        // sumArray(new int[]{6,7,8}) → 21
        System.out.println("匿名数组求和: " + sumArray(new int[]{6, 7, 8}));

        // 【4】注意事项
        // 可变参数不能有两个
        // 可变参数必须在最后
        // 可以传数组过去
        System.out.println("传数组过去: " + sum(nums));

        // 【5】遍历可变参数
        printMax(3, 1, 4, 1, 5, 9, 2, 6);
        // 最大值: 9
    }

    public static int sum(int... numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    public static void printAll(String prefix, int... numbers) {
        System.out.print(prefix + " ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static int sumArray(int[] numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    public static void printMax(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            System.out.println("没有数据");
            return;
        }
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        System.out.println("最大值: " + max);
    }

    // ================================================
    // 十、方法返回值
    // ================================================
    public static void methodReturn() {
        System.out.println("--- 方法返回值 ---");

        // 【1】返回基本类型
        int sum = add(5, 3);
        // sum → 8
        System.out.println("返回int: " + sum);

        double ratio = getRatio(10, 20);
        // ratio → 0.5
        System.out.println("返回double: " + ratio);

        boolean isAdult = isAdult(20);
        // isAdult → true
        System.out.println("返回boolean: " + isAdult);

        // 【2】返回引用类型
        String greeting = createGreeting("张三");
        // greeting → "你好, 张三!"
        System.out.println("返回String: " + greeting);

        int[] arr = getArray();
        // arr → [1, 2, 3]
        System.out.println("返回数组: " + java.util.Arrays.toString(arr));

        // 【3】返回自定义对象
        Person p = createPerson("李四", 30);
        // p.name → "李四"
        // p.age → 30
        System.out.println("返回自定义对象: " + p.name + ", " + p.age);

        // 【4】void方法
        printWelcome();
        // 输出: 欢迎来到Java世界！

        // 【5】提前返回
        int result = findFirstPositive(new int[]{-3, -2, -1, 0, 1, 2, 3});
        // result → 1
        System.out.println("第一个正数: " + result);

        // 【6】返回多个值（通过数组或对象）
        int[] minMax = getMinMax(new int[]{5, 3, 8, 1, 9, 2});
        // minMax → [1, 9]
        System.out.println("最小值: " + minMax[0] + ", 最大值: " + minMax[1]);

        // 【7】return的注意事项
        // - return后面不能有代码
        // - void方法可以用return;提前结束
    }

    public static double getRatio(int a, int b) {
        return (double) a / b;
    }

    public static boolean isAdult(int age) {
        return age >= 18;
    }

    public static String createGreeting(String name) {
        return "你好, " + name + "!";
    }

    public static int[] getArray() {
        return new int[]{1, 2, 3};
    }

    public static Person createPerson(String name, int age) {
        return new Person(name, age);
    }

    public static void printWelcome() {
        System.out.println("欢迎来到Java世界！");
    }

    public static int findFirstPositive(int[] arr) {
        for (int num : arr) {
            if (num > 0) {
                return num;  // 找到就立即返回
            }
        }
        return -1;  // 没找到返回-1
    }

    public static int[] getMinMax(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        return new int[]{min, max};
    }

    // ================================================
    // 十一、递归方法
    // ================================================
    public static void recursion() {
        System.out.println("--- 递归方法 ---");

        // 【递归的条件】
        // 1. 自己调用自己
        // 2. 有终止条件

        // 【1】计算阶乘
        // factorial(5) → 5 * 4 * 3 * 2 * 1 = 120
        System.out.println("5的阶乘: " + factorial(5));
        // factorial(10) → 3628800
        System.out.println("10的阶乘: " + factorial(10));

        // 【2】斐波那契数列
        // fibonacci(6) → 8
        System.out.println("斐波那契第6项: " + fibonacci(6));
        // fibonacci(10) → 55
        System.out.println("斐波那契第10项: " + fibonacci(10));

        // 【3】计算1+2+3+...+n
        // sumTo(100) → 5050
        System.out.println("1+2+...+100 = " + sumTo(100));

        // 【4】倒序打印字符串
        System.out.print("倒序打印 'hello': ");
        reversePrint("hello");
        System.out.println();

        // 【5】计算幂
        // power(2, 10) → 1024
        System.out.println("2的10次方: " + power(2, 10));
        // power(3, 4) → 81
        System.out.println("3的4次方: " + power(3, 4));

        // 【6】汉诺塔
        System.out.println("\n汉诺塔移动3层:");
        hanoi(3, 'A', 'B', 'C');

        // 【7】递归与循环的对比
        System.out.println("\n--- 递归 vs 循环 ---");
        // 循环计算阶乘
        long factorialLoop = 1;
        for (int i = 1; i <= 10; i++) {
            factorialLoop *= i;
        }
        // factorialLoop → 3628800
        System.out.println("循环计算10! = " + factorialLoop);

        // 递归计算阶乘
        // factorial(10) → 3628800
        System.out.println("递归计算10! = " + factorial(10));
    }

    public static long factorial(int n) {
        if (n <= 1) {
            return 1;  // 终止条件
        }
        return n * factorial(n - 1);  // 递归调用
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;  // 终止条件
        }
        return fibonacci(n - 1) + fibonacci(n - 2);  // 递归调用
    }

    public static int sumTo(int n) {
        if (n <= 0) {
            return 0;  // 终止条件
        }
        return n + sumTo(n - 1);  // 递归调用
    }

    public static void reversePrint(String s) {
        if (s.length() == 0) {
            return;  // 终止条件
        }
        System.out.print(s.charAt(s.length() - 1));
        reversePrint(s.substring(0, s.length() - 1));
    }

    public static long power(int base, int exponent) {
        if (exponent == 0) {
            return 1;  // 终止条件
        }
        return base * power(base, exponent - 1);  // 递归调用
    }

    public static void hanoi(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("移动盘子1从 " + from + " 到 " + to);
            return;  // 终止条件
        }
        hanoi(n - 1, from, aux, to);        // 把n-1个盘子从from移到aux
        System.out.println("移动盘子" + n + "从 " + from + " 到 " + to);
        hanoi(n - 1, aux, to, from);        // 把n-1个盘子从aux移到to
    }

    // ================================================
    // 十二、注意事项
    // ================================================
    public static void methodCautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】方法名命名规范
        System.out.println("\n1. 命名规范:");
        System.out.println("   - 小驼峰: getName, calculateSum");
        System.out.println("   - 见名知意: calculateAverage() 而非 calc()");
        System.out.println("   - 不使用数字开头: valid10 可能, 10valid 非法");

        // 【2】参数个数
        System.out.println("\n2. 参数个数:");
        System.out.println("   - Java不支持默认参数");
        System.out.println("   - 使用重载实现默认参数效果");

        // 【3】返回值类型
        System.out.println("\n3. 返回值:");
        System.out.println("   - 一个方法只能有一个返回值");
        System.out.println("   - 返回null要小心NPE");
        System.out.println("   - void方法不能用return value;");

        // 【4】可变参数的限制
        System.out.println("\n4. 可变参数:");
        System.out.println("   - 只能有一个可变参数");
        System.out.println("   - 必须在参数列表最后");

        // 【5】递归的效率问题
        System.out.println("\n5. 递归效率:");
        System.out.println("   - 递归调用有函数调用开销");
        System.out.println("   - 深度过大可能导致StackOverflowError");
        System.out.println("   - 可以用尾递归优化（JVM不支持自动优化）");

        // 【6】方法覆盖 vs 方法重载
        System.out.println("\n6. 重写 vs 重载:");
        System.out.println("   - 重写(Override): 子类重写父类方法，方法签名必须相同");
        System.out.println("   - 重载(Overload): 同一类中，方法名相同，参数不同");

        // 【7】静态方法调用实例成员会报错
        // public static void error() {
        //     this.name = "test";      // 错误: 无法在静态上下文中引用非静态变量
        // }
    }
}

// ================================================
// 辅助类
// ================================================

class Dog {
    private String name;
    private int age;

    // 构造方法
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void bark() {
        System.out.println(name + "正在叫: 汪汪汪！");
    }
}

class Person {
    String name;
    int age;

    // 无参构造方法
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    public Person(String name) {
        this.name = name;
    }

    public  Person(int age) {
        this.age = age;
    }

    // 有参构造方法
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Rectangle {
    int width;
    int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

class Calculator {
    private int result;

    public Calculator() {
        this.result = 0;
    }

    public Calculator(int initial) {
        this.result = initial;
    }

    public Calculator(int a, int b) {
        this.result = a + b;
    }

    public int getResult() {
        return result;
    }

    public int add() {
        return result;
    }
}

class MathUtil {
    // 判断是否是质数
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 计算阶乘
    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    // 计算最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 判断是否是偶数
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }
}

class PersonWithThis {
    String name;
    int age;

    public PersonWithThis(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String introduce() {
        return "我是" + this.name + ", 今年" + this.age + "岁";
    }

    public void show() {
        System.out.println("姓名: " + this.name + ", 年龄: " + this.age);
    }

    // 链式调用：返回this
    public PersonWithThis setName(String name) {
        this.name = name;
        return this;
    }

    public PersonWithThis setAge(int age) {
        this.age = age;
        return this;
    }
}

class StudentWithThis {
    String name;
    int age;
    int grade;

    // 无参构造
    public StudentWithThis() {
        this("Guest", 18, 1);  // 调用三参构造方法
    }

    // 只传名字
    public StudentWithThis(String name) {
        this(name, 18, 1);  // 调用三参构造方法
    }

    // 三个参数
    public StudentWithThis(String name, int age, int grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getInfo() {
        return "姓名: " + name + ", 年龄: " + age + ", 年级: " + grade;
    }
}
