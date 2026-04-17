package com.zhucj.java.array;

import java.util.*;

/**
 * =====================================================
 * Java Collections工具类详解
 * =====================================================
 *
 * 【一、Collections是什么】
 * -----------------------
 * Collections是java.util包下的工具类，提供了操作集合的静态方法。
 * - 所有方法都是static
 * - 不能实例化（私有构造方法）
 * - 提供排序、查找、同步、不可变等操作
 *
 * 【二、主要功能分类】
 * -----------------
 * 1. 排序操作      - sort, reverse, shuffle等
 * 2. 查找操作      - binarySearch, max, min等
 * 3. 同步包装      - synchronizedList, synchronizedMap等
 * 4. 不可变包装    - unmodifiableList, unmodifiableMap等
 * 5. 填充和替换    - fill, copy, replaceAll等
 * 6. 其他工具      - frequency, disjoint, rotate等
 *
 * Collections工具类在现在 Java 开发中使用不多。主要是很多操作对象类都已经实现。
 * =====================================================
 */
public class CollectionsLesson {

    public static void main(String[] args) {
        System.out.println("========== 排序操作 ==========");
        sortOperations();

        System.out.println("\n========== 查找操作 ==========");
        searchOperations();

        System.out.println("\n========== 同步包装 ==========");
        synchronizedWrappers();

        System.out.println("\n========== 不可变包装 ==========");
        unmodifiableWrappers();

        System.out.println("\n========== 填充和替换 ==========");
        fillAndReplace();

        System.out.println("\n========== 其他工具方法 ==========");
        otherUtilities();

        System.out.println("\n========== 线程安全集合 ==========");
        threadSafeCollections();

        System.out.println("\n========== 注意事项 ==========");
        cautions();
    }

