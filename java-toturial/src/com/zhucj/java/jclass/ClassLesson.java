package com.zhucj.java.jclass;

/**
 * =====================================================
 * Java 类（Class）详解
 * =====================================================
 *
 * 【一、什么是类】
 * ---------------
 * 类是面向对象编程的核心概念，是对现实世界中事物的抽象。
 * - 类是对象的模板/蓝图
 * - 对象是类的实例
 * - 类封装了数据（属性）和行为（方法）
 *
 * 【二、类的组成要素】
 * -----------------
 * 1. 成员变量（字段/属性）- 描述对象的状态
 * 2. 构造方法           - 创建和初始化对象
 * 3. 成员方法           - 定义对象的行为
 * 4. 代码块             - 静态代码块、实例代码块
 * 5. 内部类            - 嵌套在类中的类
 *
 * 【三、面向对象的三大特性】
 * ----------------------
 * 1. 封装（Encapsulation）   - 隐藏实现细节，暴露接口
 * 2. 继承（Inheritance）     - 代码复用，建立类层次结构
 * 3. 多态（Polymorphism）    - 同一操作作用于不同对象产生不同行为
 *
 * =====================================================
 */
public class ClassLesson {

    public static void main(String[] args) {
        System.out.println("========== 类的基础 ==========");
        classBasics();

        System.out.println("\n========== 访问修饰符 ==========");
        accessModifiers();

        System.out.println("\n========== this关键字 ==========");
        thisKeyword();

        System.out.println("\n========== static关键字 ==========");
        staticKeyword();

        System.out.println("\n========== final关键字 ==========");
        finalKeyword();

        System.out.println("\n========== 继承 ==========");
        inheritance();

        System.out.println("\n========== 方法重写 ==========");
        methodOverride();

        System.out.println("\n========== super关键字 ==========");
        superKeyword();

        System.out.println("\n========== 多态 ==========");
        polymorphism();

        System.out.println("\n========== 抽象类 ==========");
        abstractClass();

        System.out.println("\n========== 接口 ==========");
        interfaceDemo();

        System.out.println("\n========== 内部类 ==========");
        innerClass();

        System.out.println("\n========== 枚举类 ==========");
        enumDemo();

        System.out.println("\n========== 注意事项 ==========");
        cautions();
    }

    // ================================================
    // 一、类的基础
    // ================================================
    public static void classBasics() {
        System.out.println("--- 类的基本概念 ---");

        // 【1】创建对象
        Student student = new Student("张三", 20);

        // 【2】访问属性
        // student.name → "张三"
        // student.age → 20
        System.out.println("学生姓名: " + student.getName());
        System.out.println("学生年龄: " + student.getAge());

        // 【3】调用方法
        // student.study() → 输出: 张三正在学习Java
        student.study();

        // 【4】修改属性（通过setter）
        student.setAge(21);
        // student.getAge() → 21
        System.out.println("修改后年龄: " + student.getAge());

        // 【5】类的本质
        System.out.println("\n--- 类的本质 ---");
        System.out.println("Student.class: " + Student.class);  // 获取Class对象
        System.out.println("student.getClass(): " + student.getClass());  // 获取运行时类

        // 【6】对象的生命周期
        // 1. 创建: new Student()
        // 2. 使用: 调用属性和方法
        // 3. 销毁: GC自动回收（当没有引用指向它时）
    }

