package com.zhucj.java.array;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * =====================================================
 * Java Set（集合）详解
 * =====================================================
 *
 * 【一、Set是什么】
 * -----------------
 * Set是不允许重复元素的集合。
 * - 元素唯一，不允许重复
 * - 最多允许一个null元素（取决于实现）
 * - 不保证顺序（除非使用LinkedHashSet或TreeSet）
 *
 * 【二、Set的主要实现类】
 * --------------------
 * 1. HashSet          - 基于HashMap，无序，最常用
 * 2. LinkedHashSet    - 基于HashMap+链表，保持插入顺序
 * 3. TreeSet          - 基于TreeMap，元素排序
 * 4. EnumSet          - 专门用于枚举类型，性能极高
 * 5. CopyOnWriteArraySet - 线程安全，读多写少
 * 6. ConcurrentSkipListSet - 线程安全且排序
 *
 * 【三、Set vs List】
 * -----------------
 * List: 有序集合，元素可重复，通过索引访问
 * Set:  无序集合，元素唯一，不能通过索引访问
 *
 * 常用：HashSet、LinkedHashSet、TreeSet.
 * =====================================================
 */
public class SetLesson {

    public static void main(String[] args) {
        System.out.println("========== Set基础 ==========");
        setBasics();

        System.out.println("\n========== HashSet详解 ==========");
        hashSetDemo();

        System.out.println("\n========== LinkedHashSet详解 ==========");
        linkedHashSetDemo();

        System.out.println("\n========== TreeSet详解 ==========");
        treeSetDemo();

        System.out.println("\n========== EnumSet详解 ==========");
        enumSetDemo();

        System.out.println("\n========== CopyOnWriteArraySet详解 ==========");
        copyOnWriteArraySetDemo();

        System.out.println("\n========== Set常用操作 ==========");
        setOperations();

        System.out.println("\n========== Set遍历方式 ==========");
        setTraversal();

        System.out.println("\n========== Set运算 ==========");
        setOperationsMath();

        System.out.println("\n========== Set去重应用 ==========");
        setDeduplication();

        System.out.println("\n========== 不可变Set ==========");
        immutableSet();

        System.out.println("\n========== Set性能对比 ==========");
        performanceComparison();

        System.out.println("\n========== 注意事项 ==========");
        cautions();
    }

    // ================================================
    // 一、Set基础
    // ================================================
    public static void setBasics() {
        System.out.println("--- Set基本概念 ---");

        // 【1】创建Set
        Set<String> set = new HashSet<>();

        // 【2】添加元素
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        // set → [Apple, Banana, Cherry]
        System.out.println("添加后: " + set);

        // 【3】添加重复元素（会被忽略）
        boolean added = set.add("Apple");  // 返回false
        System.out.println("添加重复元素: " + added);
        System.out.println("Set内容: " + set);  // 仍然是3个元素

        // 【4】判断是否包含
        // set.contains("Apple") → true
        System.out.println("包含Apple: " + set.contains("Apple"));

        // 【5】删除元素
        set.remove("Banana");
        // set → [Apple, Cherry]
        System.out.println("删除后: " + set);

        // 【6】Set大小
        // set.size() → 2
        System.out.println("Set大小: " + set.size());

        // 【7】判断是否为空
        System.out.println("是否为空: " + set.isEmpty());

        // 【8】Set特性
        System.out.println("\n--- Set特性 ---");
        Set<Integer> nums = new HashSet<>();
        nums.add(1);
        nums.add(2);
        nums.add(1);  // 重复，被忽略
        // nums → [1, 2]
        System.out.println("元素唯一性: " + nums);

        nums.add(null);  // 允许一个null
        System.out.println("允许null: " + nums);
    }

