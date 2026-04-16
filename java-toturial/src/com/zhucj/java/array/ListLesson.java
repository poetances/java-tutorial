package com.zhucj.java.array;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * =====================================================
 * Java 列表（List）详解
 * =====================================================
 *
 * 【一、List是什么】
 * -----------------
 * List是Java集合框架中的核心接口，表示有序的可重复元素集合。
 * - List继承自Collection接口
 * - 元素可以重复
 * - 元素按插入顺序存储（有序）
 * - 可以通过索引访问元素
 * - 允许null元素
 *
 * 【二、List的主要实现类】
 * ----------------------
 * 1. ArrayList     - 基于动态数组，随机访问快，增删慢
 * 2. LinkedList    - 基于双向链表，增删快，随机访问慢
 * 3. Vector        - 线程安全的ArrayList（已过时）
 * 4. Stack         - 继承Vector的栈结构（已过时）
 * 5. CopyOnWriteArrayList - 线程安全，读多写少场景
 *
 * 【三、List vs Array】
 * ------------------
 * Array: 固定长度，基本类型和引用类型都支持，性能好
 * List:  动态长度，只支持引用类型，功能丰富，使用方便
 *
 * 常用：ArrayList、LinkedList
 * =====================================================
 */
public class ListLesson {

    public static void main(String[] args) {
        System.out.println("========== List基础 ==========");
        listBasics();

        System.out.println("\n========== ArrayList详解 ==========");
        arrayListDemo();

        System.out.println("\n========== LinkedList详解 ==========");
        linkedListDemo();

        System.out.println("\n========== Vector详解 ==========");
        vectorDemo();

        System.out.println("\n========== Stack详解 ==========");
        stackDemo();

        System.out.println("\n========== CopyOnWriteArrayList详解 ==========");
        copyOnWriteArrayListDemo();

        System.out.println("\n========== List常用操作 ==========");
        listOperations();

        System.out.println("\n========== List遍历方式 ==========");
        listTraversal();

        System.out.println("\n========== List排序 ==========");
        listSort();

        System.out.println("\n========== List查找 ==========");
        listSearch();

        System.out.println("\n========== List与Array转换 ==========");
        listToArray();

        System.out.println("\n========== 不可变List ==========");
        immutableList();

        System.out.println("\n========== List性能对比 ==========");
        performanceComparison();

        System.out.println("\n========== 注意事项 ==========");
        cautions();
    }

    // ================================================
    // 一、List基础
    // ================================================
    public static void listBasics() {
        System.out.println("--- List基本概念 ---");

        // 【1】创建List
        List<String> list = new ArrayList<>();

        // 【2】添加元素
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        // list → [Apple, Banana, Cherry]
        System.out.println("添加元素: " + list);

        // 【3】获取元素
        // list.get(0) → "Apple"
        System.out.println("第一个元素: " + list.get(0));

        // 【4】修改元素
        list.set(1, "Blueberry");
        // list → [Apple, Blueberry, Cherry]
        System.out.println("修改后: " + list);

        // 【5】删除元素
        list.remove(0);
        // list → [Blueberry, Cherry]
        System.out.println("删除后: " + list);

        // 【6】List大小
        // list.size() → 2
        System.out.println("List大小: " + list.size());

        // 【7】判断是否包含
        // list.contains("Cherry") → true
        System.out.println("包含Cherry: " + list.contains("Cherry"));

        // 【8】List特性
        System.out.println("\n--- List特性 ---");
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(1);  // 允许重复
        // nums → [1, 2, 1]
        System.out.println("允许重复: " + nums);

        nums.add(null);  // 允许null
        // nums → [1, 2, 1, null]
        System.out.println("允许null: " + nums);
    }