    // ================================================
    // 二、访问修饰符
    // ================================================
    public static void accessModifiers() {
        System.out.println("--- 访问修饰符 ---");

        AccessDemo demo = new AccessDemo();

        // 【1】public - 公开访问
        demo.publicMethod();  // 可以访问

        // 【2】private - 私有访问（只能在类内部访问）
//         demo.privateMethod();  // 编译错误！

        // 【3】protected - 受保护访问（同包或子类可访问）
        demo.protectedMethod();  // 同包可以访问

        // 【4】default (不写) - 包级私有（同包可访问）
        demo.defaultMethod();  // 同包可以访问

        // 【访问权限总结】
        System.out.println("\n--- 访问权限表 ---");
        System.out.println("修饰符      | 同类 | 同包 | 子类 | 其他");
        System.out.println("------------|------|------|------|----");
        System.out.println("private     |  √   |  ×   |  ×   |  ×");
        System.out.println("default     |  √   |  √   |  ×   |  ×");
        System.out.println("protected   |  √   |  √   |  √   |  ×");
        System.out.println("public      |  √   |  √   |  √   |  √");

        // 【实际应用：封装】
        BankAccount account = new BankAccount("李四", 1000);
        // account.deposit(500) → 存款成功，余额: 1500.0
        account.deposit(500);
        // account.withdraw(200) → 取款成功，余额: 1300.0
        account.withdraw(200);
        // account.getBalance() → 1300.0
        System.out.println("账户余额: " + account.getBalance());

        // 不能直接访问私有属性
        // account.balance = 999999;  // 编译错误！
    }

    // ================================================
    // 三、this关键字
    // ================================================
    public static void thisKeyword() {
        System.out.println("--- this关键字 ---");

        // 【this的三个作用】
        // 1. 指向当前对象
        // 2. 区分成员变量和局部变量
        // 3. 调用本类的其他构造方法

        Person person = new Person("王五", 25);
        // person.introduce() → 我是王五，今年25岁
        System.out.println(person.introduce());

        // 【链式调用】
        StringBuilder sb = new StringBuilder();
        sb.append("Hello").append(" ").append("World");
        // sb.toString() → "Hello World"
        System.out.println("链式调用: " + sb.toString());

        // 【自定义链式调用】
        User user = new User();
        user.setName("赵六").setAge(30).setEmail("zhaoliu@example.com");
        // user.getInfo() → 姓名: 赵六, 年龄: 30, 邮箱: zhaoliu@example.com
        System.out.println(user.getInfo());
    }

    // ================================================
    // 四、static关键字
    // ================================================
    public static void staticKeyword() {
        System.out.println("--- static关键字 ---");

        // 【static的作用】
        // 1. 修饰成员变量 - 类变量，所有对象共享
        // 2. 修饰方法     - 类方法，不需要对象即可调用
        // 3. 修饰代码块   - 类加载时执行一次
        // 4. 修饰内部类   - 静态内部类

        // 【1】静态变量
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();

        // Counter.count → 3 （所有对象共享同一个count）
        System.out.println("创建了 " + Counter.getCount() + " 个Counter对象");

        // 【2】静态方法
        // MathUtil.add(5, 3) → 8
        System.out.println("MathUtil.add(5, 3): " + MathUtil.add(5, 3));

        // 【3】静态代码块
        // 在类加载时执行，只执行一次
        System.out.println("StaticBlock.message: " + StaticBlock.getMessage());

        // 【4】静态导入
        // import static java.lang.Math.*;
        // sqrt(16) → 4.0
        System.out.println("sqrt(16): " + Math.sqrt(16));

        // 【5】单例模式（经典应用）
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        // s1 == s2 → true （同一个实例）
        System.out.println("s1 == s2: " + (s1 == s2));
    }

    // ================================================
    // 五、final关键字
    // ================================================
    public static void finalKeyword() {
        System.out.println("--- final关键字 ---");

        // 【final的三个作用】
        // 1. 修饰变量 - 常量，不可修改
        // 2. 修饰方法 - 不能被重写
        // 3. 修饰类   - 不能被继承

        // 【1】final变量
        final int MAX_VALUE = 100;
        // MAX_VALUE = 200;  // 编译错误！
        System.out.println("MAX_VALUE: " + MAX_VALUE);

        // 【2】final方法
        FinalChild child = new FinalChild();
        // child.finalMethod() → 父类的final方法
        child.finalMethod();

        // 【3】final类
        String str = "Hello";  // String是final类，不能被继承
        System.out.println("String是final类: " + str.getClass().getName());

        // 【4】空白final
        BlankFinal blank = new BlankFinal(42);
        // blank.getValue() → 42
        System.out.println("blank.getValue(): " + blank.getValue());

        // 【5】final参数
        System.out.println("final参数示例: " + useFinalParam("test"));
    }