    // ================================================
    // 二、HashSet详解
    // ================================================
    public static void hashSetDemo() {
        System.out.println("--- HashSet详解 ---");

        // 【HashSet的特点】
        // 1. 基于HashMap实现
        // 2. 允许null元素
        // 3. 无序（不保证插入顺序）
        // 4. 非线程安全
        // 5. 性能优秀：add/remove/contains平均O(1)

        // 【1】创建HashSet
        HashSet<String> set = new HashSet<>();

        // 【2】指定初始容量和负载因子
        HashSet<String> customSet = new HashSet<>(16, 0.75f);
        System.out.println("初始容量: 16, 负载因子: 0.75");

        // 【3】从Collection创建
        List<String> list = Arrays.asList("A", "B", "C");
        HashSet<String> fromList = new HashSet<>(list);
        System.out.println("从List创建: " + fromList);

        // 【4】基本操作
        set.add("Java");
        set.add("Python");
        set.add("JavaScript");
        set.add("Go");
        // set → [Java, Python, JavaScript, Go]（顺序不确定）
        System.out.println("基本操作: " + set);

        // 【5】允许null
        set.add(null);
        System.out.println("允许null: " + set);

        // 【6】批量操作
        Set<String> other = new HashSet<>(Arrays.asList("Ruby", "Swift"));
        set.addAll(other);
        System.out.println("addAll后: " + set);

        // 【7】retainAll（保留交集）
        Set<String> common = new HashSet<>(Arrays.asList("Java", "Go", "Rust"));
        set.retainAll(common);
        System.out.println("retainAll后: " + set);

        // 【8】removeAll（删除交集）
        set.removeAll(Arrays.asList("Java"));
        System.out.println("removeAll后: " + set);

        // 【9】clear
        set.clear();
        System.out.println("clear后: " + set);

        // 【10】内部机制
        System.out.println("\n--- HashSet内部机制 ---");
        System.out.println("底层使用HashMap存储");
        System.out.println("元素作为Key，一个固定的Object作为Value");
        System.out.println("因此具有HashMap的所有特性");
    }

    // ================================================
    // 三、LinkedHashSet详解
    // ================================================
    public static void linkedHashSetDemo() {
        System.out.println("--- LinkedHashSet详解 ---");

        // 【LinkedHashSet的特点】
        // 1. 继承自HashSet
        // 2. 基于HashMap+双向链表
        // 3. 保持插入顺序
        // 4. 允许null元素
        // 5. 非线程安全

        // 【1】保持插入顺序
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("First");
        set.add("Second");
        set.add("Third");
        set.add("Fourth");
        // 按插入顺序遍历
        System.out.println("插入顺序: " + set);

        // 【2】与HashSet对比
        HashSet<String> hashSet = new HashSet<>(set);
        System.out.println("HashSet顺序: " + hashSet);  // 顺序不确定
        System.out.println("LinkedHashSet顺序: " + set);  // 保持插入顺序

        // 【3】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 需要保持插入顺序的去重");
        System.out.println("2. LRU缓存的基础结构");
        System.out.println("3. 需要预测迭代顺序的场景");
    }

