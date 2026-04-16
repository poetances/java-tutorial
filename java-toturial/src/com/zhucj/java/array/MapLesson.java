package com.zhucj.java.array;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

/**
 * =====================================================
 * Java Map（映射）详解
 * =====================================================
 *
 * 【一、Map是什么】
 * -----------------
 * Map是键值对（Key-Value）的集合，用于存储和检索数据。
 * - Key是唯一的，不允许重复
 * - Value可以重复
 * - 每个Key最多映射到一个Value
 * - Key和Value都可以是null（取决于具体实现）
 *
 * 【二、Map的主要实现类】
 * --------------------
 * 1. HashMap          - 基于哈希表，无序，最常用
 * 2. LinkedHashMap    - 基于哈希表+链表，保持插入顺序
 * 3. TreeMap          - 基于红黑树，按键排序
 * 4. Hashtable        - 线程安全的HashMap（已过时）
 * 5. ConcurrentHashMap - 线程安全，高性能
 * 6. WeakHashMap      - 弱引用Key，可被GC回收
 * 7. IdentityHashMap  - 使用==比较Key，而非equals
 *
 * 【三、Map vs List】
 * -----------------
 * List: 有序集合，通过索引访问，元素可重复
 * Map:  键值对集合，通过Key访问，Key唯一
 *
 * 常用：HashMap、LinkedHashMap、ConcurrentHashMap、TreeMap.
 * =====================================================
 */
public class MapLesson {

    public static void main(String[] args) {
        System.out.println("========== Map基础 ==========");
        mapBasics();

        System.out.println("\n========== HashMap详解 ==========");
        hashMapDemo();

        System.out.println("\n========== LinkedHashMap详解 ==========");
        linkedHashMapDemo();

        System.out.println("\n========== TreeMap详解 ==========");
        treeMapDemo();

        System.out.println("\n========== Hashtable详解 ==========");
        hashtableDemo();

        System.out.println("\n========== ConcurrentHashMap详解 ==========");
        concurrentHashMapDemo();

        System.out.println("\n========== WeakHashMap详解 ==========");
        weakHashMapDemo();

        System.out.println("\n========== IdentityHashMap详解 ==========");
        identityHashMapDemo();

        System.out.println("\n========== Map常用操作 ==========");
        mapOperations();

        System.out.println("\n========== Map遍历方式 ==========");
        mapTraversal();

        System.out.println("\n========== Map排序 ==========");
        mapSort();

        System.out.println("\n========== Map与List转换 ==========");
        mapToList();

        System.out.println("\n========== 不可变Map ==========");
        immutableMap();

        System.out.println("\n========== Map性能对比 ==========");
        performanceComparison();

        System.out.println("\n========== 注意事项 ==========");
        cautions();
    }

    // ================================================
    // 一、Map基础
    // ================================================
    public static void mapBasics() {
        System.out.println("--- Map基本概念 ---");

        // 【1】创建Map
        Map<String, Integer> map = new HashMap<>();

        // 【2】添加键值对
        map.put("Apple", 5);
        map.put("Banana", 3);
        map.put("Cherry", 8);
        // map → {Apple=5, Banana=3, Cherry=8}
        System.out.println("添加后: " + map);

        // 【3】获取值
        // map.get("Apple") → 5
        System.out.println("Apple的数量: " + map.get("Apple"));
        // map.get("Orange") → null （不存在）
        System.out.println("Orange的数量: " + map.get("Orange"));

        // 【4】修改值
        map.put("Apple", 10);  // Key已存在，覆盖原值
        // map → {Apple=10, Banana=3, Cherry=8}
        System.out.println("修改后: " + map);

        // 【5】删除键值对
        map.remove("Banana");
        // map → {Apple=10, Cherry=8}
        System.out.println("删除后: " + map);

        // 【6】Map大小
        // map.size() → 2
        System.out.println("Map大小: " + map.size());

        // 【7】判断操作
        System.out.println("包含Apple: " + map.containsKey("Apple"));
        System.out.println("包含值8: " + map.containsValue(8));
        System.out.println("是否为空: " + map.isEmpty());

        // 【8】Map特性
        System.out.println("\n--- Map特性 ---");
        Map<String, String> testMap = new HashMap<>();
        testMap.put("Key1", "Value1");
        testMap.put("Key1", "Value2");  // 覆盖
        // testMap → {Key1=Value2}
        System.out.println("Key唯一性: " + testMap);

        testMap.put("Key2", "Value1");  // Value可以重复
        testMap.put("Key3", "Value1");
        System.out.println("Value可重复: " + testMap);
    }