    public static String useFinalParam(final String param) {
        // param = "other";  // 编译错误！
        return param.toUpperCase();
    }

    // ================================================
    // 六、继承
    // ================================================
    public static void inheritance() {
        System.out.println("--- 继承 ---");

        // 【继承的概念】
        // - 子类继承父类的属性和方法
        // - 实现代码复用
        // - 建立类的层次结构
        // - Java只支持单继承（一个类只能有一个直接父类）

        // 【1】基本继承
        Dog dog = new Dog("旺财", 3, "金毛");
        // dog.getName() → "旺财" （继承自Animal）
        // dog.getAge() → 3 （继承自Animal）
        System.out.println("狗狗名字: " + dog.getName());
        System.out.println("狗狗年龄: " + dog.getAge());
        System.out.println("狗狗品种: " + dog.getBreed());
        // dog.eat() → 旺财正在吃东西
        dog.eat();
        // dog.bark() → 旺财正在汪汪叫
        dog.bark();

        // 【2】继承的层次结构
        Cat cat = new Cat("咪咪", 2, "波斯猫");
        // cat.sleep() → 咪咪正在睡觉 （继承自Animal）
        cat.sleep();
        // cat.meow() → 咪咪正在喵喵叫
        cat.meow();

        // 【3】Object类 - 所有类的根类
        Object obj = dog;
        // obj.toString() → 自动调用Dog的toString
        System.out.println("obj.toString(): " + obj.toString());

        // 【4】instanceof运算符
        System.out.println("dog instanceof Dog: " + (dog instanceof Dog));  // true
        System.out.println("dog instanceof Animal: " + (dog instanceof Animal));  // true
        System.out.println("dog instanceof Object: " + (dog instanceof Object));  // true

        // 【5】类型转换
        Animal animal = new Dog("小黑", 2, "哈士奇");  // 向上转型（自动）
        if (animal instanceof Dog) {
            Dog d = (Dog) animal;  // 向下转型（需要强制）
            System.out.println("向下转型成功: " + d.getBreed());
        }
    }

    // ================================================
    // 七、方法重写
    // ================================================
    public static void methodOverride() {
        System.out.println("--- 方法重写 ---");

        // 【重写的规则】
        // 1. 方法名、参数列表必须相同
        // 2. 返回类型可以相同或是其子类
        // 3. 访问权限不能更严格
        // 4. 不能抛出新的或更广泛的异常
        // 5. 使用@Override注解（推荐）

        Animal animal = new Animal("动物", 1);
        Bird bird = new Bird("鹦鹉", 2, "彩色");

        // animal.makeSound() → 动物发出声音
        System.out.println("animal.makeSound(): " + animal.makeSound());
        // bird.makeSound() → 鹦鹉叽叽喳喳叫 （重写了父类方法）
        System.out.println("bird.makeSound(): " + bird.makeSound());

        // 【重写 vs 重载】
        System.out.println("\n--- 重写 vs 重载 ---");
        System.out.println("重写(Override): 子类重写父类方法，方法签名相同");
        System.out.println("重载(Overload): 同一类中，方法名相同，参数不同");

        // 【@Override注解的作用】
        // 1. 编译器检查是否是正确的重写
        // 2. 提高代码可读性
        // 3. 防止拼写错误
    }

    // ================================================
    // 八、super关键字
    // ================================================
    public static void superKeyword() {
        System.out.println("--- super关键字 ---");

        // 【super的作用】
        // 1. 调用父类的构造方法
        // 2. 调用父类的方法
        // 3. 访问父类的属性

        Teacher teacher = new Teacher("张老师", 35, "数学");
        // teacher.introduce() → 我是张老师，今年35岁，教数学
        System.out.println(teacher.introduce());

        // 【super和this的区别】
        System.out.println("\n--- super vs this ---");
        System.out.println("this: 指向当前对象");
        System.out.println("super: 指向父类对象");

        // 【构造方法中的super】
        // 子类构造方法第一行默认调用super()
        // 如果父类没有无参构造，必须显式调用super(参数)
    }