    // ================================================
    // 二、ArrayList详解
    // ================================================
    public static void arrayListDemo() {
        System.out.println("--- ArrayList详解 ---");

        // 【ArrayList的特点】
        // 1. 基于动态数组实现
        // 2. 随机访问快 O(1)
        // 3. 尾部添加快 O(1)，中间插入慢 O(n)
        // 4. 非线程安全
        // 5. 初始容量10，自动扩容1.5倍

        // 【1】创建ArrayList
        ArrayList<String> fruits = new ArrayList<>();

        // 【2】指定初始容量（优化性能）
        ArrayList<String> largeList = new ArrayList<>(1000);
        System.out.println("指定容量: " + largeList.size());

        // 【3】从Collection创建
        List<String> source = Arrays.asList("A", "B", "C");
        ArrayList<String> copied = new ArrayList<>(source);
        // copied → [A, B, C]
        System.out.println("从Collection创建: " + copied);

        // 【4】添加元素
        fruits.add("Apple");           // 尾部添加
        fruits.add("Banana");
        fruits.add(1, "Cherry");      // 指定位置插入
        // fruits → [Apple, Cherry, Banana]
        System.out.println("添加后: " + fruits);

        // 【5】批量添加
        List<String> more = Arrays.asList("Durian", "Elderberry");
        fruits.addAll(more);
        // fruits → [Apple, Cherry, Banana, Durian, Elderberry]
        System.out.println("批量添加: " + fruits);

        // 【6】随机访问
        // fruits.get(0) → "Apple"
        System.out.println("随机访问[0]: " + fruits.get(0));
        // fruits.get(2) → "Banana"
        System.out.println("随机访问[2]: " + fruits.get(2));

        // 【7】搜索元素
        // fruits.indexOf("Cherry") → 1
        System.out.println("Cherry索引: " + fruits.indexOf("Cherry"));
        // fruits.lastIndexOf("Apple") → 0
        System.out.println("Apple最后索引: " + fruits.lastIndexOf("Apple"));

        // 【8】子列表
        List<String> subList = fruits.subList(1, 3);
        // subList → [Cherry, Banana]
        System.out.println("子列表[1,3): " + subList);

        // 【9】清空列表
        fruits.clear();
        // fruits → []
        System.out.println("清空后: " + fruits);

        // 【10】ArrayList内部机制
        System.out.println("\n--- ArrayList内部机制 ---");
        ArrayList<Integer> nums = new ArrayList<>();
        System.out.println("初始容量: 10（默认）");
        for (int i = 0; i < 15; i++) {
            nums.add(i);
        }
        System.out.println("添加15个元素后，容量自动扩容到: 15");
        System.out.println("扩容规则: 新容量 = 旧容量 * 1.5");
    }