    // ================================================
    // 四、TreeSet详解
    // ================================================
    public static void treeSetDemo() {
        System.out.println("--- TreeSet详解 ---");

        // 【TreeSet的特点】
        // 1. 基于TreeMap实现（红黑树）
        // 2. 元素自动排序（自然顺序或自定义Comparator）
        // 3. 不允许null元素
        // 4. 非线程安全
        // 5. add/remove/contains时间复杂度O(log n)

        // 【1】自然排序
        TreeSet<String> naturalOrder = new TreeSet<>();
        naturalOrder.add("Banana");
        naturalOrder.add("Apple");
        naturalOrder.add("Cherry");
        naturalOrder.add("Date");
        // 按字母顺序排序
        System.out.println("自然排序: " + naturalOrder);

        // 【2】数字排序
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        // 按数字升序
        System.out.println("数字排序: " + numbers);

        // 【3】自定义排序（降序）
        TreeSet<Integer> reverseOrder = new TreeSet<>(Comparator.reverseOrder());
        reverseOrder.add(5);
        reverseOrder.add(2);
        reverseOrder.add(8);
        System.out.println("降序排序: " + reverseOrder);

        // 【4】不允许null
        try {
            naturalOrder.add(null);  // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("不允许null: NullPointerException");
        }

        // 【5】范围查询
        System.out.println("\n--- 范围查询 ---");
        // headSet: 小于指定元素的部分
        System.out.println("headSet(Cherry): " + naturalOrder.headSet("Cherry"));

        // tailSet: 大于等于指定元素的部分
        System.out.println("tailSet(Cherry): " + naturalOrder.tailSet("Cherry"));

        // subSet: 指定范围
        System.out.println("subSet(Apple, Cherry): " + naturalOrder.subSet("Apple", "Cherry"));

        // 【6】边界操作
        System.out.println("\n--- 边界操作 ---");
        // first: 第一个元素
        System.out.println("first: " + naturalOrder.first());
        // last: 最后一个元素
        System.out.println("last: " + naturalOrder.last());

        // lower: 小于指定元素的最大元素
        System.out.println("lower(Cherry): " + naturalOrder.lower("Cherry"));
        // higher: 大于指定元素的最小元素
        System.out.println("higher(Cherry): " + naturalOrder.higher("Cherry"));

        // floor: 小于等于指定元素的最大元素
        System.out.println("floor(Cherry): " + naturalOrder.floor("Cherry"));
        // ceiling: 大于等于指定元素的最小元素
        System.out.println("ceiling(Cherry): " + naturalOrder.ceiling("Cherry"));

        // 【7】导航方法
        System.out.println("\n--- 导航方法 ---");
        // pollFirst: 移除并返回第一个
        System.out.println("pollFirst: " + naturalOrder.pollFirst());
        // pollLast: 移除并返回最后一个
        System.out.println("pollLast: " + naturalOrder.pollLast());
        System.out.println("剩余: " + naturalOrder);

        // 【8】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 需要自动排序的场景");
        System.out.println("2. 范围查询（如查找某个区间的记录）");
        System.out.println("3. 排行榜实现");
        System.out.println("4. 去重且排序");
    }

    // ================================================
    // 五、EnumSet详解
    // ================================================
    public static void enumSetDemo() {
        System.out.println("--- EnumSet详解 ---");

        // 【EnumSet的特点】
        // 1. 专门用于枚举类型
        // 2. 基于位向量实现，性能极高
        // 3. 所有元素必须是同一枚举类型
        // 4. 不允许null
        // 5. 非线程安全

        // 【1】创建EnumSet
        EnumSet<Day> allDays = EnumSet.allOf(Day.class);
        System.out.println("所有天: " + allDays);

        // 【2】noneOf - 空EnumSet
        EnumSet<Day> empty = EnumSet.noneOf(Day.class);
        System.out.println("空EnumSet: " + empty);

        // 【3】of - 创建一个或多个元素
        EnumSet<Day> weekdays = EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, 
                                            Day.THURSDAY, Day.FRIDAY);
        System.out.println("工作日: " + weekdays);

        // 【4】range - 范围
        EnumSet<Day> range = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("范围(MONDAY-FRIDAY): " + range);

        // 【5】complementOf - 补集
        EnumSet<Day> weekend = EnumSet.complementOf(weekdays);
        System.out.println("周末: " + weekend);

        // 【6】copyOf
        EnumSet<Day> copy = EnumSet.copyOf(weekdays);
        System.out.println("拷贝: " + copy);