    // ================================================
    // 九、多态
    // ================================================
    public static void polymorphism() {
        System.out.println("--- 多态 ---");

        // 【多态的三个条件】
        // 1. 继承
        // 2. 重写
        // 3. 父类引用指向子类对象

        // 【1】多态的表现形式
        Animal a1 = new Dog("旺财", 3, "金毛");
        Animal a2 = new Cat("咪咪", 2, "波斯猫");
        Animal a3 = new Bird("鹦鹉", 1, "绿色");

        // 同一个方法，不同的行为
        // a1.makeSound() → 旺财汪汪叫
        // a2.makeSound() → 咪咪喵喵叫
        // a3.makeSound() → 鹦鹉叽叽喳喳叫
        System.out.println(a1.makeSound());
        System.out.println(a2.makeSound());
        System.out.println(a3.makeSound());

        // 【2】多态的实际应用
        System.out.println("\n--- 多态的实际应用 ---");

        // 动物园喂食
        Zoo zoo = new Zoo();
        zoo.feedAnimal(new Dog("大黄", 4, "土狗"));
        zoo.feedAnimal(new Cat("小花", 3, "橘猫"));
        zoo.feedAnimal(new Bird("小黄", 1, "黄色"));

        // 【3】多态的优势
        System.out.println("\n--- 多态的优势 ---");
        System.out.println("1. 提高代码的可扩展性");
        System.out.println("2. 降低代码的耦合度");
        System.out.println("3. 提高代码的可维护性");
        System.out.println("4. 符合开闭原则（对扩展开放，对修改关闭）");

        // 【4】多态的局限性
        // 父类引用只能调用父类中声明的方法
        // 如果要调用子类特有的方法，需要向下转型
        Animal animal = new Dog("小黑", 2, "哈士奇");
        // animal.bark();  // 编译错误！Animal中没有bark方法
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dog.bark();  // 向下转型后可以调用
        }
    }

    // ================================================
    // 十、抽象类
    // ================================================
    public static void abstractClass() {
        System.out.println("--- 抽象类 ---");

        // 【抽象类的特点】
        // 1. 用abstract修饰
        // 2. 不能实例化
        // 3. 可以包含抽象方法（没有方法体）
        // 4. 可以包含具体方法
        // 5. 子类必须实现所有抽象方法（除非子类也是抽象类）

        // AbstractShape shape = new AbstractShape();  // 编译错误！

        Circle circle = new Circle(5);
        Rectangle rect = new Rectangle(4, 6);

        // circle.calculateArea() → 78.54
        System.out.println("圆的面积: " + String.format("%.2f", circle.calculateArea()));
        // rect.calculateArea() → 24.0
        System.out.println("矩形面积: " + rect.calculateArea());

        // circle.getDescription() → 圆形，半径: 5.0
        System.out.println(circle.getDescription());
        // rect.getDescription() → 矩形，长: 4.0, 宽: 6.0
        System.out.println(rect.getDescription());

        // 【抽象类 vs 接口】
        System.out.println("\n--- 抽象类 vs 接口 ---");
        System.out.println("抽象类: 表示'是什么'，可以有状态，单继承");
        System.out.println("接口:   表示'能做什么'，不能有状态，多实现");
    }

    // ================================================
    // 十一、接口
    // ================================================
    public static void interfaceDemo() {
        System.out.println("--- 接口 ---");

        // 【接口的特点】
        // 1. 用interface定义
        // 2. 所有方法默认是public abstract
        // 3. 所有字段默认是public static final
        // 4. 一个类可以实现多个接口
        // 5. JDK8+可以有默认方法和静态方法
        // 6. JDK9+可以有私有方法

        // 【1】基本实现
        SmartPhone phone = new SmartPhone("iPhone 15", 8999);
        // phone.call("13800138000") → 正在拨打 13800138000...
        phone.call("13800138000");
        // phone.sendMessage("13800138000", "你好") → 发送短信到 13800138000: 你好
        phone.sendMessage("13800138000", "你好");
        // phone.browseInternet("https://www.example.com") → 浏览网页: https://www.example.com
        phone.browseInternet("https://www.example.com");
        // phone.takePhoto() → 拍照中...咔嚓！
        phone.takePhoto();

        // 【2】多接口实现
        HybridDevice device = new HybridDevice("智能手表");
        device.call("13900139000");
        device.browseInternet("https://watch.example.com");
        device.monitorHeartRate();  // 心率: 75 bpm

        // 【3】接口的继承
//        AdvancedCamera camera = new AdvancedCamera("Canon EOS R5");
//        camera.takePhoto();
//        camera.recordVideo();  // 录制视频中...
//        camera.autoFocus();    // 自动对焦中...

        // 【4】函数式接口
        // 只有一个抽象方法的接口
        java.util.Comparator<String> comparator = (s1, s2) -> s1.length() - s2.length();
        System.out.println("比较结果: " + comparator.compare("hello", "hi"));

        // 【5】接口的实际应用
        System.out.println("\n--- 接口的实际应用 ---");
        System.out.println("1. 定义规范（如JDBC、Servlet）");
        System.out.println("2. 解耦（依赖倒置原则）");
        System.out.println("3. 多继承的替代方案");
        System.out.println("4. 回调机制");
        System.out.println("5. 策略模式");
    }

    // ================================================
    // 十二、内部类
    // ================================================
    public static void innerClass() {
        System.out.println("--- 内部类 ---");

        // 【内部类的分类】
        // 1. 成员内部类
        // 2. 静态内部类
        // 3. 局部内部类
        // 4. 匿名内部类

        // 【1】成员内部类
        OuterClass outer = new OuterClass("外部类");
        OuterClass.InnerClass inner = outer.new InnerClass();
        // inner.display() → 外部类名称: 外部类, 内部类数据: 内部数据
        inner.display();

        // 【2】静态内部类
        OuterClass.StaticInnerClass staticInner = new OuterClass.StaticInnerClass();
        // staticInner.show() → 静态内部类
        staticInner.show();

        // 【3】局部内部类
        outer.useLocalClass();

        // 【4】匿名内部类
        outer.useAnonymousClass();

        // 【5】Lambda表达式（匿名内部类的简化）
        java.util.List<String> list = java.util.Arrays.asList("banana", "apple", "cherry");
        list.sort((a, b) -> a.compareTo(b));
        System.out.println("排序后: " + list);

        // 【内部类的应用场景】
        System.out.println("\n--- 内部类的应用 ---");
        System.out.println("1. 封装性：隐藏实现细节");
        System.out.println("2. 多继承：间接实现多继承");
        System.out.println("3. 回调：事件监听器");
        System.out.println("4. 辅助类：只被外部类使用");
    }

    // ================================================
    // 十三、枚举类
    // ================================================
    public static void enumDemo() {
        System.out.println("--- 枚举类 ---");

        // 【枚举的特点】
        // 1. 用enum定义
        // 2. 隐式继承java.lang.Enum
        // 3. 构造函数默认private
        // 4. 线程安全
        // 5. 可以实现接口

        // 【1】基本使用
        Day today = Day.MONDAY;
        System.out.println("今天: " + today);
        System.out.println("ordinal: " + today.ordinal());  // 0
        System.out.println("name: " + today.name());        // MONDAY

        // 【2】遍历枚举
        System.out.println("\n一周的天数:");
        for (Day day : Day.values()) {
            System.out.println(day + " (ordinal: " + day.ordinal() + ")");
        }

        // 【3】带属性的枚举
        Color red = Color.RED;
        // red.getColorCode() → "#FF0000"
        System.out.println("红色代码: " + red.getColorCode());
        // red.getDescription() → "红色"
        System.out.println("红色描述: " + red.getDescription());

        // 【4】switch中使用枚举
        printDayInfo(Day.WEDNESDAY);
        printDayInfo(Day.SUNDAY);

        // 【5】枚举的实际应用
        System.out.println("\n--- 枚举的应用 ---");
        Order order = new Order("ORD001", OrderStatus.PENDING);
        System.out.println("订单状态: " + order.getStatus());
        order.setStatus(OrderStatus.SHIPPED);
        System.out.println("更新后状态: " + order.getStatus());
    }

    public static void printDayInfo(Day day) {
        switch (day) {
            case MONDAY:
                System.out.println("星期一：新的一周开始");
                break;
            case FRIDAY:
                System.out.println("星期五：即将周末");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("周末：休息日");
                break;
            default:
                System.out.println("工作日");
        }
    }

    // ================================================
    // 十四、注意事项
    // ================================================
    public static void cautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】命名规范
        System.out.println("\n1. 命名规范:");
        System.out.println("   - 类名: 大驼峰 (Student, UserProfile)");
        System.out.println("   - 方法名: 小驼峰 (getName, calculateSum)");
        System.out.println("   - 常量: 全大写+下划线 (MAX_VALUE, PI)");

        // 【2】继承的限制
        System.out.println("\n2. 继承限制:");
        System.out.println("   - Java不支持多继承（一个类只能有一个父类）");
        System.out.println("   - 可以用接口实现多重继承的效果");
        System.out.println("   - final类不能被继承");

        // 【3】构造方法的注意点
        System.out.println("\n3. 构造方法:");
        System.out.println("   - 子类构造方法第一行默认调用super()");
        System.out.println("   - 如果父类没有无参构造，必须显式调用super(参数)");

        // 【4】多态的注意事项
        System.out.println("\n4. 多态注意:");
        System.out.println("   - 父类引用只能调用父类中声明的方法");
        System.out.println("   - 向下转型前要用instanceof检查");
        System.out.println("   - 避免过度使用向下转型");

        // 【5】抽象类和接口的选择
        System.out.println("\n5. 抽象类 vs 接口:");
        System.out.println("   - 有共同状态和行为 → 抽象类");
        System.out.println("   - 只有行为规范 → 接口");
        System.out.println("   - 需要多继承 → 接口");

        // 【6】equals和hashCode
        System.out.println("\n6. equals和hashCode:");
        System.out.println("   - 重写equals时必须重写hashCode");
        System.out.println("   - 用于HashMap、HashSet等集合");

        // 【7】深拷贝 vs 浅拷贝
        System.out.println("\n7. 深拷贝 vs 浅拷贝:");
        System.out.println("   - 浅拷贝: 复制引用，指向同一对象");
        System.out.println("   - 深拷贝: 复制对象本身，创建新对象");

        // 【8】设计原则
        System.out.println("\n8. 设计原则:");
        System.out.println("   - 单一职责原则: 一个类只做一件事");
        System.out.println("   - 开闭原则: 对扩展开放，对修改关闭");
        System.out.println("   - 依赖倒置: 依赖抽象，不依赖具体");
        System.out.println("   - 接口隔离: 接口要小而专");
    }
}

