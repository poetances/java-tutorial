package com.zhucj.java.generics;

import java.util.*;

/**
 * =====================================================
 * Java 泛型（Generics）详解
 * =====================================================
 *
 * 【一、什么是泛型】
 * -----------------
 * 泛型是JDK5引入的特性，允许在定义类、接口、方法时使用类型参数。
 * - 类型安全：编译时检查类型，避免ClassCastException
 * - 消除强制转换：不需要手动类型转换
 * - 代码复用：一套代码支持多种类型
 *
 * 【二、为什么需要泛型】
 * --------------------
 * 没有泛型的问题：
 * 1. 类型不安全 - 可以放入任何类型，取出时可能ClassCastException
 * 2. 需要强制转换 - 每次取出都要手动转换
 * 3. 代码不清晰 - 无法从代码看出集合存储的类型
 *
 * 【三、泛型的发展】
 * -----------------
 * JDK 5:  引入泛型（类型擦除实现）
 * JDK 7:  菱形操作符 <>
 * JDK 8:  泛型目标类型推断
 * JDK 10: var局部变量类型推断
 *
 * =====================================================
 */
public class GenericsLesson {

    public static void main(String[] args) {
        System.out.println("========== 泛型基础 ==========");
        genericsBasics();

        System.out.println("\n========== 泛型类 ==========");
        genericClass();

        System.out.println("\n========== 泛型接口 ==========");
        genericInterface();

        System.out.println("\n========== 泛型方法 ==========");
        genericMethod();

        System.out.println("\n========== 类型通配符 ==========");
        wildcardTypes();

        System.out.println("\n========== 类型边界 ==========");
        typeBounds();

        System.out.println("\n========== 泛型与继承 ==========");
        genericsAndInheritance();

        System.out.println("\n========== 类型擦除 ==========");
        typeErasure();

        System.out.println("\n========== 泛型限制 ==========");
        genericLimitations();

        System.out.println("\n========== 实际应用场景 ==========");
        practicalApplications();

        System.out.println("\n========== 注意事项 ==========");
        cautions();
    }

    // ================================================
    // 一、泛型基础
    // ================================================
    public static void genericsBasics() {
        System.out.println("--- 泛型基础 ---");

        // 【没有泛型的问题】
        System.out.println("1. 没有泛型（JDK5之前）:");
        List rawList = new ArrayList();
        rawList.add("String");
        rawList.add(123);  // 可以添加任何类型
        rawList.add(new Object());

        // 取出时需要强制转换，且可能ClassCastException
        String str = (String) rawList.get(0);  // 需要强转
        System.out.println("   需要强制转换: " + str);

        try {
            String wrong = (String) rawList.get(1);  // ClassCastException!
        } catch (ClassCastException e) {
            System.out.println("   运行时错误: ClassCastException");
        }

        // 【使用泛型】
        System.out.println("\n2. 使用泛型（JDK5+）:");
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("World");
        // stringList.add(123);  // 编译错误！类型安全

        // 不需要强制转换
        String s1 = stringList.get(0);  // 自动转换
        String s2 = stringList.get(1);
        System.out.println("   类型安全: " + s1 + ", " + s2);
        System.out.println("   无需强转，编译时检查");

        // 【泛型的优势】
        System.out.println("\n--- 泛型优势 ---");
        System.out.println("✓ 类型安全 - 编译时检查");
        System.out.println("✓ 消除强转 - 代码更简洁");
        System.out.println("✓ 代码复用 - 一套代码支持多类型");
        System.out.println("✓ 可读性好 - 明确数据类型");
    }

    // ================================================
    // 二、泛型类
    // ================================================
    public static void genericClass() {
        System.out.println("--- 泛型类 ---");

        // 【1】基本使用
        Box<String> stringBox = new Box<>("Hello");
        System.out.println("String Box: " + stringBox.getContent());

        Box<Integer> intBox = new Box<>(123);
        System.out.println("Integer Box: " + intBox.getContent());

        Box<Double> doubleBox = new Box<>(3.14);
        System.out.println("Double Box: " + doubleBox.getContent());

        // 【2】多个类型参数
        Pair<String, Integer> pair = new Pair<>("Age", 25);
        System.out.println("\nPair: " + pair.getKey() + " = " + pair.getValue());

        Pair<String, String> namePair = new Pair<>("First", "Last");
        System.out.println("Name Pair: " + namePair.getKey() + " " + namePair.getValue());

        // 【3】泛型类的特点】
        System.out.println("\n--- 泛型类特点 ---");
        System.out.println("1. 类型参数在类名后声明：<T>");
        System.out.println("2. 可以有多个类型参数：<K, V>");
        System.out.println("3. 创建对象时指定具体类型");
        System.out.println("4. 类型参数可以是任何引用类型");
    }