    // ================================================
    // 三、LinkedList详解
    // ================================================
    public static void linkedListDemo() {
        System.out.println("--- LinkedList详解 ---");

        // 【LinkedList的特点】
        // 1. 基于双向链表实现
        // 2. 随机访问慢 O(n)
        // 3. 首尾增删快 O(1)，中间增删需要定位O(n)
        // 4. 非线程安全
        // 5. 实现了Deque接口，可用作队列/双端队列/栈

        // 【1】创建LinkedList
        LinkedList<String> list = new LinkedList<>();

        // 【2】作为普通List使用
        list.add("First");
        list.add("Second");
        list.add("Third");
        // list → [First, Second, Third]
        System.out.println("基本List操作: " + list);

        // 【3】作为双端队列使用
        LinkedList<String> deque = new LinkedList<>();

        // 头部操作
        deque.addFirst("Head");
        deque.offerFirst("Front");  // 推荐，不抛异常
        // deque → [Front, Head]
        System.out.println("头部添加: " + deque);

        // 尾部操作
        deque.addLast("Tail");
        deque.offerLast("End");  // 推荐，不抛异常
        // deque → [Front, Head, Tail, End]
        System.out.println("尾部添加: " + deque);

        // 获取但不删除
        // deque.getFirst() → "Front"
        System.out.println("第一个元素: " + deque.getFirst());
        // deque.peekFirst() → "Front" （推荐，空时返回null）
        System.out.println("peek第一个: " + deque.peekFirst());
        // deque.getLast() → "End"
        System.out.println("最后一个元素: " + deque.getLast());

        // 删除并返回
        // deque.removeFirst() → "Front"
        System.out.println("移除第一个: " + deque.removeFirst());
        // deque.pollFirst() → "Head" （推荐，空时返回null）
        System.out.println("poll第一个: " + deque.pollFirst());
        // deque → [Tail, End]
        System.out.println("当前列表: " + deque);

        // 【4】作为队列使用（FIFO）
        LinkedList<String> queue = new LinkedList<>();
        queue.offer("Task1");  // 入队
        queue.offer("Task2");
        queue.offer("Task3");
        // queue → [Task1, Task2, Task3]
        System.out.println("\n队列: " + queue);

        // String task = queue.poll();  // 出队 → "Task1"
        System.out.println("出队: " + queue.poll());
        // queue → [Task2, Task3]
        System.out.println("队列剩余: " + queue);

        // 【5】作为栈使用（LIFO）
        LinkedList<String> stack = new LinkedList<>();
        stack.push("A");  // 压栈
        stack.push("B");
        stack.push("C");
        // stack → [C, B, A]
        System.out.println("\n栈: " + stack);

        // String top = stack.pop();  // 弹栈 → "C"
        System.out.println("弹栈: " + stack.pop());
        // stack → [B, A]
        System.out.println("栈剩余: " + stack);

        // 【6】LinkedList vs ArrayList
        System.out.println("\n--- LinkedList vs ArrayList ---");
        System.out.println("ArrayList: 随机访问快，适合读多写少");
        System.out.println("LinkedList: 首尾增删快，适合频繁增删");
        System.out.println("实际开发中，ArrayList使用更多");
    }