// ================================================
// 辅助类定义
// ================================================

// 【学生类 - 展示封装】
class Student {
    private String name;  // 私有属性
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter/setter - 提供访问接口
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age < 150) {  // 数据验证
            this.age = age;
        }
    }

    public void study() {
        System.out.println(name + "正在学习Java");
    }
}

// 【访问修饰符演示】
class AccessDemo {
    public void publicMethod() {
        System.out.println("public方法");
    }

    private void privateMethod() {
        System.out.println("private方法");
    }

    protected void protectedMethod() {
        System.out.println("protected方法");
    }

    void defaultMethod() {
        System.out.println("default方法");
    }
}

// 【银行账户 - 封装示例】
class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存款成功，余额: " + balance);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("取款成功，余额: " + balance);
        } else {
            System.out.println("余额不足");
        }
    }

    public double getBalance() {
        return balance;
    }
}

// 【Person类 - this示例】
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;  // this区分成员变量和参数
        this.age = age;
    }

    public String introduce() {
        return "我是" + name + "，今年" + age + "岁";
    }
}

// 【User类 - 链式调用】
class User {
    private String name;
    private int age;
    private String email;

    public User setName(String name) {
        this.name = name;
        return this;  // 返回this实现链式调用
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getInfo() {
        return "姓名: " + name + ", 年龄: " + age + ", 邮箱: " + email;
    }
}

// 【Counter类 - static示例】
class Counter {
    private static int count = 0;  // 静态变量，所有对象共享