        // 【7】性能优势
        System.out.println("\n--- 性能优势 ---");
        System.out.println("1. 内存占用极小（位向量）");
        System.out.println("2. 操作速度极快（位运算）");
        System.out.println("3. 是处理枚举集合的最佳选择");
    }

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // ================================================
    // 六、CopyOnWriteArraySet详解
    // ================================================
    public static void copyOnWriteArraySetDemo() {
        System.out.println("--- CopyOnWriteArraySet详解 ---");

        // 【CopyOnWriteArraySet的特点】
        // 1. 线程安全的Set
        // 2. 基于CopyOnWriteArrayList实现
        // 3. 写时复制，读无锁
        // 4. 适合读多写少的并发场景

        // 【1】创建CopyOnWriteArraySet
        java.util.concurrent.CopyOnWriteArraySet<String> set = 
            new java.util.concurrent.CopyOnWriteArraySet<>();

        // 【2】基本操作
        set.add("A");
        set.add("B");
        set.add("C");
        System.out.println("内容: " + set);

        // 【3】线程安全迭代
        System.out.println("\n线程安全迭代:");
        for (String item : set) {
            System.out.println("  " + item);
        }

        // 【4】适用场景
        System.out.println("\n--- 适用场景 ---");
        System.out.println("✓ 读多写少的并发场景");
        System.out.println("✓ 事件监听器集合");
        System.out.println("✗ 不适合频繁写入的场景");
    }

    // ================================================
    // 七、Set常用操作
    // ================================================
    public static void setOperations() {
        System.out.println("--- Set常用操作 ---");

        Set<String> set = new HashSet<>();

        // 【1】添加
        set.add("A");
        set.add("B");
        set.add("C");
        System.out.println("添加后: " + set);

        // 【2】批量添加
        set.addAll(Arrays.asList("D", "E"));
        System.out.println("addAll后: " + set);

        // 【3】删除
        set.remove("A");
        System.out.println("remove后: " + set);

        // 【4】批量删除
        set.removeAll(Arrays.asList("B", "C"));
        System.out.println("removeAll后: " + set);

        // 【5】保留交集
        Set<String> other = new HashSet<>(Arrays.asList("D", "F"));
        set.retainAll(other);
        System.out.println("retainAll后: " + set);

        // 【6】清空
        set.clear();
        System.out.println("clear后: " + set);

        // 【7】判断
        System.out.println("\n判断操作:");
        System.out.println("isEmpty: " + set.isEmpty());
        System.out.println("contains: " + set.contains("A"));
        System.out.println("size: " + set.size());

        // 【8】toArray
        Set<String> fruits = new HashSet<>(Arrays.asList("Apple", "Banana", "Cherry"));
        String[] array = fruits.toArray(new String[0]);
        System.out.println("\ntoArray: " + Arrays.toString(array));
    }

    // ================================================
    // 八、Set遍历方式
    // ================================================
    public static void setTraversal() {
        System.out.println("--- Set遍历方式 ---");

        Set<String> set = new HashSet<>(Arrays.asList("A", "B", "C", "D"));

        // 【方式1】增强for循环
        System.out.print("1. 增强for: ");
        for (String item : set) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 【方式2】Iterator
        System.out.print("2. Iterator: ");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // 【方式3】forEach方法（JDK8+）
        System.out.print("3. forEach: ");
        set.forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 【方式4】Stream API（JDK8+）
        System.out.print("4. Stream: ");
        set.stream().forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 【方式5】并行Stream
        System.out.print("5. Parallel Stream: ");
        set.parallelStream().forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 【遍历时删除】
        System.out.println("\n--- 遍历时删除 ---");
        Set<Integer> nums = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        // 正确方式：使用Iterator
        Iterator<Integer> it = nums.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) {
                it.remove();  // 安全删除
            }
        }
        System.out.println("Iterator删除偶数: " + nums);

        // 正确方式：使用removeIf（JDK8+）
        nums = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        nums.removeIf(n -> n % 2 == 0);
        System.out.println("removeIf删除偶数: " + nums);
    }

    // ================================================
    // 九、Set运算
    // ================================================
    public static void setOperationsMath() {
        System.out.println("--- Set集合运算 ---");

        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));

        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);

        // 【1】并集（Union）
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("\n并集: " + union);

        // 【2】交集（Intersection）
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("交集: " + intersection);

        // 【3】差集（Difference）
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("差集(Set1-Set2): " + difference);

        // 【4】对称差集（Symmetric Difference）
        Set<Integer> symmetricDiff = new HashSet<>(set1);
        symmetricDiff.addAll(set2);
        Set<Integer> temp = new HashSet<>(set1);
        temp.retainAll(set2);
        symmetricDiff.removeAll(temp);
        System.out.println("对称差集: " + symmetricDiff);

        // 【5】判断子集
        Set<Integer> subset = new HashSet<>(Arrays.asList(1, 2));
        System.out.println("\n{1,2}是Set1的子集: " + set1.containsAll(subset));

        // 【6】判断不相交
        Set<Integer> disjoint = new HashSet<>(Arrays.asList(9, 10));
        Set<Integer> temp2 = new HashSet<>(set1);
        temp2.retainAll(disjoint);
        System.out.println("Set1和{9,10}不相交: " + temp2.isEmpty());
    }

    // ================================================
    // 十、Set去重应用
    // ================================================
    public static void setDeduplication() {
        System.out.println("--- Set去重应用 ---");

        // 【1】List去重
        List<Integer> listWithDup = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 5);
        System.out.println("原始List: " + listWithDup);

        Set<Integer> set = new HashSet<>(listWithDup);
        List<Integer> listWithoutDup = new ArrayList<>(set);
        System.out.println("去重后: " + listWithoutDup);

        // 【2】保持顺序去重
        List<String> names = Arrays.asList("Alice", "Bob", "Alice", "Charlie", "Bob");
        System.out.println("\n原始List: " + names);

        LinkedHashSet<String> linkedSet = new LinkedHashSet<>(names);
        List<String> uniqueNames = new ArrayList<>(linkedSet);
        System.out.println("保持顺序去重: " + uniqueNames);

        // 【3】Stream去重
        List<Integer> nums = Arrays.asList(1, 2, 2, 3, 3, 4, 5);
        List<Integer> distinct = nums.stream()
                                     .distinct()
                                     .collect(Collectors.toList());
        System.out.println("\nStream去重: " + distinct);

        // 【4】对象去重
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 20),
            new Person("Alice", 25),  // 重复
            new Person("Charlie", 30)
        );

        // 需要Person正确实现equals和hashCode
        Set<Person> uniquePeople = new HashSet<>(people);
        System.out.println("\n对象去重: " + uniquePeople.size() + "个唯一对象");
    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    // ================================================
    // 十一、不可变Set
    // ================================================
    public static void immutableSet() {
        System.out.println("--- 不可变Set ---");

        // 【1】Collections.unmodifiableSet()
        Set<String> mutable = new HashSet<>(Arrays.asList("A", "B", "C"));
        Set<String> unmodifiable = Collections.unmodifiableSet(mutable);
        System.out.println("unmodifiableSet: " + unmodifiable);
        // unmodifiable.add("D");  // UnsupportedOperationException!

        // 注意：原Set仍可修改
        mutable.add("D");
        System.out.println("修改原Set后: " + unmodifiable);

        // 【2】Set.of()（JDK9+）
        Set<String> ofSet = Set.of("X", "Y", "Z");
        System.out.println("\nSet.of(): " + ofSet);
        // ofSet.add("W");  // UnsupportedOperationException!

        // 【3】Set.copyOf()（JDK10+）
        Set<String> copyOfSet = Set.copyOf(mutable);
        System.out.println("Set.copyOf(): " + copyOfSet);

        // 【4】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 常量集合");
        System.out.println("2. 配置信息");
        System.out.println("3. API返回值（防止外部修改）");
        System.out.println("4. 多线程共享数据");
    }

    // ================================================
    // 十二、Set性能对比
    // ================================================
    public static void performanceComparison() {
        System.out.println("--- Set性能对比 ---");

        int size = 100000;

        // 【1】插入性能对比
        System.out.println("\n1. 插入 " + size + " 个元素:");

        long start = System.nanoTime();
        Set<Integer> hashSet = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            hashSet.add(i);
        }
        long hashTime = System.nanoTime() - start;
        System.out.println("   HashSet: " + hashTime / 1_000_000 + " ms");

        start = System.nanoTime();
        Set<Integer> linkedHashSet = new LinkedHashSet<>(size);
        for (int i = 0; i < size; i++) {
            linkedHashSet.add(i);
        }
        long linkedTime = System.nanoTime() - start;
        System.out.println("   LinkedHashSet: " + linkedTime / 1_000_000 + " ms");

        start = System.nanoTime();
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            treeSet.add(i);
        }
        long treeTime = System.nanoTime() - start;
        System.out.println("   TreeSet: " + treeTime / 1_000_000 + " ms");

        // 【2】查找性能对比
        System.out.println("\n2. 查找 " + size + " 次:");

        Random random = new Random(42);
        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            hashSet.contains(random.nextInt(size));
        }
        hashTime = System.nanoTime() - start;
        System.out.println("   HashSet: " + hashTime / 1_000_000 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedHashSet.contains(random.nextInt(size));
        }
        linkedTime = System.nanoTime() - start;
        System.out.println("   LinkedHashSet: " + linkedTime / 1_000_000 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            treeSet.contains(random.nextInt(size));
        }
        treeTime = System.nanoTime() - start;
        System.out.println("   TreeSet: " + treeTime / 1_000_000 + " ms");

        // 【总结】
        System.out.println("\n--- 性能总结 ---");
        System.out.println("HashSet:");
        System.out.println("  ✓ 插入/查找最快 O(1)");
        System.out.println("  ✓ 大多数场景的首选");
        System.out.println("\nLinkedHashSet:");
        System.out.println("  ✓ 性能接近HashSet");
        System.out.println("  ✓ 额外开销：维护链表");
        System.out.println("\nTreeSet:");
        System.out.println("  ✓ 插入/查找 O(log n)，较慢");
        System.out.println("  ✓ 优势：有序、范围查询");
    }

    // ================================================
    // 十三、注意事项
    // ================================================
    public static void cautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】选择合适的Set实现
        System.out.println("\n1. 选择Set实现:");
        System.out.println("   - 大多数场景: HashSet");
        System.out.println("   - 需要保持顺序: LinkedHashSet");
        System.out.println("   - 需要排序: TreeSet");
        System.out.println("   - 枚举类型: EnumSet（性能最优）");
        System.out.println("   - 线程安全: CopyOnWriteArraySet（读多写少）");

        // 【2】equals和hashCode
        System.out.println("\n2. equals和hashCode:");
        System.out.println("   - 存入Set的对象必须正确实现equals()和hashCode()");
        System.out.println("   - 否则无法正确去重");
        System.out.println("   - 两个相等的对象必须有相同的hashCode");

        // 【3】可变对象作为元素
        System.out.println("\n3. 可变对象:");
        System.out.println("   - 避免使用可变对象作为Set元素");
        System.out.println("   - 修改对象后可能导致hashCode变化");
        System.out.println("   - 可能无法正确查找或删除");

        // 【4】null的处理
        System.out.println("\n4. null处理:");
        System.out.println("   - HashSet/LinkedHashSet允许一个null");
        System.out.println("   - TreeSet不允许null");
        System.out.println("   - EnumSet不允许null");

        // 【5】初始化容量
        System.out.println("\n5. 初始化容量:");
        System.out.println("   - 知道大致数量时，指定初始容量");
        System.out.println("   - 避免频繁扩容");
        Set<Integer> optimized = new HashSet<>(1000);

        // 【6】泛型的使用
        System.out.println("\n6. 泛型:");
        System.out.println("   - 始终使用泛型");
        Set<String> safe = new HashSet<>();  // 推荐

        // 【7】遍历时的修改
        System.out.println("\n7. 遍历修改:");
        System.out.println("   - 不要在foreach中直接修改Set");
        System.out.println("   - 使用Iterator或removeIf");

        // 【8】线程安全
        System.out.println("\n8. 线程安全:");
        System.out.println("   - HashSet/TreeSet/LinkedHashSet都不是线程安全的");
        System.out.println("   - 多线程环境使用CopyOnWriteArraySet");
        System.out.println("   - 或使用Collections.synchronizedSet()");
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());

        // 【9】Set.of的限制
        System.out.println("\n9. Set.of限制:");
        System.out.println("   - 不允许null元素");
        System.out.println("   - 不允许重复元素");
        System.out.println("   - 返回的Set是不可变的");

        // 【10】性能优化建议
        System.out.println("\n10. 性能优化:");
        System.out.println("   - 大批量数据: 预分配容量");
        System.out.println("   - 枚举集合: 优先使用EnumSet");
        System.out.println("   - 需要排序: 考虑TreeSet");
        System.out.println("   - 高并发: CopyOnWriteArraySet");
    }
}