    // ================================================
    // 四、Vector详解
    // ================================================
    public static void vectorDemo() {
        System.out.println("--- Vector详解 ---");

        // 【Vector的特点】
        // 1. 线程安全的ArrayList
        // 2. 所有方法都是synchronized
        // 3. 性能较差（因为同步开销）
        // 4. 已过时，推荐使用Collections.synchronizedList或CopyOnWriteArrayList

        // 【1】创建Vector
        Vector<String> vector = new Vector<>();

        // 【2】指定初始容量和扩容增量
        Vector<String> customVector = new Vector<>(10, 5);
        System.out.println("初始容量: 10, 扩容增量: 5");

        // 【3】基本操作（与ArrayList类似）
        vector.add("Element1");
        vector.add("Element2");
        vector.addElement("Element3");  // Vector特有方法
        // vector → [Element1, Element2, Element3]
        System.out.println("Vector内容: " + vector);

        // 【4】线程安全演示
        System.out.println("\nVector是线程安全的");
        System.out.println("但现代开发中更推荐使用:");
        System.out.println("1. Collections.synchronizedList(new ArrayList<>())");
        System.out.println("2. CopyOnWriteArrayList（读多写少）");

        // 【5】Vector的遗留方法
        // vector.firstElement() → "Element1"
        System.out.println("第一个元素: " + vector.firstElement());
        // vector.lastElement() → "Element3"
        System.out.println("最后一个元素: " + vector.lastElement());

        // 【6】枚举遍历（过时）
        System.out.print("枚举遍历: ");
        Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            System.out.print(elements.nextElement() + " ");
        }
        System.out.println();
    }

    // ================================================
    // 五、Stack详解
    // ================================================
    public static void stackDemo() {
        System.out.println("--- Stack详解 ---");

        // 【Stack的特点】
        // 1. 继承自Vector
        // 2. LIFO（后进先出）数据结构
        // 3. 已过时，推荐使用ArrayDeque或LinkedList

        // 【1】创建Stack
        Stack<String> stack = new Stack<>();

        // 【2】压栈
        stack.push("A");
        stack.push("B");
        stack.push("C");
        // stack → [A, B, C] （C在栈顶）
        System.out.println("压栈后: " + stack);

        // 【3】查看栈顶（不移除）
        // stack.peek() → "C"
        System.out.println("栈顶元素: " + stack.peek());

        // 【4】弹栈
        // stack.pop() → "C"
        System.out.println("弹栈: " + stack.pop());
        // stack → [A, B]
        System.out.println("弹栈后: " + stack);

        // 【5】搜索元素
        // stack.search("A") → 2 （从栈顶开始数，1-based）
        System.out.println("搜索A的位置: " + stack.search("A"));
        // stack.search("X") → -1 （不存在）
        System.out.println("搜索X的位置: " + stack.search("X"));

        // 【6】判断是否为空
        // stack.empty() → false
        System.out.println("是否为空: " + stack.empty());

        // 【7】推荐使用ArrayDeque替代
        System.out.println("\n--- 推荐使用ArrayDeque ---");
        Deque<String> betterStack = new ArrayDeque<>();
        betterStack.push("X");
        betterStack.push("Y");
        betterStack.push("Z");
        System.out.println("ArrayDeque作为栈: " + betterStack);
        System.out.println("弹栈: " + betterStack.pop());
    }

    // ================================================
    // 六、CopyOnWriteArrayList详解
    // ================================================
    public static void copyOnWriteArrayListDemo() {
        System.out.println("--- CopyOnWriteArrayList详解 ---");

        // 【CopyOnWriteArrayList的特点】
        // 1. 线程安全的ArrayList
        // 2. 写时复制：每次修改都复制一份新数组
        // 3. 读操作无锁，性能极高
        // 4. 写操作有锁，性能较低
        // 5. 适合读多写少的并发场景

        // 【1】创建CopyOnWriteArrayList
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();

        // 【2】基本操作
        cowList.add("Item1");
        cowList.add("Item2");
        cowList.add("Item3");
        // cowList → [Item1, Item2, Item3]
        System.out.println("内容: " + cowList);

        // 【3】线程安全迭代
        // 迭代器是快照，不会抛出ConcurrentModificationException
        System.out.println("\n线程安全迭代:");
        for (String item : cowList) {
            System.out.println("  " + item);
        }

        // 【4】适用场景
        System.out.println("\n--- 适用场景 ---");
        System.out.println("✓ 读多写少的并发场景");
        System.out.println("✓ 事件监听器列表");
        System.out.println("✓ 配置信息缓存");
        System.out.println("✗ 不适合频繁写入的场景");

        // 【5】性能特点
        System.out.println("\n--- 性能特点 ---");
        System.out.println("读操作: O(1)，无锁，极快");
        System.out.println("写操作: O(n)，需要复制数组，较慢");
        System.out.println("内存: 写操作时会占用双倍内存");
    }

    // ================================================
    // 七、List常用操作
    // ================================================
    public static void listOperations() {
        System.out.println("--- List常用操作 ---");

        List<String> list = new ArrayList<>();

        // 【1】添加元素
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(1, "X");  // 在索引1处插入
        // list → [A, X, B, C]
        System.out.println("添加后: " + list);

        // 【2】批量添加
        list.addAll(Arrays.asList("D", "E"));
        // list → [A, X, B, C, D, E]
        System.out.println("批量添加: " + list);

        // 【3】获取元素
        // list.get(0) → "A"
        System.out.println("get(0): " + list.get(0));

        // 【4】修改元素
        list.set(1, "Y");
        // list → [A, Y, B, C, D, E]
        System.out.println("set(1, Y): " + list);

        // 【5】删除元素
        list.remove(0);  // 按索引删除
        // list → [Y, B, C, D, E]
        System.out.println("remove(0): " + list);

        list.remove("C");  // 按对象删除
        // list → [Y, B, D, E]
        System.out.println("remove(C): " + list);

        // 【6】批量删除
        list.removeAll(Arrays.asList("B", "D"));
        // list → [Y, E]
        System.out.println("批量删除: " + list);

        // 【7】保留交集
        List<String> list2 = new ArrayList<>(Arrays.asList("E", "F", "G"));
        list.retainAll(list2);
        // list → [E]
        System.out.println("保留交集: " + list);

        // 【8】判断操作
        System.out.println("\n判断操作:");
        System.out.println("isEmpty(): " + list.isEmpty());
        System.out.println("contains(E): " + list.contains("E"));
        System.out.println("size(): " + list.size());

        // 【9】截取子列表
        List<String> original = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
        List<String> subList = original.subList(1, 4);
        // subList → [B, C, D]
        System.out.println("\n子列表[1,4): " + subList);

        // 注意：subList是原列表的视图，修改会影响原列表
        subList.set(0, "X");
        // original → [A, X, C, D, E]
        System.out.println("修改子列表后原列表: " + original);

        // 【10】清空列表
        list.clear();
        System.out.println("清空后: " + list);
    }

    // ================================================
    // 八、List遍历方式
    // ================================================
    public static void listTraversal() {
        System.out.println("--- List遍历方式 ---");

        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));

        // 【方式1】普通for循环（可通过索引访问）
        System.out.print("1. 普通for循环: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        // 【方式2】增强for循环（简洁）
        System.out.print("2. 增强for循环: ");
        for (String item : list) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 【方式3】Iterator迭代器（可安全删除）
        System.out.print("3. Iterator: ");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // 【方式4】ListIterator（可双向遍历）
        System.out.print("4. ListIterator正向: ");
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();

        System.out.print("   ListIterator反向: ");
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();

        // 【方式5】forEach方法（JDK8+）
        System.out.print("5. forEach: ");
        list.forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 【方式6】Stream API（JDK8+）
        System.out.print("6. Stream: ");
        list.stream().forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 【方式7】并行Stream（大数据量）
        System.out.print("7. Parallel Stream: ");
        list.parallelStream().forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 【遍历时删除元素】
        System.out.println("\n--- 遍历时删除 ---");
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        // 错误方式：会抛出ConcurrentModificationException
        // for (Integer num : nums) {
        //     if (num % 2 == 0) nums.remove(num);
        // }

        // 正确方式1：使用Iterator
        Iterator<Integer> it = nums.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) {
                it.remove();  // 安全删除
            }
        }
        // nums → [1, 3, 5]
        System.out.println("Iterator删除偶数: " + nums);

        // 正确方式2：使用removeIf（JDK8+）
        nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        nums.removeIf(n -> n % 2 == 0);
        // nums → [1, 3, 5]
        System.out.println("removeIf删除偶数: " + nums);

        // 正确方式3：使用Stream过滤
        nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> filtered = nums.stream()
                                     .filter(n -> n % 2 != 0)
                                     .collect(Collectors.toList());
        // filtered → [1, 3, 5]
        System.out.println("Stream过滤: " + filtered);
    }

    // ================================================
    // 九、List排序
    // ================================================
    public static void listSort() {
        System.out.println("--- List排序 ---");

        // 【1】自然排序
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        Collections.sort(nums);
        // nums → [1, 2, 3, 5, 8, 9]
        System.out.println("自然排序: " + nums);

        // 【2】降序排序
        Collections.sort(nums, Collections.reverseOrder());
        // nums → [9, 8, 5, 3, 2, 1]
        System.out.println("降序排序: " + nums);

        // 【3】自定义排序（Lambda）
        List<String> names = new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob"));
        Collections.sort(names, (a, b) -> a.length() - b.length());
        // names → [Bob, Alice, Charlie]
        System.out.println("按长度排序: " + names);

        // 【4】List.sort()方法（JDK8+）
        List<Integer> nums2 = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        nums2.sort(Integer::compareTo);
        // nums2 → [1, 2, 5, 8, 9]
        System.out.println("List.sort(): " + nums2);

        // 【5】Stream排序
        List<String> sorted = names.stream()
                                   .sorted(Comparator.comparingInt(String::length))
                                   .collect(Collectors.toList());
        // sorted → [Bob, Alice, Charlie]
        System.out.println("Stream排序: " + sorted);

        // 【6】复杂对象排序
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 20));
        people.add(new Person("Charlie", 30));

        // 按年龄排序
        people.sort(Comparator.comparingInt(p -> p.age));
        System.out.println("\n按年龄排序:");
        people.forEach(p -> System.out.println("  " + p.name + ": " + p.age));

        // 按姓名降序
        people.sort(Comparator.comparing((Person p) -> p.name).reversed());
        System.out.println("按姓名降序:");
        people.forEach(p -> System.out.println("  " + p.name + ": " + p.age));

        // 【7】多条件排序
        people.sort(Comparator.comparingInt((Person p) -> p.age)
                              .thenComparing(p -> p.name));
        System.out.println("多条件排序（年龄+姓名）:");
        people.forEach(p -> System.out.println("  " + p.name + ": " + p.age));
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
    // 十、List查找
    // ================================================
    public static void listSearch() {
        System.out.println("--- List查找 ---");

        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "C"));

        // 【1】indexOf - 查找第一次出现的位置
        // list.indexOf("C") → 2
        System.out.println("indexOf(C): " + list.indexOf("C"));
        // list.indexOf("Z") → -1 （不存在）
        System.out.println("indexOf(Z): " + list.indexOf("Z"));

        // 【2】lastIndexOf - 查找最后一次出现的位置
        // list.lastIndexOf("C") → 5
        System.out.println("lastIndexOf(C): " + list.lastIndexOf("C"));

        // 【3】contains - 判断是否包含
        // list.contains("D") → true
        System.out.println("contains(D): " + list.contains("D"));

        // 【4】自定义对象查找
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 20));
        people.add(new Person("Charlie", 30));

        // 查找名字为Bob的人
        Person found = people.stream()
                            .filter(p -> "Bob".equals(p.name))
                            .findFirst()
                            .orElse(null);
        if (found != null) {
            System.out.println("找到: " + found.name + ", " + found.age);
        }

        // 【5】查找所有符合条件的元素
        List<Person> youngPeople = people.stream()
                                        .filter(p -> p.age < 28)
                                        .collect(Collectors.toList());
        System.out.println("年龄<28的人: " + youngPeople.size() + "个");

        // 【6】二分查找（需要先排序）
        List<Integer> sorted = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        int index = Collections.binarySearch(sorted, 5);
        // index → 2
        System.out.println("二分查找5: " + index);
    }

    // ================================================
    // 十一、List与Array转换
    // ================================================
    public static void listToArray() {
        System.out.println("--- List与Array转换 ---");

        // 【1】List转Array
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        // 方式1：toArray()
        Object[] array1 = list.toArray();
        System.out.println("toArray(): " + Arrays.toString(array1));

        // 方式2：toArray(T[] a) - 推荐
        String[] array2 = list.toArray(new String[0]);
        System.out.println("toArray(new String[0]): " + Arrays.toString(array2));

        // 方式3：指定大小的数组
        String[] array3 = list.toArray(new String[list.size()]);
        System.out.println("toArray(new String[size]): " + Arrays.toString(array3));

        // 【2】Array转List
        String[] arr = {"X", "Y", "Z"};

        // 方式1：Arrays.asList() - 返回固定大小的List
        List<String> fixedList = Arrays.asList(arr);
        System.out.println("\nArrays.asList(): " + fixedList);
        // fixedList.add("W");  // 抛出UnsupportedOperationException!

        // 方式2：new ArrayList<>(Arrays.asList()) - 可变List
        List<String> mutableList = new ArrayList<>(Arrays.asList(arr));
        mutableList.add("W");
        System.out.println("可变List: " + mutableList);

        // 方式3：List.of()（JDK9+）- 不可变List
        List<String> immutableList = List.of("P", "Q", "R");
        System.out.println("List.of(): " + immutableList);
        // immutableList.add("S");  // 抛出UnsupportedOperationException!

        // 【3】注意事项
        System.out.println("\n--- 注意事项 ---");
        System.out.println("1. Arrays.asList()返回的是固定大小的List");
        System.out.println("2. 修改原数组会影响asList返回的List");
        String[] source = {"A", "B"};
        List<String> view = Arrays.asList(source);
        source[0] = "X";
        System.out.println("修改数组后List: " + view);  // [X, B]
    }

    // ================================================
    // 十二、不可变List
    // ================================================
    public static void immutableList() {
        System.out.println("--- 不可变List ---");

        // 【1】Collections.unmodifiableList()
        List<String> mutable = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> unmodifiable = Collections.unmodifiableList(mutable);
        System.out.println("unmodifiableList: " + unmodifiable);
        // unmodifiable.add("D");  // 抛出UnsupportedOperationException!

        // 注意：原List仍可修改
        mutable.add("D");
        System.out.println("修改原List后: " + unmodifiable);  // [A, B, C, D]

        // 【2】List.of()（JDK9+）
        List<String> ofList = List.of("X", "Y", "Z");
        System.out.println("\nList.of(): " + ofList);
        // ofList.add("W");  // 抛出UnsupportedOperationException!
        // ofList.set(0, "A");  // 抛出UnsupportedOperationException!

        // 【3】List.copyOf()（JDK10+）
        List<String> copyOfList = List.copyOf(Arrays.asList("P", "Q", "R"));
        System.out.println("List.copyOf(): " + copyOfList);

        // 【4】不可变List的应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 常量集合");
        System.out.println("2. 配置信息");
        System.out.println("3. API返回值（防止外部修改）");
        System.out.println("4. 多线程共享数据");
    }

    // ================================================
    // 十三、List性能对比
    // ================================================
    public static void performanceComparison() {
        System.out.println("--- List性能对比 ---");

        int size = 100000;

        // 【1】尾部添加性能对比
        System.out.println("\n1. 尾部添加 " + size + " 个元素:");

        long start = System.nanoTime();
        List<Integer> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        long arrayTime = System.nanoTime() - start;
        System.out.println("   ArrayList: " + arrayTime / 1_000_000 + " ms");

        start = System.nanoTime();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        long linkedTime = System.nanoTime() - start;
        System.out.println("   LinkedList: " + linkedTime / 1_000_000 + " ms");

        // 【2】随机访问性能对比
        System.out.println("\n2. 随机访问 " + size + " 次:");

        start = System.nanoTime();
        int sum1 = 0;
        for (int i = 0; i < size; i++) {
            sum1 += arrayList.get(i);
        }
        arrayTime = System.nanoTime() - start;
        System.out.println("   ArrayList: " + arrayTime / 1_000_000 + " ms");

        start = System.nanoTime();
        int sum2 = 0;
        for (int i = 0; i < size; i++) {
            sum2 += linkedList.get(i);
        }
        linkedTime = System.nanoTime() - start;
        System.out.println("   LinkedList: " + linkedTime / 1_000_000 + " ms");

        // 【3】头部插入性能对比
        System.out.println("\n3. 头部插入10000个元素:");
        int insertSize = 10000;

        start = System.nanoTime();
        List<Integer> al = new ArrayList<>();
        for (int i = 0; i < insertSize; i++) {
            al.add(0, i);
        }
        arrayTime = System.nanoTime() - start;
        System.out.println("   ArrayList: " + arrayTime / 1_000_000 + " ms");

        start = System.nanoTime();
        List<Integer> ll = new LinkedList<>();
        for (int i = 0; i < insertSize; i++) {
            ll.add(0, i);
        }
        linkedTime = System.nanoTime() - start;
        System.out.println("   LinkedList: " + linkedTime / 1_000_000 + " ms");

        // 【总结】
        System.out.println("\n--- 性能总结 ---");
        System.out.println("ArrayList:");
        System.out.println("  ✓ 随机访问快 O(1)");
        System.out.println("  ✓ 尾部添加快 O(1)");
        System.out.println("  ✗ 头部/中间插入慢 O(n)");
        System.out.println("\nLinkedList:");
        System.out.println("  ✓ 头部/尾部插入快 O(1)");
        System.out.println("  ✗ 随机访问慢 O(n)");
        System.out.println("  ✗ 需要额外内存存储指针");
    }

    // ================================================
    // 十四、注意事项
    // ================================================
    public static void cautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】选择合适的List实现
        System.out.println("\n1. 选择List实现:");
        System.out.println("   - 大多数场景: ArrayList");
        System.out.println("   - 频繁首尾增删: LinkedList");
        System.out.println("   - 线程安全: CopyOnWriteArrayList（读多写少）");
        System.out.println("   - 线程安全: Collections.synchronizedList（通用）");

        // 【2】初始化容量
        System.out.println("\n2. 初始化容量:");
        System.out.println("   - 知道大致数量时，指定初始容量");
        System.out.println("   - 避免频繁扩容带来的性能损耗");
        List<Integer> optimized = new ArrayList<>(1000);

        // 【3】泛型的使用
        System.out.println("\n3. 泛型:");
        System.out.println("   - 始终使用泛型，避免类型转换");
        List<String> safe = new ArrayList<>();  // 推荐
        // List unsafe = new ArrayList();       // 不推荐

        // 【4】空指针检查
        System.out.println("\n4. 空指针:");
        List<String> list = null;
        // list.size();  // NullPointerException!
        if (list != null && !list.isEmpty()) {
            System.out.println("List不为空");
        }

        // 【5】subList的陷阱
        System.out.println("\n5. subList陷阱:");
        List<Integer> original = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> sub = original.subList(1, 3);
        // sub是原列表的视图，不是副本
        sub.set(0, 99);
        // original → [1, 99, 3, 4, 5]
        System.out.println("修改sub影响original: " + original);

        // 【6】并发修改异常
        System.out.println("\n6. ConcurrentModificationException:");
        System.out.println("   - 遍历时不要直接修改List");
        System.out.println("   - 使用Iterator.remove()或removeIf()");

        // 【7】Arrays.asList的陷阱
        System.out.println("\n7. Arrays.asList陷阱:");
        List<String> fixed = Arrays.asList("A", "B", "C");
        // fixed.add("D");  // UnsupportedOperationException!
        System.out.println("   - Arrays.asList返回固定大小的List");
        System.out.println("   - 需要可变List时: new ArrayList<>(Arrays.asList(...))");

        // 【8】性能优化建议
        System.out.println("\n8. 性能优化:");
        System.out.println("   - 大批量数据: 考虑使用ArrayList并预分配容量");
        System.out.println("   - 频繁查找: 考虑使用HashSet或HashMap");
        System.out.println("   - 有序且唯一: 考虑使用TreeSet");

        // 【9】内存泄漏
        System.out.println("\n9. 内存泄漏:");
        System.out.println("   - 及时清理不再使用的List");
        System.out.println("   - 大对象列表要注意GC压力");
        List<byte[]> bigList = new ArrayList<>();
        // 使用完后
        bigList.clear();
        bigList = null;  // 帮助GC回收

        // 【10】线程安全
        System.out.println("\n10. 线程安全:");
        System.out.println("   - ArrayList/LinkedList都不是线程安全的");
        System.out.println("   - 多线程环境使用: CopyOnWriteArrayList");
        System.out.println("   - 或使用: Collections.synchronizedList()");
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
    }
}