    // ================================================
    // 二、HashMap详解
    // ================================================
    public static void hashMapDemo() {
        System.out.println("--- HashMap详解 ---");

        // 【HashMap的特点】
        // 1. 基于哈希表实现（数组+链表+红黑树）
        // 2. 允许null键和null值
        // 3. 无序（不保证插入顺序）
        // 4. 非线程安全
        // 5. 性能优秀：get/put平均O(1)

        // 【1】创建HashMap
        HashMap<String, Integer> map = new HashMap<>();

        // 【2】指定初始容量和负载因子
        HashMap<String, Integer> customMap = new HashMap<>(16, 0.75f);
        System.out.println("初始容量: 16, 负载因子: 0.75");

        // 【3】基本操作
        map.put("Java", 90);
        map.put("Python", 85);
        map.put("JavaScript", 88);
        map.put("Go", 92);
        // map → {Java=90, Python=85, JavaScript=88, Go=92}
        System.out.println("基本操作: " + map);

        // 【4】允许null键和null值
        map.put(null, 100);
        map.put("C++", null);
        System.out.println("允许null: " + map);

        // 【5】getOrDefault（JDK8+）
        // map.getOrDefault("Java", 0) → 90
        System.out.println("Java分数: " + map.getOrDefault("Java", 0));
        // map.getOrDefault("Ruby", 0) → 0 （不存在返回默认值）
        System.out.println("Ruby分数: " + map.getOrDefault("Ruby", 0));

        // 【6】putIfAbsent（JDK8+）
        map.putIfAbsent("Java", 95);  // Key已存在，不覆盖
        map.putIfAbsent("Ruby", 80);  // Key不存在，添加
        System.out.println("putIfAbsent后: " + map);

        // 【7】compute系列方法（JDK8+）
        map.compute("Java", (key, value) -> value + 5);  // 计算新值
        System.out.println("compute后Java: " + map.get("Java"));

        map.computeIfAbsent("Swift", k -> 85);  // 不存在时计算
        System.out.println("computeIfAbsent后: " + map.get("Swift"));

        map.computeIfPresent("Python", (k, v) -> v > 80 ? v - 5 : v);
        System.out.println("computeIfPresent后Python: " + map.get("Python"));

        // 【8】merge方法（JDK8+）
        map.merge("Java", 5, Integer::sum);  // 合并值
        System.out.println("merge后Java: " + map.get("Java"));

        // 【9】replace方法
        map.replace("Go", 95);  // 替换值
        System.out.println("replace后: " + map.get("Go"));

        map.replace("Go", 95, 100);  // 条件替换（旧值匹配才替换）
        System.out.println("条件替换后: " + map.get("Go"));

        // 【10】HashMap内部机制
        System.out.println("\n--- HashMap内部机制 ---");
        System.out.println("数据结构: 数组 + 链表 + 红黑树");
        System.out.println("默认容量: 16（必须是2的幂）");
        System.out.println("负载因子: 0.75（达到75%时扩容）");
        System.out.println("扩容规则: 容量翻倍");
        System.out.println("链表转红黑树: 链表长度 >= 8 且 数组长度 >= 64");
        System.out.println("红黑树转链表: 链表长度 <= 6");
    }

    // ================================================
    // 三、LinkedHashMap详解
    // ================================================
    public static void linkedHashMapDemo() {
        System.out.println("--- LinkedHashMap详解 ---");

        // 【LinkedHashMap的特点】
        // 1. 继承自HashMap
        // 2. 基于哈希表+双向链表
        // 3. 保持插入顺序（或访问顺序）
        // 4. 允许null键和null值
        // 5. 非线程安全

        // 【1】保持插入顺序（默认）
        LinkedHashMap<String, Integer> insertOrder = new LinkedHashMap<>();
        insertOrder.put("First", 1);
        insertOrder.put("Second", 2);
        insertOrder.put("Third", 3);
        insertOrder.put("Fourth", 4);
        // 按插入顺序遍历
        System.out.println("插入顺序: " + insertOrder);

        // 【2】保持访问顺序（LRU缓存的基础）
        // accessOrder=true: 按访问顺序排序
        LinkedHashMap<String, Integer> accessOrder = new LinkedHashMap<>(16, 0.75f, true);
        accessOrder.put("A", 1);
        accessOrder.put("B", 2);
        accessOrder.put("C", 3);

        // 访问B，B会移到最后
        accessOrder.get("B");
        // 访问A，A会移到最后
        accessOrder.get("A");
        // 顺序: C, B, A（最近访问的在最后）
        System.out.println("访问顺序: " + accessOrder);

        // 【3】LRU缓存实现示例
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("1", "One");
        cache.put("2", "Two");
        cache.put("3", "Three");
        System.out.println("\nLRU缓存: " + cache);

        cache.get("1");  // 访问1，1移到末尾
        cache.put("4", "Four");  // 超出容量，移除最久未使用的2
        System.out.println("添加4后: " + cache);  // {3=Three, 1=One, 4=Four}

        // 【4】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 需要保持插入顺序的场景");
        System.out.println("2. LRU缓存实现");
        System.out.println("3. 序列化时保持顺序");
        System.out.println("4. JSON对象解析（保持字段顺序）");
    }