    public Counter() {
        count++;  // 每创建一个对象，count加1
    }

    public static int getCount() {
        return count;
    }
}

// 【工具类 - static方法】
class MathUtil {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }
}

// 【静态代码块示例】
class StaticBlock {
    private static String message;

    // 静态代码块 - 类加载时执行
    static {
        message = "静态代码块已执行";
        System.out.println("[StaticBlock] 静态代码块执行");
    }

    public static String getMessage() {
        return message;
    }
}

// 【单例模式】
class Singleton {
    private static Singleton instance;

    private Singleton() {}  // 私有构造方法

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// 【final示例】
class FinalParent {
    public final void finalMethod() {
        System.out.println("父类的final方法");
    }
}

class FinalChild extends FinalParent {
    // public void finalMethod() {}  // 编译错误！不能重写final方法
}

final class FinalClass {
    // final类不能被继承
}

// class SubFinalClass extends FinalClass {}  // 编译错误！

class BlankFinal {
    private final int value;

    public BlankFinal(int value) {
        this.value = value;  // 必须在构造方法中初始化
    }

    public int getValue() {
        return value;
    }
}

// 【继承示例 - 动物类层次】
class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void eat() {
        System.out.println(name + "正在吃东西");
    }

    public void sleep() {
        System.out.println(name + "正在睡觉");
    }