    // ================================================
    // 一、排序操作
    // ================================================
    public static void sortOperations() {
        System.out.println("--- 排序操作 ---");

        // 【1】sort - 自然排序
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        Collections.sort(nums);
        // nums → [1, 2, 3, 5, 8, 9]
        System.out.println("自然排序: " + nums);

        // 【2】sort - 自定义排序
        List<String> names = new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob"));
        Collections.sort(names, Comparator.comparingInt(String::length));
        // names → [Bob, Alice, Charlie]
        System.out.println("按长度排序: " + names);

        // 【3】reverse - 反转
        Collections.reverse(nums);
        // nums → [9, 8, 5, 3, 2, 1]
        System.out.println("反转: " + nums);

        // 【4】shuffle - 随机打乱
        List<Integer> shuffled = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.shuffle(shuffled);
        System.out.println("打乱: " + shuffled);

        // 【5】shuffle - 指定Random
        Collections.shuffle(shuffled, new Random(42));
        System.out.println("指定Random打乱: " + shuffled);

        // 【6】rotate - 旋转
        List<Integer> rotated = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.rotate(rotated, 2);  // 向右旋转2位
        // rotated → [4, 5, 1, 2, 3]
        System.out.println("旋转2位: " + rotated);

        // 【7】swap - 交换
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.swap(list, 0, 3);
        // list → [D, B, C, A]
        System.out.println("交换0和3: " + list);

        // 【8】sort vs List.sort
        System.out.println("\n--- sort vs List.sort ---");
        List<Integer> list1 = new ArrayList<>(Arrays.asList(3, 1, 2));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 1, 2));

        Collections.sort(list1);           // 工具类方法
        list2.sort(Integer::compareTo);    // 实例方法（JDK8+）

        System.out.println("Collections.sort: " + list1);
        System.out.println("List.sort: " + list2);
        System.out.println("推荐使用List.sort()，更面向对象");
    }

    // ================================================
    // 二、查找操作
    // ================================================
    public static void searchOperations() {
        System.out.println("--- 查找操作 ---");

        // 【1】binarySearch - 二分查找
        List<Integer> sorted = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        int index = Collections.binarySearch(sorted, 5);
        // index → 2
        System.out.println("binarySearch(5): " + index);

        // 未找到时返回 -(插入点+1)
        int notFound = Collections.binarySearch(sorted, 6);
        // notFound → -4
        System.out.println("binarySearch(6): " + notFound);

        // 【2】max - 最大值
        List<Integer> nums = Arrays.asList(5, 2, 8, 1, 9);
        Integer max = Collections.max(nums);
        // max → 9
        System.out.println("max: " + max);

        // 自定义Comparator
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        String longest = Collections.max(names, Comparator.comparingInt(String::length));
        System.out.println("最长名字: " + longest);

        // 【3】min - 最小值
        Integer min = Collections.min(nums);
        // min → 1
        System.out.println("min: " + min);

        // 【4】frequency - 频率统计
        List<Integer> list = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        int freq = Collections.frequency(list, 3);
        // freq → 3
        System.out.println("3出现的次数: " + freq);

        // 【5】indexOfSubList - 子列表首次出现位置
        List<Integer> source = Arrays.asList(1, 2, 3, 4, 5, 2, 3);
        List<Integer> target = Arrays.asList(2, 3);
        int firstIndex = Collections.indexOfSubList(source, target);
        // firstIndex → 1
        System.out.println("子列表首次出现: " + firstIndex);

        // 【6】lastIndexOfSubList - 子列表最后出现位置
        int lastIndex = Collections.lastIndexOfSubList(source, target);
        // lastIndex → 5
        System.out.println("子列表最后出现: " + lastIndex);

        // 【7】disjoint - 判断是否不相交
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(3, 4, 5);

        boolean disjoint1 = Collections.disjoint(list1, list2);
        // disjoint1 → true
        System.out.println("list1和list2不相交: " + disjoint1);

        boolean disjoint2 = Collections.disjoint(list1, list3);
        // disjoint2 → false
        System.out.println("list1和list3不相交: " + disjoint2);
    }

    // ================================================
    // 三、同步包装
    // ================================================
    public static void synchronizedWrappers() {
        System.out.println("--- 同步包装 ---");

        // 【1】synchronizedList
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        syncList.add("A");
        syncList.add("B");
        System.out.println("synchronizedList: " + syncList);

        // 遍历时需要手动同步
        synchronized (syncList) {
            for (String item : syncList) {
                System.out.print(item + " ");
            }
        }
        System.out.println();

        // 【2】synchronizedSet
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
        syncSet.add("X");
        syncSet.add("Y");
        System.out.println("synchronizedSet: " + syncSet);

        // 【3】synchronizedMap
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put("A", 1);
        syncMap.put("B", 2);
        System.out.println("synchronizedMap: " + syncMap);

        // 【4】synchronizedCollection
        Collection<String> syncCollection = Collections.synchronizedCollection(new ArrayList<>());
        syncCollection.add("Item");
        System.out.println("synchronizedCollection: " + syncCollection);

        // 【5】注意事项
        System.out.println("\n--- 注意事项 ---");
        System.out.println("1. 迭代时需要手动同步");
        System.out.println("2. 推荐使用java.util.concurrent包的并发集合");
        System.out.println("3. ConcurrentHashMap优于synchronizedMap");
    }

    // ================================================
    // 四、不可变包装
    // ================================================
    public static void unmodifiableWrappers() {
        System.out.println("--- 不可变包装 ---");

        // 【1】unmodifiableList
        List<String> mutableList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> unmodList = Collections.unmodifiableList(mutableList);
        System.out.println("unmodifiableList: " + unmodList);

        try {
            unmodList.add("D");  // UnsupportedOperationException!
        } catch (UnsupportedOperationException e) {
            System.out.println("添加失败: UnsupportedOperationException");
        }

        // 注意：原List仍可修改
        mutableList.add("D");
        System.out.println("修改原List后: " + unmodList);

        // 【2】unmodifiableSet
        Set<String> mutableSet = new HashSet<>(Arrays.asList("X", "Y"));
        Set<String> unmodSet = Collections.unmodifiableSet(mutableSet);
        System.out.println("\nunmodifiableSet: " + unmodSet);

        // 【3】unmodifiableMap
        Map<String, Integer> mutableMap = new HashMap<>();
        mutableMap.put("A", 1);
        Map<String, Integer> unmodMap = Collections.unmodifiableMap(mutableMap);
        System.out.println("unmodifiableMap: " + unmodMap);

        // 【4】unmodifiableCollection
        Collection<String> mutableCol = new ArrayList<>(Arrays.asList("Item"));
        Collection<String> unmodCol = Collections.unmodifiableCollection(mutableCol);
        System.out.println("unmodifiableCollection: " + unmodCol);

        // 【5】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 返回不可变视图给调用者");
        System.out.println("2. 保护内部数据不被修改");
        System.out.println("3. 线程安全的只读访问");
        System.out.println("4. JDK9+推荐使用List.of(), Set.of(), Map.of()");
    }

    // ================================================
    // 五、填充和替换
    // ================================================
    public static void fillAndReplace() {
        System.out.println("--- 填充和替换 ---");

        // 【1】fill - 填充
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.fill(list, 0);
        // list → [0, 0, 0, 0, 0]
        System.out.println("fill: " + list);

        // 【2】copy - 复制
        List<Integer> source = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> dest = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        Collections.copy(dest, source);
        // dest → [1, 2, 3, 4, 5]
        System.out.println("copy: " + dest);

        // 目标列表必须至少和源列表一样长
        try {
            List<Integer> shortDest = new ArrayList<>(Arrays.asList(0, 0));
            Collections.copy(shortDest, source);  // IndexOutOfBoundsException!
        } catch (IndexOutOfBoundsException e) {
            System.out.println("目标列表太短: IndexOutOfBoundsException");
        }

        // 【3】replaceAll - 替换所有匹配元素
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Alice", "Charlie"));
        Collections.replaceAll(names, "Alice", "Alex");
        // names → [Alex, Bob, Alex, Charlie]
        System.out.println("replaceAll: " + names);

        // 【4】nCopies - 创建n个副本的列表
        List<String> copies = Collections.nCopies(5, "Hello");
        // copies → [Hello, Hello, Hello, Hello, Hello]
        System.out.println("nCopies: " + copies);

        // 注意：返回的是不可变列表
        try {
            copies.add("World");  // UnsupportedOperationException!
        } catch (UnsupportedOperationException e) {
            System.out.println("nCopies返回不可变列表");
        }

        // 【5】singletonList - 单元素列表
        List<String> singleton = Collections.singletonList("OnlyOne");
        System.out.println("singletonList: " + singleton);

        // 【6】emptyList/emptySet/emptyMap - 空集合
        List<String> emptyList = Collections.emptyList();
        Set<String> emptySet = Collections.emptySet();
        Map<String, Integer> emptyMap = Collections.emptyMap();
        System.out.println("emptyList: " + emptyList);
        System.out.println("emptySet: " + emptySet);
        System.out.println("emptyMap: " + emptyMap);
    }

    // ================================================
    // 六、其他工具方法
    // ================================================
    public static void otherUtilities() {
        System.out.println("--- 其他工具方法 ---");

        // 【1】addAll - 批量添加
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "A", "B", "C", "D");
        // list → [A, B, C, D]
        System.out.println("addAll: " + list);

        // 【2】enumeration - 将Iterator转为Enumeration
        List<String> enumList = Arrays.asList("X", "Y", "Z");
        Enumeration<String> enumeration = Collections.enumeration(enumList);
        System.out.print("enumeration: ");
        while (enumeration.hasMoreElements()) {
            System.out.print(enumeration.nextElement() + " ");
        }
        System.out.println();

        // 【3】list - 将Enumeration转为List
        Vector<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");
        List<String> fromEnum = Collections.list(vector.elements());
        System.out.println("list(from Enumeration): " + fromEnum);

        // 【4】reverseOrder - 反向比较器
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        Collections.sort(nums, Collections.reverseOrder());
        // nums → [9, 8, 5, 2, 1]
        System.out.println("reverseOrder排序: " + nums);

        // 【5】checkedCollection - 类型检查包装
        List rawList = new ArrayList();
        rawList.add("String");
        rawList.add(123);  // 可以添加不同类型

        List<String> checkedList = Collections.checkedList(new ArrayList<>(), String.class);
        try {
            checkedList.add("Valid");
            // checkedList.add(123);  // ClassCastException!
        } catch (ClassCastException e) {
            System.out.println("类型检查: ClassCastException");
        }

        // 【6】newSetFromMap - 从Map创建Set
        Set<String> mapBackedSet = Collections.newSetFromMap(new HashMap<>());
        mapBackedSet.add("A");
        mapBackedSet.add("B");
        System.out.println("newSetFromMap: " + mapBackedSet);
    }

    // ================================================
    // 七、线程安全集合
    // ================================================
    public static void threadSafeCollections() {
        System.out.println("--- 线程安全集合对比 ---");

        System.out.println("\n【方式1】Collections.synchronizedXXX");
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        System.out.println("优点: 简单易用");
        System.out.println("缺点: 性能较差（全局锁）");

        System.out.println("\n【方式2】java.util.concurrent包");
        System.out.println("CopyOnWriteArrayList: 读多写少");
        System.out.println("ConcurrentHashMap: 高并发Map");
        System.out.println("ConcurrentLinkedQueue: 无界并发队列");
        System.out.println("优点: 细粒度锁，性能更好");
        System.out.println("缺点: 使用稍复杂");

        System.out.println("\n【推荐】");
        System.out.println("- 大多数场景: java.util.concurrent包");
        System.out.println("- 简单场景: Collections.synchronizedXXX");
        System.out.println("- 读多写少: CopyOnWriteArrayList");
        System.out.println("- 高并发Map: ConcurrentHashMap");
    }

    // ================================================
    // 八、注意事项
    // ================================================
    public static void cautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】binarySearch需要先排序
        System.out.println("\n1. binarySearch前提:");
        System.out.println("   - 列表必须先排序");
        System.out.println("   - 否则结果不确定");

        // 【2】同步包装的迭代
        System.out.println("\n2. 同步包装迭代:");
        System.out.println("   - 必须手动同步");
        System.out.println("   - 推荐使用并发集合");

        // 【3】不可变包装的陷阱
        System.out.println("\n3. 不可变包装:");
        System.out.println("   - 只是视图，原集合仍可修改");
        System.out.println("   - JDK9+使用List.of()等真正不可变");

        // 【4】nCopies和singleton
        System.out.println("\n4. nCopies/singleton:");
        System.out.println("   - 返回不可变集合");
        System.out.println("   - 不要尝试修改");

        // 【5】性能考虑
        System.out.println("\n5. 性能考虑:");
        System.out.println("   - Collections.sort底层使用TimSort");
        System.out.println("   - 大数据量考虑parallelSort");
        System.out.println("   - synchronized包装性能较差");

        // 【6】JDK8+替代方案
        System.out.println("\n6. JDK8+替代:");
        System.out.println("   - List.sort()替代Collections.sort()");
        System.out.println("   - Stream API提供更多功能");
        System.out.println("   - Comparator工厂方法更简洁");

        // 【7】JDK9+不可变集合
        System.out.println("\n7. JDK9+不可变集合:");
        System.out.println("   - List.of(), Set.of(), Map.of()");
        System.out.println("   - 真正不可变，不允许null");
        System.out.println("   - 性能更好，推荐使用");

        // 【8】常见错误
        System.out.println("\n8. 常见错误:");
        System.out.println("   - 忘记排序就使用binarySearch");
        System.out.println("   - 修改不可变集合");
        System.out.println("   - 忽略同步包装的迭代问题");
        System.out.println("   - copy时目标列表太短");
    }
}