    // LRU缓存简单实现
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);  // accessOrder=true
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;  // 超出容量时移除最老的
        }
    }

    // ================================================
    // 四、TreeMap详解
    // ================================================
    public static void treeMapDemo() {
        System.out.println("--- TreeMap详解 ---");

        // 【TreeMap的特点】
        // 1. 基于红黑树实现
        // 2. 按键的自然顺序或自定义Comparator排序
        // 3. 不允许null键（但允许null值）
        // 4. 非线程安全
        // 5. get/put/remove时间复杂度O(log n)

        // 【1】自然排序
        TreeMap<String, Integer> naturalOrder = new TreeMap<>();
        naturalOrder.put("Banana", 3);
        naturalOrder.put("Apple", 5);
        naturalOrder.put("Cherry", 8);
        naturalOrder.put("Date", 2);
        // 按Key的自然顺序（字母序）
        System.out.println("自然排序: " + naturalOrder);

        // 【2】自定义排序（降序）
        TreeMap<String, Integer> reverseOrder = new TreeMap<>(Comparator.reverseOrder());
        reverseOrder.put("Banana", 3);
        reverseOrder.put("Apple", 5);
        reverseOrder.put("Cherry", 8);
        System.out.println("降序排序: " + reverseOrder);

        // 【3】数字Key排序
        TreeMap<Integer, String> numberMap = new TreeMap<>();
        numberMap.put(5, "Five");
        numberMap.put(2, "Two");
        numberMap.put(8, "Eight");
        numberMap.put(1, "One");
        // 按数字升序
        System.out.println("数字排序: " + numberMap);

        // 【4】范围查询
        System.out.println("\n--- 范围查询 ---");
        // headMap: 小于指定Key的部分
        System.out.println("headMap(5): " + naturalOrder.headMap("Cherry"));

        // tailMap: 大于等于指定Key的部分
        System.out.println("tailMap(Cherry): " + naturalOrder.tailMap("Cherry"));

        // subMap: 指定范围
        System.out.println("subMap(Apple, Cherry): " + naturalOrder.subMap("Apple", "Cherry"));

        // 【5】边界操作
        System.out.println("\n--- 边界操作 ---");
        // firstEntry: 第一个键值对
        System.out.println("firstEntry: " + naturalOrder.firstEntry());
        // lastEntry: 最后一个键值对
        System.out.println("lastEntry: " + naturalOrder.lastEntry());

        // lowerEntry: 小于指定Key的最大键值对
        System.out.println("lowerEntry(Cherry): " + naturalOrder.lowerEntry("Cherry"));
        // higherEntry: 大于指定Key的最小键值对
        System.out.println("higherEntry(Cherry): " + naturalOrder.higherEntry("Cherry"));

        // floorEntry: 小于等于指定Key的最大键值对
        System.out.println("floorEntry(Cherry): " + naturalOrder.floorEntry("Cherry"));
        // ceilingEntry: 大于等于指定Key的最小键值对
        System.out.println("ceilingEntry(Cherry): " + naturalOrder.ceilingEntry("Cherry"));

        // 【6】导航方法
        System.out.println("\n--- 导航方法 ---");
        // firstKey / lastKey
        System.out.println("firstKey: " + naturalOrder.firstKey());
        System.out.println("lastKey: " + naturalOrder.lastKey());

        // pollFirstEntry: 移除并返回第一个
        Map.Entry<String, Integer> first = naturalOrder.pollFirstEntry();
        System.out.println("pollFirstEntry: " + first);
        System.out.println("剩余: " + naturalOrder);

        // 【7】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 需要按键排序的场景");
        System.out.println("2. 范围查询（如查找某个区间的记录）");
        System.out.println("3. 排行榜实现");
        System.out.println("4. 时间序列数据（Key为时间戳）");
    }

    // ================================================
    // 五、Hashtable详解
    // ================================================
    public static void hashtableDemo() {
        System.out.println("--- Hashtable详解 ---");

        // 【Hashtable的特点】
        // 1. 线程安全的HashMap
        // 2. 所有方法都是synchronized
        // 3. 不允许null键和null值
        // 4. 已过时，推荐使用ConcurrentHashMap

        // 【1】创建Hashtable
        Hashtable<String, Integer> table = new Hashtable<>();

        // 【2】基本操作
        table.put("A", 1);
        table.put("B", 2);
        table.put("C", 3);
        System.out.println("Hashtable: " + table);

        // 【3】不允许null
        try {
            table.put(null, 1);  // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("不允许null键: NullPointerException");
        }

        try {
            table.put("D", null);  // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("不允许null值: NullPointerException");
        }

        // 【4】遗留方法
        System.out.println("\n--- 遗留方法 ---");
        // contains(): 检查是否包含值（已被containsValue替代）
        System.out.println("contains(2): " + table.contains(2));

        // elements(): 返回Enumeration（已被values替代）
        System.out.print("elements: ");
        Enumeration<Integer> elements = table.elements();
        while (elements.hasMoreElements()) {
            System.out.print(elements.nextElement() + " ");
        }
        System.out.println();

        // 【5】为什么不推荐使用
        System.out.println("\n--- 不推荐原因 ---");
        System.out.println("1. 性能差：所有方法都同步");
        System.out.println("2. 功能少：不支持Iterator");
        System.out.println("3. 已过时：JDK1就标记为Legacy");
        System.out.println("4. 替代方案: ConcurrentHashMap");
    }

    // ================================================
    // 六、ConcurrentHashMap详解
    // ================================================
    public static void concurrentHashMapDemo() {
        System.out.println("--- ConcurrentHashMap详解 ---");

        // 【ConcurrentHashMap的特点】
        // 1. 线程安全的HashMap
        // 2. JDK7: 分段锁（Segment）
        // 3. JDK8+: CAS + synchronized（细粒度锁）
        // 4. 高并发下性能优秀
        // 5. 不允许null键和null值

        // 【1】创建ConcurrentHashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // 【2】指定初始容量和并发级别
        ConcurrentHashMap<String, Integer> customMap = new ConcurrentHashMap<>(16, 0.75f, 16);
        System.out.println("初始容量: 16, 负载因子: 0.75, 并发级别: 16");

        // 【3】基本操作
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        System.out.println("基本操作: " + map);

        // 【4】原子操作
        // putIfAbsent: 不存在时才放入
        map.putIfAbsent("A", 10);  // A已存在，不覆盖
        map.putIfAbsent("D", 4);   // D不存在，添加
        System.out.println("putIfAbsent后: " + map);

        // replace: 替换
        map.replace("B", 20);  // 无条件替换
        map.replace("C", 3, 30);  // 条件替换（旧值匹配才替换）
        System.out.println("replace后: " + map);

        // remove: 条件删除
        map.remove("A", 1);  // 只有值为1时才删除
        System.out.println("条件删除后: " + map);

        // 【5】compute系列方法（原子操作）
        map.compute("B", (key, value) -> value + 10);  // 计算新值
        System.out.println("compute后B: " + map.get("B"));

        map.computeIfAbsent("E", k -> 50);  // 不存在时计算
        System.out.println("computeIfAbsent后: " + map.get("E"));

        map.merge("B", 5, Integer::sum);  // 合并值
        System.out.println("merge后B: " + map.get("B"));

        // 【6】批量操作
        System.out.println("\n--- 批量操作 ---");
        // forEach: 并行遍历
        map.forEach(2, (key, value) -> System.out.println("  " + key + "=" + value));

        // search: 搜索
        String found = map.search(2, (key, value) -> value > 20 ? key : null);
        System.out.println("search找到: " + found);

        // reduce: 归约
        Integer sum = map.reduceValues(2, Integer::sum);
        System.out.println("reduce求和: " + sum);

        // 【7】计数操作
        System.out.println("\n--- 计数操作 ---");
        ConcurrentHashMap<String, LongAdder> counter = new ConcurrentHashMap<>();
        counter.computeIfAbsent("page1", k -> new LongAdder()).increment();
        counter.computeIfAbsent("page1", k -> new LongAdder()).increment();
        counter.computeIfAbsent("page2", k -> new LongAdder()).increment();
        System.out.println("page1访问次数: " + counter.get("page1"));
        System.out.println("page2访问次数: " + counter.get("page2"));

        // 【8】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 高并发环境下的共享Map");
        System.out.println("2. 计数器（如网站访问量统计）");
        System.out.println("3. 缓存系统");
        System.out.println("4. 多线程数据处理");
    }

    // ================================================
    // 七、WeakHashMap详解
    // ================================================
    public static void weakHashMapDemo() {
        System.out.println("--- WeakHashMap详解 ---");

        // 【WeakHashMap的特点】
        // 1. Key是弱引用（WeakReference）
        // 2. 当Key没有被强引用时，可被GC回收
        // 3. 适合做缓存
        // 4. 非线程安全

        // 【1】基本使用
        WeakHashMap<String, String> map = new WeakHashMap<>();
        map.put("Key1", "Value1");
        map.put("Key2", "Value2");
        System.out.println("初始: " + map);

        // 【2】弱引用特性
        String key = new String("Key3");  // 创建新的String对象
        map.put(key, "Value3");
        System.out.println("添加Key3: " + map);

        // 断开强引用
        key = null;
        System.out.println("断开引用后: " + map);

        // 建议GC回收
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Key3可能被回收
        System.out.println("GC后: " + map);

        // 【3】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 缓存（自动清理不再使用的条目）");
        System.out.println("2. 监听器注册表");
        System.out.println("3. 元数据存储");
        System.out.println("4. 避免内存泄漏");

        // 【4】注意事项
        System.out.println("\n--- 注意事项 ---");
        System.out.println("1. Key必须是新创建的对象（不能用字符串常量）");
        System.out.println("2. 不能保证何时被回收");
        System.out.println("3. 不适合需要确定性的场景");
    }

    // ================================================
    // 八、IdentityHashMap详解
    // ================================================
    public static void identityHashMapDemo() {
        System.out.println("--- IdentityHashMap详解 ---");

        // 【IdentityHashMap的特点】
        // 1. 使用==比较Key，而非equals()
        // 2. 使用System.identityHashCode()计算哈希
        // 3. 允许null键和null值
        // 4. 非线程安全

        // 【1】与HashMap的区别
        HashMap<String, Integer> hashMap = new HashMap<>();
        IdentityHashMap<String, Integer> identityMap = new IdentityHashMap<>();

        String key1 = new String("test");
        String key2 = new String("test");

        // HashMap使用equals比较，认为key1和key2相同
        hashMap.put(key1, 1);
        hashMap.put(key2, 2);  // 覆盖
        System.out.println("HashMap大小: " + hashMap.size());  // 1

        // IdentityHashMap使用==比较，认为key1和key2不同
        identityMap.put(key1, 1);
        identityMap.put(key2, 2);  // 不覆盖
        System.out.println("IdentityHashMap大小: " + identityMap.size());  // 2
        System.out.println("IdentityHashMap: " + identityMap);

        // 【2】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 拓扑结构保持（如序列化）");
        System.out.println("2. 代理对象处理");
        System.out.println("3. 需要区分相同内容的不同对象");
        System.out.println("4. 调试工具");

        // 【3】注意事项
        System.out.println("\n--- 注意事项 ---");
        System.out.println("1. 违反Map的一般约定（使用==而非equals）");
        System.out.println("2. 很少使用，特殊场景才用");
        System.out.println("3. 性能略低于HashMap");
    }

    // ================================================
    // 九、Map常用操作
    // ================================================
    public static void mapOperations() {
        System.out.println("--- Map常用操作 ---");

        Map<String, Integer> map = new HashMap<>();

        // 【1】添加/更新
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        System.out.println("添加后: " + map);

        // 【2】获取
        System.out.println("get(A): " + map.get("A"));
        System.out.println("get(D): " + map.get("D"));  // null

        // 【3】删除
        map.remove("B");
        System.out.println("remove(B): " + map);

        // 【4】清空
        map.clear();
        System.out.println("clear后: " + map);

        // 【5】批量操作
        Map<String, Integer> other = new HashMap<>();
        other.put("X", 10);
        other.put("Y", 20);

        map.putAll(other);  // 批量添加
        System.out.println("putAll后: " + map);

        // 【6】JDK8+新方法
        System.out.println("\n--- JDK8+新方法 ---");

        // getOrDefault
        System.out.println("getOrDefault(Z, 0): " + map.getOrDefault("Z", 0));

        // putIfAbsent
        map.putIfAbsent("A", 100);  // A已存在，不覆盖
        map.putIfAbsent("Z", 100);  // Z不存在，添加
        System.out.println("putIfAbsent后: " + map);

        // remove(key, value)
        boolean removed = map.remove("X", 10);  // 条件删除
        System.out.println("条件删除X: " + removed);

        // replace
        map.replace("Y", 25);  // 替换值
        System.out.println("replace后Y: " + map.get("Y"));

        // replace(key, oldValue, newValue)
        boolean replaced = map.replace("Y", 25, 30);  // 条件替换
        System.out.println("条件替换Y: " + replaced);

        // compute
        map.compute("A", (k, v) -> v + 10);  // 计算新值
        System.out.println("compute后A: " + map.get("A"));

        // computeIfAbsent
        map.computeIfAbsent("W", k -> 50);  // 不存在时计算
        System.out.println("computeIfAbsent后: " + map.get("W"));

        // computeIfPresent
        map.computeIfPresent("A", (k, v) -> v * 2);  // 存在时计算
        System.out.println("computeIfPresent后A: " + map.get("A"));

        // merge
        map.merge("A", 5, Integer::sum);  // 合并
        System.out.println("merge后A: " + map.get("A"));

        // forEach
        System.out.print("forEach: ");
        map.forEach((k, v) -> System.out.print(k + "=" + v + " "));
        System.out.println();

        // replaceAll
        map.replaceAll((k, v) -> v * 2);  // 全部替换
        System.out.println("replaceAll后: " + map);
    }

    // ================================================
    // 十、Map遍历方式
    // ================================================
    public static void mapTraversal() {
        System.out.println("--- Map遍历方式 ---");

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // 【方式1】遍历KeySet
        System.out.print("1. keySet: ");
        for (String key : map.keySet()) {
            System.out.print(key + "=" + map.get(key) + " ");
        }
        System.out.println();

        // 【方式2】遍历Values
        System.out.print("2. values: ");
        for (Integer value : map.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        // 【方式3】遍历EntrySet（推荐）
        System.out.print("3. entrySet: ");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();

        // 【方式4】Iterator遍历EntrySet
        System.out.print("4. Iterator: ");
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();

        // 【方式5】forEach方法（JDK8+）
        System.out.print("5. forEach: ");
        map.forEach((key, value) -> System.out.print(key + "=" + value + " "));
        System.out.println();

        // 【方式6】Stream API（JDK8+）
        System.out.print("6. Stream: ");
        map.entrySet().stream()
           .forEach(entry -> System.out.print(entry.getKey() + "=" + entry.getValue() + " "));
        System.out.println();

        // 【方式7】并行Stream
        System.out.print("7. Parallel Stream: ");
        map.entrySet().parallelStream()
           .forEach(entry -> System.out.print(entry.getKey() + "=" + entry.getValue() + " "));
        System.out.println();

        // 【遍历时删除】
        System.out.println("\n--- 遍历时删除 ---");
        Map<Integer, String> numMap = new HashMap<>();
        numMap.put(1, "One");
        numMap.put(2, "Two");
        numMap.put(3, "Three");
        numMap.put(4, "Four");

        // 正确方式：使用Iterator
        Iterator<Map.Entry<Integer, String>> it = numMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            if (entry.getKey() % 2 == 0) {
                it.remove();  // 安全删除
            }
        }
        System.out.println("删除偶数Key后: " + numMap);

        // 正确方式：使用removeIf（JDK8+）
        numMap = new HashMap<>();
        numMap.put(1, "One");
        numMap.put(2, "Two");
        numMap.put(3, "Three");
        numMap.entrySet().removeIf(entry -> entry.getKey() % 2 == 0);
        System.out.println("removeIf后: " + numMap);
    }

    // ================================================
    // 十一、Map排序
    // ================================================
    public static void mapSort() {
        System.out.println("--- Map排序 ---");

        Map<String, Integer> unsorted = new HashMap<>();
        unsorted.put("Banana", 3);
        unsorted.put("Apple", 5);
        unsorted.put("Cherry", 8);
        unsorted.put("Date", 2);

        // 【1】按Key排序（使用TreeMap）
        TreeMap<String, Integer> sortedByKey = new TreeMap<>(unsorted);
        System.out.println("按Key排序: " + sortedByKey);

        // 【2】按Value排序（使用Stream）
        LinkedHashMap<String, Integer> sortedByValue = unsorted.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                ));
        System.out.println("按Value排序: " + sortedByValue);

        // 【3】按Value降序排序
        LinkedHashMap<String, Integer> sortedByValueDesc = unsorted.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                ));
        System.out.println("按Value降序: " + sortedByValueDesc);

        // 【4】复杂对象排序
        Map<String, Person> people = new HashMap<>();
        people.put("Alice", new Person("Alice", 25));
        people.put("Bob", new Person("Bob", 20));
        people.put("Charlie", new Person("Charlie", 30));

        // 按年龄排序
        LinkedHashMap<String, Person> sortedByAge = people.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(p -> p.age)))
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                ));
        System.out.println("\n按年龄排序:");
        sortedByAge.forEach((k, v) -> System.out.println("  " + k + ": " + v.age));
    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    // ================================================
    // 十二、Map与List转换
    // ================================================
    public static void mapToList() {
        System.out.println("--- Map与List转换 ---");

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // 【1】Map的Key转List
        List<String> keys = new ArrayList<>(map.keySet());
        System.out.println("Keys: " + keys);

        // 【2】Map的Value转List
        List<Integer> values = new ArrayList<>(map.values());
        System.out.println("Values: " + values);

        // 【3】Map的Entry转List
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        System.out.println("Entries: " + entries);

        // 【4】使用Stream转换
        List<String> keysStream = map.keySet().stream().collect(Collectors.toList());
        System.out.println("Keys(Stream): " + keysStream);

        // 【5】List转Map
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 20),
            new Person("Charlie", 30)
        );

        // 按姓名作为Key
        Map<String, Person> personMap = people.stream()
                .collect(Collectors.toMap(p -> p.name, p -> p));
        System.out.println("\nList转Map: " + personMap);

        // 【6】处理重复Key
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry");
        Map<String, Long> wordCount = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println("单词计数: " + wordCount);

        // 【7】分组
        Map<Integer, List<Person>> groupedByAge = people.stream()
                .collect(Collectors.groupingBy(p -> p.age / 10 * 10));
        System.out.println("按年龄段分组: " + groupedByAge);
    }

    // ================================================
    // 十三、不可变Map
    // ================================================
    public static void immutableMap() {
        System.out.println("--- 不可变Map ---");

        // 【1】Collections.unmodifiableMap()
        Map<String, Integer> mutable = new HashMap<>();
        mutable.put("A", 1);
        mutable.put("B", 2);
        Map<String, Integer> unmodifiable = Collections.unmodifiableMap(mutable);
        System.out.println("unmodifiableMap: " + unmodifiable);
        // unmodifiable.put("C", 3);  // UnsupportedOperationException!

        // 注意：原Map仍可修改
        mutable.put("C", 3);
        System.out.println("修改原Map后: " + unmodifiable);

        // 【2】Map.of()（JDK9+）
        Map<String, Integer> ofMap = Map.of("A", 1, "B", 2, "C", 3);
        System.out.println("\nMap.of(): " + ofMap);
        // ofMap.put("D", 4);  // UnsupportedOperationException!

        // 【3】Map.ofEntries()（JDK9+）
        Map<String, Integer> ofEntriesMap = Map.ofEntries(
            Map.entry("X", 10),
            Map.entry("Y", 20),
            Map.entry("Z", 30)
        );
        System.out.println("Map.ofEntries(): " + ofEntriesMap);

        // 【4】Map.copyOf()（JDK10+）
        Map<String, Integer> copyOfMap = Map.copyOf(mutable);
        System.out.println("Map.copyOf(): " + copyOfMap);

        // 【5】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 常量Map");
        System.out.println("2. 配置信息");
        System.out.println("3. API返回值（防止外部修改）");
        System.out.println("4. 多线程共享数据");
    }

    // ================================================
    // 十四、Map性能对比
    // ================================================
    public static void performanceComparison() {
        System.out.println("--- Map性能对比 ---");

        int size = 100000;

        // 【1】插入性能对比
        System.out.println("\n1. 插入 " + size + " 个元素:");

        long start = System.nanoTime();
        Map<Integer, Integer> hashMap = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            hashMap.put(i, i);
        }
        long hashMapTime = System.nanoTime() - start;
        System.out.println("   HashMap: " + hashMapTime / 1_000_000 + " ms");

        start = System.nanoTime();
        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>(size);
        for (int i = 0; i < size; i++) {
            linkedHashMap.put(i, i);
        }
        long linkedTime = System.nanoTime() - start;
        System.out.println("   LinkedHashMap: " + linkedTime / 1_000_000 + " ms");

        start = System.nanoTime();
        Map<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            treeMap.put(i, i);
        }
        long treeTime = System.nanoTime() - start;
        System.out.println("   TreeMap: " + treeTime / 1_000_000 + " ms");

        // 【2】查找性能对比
        System.out.println("\n2. 查找 " + size + " 次:");

        Random random = new Random(42);
        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            hashMap.get(random.nextInt(size));
        }
        hashMapTime = System.nanoTime() - start;
        System.out.println("   HashMap: " + hashMapTime / 1_000_000 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedHashMap.get(random.nextInt(size));
        }
        linkedTime = System.nanoTime() - start;
        System.out.println("   LinkedHashMap: " + linkedTime / 1_000_000 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            treeMap.get(random.nextInt(size));
        }
        treeTime = System.nanoTime() - start;
        System.out.println("   TreeMap: " + treeTime / 1_000_000 + " ms");

        // 【总结】
        System.out.println("\n--- 性能总结 ---");
        System.out.println("HashMap:");
        System.out.println("  ✓ 插入/查找最快 O(1)");
        System.out.println("  ✓ 大多数场景的首选");
        System.out.println("\nLinkedHashMap:");
        System.out.println("  ✓ 性能接近HashMap");
        System.out.println("  ✓ 额外开销：维护链表");
        System.out.println("\nTreeMap:");
        System.out.println("  ✓ 插入/查找 O(log n)，较慢");
        System.out.println("  ✓ 优势：有序、范围查询");
    }

    // ================================================
    // 十五、注意事项
    // ================================================
    public static void cautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】选择合适的Map实现
        System.out.println("\n1. 选择Map实现:");
        System.out.println("   - 大多数场景: HashMap");
        System.out.println("   - 需要保持顺序: LinkedHashMap");
        System.out.println("   - 需要排序: TreeMap");
        System.out.println("   - 线程安全: ConcurrentHashMap");
        System.out.println("   - 缓存: WeakHashMap");

        // 【2】Key的设计
        System.out.println("\n2. Key的设计:");
        System.out.println("   - Key必须正确实现hashCode()和equals()");
        System.out.println("   - Key应该是不可变对象");
        System.out.println("   - 避免使用可变对象作为Key");

        // 【3】null的处理
        System.out.println("\n3. null处理:");
        System.out.println("   - HashMap允许null键和null值");
        System.out.println("   - Hashtable/ConcurrentHashMap不允许null");
        System.out.println("   - TreeMap不允许null键");
        System.out.println("   - 使用getOrDefault避免NPE");

        // 【4】初始化容量
        System.out.println("\n4. 初始化容量:");
        System.out.println("   - 知道大致数量时，指定初始容量");
        System.out.println("   - 避免频繁扩容");
        Map<String, Integer> optimized = new HashMap<>(1000);

        // 【5】泛型的使用
        System.out.println("\n5. 泛型:");
        System.out.println("   - 始终使用泛型");
        Map<String, Integer> safe = new HashMap<>();  // 推荐

        // 【6】遍历时的修改
        System.out.println("\n6. 遍历修改:");
        System.out.println("   - 不要在foreach中直接修改Map");
        System.out.println("   - 使用Iterator或removeIf");

        // 【7】线程安全
        System.out.println("\n7. 线程安全:");
        System.out.println("   - HashMap/TreeMap/LinkedHashMap都不是线程安全的");
        System.out.println("   - 多线程环境使用ConcurrentHashMap");
        System.out.println("   - 或使用Collections.synchronizedMap()");
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

        // 【8】内存泄漏
        System.out.println("\n8. 内存泄漏:");
        System.out.println("   - 及时清理不再使用的Map");
        System.out.println("   - 大Value对象要注意");
        Map<String, byte[]> bigMap = new HashMap<>();
        bigMap.clear();
        bigMap = null;

        // 【9】Key的hashCode冲突
        System.out.println("\n9. Hash冲突:");
        System.out.println("   - 良好的hashCode分布提高性能");
        System.out.println("   - 避免所有Key返回相同的hashCode");

        // 【10】Map.of的限制
        System.out.println("\n10. Map.of限制:");
        System.out.println("   - 最多10个键值对");
        System.out.println("   - 超过10个使用Map.ofEntries()");
        System.out.println("   - 不允许null键和null值");
        System.out.println("   - 不允许重复Key");

        // 【11】性能优化建议
        System.out.println("\n11. 性能优化:");
        System.out.println("   - 大批量数据: 预分配容量");
        System.out.println("   - 频繁查找: 确保良好的hashCode");
        System.out.println("   - 需要排序: 考虑TreeMap");
        System.out.println("   - 高并发: ConcurrentHashMap");

        // 【12】常见错误
        System.out.println("\n12. 常见错误:");
        System.out.println("   - 忘记检查get返回的null");
        System.out.println("   - 在迭代时修改Map");
        System.out.println("   - 使用可变对象作为Key");
        System.out.println("   - 忽略线程安全问题");
    }
}