    public String makeSound() {
        return name + "发出声音";
    }

    @Override
    public String toString() {
        return "Animal{name='" + name + "', age=" + age + "}";
    }
}

class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);  // 调用父类构造方法
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void bark() {
        System.out.println(name + "正在汪汪叫");
    }

    @Override
    public String makeSound() {
        return name + "汪汪叫";
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', age=" + age + ", breed='" + breed + "'}";
    }
}

class Cat extends Animal {
    private String breed;

    public Cat(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void meow() {
        System.out.println(name + "正在喵喵叫");
    }

    @Override
    public String makeSound() {
        return name + "喵喵叫";
    }
}

class Bird extends Animal {
    private String color;

    public Bird(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void fly() {
        System.out.println(name + "正在飞翔");
    }

    @Override
    public String makeSound() {
        return name + "叽叽喳喳叫";
    }
}

// 【Teacher类 - super示例】
class Teacher extends Person {
    private String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);  // 调用父类构造方法
        this.subject = subject;
    }

    public String introduce() {
        return super.introduce() + "，教" + subject;  // 调用父类方法
    }
}

// 【动物园 - 多态应用】
class Zoo {
    public void feedAnimal(Animal animal) {
        System.out.println("饲养员喂食: " + animal.getName());
        animal.eat();
        System.out.println(animal.makeSound());
        System.out.println();
    }
}

// 【抽象类 - 形状】
abstract class AbstractShape {
    protected String name;

    public AbstractShape(String name) {
        this.name = name;
    }

    // 抽象方法 - 没有方法体
    public abstract double calculateArea();

    // 具体方法
    public String getDescription() {
        return name + "，面积: " + String.format("%.2f", calculateArea());
    }

}