    // 泛型类示例
    static class Box<T> {
        private T content;

        public Box(T content) {
            this.content = content;
        }

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Box{" + content + "}";
        }
    }

    // 多个类型参数的泛型类
    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Pair{" + key + "=" + value + "}";
        }
    }

    // ================================================
    // 三、泛型接口
    // ================================================
    public static void genericInterface() {
        System.out.println("--- 泛型接口 ---");

        // 【1】实现泛型接口
        StringRepository stringRepo = new StringRepository();
        stringRepo.save("Hello");
        stringRepo.save("World");
        System.out.println("字符串仓库: " + stringRepo.findAll());

        IntegerRepository intRepo = new IntegerRepository();
        intRepo.save(1);
        intRepo.save(2);
        intRepo.save(3);
        System.out.println("整数仓库: " + intRepo.findAll());

        // 【2】泛型接口的应用】
        System.out.println("\n--- 泛型接口应用 ---");
        System.out.println("1. Repository模式（数据访问层）");
        System.out.println("2. Comparator比较器");
        System.out.println("3. Iterable可迭代接口");
        System.out.println("4. Callable/Future异步编程");
    }

    // 泛型接口
    interface Repository<T> {
        void save(T entity);
        T findById(Long id);
        List<T> findAll();
        void delete(Long id);
    }

    // 实现泛型接口
    static class StringRepository implements Repository<String> {
        private final Map<Long, String> data = new HashMap<>();
        private Long nextId = 1L;

        @Override
        public void save(String entity) {
            data.put(nextId++, entity);
        }

        @Override
        public String findById(Long id) {
            return data.get(id);
        }

        @Override
        public List<String> findAll() {
            return new ArrayList<>(data.values());
        }

        @Override
        public void delete(Long id) {
            data.remove(id);
        }
    }

    static class IntegerRepository implements Repository<Integer> {
        private final Map<Long, Integer> data = new HashMap<>();
        private Long nextId = 1L;

        @Override
        public void save(Integer entity) {
            data.put(nextId++, entity);
        }

        @Override
        public Integer findById(Long id) {
            return data.get(id);
        }

        @Override
        public List<Integer> findAll() {
            return new ArrayList<>(data.values());
        }

        @Override
        public void delete(Long id) {
            data.remove(id);
        }
    }

    // ================================================
    // 四、泛型方法
    // ================================================
    public static void genericMethod() {
        System.out.println("--- 泛型方法 ---");

        // 【1】基本泛型方法
        String[] strings = {"A", "B", "C"};
        Integer[] integers = {1, 2, 3};

        printArray(strings);
        printArray(integers);

        // 【2】泛型方法的类型推断
        String first = getFirst(Arrays.asList("A", "B", "C"));
        System.out.println("\n第一个元素: " + first);

        Integer firstNum = getFirst(Arrays.asList(1, 2, 3));
        System.out.println("第一个数字: " + firstNum);

        // 【3】静态泛型方法
        List<String> list1 = Arrays.asList("A", "B");
        List<String> list2 = Arrays.asList("C", "D");
        List<String> merged = merge(list1, list2);
        System.out.println("\n合并列表: " + merged);

        // 【4】泛型方法与泛型类的区别】
        System.out.println("\n--- 泛型方法 vs 泛型类 ---");
        System.out.println("泛型类: 类型参数在类级别，整个类共享");
        System.out.println("泛型方法: 类型参数在方法级别，每个方法独立");
        System.out.println("静态方法必须是泛型方法（不能访问类的类型参数）");

        // 【5】实际应用：交换元素
        Integer[] nums = {1, 2, 3, 4, 5};
        System.out.println("\n交换前: " + Arrays.toString(nums));
        swap(nums, 0, 4);
        System.out.println("交换后: " + Arrays.toString(nums));
    }

    // 泛型方法
    public static <T> void printArray(T[] array) {
        System.out.print("数组: ");
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // 泛型方法 - 获取第一个元素
    public static <T> T getFirst(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // 静态泛型方法 - 合并列表
    public static <T> List<T> merge(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>(list1);
        result.addAll(list2);
        return result;
    }

    // 泛型方法 - 交换数组元素
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // ================================================
    // 五、类型通配符
    // ================================================
    public static void wildcardTypes() {
        System.out.println("--- 类型通配符 ---");

        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        List<Number> numberList = Arrays.asList(1, 2.5, 3);

        // 【1】无界通配符 <?>】
        System.out.println("1. 无界通配符 <?>:");
        printList(intList);
        printList(doubleList);
        printList(numberList);

        // 【2】上界通配符 <? extends T>】
        System.out.println("\n2. 上界通配符 <? extends Number>:");
        printNumbers(intList);      // Integer extends Number
        printNumbers(doubleList);   // Double extends Number
        printNumbers(numberList);   // Number extends Number

        // 【3】下界通配符 <? super T>】
        System.out.println("\n3. 下界通配符 <? super Integer>:");
        addNumbers(intList, 10);
        List<Number> numList = new ArrayList<>();
        addNumbers(numList, 20);
        List<Object> objList = new ArrayList<>();
        addNumbers(objList, 30);

        System.out.println("   intList: " + intList);
        System.out.println("   numList: " + numList);
        System.out.println("   objList: " + objList);

        // 【4】PECS原则】
        System.out.println("\n--- PECS原则 ---");
        System.out.println("Producer Extends - 生产者使用extends");
        System.out.println("Consumer Super - 消费者使用super");
        System.out.println("");
        System.out.println("<? extends T>: 只能读取，不能写入（除了null）");
        System.out.println("<? super T>: 只能写入，读取时是Object");
        System.out.println("<T>: 既能读又能写");
    }

    // 无界通配符
    public static void printList(List<?> list) {
        System.out.print("   列表: ");
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // 上界通配符 - 只能读取
    public static void printNumbers(List<? extends Number> list) {
        System.out.print("   数字: ");
        for (Number num : list) {
            System.out.print(num.doubleValue() + " ");
        }
        System.out.println();
        // list.add(1);  // 编译错误！不能写入
    }

    // 下界通配符 - 只能写入
    public static void addNumbers(List<? super Integer> list, Integer num) {
        list.add(num);  // 可以写入
        // Integer n = list.get(0);  // 编译错误！读取的是Object
    }

    // ================================================
    // 六、类型边界
    // ================================================
    public static void typeBounds() {
        System.out.println("--- 类型边界 ---");

        // 【1】上界 - extends】
        System.out.println("1. 上界（extends）:");
        BoxWithBounds<Number> numberBox = new BoxWithBounds<>(100);
        System.out.println("   Number Box: " + numberBox.getContent());

        BoxWithBounds<Integer> intBox = new BoxWithBounds<>(200);
        System.out.println("   Integer Box: " + intBox.getContent());

        // BoxWithBounds<String> stringBox = new BoxWithBounds<>("test");  // 编译错误

        // 【2】多重边界】
        System.out.println("\n2. 多重边界:");
        ComparableBox<Integer> compBox = new ComparableBox<>(42);
        System.out.println("   可比较Box: " + compBox.getContent());
        System.out.println("   哈希码: " + compBox.getHashCode());

        // 【3】下界 - super（主要用于通配符）】
        System.out.println("\n3. 下界（super）:");
        System.out.println("   主要用于方法参数的通配符");
        System.out.println("   类定义中较少使用");
    }

    // 有边界的泛型类
    static class BoxWithBounds<T extends Number> {
        private T content;

        public BoxWithBounds(T content) {
            this.content = content;
        }

        public T getContent() {
            return content;
        }

        public double doubleValue() {
            return content.doubleValue();  // 可以调用Number的方法
        }
    }

    // 多重边界
    static class ComparableBox<T extends Number & Comparable<T>> {
        private T content;

        public ComparableBox(T content) {
            this.content = content;
        }

        public T getContent() {
            return content;
        }

        public int compareTo(T other) {
            return content.compareTo(other);  // 可以调用Comparable方法
        }

        public int getHashCode() {
            return content.hashCode();  // 可以调用Object方法
        }
    }

    // ================================================
    // 七、泛型与继承
    // ================================================
    public static void genericsAndInheritance() {
        System.out.println("--- 泛型与继承 ---");

        // 【1】泛型不是协变的】
        System.out.println("1. 泛型不是协变的:");
        List<String> stringList = new ArrayList<>();
        // List<Object> objectList = stringList;  // 编译错误！

        // 原因：如果允许，会导致类型不安全
        // objectList.add(123);  // 看似合法，但stringList中会有Integer
        // String s = stringList.get(0);  // ClassCastException!

        // 【2】使用通配符实现协变】
        System.out.println("\n2. 使用通配符实现协变:");
        List<? extends Object> wildcardList = stringList;  // 可以
        System.out.println("   List<? extends Object> = List<String> ✓");

        // 【3】数组是协变的】
        System.out.println("\n3. 数组是协变的:");
        String[] stringArray = {"A", "B"};
        Object[] objectArray = stringArray;  // 可以
        System.out.println("   Object[] = String[] ✓");

        // 但运行时会检查
        try {
            objectArray[0] = 123;  // ArrayStoreException!
        } catch (ArrayStoreException e) {
            System.out.println("   运行时错误: ArrayStoreException");
        }

        // 【4】泛型子类】
        System.out.println("\n4. 泛型子类:");
        NamedBox<String> namedBox = new NamedBox<>("MyBox", "Content");
        System.out.println("   命名Box: " + namedBox.getName() + " = " + namedBox.getContent());
    }

    // 泛型子类
    static class NamedBox<T> extends Box<T> {
        private String name;

        public NamedBox(String name, T content) {
            super(content);
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // ================================================
    // 八、类型擦除
    // ================================================
    public static void typeErasure() {
        System.out.println("--- 类型擦除 ---");

        // 【1】什么是类型擦除】
        System.out.println("1. 类型擦除概念:");
        System.out.println("   泛型信息只在编译时存在");
        System.out.println("   运行时泛型类型被擦除为Object或边界类型");

        // 【2】验证类型擦除】
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        // 运行时类型相同
        System.out.println("\n2. 运行时类型:");
        System.out.println("   List<String> class: " + stringList.getClass());
        System.out.println("   List<Integer> class: " + intList.getClass());
        System.out.println("   两者相同: " + (stringList.getClass() == intList.getClass()));

        // 【3】通过反射添加元素】
        System.out.println("\n3. 绕过泛型检查:");
        try {
            // 通过反射可以向List<String>添加Integer
            stringList.getClass().getMethod("add", Object.class).invoke(stringList, 123);
            System.out.println("   通过反射添加Integer到List<String>: " + stringList);
            System.out.println("   ⚠️ 这破坏了类型安全！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 【4】类型擦除的影响】
        System.out.println("\n--- 类型擦除的影响 ---");
        System.out.println("✗ 不能使用instanceof检查泛型类型");
        System.out.println("✗ 不能创建泛型数组");
        System.out.println("✗ 不能catch泛型异常");
        System.out.println("✗ 静态字段不能是泛型类型");
        System.out.println("✗ 泛型类型信息运行时不可用");

        // 【5】桥方法】
        System.out.println("\n5. 桥方法（Bridge Method）:");
        System.out.println("   编译器自动生成桥方法保持多态");
        GenericParent<String> parent = new GenericChild();
        parent.setValue("Hello");  // 调用桥方法
        System.out.println("   桥方法确保多态正常工作");
    }

    // 泛型父类
    static class GenericParent<T> {
        public void setValue(T value) {
            System.out.println("   Parent setValue: " + value);
        }
    }

    // 泛型子类
    static class GenericChild extends GenericParent<String> {
        @Override
        public void setValue(String value) {
            System.out.println("   Child setValue: " + value);
        }
    }

    // ================================================
    // 九、泛型限制
    // ================================================
    public static void genericLimitations() {
        System.out.println("--- 泛型限制 ---");

        // 【1】不能用基本类型】
        System.out.println("1. 不能用基本类型:");
        // List<int> intList = new ArrayList<>();  // 编译错误
        List<Integer> integerList = new ArrayList<>();  // 必须用包装类
        System.out.println("   必须使用包装类: List<Integer>");

        // 【2】不能创建泛型数组】
        System.out.println("\n2. 不能创建泛型数组:");
        // List<String>[] array = new List<String>[10];  // 编译错误
        List<String>[] workaround = (List<String>[]) new List[10];  // 警告
        System.out.println("   只能创建原始类型数组，然后强转（不安全）");

        // 【3】不能实例化类型参数】
        System.out.println("\n3. 不能实例化类型参数:");
        // T obj = new T();  // 编译错误
        System.out.println("   不能: new T()");
        System.out.println("   解决: 使用工厂模式或Supplier");

        // 【4】不能catch泛型异常】
        System.out.println("\n4. 不能catch泛型异常:");
        // catch (TException e) {}  // 编译错误
        System.out.println("   不能在catch子句中使用类型参数");

        // 【5】静态上下文限制】
        System.out.println("\n5. 静态上下文限制:");
        System.out.println("   静态字段不能是泛型类型");
        System.out.println("   静态方法不能访问类的类型参数");
        System.out.println("   但静态方法可以是泛型方法");

        // 【6】instanceof限制】
        System.out.println("\n6. instanceof限制:");
        Object obj = new ArrayList<String>();
        // if (obj instanceof List<String>) {}  // 编译错误
        if (obj instanceof List) {  // 只能检查原始类型
            System.out.println("   只能检查: obj instanceof List");
        }
    }

    // ================================================
    // 十、实际应用场景
    // ================================================
    public static void practicalApplications() {
        System.out.println("--- 实际应用场景 ---");

        // 【1】通用工具类】
        System.out.println("1. 通用工具类:");
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6);
        Integer max = CollectionsUtil.max(numbers);
        Integer min = CollectionsUtil.min(numbers);
        System.out.println("   最大值: " + max);
        System.out.println("   最小值: " + min);

        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        String longest = CollectionsUtil.maxBy(names, Comparator.comparingInt(String::length));
        System.out.println("   最长名字: " + longest);

        // 【2】Builder模式】
        System.out.println("\n2. Builder模式:");
        User user = new User.Builder()
            .name("张三")
            .age(25)
            .email("zhangsan@example.com")
            .build();
        System.out.println("   用户: " + user);

        // 【3】回调/函数式接口】
        System.out.println("\n3. 回调/函数式接口:");
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        List<String> uppercased = CollectionsUtil.transform(words, String::toUpperCase);
        System.out.println("   转换后: " + uppercased);

        // 【4】Repository模式】
        System.out.println("\n4. Repository模式:");
        UserRepository userRepo = new UserRepository();
        userRepo.save(new UserEntity(1L, "李四"));
        userRepo.save(new UserEntity(2L, "王五"));
        System.out.println("   所有用户: " + userRepo.findAll());

        // 【5】缓存】
        System.out.println("\n5. 泛型缓存:");
        SimpleCache<String> cache = new SimpleCache<>();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        System.out.println("   缓存key1: " + cache.get("key1"));
        System.out.println("   缓存key2: " + cache.get("key2"));
    }

    // 通用集合工具类
    static class CollectionsUtil {
        // 找最大值
        public static <T extends Comparable<T>> T max(List<T> list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("列表不能为空");
            }
            T max = list.get(0);
            for (T item : list) {
                if (item.compareTo(max) > 0) {
                    max = item;
                }
            }
            return max;
        }

        // 找最小值
        public static <T extends Comparable<T>> T min(List<T> list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("列表不能为空");
            }
            T min = list.get(0);
            for (T item : list) {
                if (item.compareTo(min) < 0) {
                    min = item;
                }
            }
            return min;
        }

        // 按条件找最大值
        public static <T> T maxBy(List<T> list, Comparator<T> comparator) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("列表不能为空");
            }
            T max = list.get(0);
            for (T item : list) {
                if (comparator.compare(item, max) > 0) {
                    max = item;
                }
            }
            return max;
        }

        // 转换列表
        public static <T, R> List<R> transform(List<T> list, java.util.function.Function<T, R> func) {
            List<R> result = new ArrayList<>();
            for (T item : list) {
                result.add(func.apply(item));
            }
            return result;
        }
    }

    // Builder模式示例
    static class User {
        private String name;
        private int age;
        private String email;

        private User(Builder builder) {
            this.name = builder.name;
            this.age = builder.age;
            this.email = builder.email;
        }

        public static class Builder {
            private String name;
            private int age;
            private String email;

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder age(int age) {
                this.age = age;
                return this;
            }

            public Builder email(String email) {
                this.email = email;
                return this;
            }

            public User build() {
                return new User(this);
            }
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + ", email='" + email + "'}";
        }
    }

    // Repository模式示例
    static class UserEntity {
        Long id;
        String name;

        UserEntity(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class UserRepository implements Repository<UserEntity> {
        private final Map<Long, UserEntity> data = new HashMap<>();

        @Override
        public void save(UserEntity entity) {
            data.put(entity.id, entity);
        }

        @Override
        public UserEntity findById(Long id) {
            return data.get(id);
        }

        @Override
        public List<UserEntity> findAll() {
            return new ArrayList<>(data.values());
        }

        @Override
        public void delete(Long id) {
            data.remove(id);
        }
    }

    // 简单缓存
    static class SimpleCache<T> {
        private final Map<String, T> cache = new HashMap<>();

        public void put(String key, T value) {
            cache.put(key, value);
        }

        public T get(String key) {
            return cache.get(key);
        }

        public void remove(String key) {
            cache.remove(key);
        }

        public boolean containsKey(String key) {
            return cache.containsKey(key);
        }
    }

    // ================================================
    // 十一、注意事项
    // ================================================
    public static void cautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】优先使用泛型】
        System.out.println("\n1. 优先使用泛型:");
        System.out.println("   ✓ 总是使用泛型集合，不用原始类型");
        System.out.println("   ✓ 避免: List list = new ArrayList()");
        System.out.println("   ✓ 推荐: List<String> list = new ArrayList<>()");

        // 【2】菱形操作符】
        System.out.println("\n2. 菱形操作符（JDK7+）:");
        System.out.println("   ✓ 推荐: List<String> list = new ArrayList<>()");
        System.out.println("   ✗ 冗余: List<String> list = new ArrayList<String>()");

        // 【3】PECS原则】
        System.out.println("\n3. PECS原则:");
        System.out.println("   Producer Extends - 从集合读取用extends");
        System.out.println("   Consumer Super - 向集合写入用super");
        System.out.println("   既读又写用确切类型<T>");

        // 【4】类型安全】
        System.out.println("\n4. 类型安全:");
        System.out.println("   ⚠️ 不要通过反射绕过泛型检查");
        System.out.println("   ⚠️ 注意泛型数组的不安全性");
        System.out.println("   ⚠️ 谨慎使用@SuppressWarnings(\"unchecked\")");

        // 【5】性能考虑】
        System.out.println("\n5. 性能考虑:");
        System.out.println("   - 泛型有装箱/拆箱开销（基本类型→包装类）");
        System.out.println("   - 大量数据考虑专用类型或使用Trove等库");
        System.out.println("   - 泛型本身没有运行时开销（类型擦除）");

        // 【6】API设计】
        System.out.println("\n6. API设计:");
        System.out.println("   ✓ 公共API应该使用泛型");
        System.out.println("   ✓ 提供合理的类型边界");
        System.out.println("   ✓ 文档中说明类型参数的含义");
        System.out.println("   ✓ 考虑向后兼容性");

        // 【7】常见错误】
        System.out.println("\n7. 常见错误:");
        System.out.println("   ✗ 混淆extends和super的用法");
        System.out.println("   ✗ 期望泛型类型信息在运行时可用");
        System.out.println("   ✗ 尝试创建泛型数组");
        System.out.println("   ✗ 在静态上下文中使用类的类型参数");

        // 【8】最佳实践】
        System.out.println("\n8. 最佳实践:");
        System.out.println("   ✓ 使用有意义的类型参数名（T, E, K, V, S等）");
        System.out.println("   ✓ 为泛型类/方法编写清晰的文档");
        System.out.println("   ✓ 合理使用类型边界约束");
        System.out.println("   ✓ 优先考虑组合而非继承");
        System.out.println("   ✓ 使用@SafeVarargs标注安全的可变参数方法");

        // 【9】类型参数命名约定】
        System.out.println("\n9. 类型参数命名约定:");
        System.out.println("   T - Type（类型）");
        System.out.println("   E - Element（元素，用于集合）");
        System.out.println("   K - Key（键）");
        System.out.println("   V - Value（值）");
        System.out.println("   N - Number（数字）");
        System.out.println("   S, U, V - 第二、第三、第四个类型");
    }
}