class Circle extends AbstractShape {
    private double radius;

    public Circle(double radius) {
        super("圆形");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getDescription() {
        return "圆形，半径: " + radius;
    }
}

class Rectangle extends AbstractShape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        super("矩形");
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public String getDescription() {
        return "矩形，长: " + width + ", 宽: " + height;
    }
}

// 【接口定义】
interface Callable {
    void call(String phoneNumber);
}

interface Messageable {
    void sendMessage(String phoneNumber, String message);
}

interface InternetBrowsable {
    void browseInternet(String url);
}

interface Photographable {
    void takePhoto();
}

// 【智能手机 - 实现多个接口】
class SmartPhone implements Callable, Messageable, InternetBrowsable, Photographable {
    private String model;
    private double price;

    public SmartPhone(String model, double price) {
        this.model = model;
        this.price = price;
    }

    @Override
    public void call(String phoneNumber) {
        System.out.println("正在拨打 " + phoneNumber + "...");
    }

    @Override
    public void sendMessage(String phoneNumber, String message) {
        System.out.println("发送短信到 " + phoneNumber + ": " + message);
    }

    @Override
    public void browseInternet(String url) {
        System.out.println("浏览网页: " + url);
    }

    @Override
    public void takePhoto() {
        System.out.println("拍照中...咔嚓！");
    }
}

// 【混合设备 - 实现多个接口】
class HybridDevice implements Callable, InternetBrowsable {
    private String name;

    public HybridDevice(String name) {
        this.name = name;
    }

    @Override
    public void call(String phoneNumber) {
        System.out.println(name + " 正在拨打 " + phoneNumber);
    }

    @Override
    public void browseInternet(String url) {
        System.out.println(name + " 浏览: " + url);
    }

    public void monitorHeartRate() {
        System.out.println("心率: 75 bpm");
    }
}

// 【接口继承】
interface Camera extends Photographable {
    void recordVideo();
}

interface AdvancedCamera extends Camera {
    void autoFocus();
}

class DigitalCamera implements AdvancedCamera {
    private String model;

    public DigitalCamera(String model) {
        this.model = model;
    }

    @Override
    public void takePhoto() {
        System.out.println(model + " 拍照中...");
    }

    @Override
    public void recordVideo() {
        System.out.println(model + " 录制视频中...");
    }

    @Override
    public void autoFocus() {
        System.out.println(model + " 自动对焦中...");
    }
}

// 【内部类示例】
class OuterClass {
    private String outerName;

    public OuterClass(String name) {
        this.outerName = name;
    }

    // 【1】成员内部类
    class InnerClass {
        private String innerData = "内部数据";

        public void display() {
            // 内部类可以访问外部类的私有成员
            System.out.println("外部类名称: " + outerName + ", 内部类数据: " + innerData);
        }
    }

    // 【2】静态内部类
    static class StaticInnerClass {
        public void show() {
            System.out.println("静态内部类");
        }
    }

    // 【3】局部内部类
    public void useLocalClass() {
        class LocalClass {
            public void display() {
                System.out.println("局部内部类");
            }
        }
        LocalClass local = new LocalClass();
        local.display();
    }

    // 【4】匿名内部类
    public void useAnonymousClass() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类");
            }
        };
        runnable.run();
    }
}

// 【枚举类】
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum Color {
    RED("#FF0000", "红色"),
    GREEN("#00FF00", "绿色"),
    BLUE("#0000FF", "蓝色");

    private final String colorCode;
    private final String description;

    Color(String colorCode, String description) {
        this.colorCode = colorCode;
        this.description = description;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getDescription() {
        return description;
    }
}

// 【订单状态枚举】
enum OrderStatus {
    PENDING("待处理"),
    PAID("已支付"),
    SHIPPED("已发货"),
    DELIVERED("已送达"),
    CANCELLED("已取消");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

class Order {
    private String orderId;
    private OrderStatus status;

    public Order(String orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
