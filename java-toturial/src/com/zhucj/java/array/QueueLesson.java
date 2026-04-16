package com.zhucj.java.array;

import java.util.*;
import java.util.concurrent.*;

/**
 * =====================================================
 * Java Queue（队列）详解
 * =====================================================
 *
 * 【一、Queue是什么】
 * ------------------
 * Queue是 FIFO（先进先出）的数据结构。
 * - 元素按插入顺序处理
 * - 从尾部添加，从头部移除
 * - 常用于任务调度、缓冲等场景
 *
 * 【二、Queue的主要实现类】
 * -----------------------
 * 1. LinkedList        - 双向链表实现，可用作队列/双端队列/栈
 * 2. ArrayDeque        - 数组实现的双端队列，性能优于LinkedList
 * 3. PriorityQueue     - 基于堆的优先队列
 * 4. ArrayBlockingQueue - 有界阻塞队列
 * 5. LinkedBlockingQueue - 可选有界的阻塞队列
 * 6. PriorityBlockingQueue - 无界阻塞优先队列
 * 7. DelayQueue        - 延迟队列
 * 8. SynchronousQueue  - 同步队列（不存储元素）
 *
 * 【三、Queue vs Stack】
 * --------------------
 * Queue: FIFO（先进先出），从尾部入，从头部出
 * Stack: LIFO（后进先出），从顶部入，从顶部出
 *
 * =====================================================
 */
public class QueueLesson {

    public static void main(String[] args) {
        System.out.println("========== Queue基础 ==========");
        queueBasics();

        System.out.println("\n========== LinkedList作为Queue ==========");
        linkedListAsQueue();

        System.out.println("\n========== ArrayDeque详解 ==========");
        arrayDequeDemo();

        System.out.println("\n========== PriorityQueue详解 ==========");
        priorityQueueDemo();

        System.out.println("\n========== ArrayBlockingQueue详解 ==========");
        arrayBlockingQueueDemo();

        System.out.println("\n========== LinkedBlockingQueue详解 ==========");
        linkedBlockingQueueDemo();

        System.out.println("\n========== PriorityBlockingQueue详解 ==========");
        priorityBlockingQueueDemo();

        System.out.println("\n========== DelayQueue详解 ==========");
        delayQueueDemo();

        System.out.println("\n========== SynchronousQueue详解 ==========");
        synchronousQueueDemo();

        System.out.println("\n========== Deque（双端队列）详解 ==========");
        dequeDemo();

        System.out.println("\n========== Queue遍历 ==========");
        queueTraversal();

        System.out.println("\n========== 注意事项 ==========");
        cautions();
    }

    // ================================================
    // 一、Queue基础
    // ================================================
    public static void queueBasics() {
        System.out.println("--- Queue基本概念 ---");

        // 【1】创建Queue
        Queue<String> queue = new LinkedList<>();

        // 【2】添加元素（尾部）
        queue.offer("First");   // 推荐，失败返回false
        queue.offer("Second");
        queue.offer("Third");
        // queue → [First, Second, Third]
        System.out.println("添加后: " + queue);

        // 【3】查看队首（不移除）
        // queue.peek() → "First"
        System.out.println("队首元素: " + queue.peek());

        // 【4】移除队首
        // queue.poll() → "First"
        System.out.println("移除队首: " + queue.poll());
        // queue → [Second, Third]
        System.out.println("移除后: " + queue);

        // 【5】Queue大小
        // queue.size() → 2
        System.out.println("Queue大小: " + queue.size());

        // 【6】判断是否为空
        System.out.println("是否为空: " + queue.isEmpty());

        // 【7】两种操作方式对比
        System.out.println("\n--- 两种操作方式 ---");
        Queue<Integer> q = new LinkedList<>();

        // 方式1：抛出异常
        try {
            q.add(1);      // 添加，失败抛异常
            q.element();   // 查看队首，空时抛异常
            q.remove();    // 移除，空时抛异常
        } catch (Exception e) {
            System.out.println("异常方式: " + e.getClass().getSimpleName());
        }

        // 方式2：返回特殊值（推荐）
        q.offer(1);        // 添加，失败返回false
        q.peek();          // 查看队首，空时返回null
        q.poll();          // 移除，空时返回null
        System.out.println("推荐方式更安全");
    }

    // ================================================
    // 二、LinkedList作为Queue
    // ================================================
    public static void linkedListAsQueue() {
        System.out.println("--- LinkedList作为Queue ---");

        // 【LinkedList的特点】
        // 1. 可以实现Queue接口
        // 2. 也可以实现Deque接口（双端队列）
        // 3. 还可以作为Stack使用
        // 4. 非线程安全

        Queue<String> queue = new LinkedList<>();

        // 【1】基本队列操作
        queue.offer("Task1");
        queue.offer("Task2");
        queue.offer("Task3");
        System.out.println("队列: " + queue);

        // 【2】FIFO特性
        System.out.println("出队顺序:");
        while (!queue.isEmpty()) {
            System.out.println("  " + queue.poll());
        }

        // 【3】作为双端队列
        Deque<String> deque = new LinkedList<>();
        deque.offerFirst("Front");
        deque.offerLast("Back");
        System.out.println("\n双端队列: " + deque);

        // 【4】作为栈
        Deque<String> stack = new LinkedList<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println("栈: " + stack);
        System.out.println("弹栈: " + stack.pop());
    }

    // ================================================
    // 三、ArrayDeque详解
    // ================================================
    public static void arrayDequeDemo() {
        System.out.println("--- ArrayDeque详解 ---");

        // 【ArrayDeque的特点】
        // 1. 基于循环数组实现
        // 2. 双端队列（可从两端操作）
        // 3. 不允许null元素
        // 4. 非线程安全
        // 5. 性能优于LinkedList（缓存友好）

        // 【1】创建ArrayDeque
        ArrayDeque<String> deque = new ArrayDeque<>();

        // 【2】指定初始容量
        ArrayDeque<String> customDeque = new ArrayDeque<>(100);
        System.out.println("初始容量: 100");

        // 【3】作为队列使用（FIFO）
        deque.offer("Task1");
        deque.offer("Task2");
        deque.offer("Task3");
        System.out.println("\n队列模式: " + deque);
        System.out.println("出队: " + deque.poll());

        // 【4】作为栈使用（LIFO）- 推荐替代Stack
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println("\n栈模式: " + stack);
        System.out.println("弹栈: " + stack.pop());

        // 【5】双端操作
        ArrayDeque<String> doubleEnd = new ArrayDeque<>();
        doubleEnd.offerFirst("Front1");
        doubleEnd.offerFirst("Front2");
        doubleEnd.offerLast("Back1");
        doubleEnd.offerLast("Back2");
        // doubleEnd → [Front2, Front1, Back1, Back2]
        System.out.println("\n双端操作: " + doubleEnd);

        // 【6】获取但不移除
        System.out.println("第一个: " + doubleEnd.peekFirst());
        System.out.println("最后一个: " + doubleEnd.peekLast());

        // 【7】移除并返回
        System.out.println("移除第一个: " + doubleEnd.pollFirst());
        System.out.println("移除最后一个: " + doubleEnd.pollLast());
        System.out.println("剩余: " + doubleEnd);

        // 【8】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. BFS广度优先搜索");
        System.out.println("2. 滑动窗口算法");
        System.out.println("3. LRU缓存实现");
        System.out.println("4. 替代Stack类（更优选择）");
    }

    // ================================================
    // 四、PriorityQueue详解
    // ================================================
    public static void priorityQueueDemo() {
        System.out.println("--- PriorityQueue详解 ---");

        // 【PriorityQueue的特点】
        // 1. 基于二叉堆实现
        // 2. 元素按优先级排序
        // 3. 不允许null元素
        // 4. 非线程安全
        // 5. 头部始终是最小（或最大）元素

        // 【1】自然排序（最小堆）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(8);
        minHeap.offer(1);
        minHeap.offer(9);
        // 头部始终是最小值
        System.out.println("最小堆: " + minHeap);
        System.out.println("最小值: " + minHeap.peek());

        // 出队顺序：从小到大
        System.out.print("出队顺序: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();

        // 【2】最大堆（自定义Comparator）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(5);
        maxHeap.offer(2);
        maxHeap.offer(8);
        maxHeap.offer(1);
        System.out.println("\n最大堆: " + maxHeap);
        System.out.println("最大值: " + maxHeap.peek());

        // 【3】自定义对象排序
        PriorityQueue<Task> tasks = new PriorityQueue<>(Comparator.comparingInt(t -> t.priority));
        tasks.offer(new Task("Low", 3));
        tasks.offer(new Task("High", 1));
        tasks.offer(new Task("Medium", 2));

        System.out.println("\n任务优先级队列:");
        while (!tasks.isEmpty()) {
            Task task = tasks.poll();
            System.out.println("  " + task.name + " (优先级: " + task.priority + ")");
        }

        // 【4】不允许null
        try {
            minHeap.offer(null);  // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("\n不允许null: NullPointerException");
        }

        // 【5】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 任务调度（按优先级执行）");
        System.out.println("2. Dijkstra最短路径算法");
        System.out.println("3. Huffman编码");
        System.out.println("4. Top K问题");
        System.out.println("5. 合并K个有序链表");
    }

    static class Task {
        String name;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // ================================================
    // 五、ArrayBlockingQueue详解
    // ================================================
    public static void arrayBlockingQueueDemo() {
        System.out.println("--- ArrayBlockingQueue详解 ---");

        // 【ArrayBlockingQueue的特点】
        // 1. 有界阻塞队列
        // 2. 基于数组实现
        // 3. 线程安全
        // 4. 生产者-消费者模式的理想选择

        // 【1】创建有界队列
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        // 【2】基本操作
        queue.offer("Task1");
        queue.offer("Task2");
        queue.offer("Task3");
        System.out.println("队列内容: " + queue);
        System.out.println("队列容量: " + queue.remainingCapacity());

        // 【3】队列满时的行为
        boolean added = queue.offer("Task4");  // 返回false，不阻塞
        System.out.println("队列满时offer: " + added);

        // 【4】阻塞操作（需要在多线程环境演示）
        System.out.println("\n--- 阻塞操作 ---");
        System.out.println("put(): 队列满时阻塞等待");
        System.out.println("take(): 队列空时阻塞等待");

        // 【5】超时操作
        try {
            boolean result = queue.offer("Task4", 1, TimeUnit.SECONDS);
            System.out.println("超时offer结果: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 【6】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 生产者-消费者模式");
        System.out.println("2. 线程池任务队列");
        System.out.println("3. 限流控制");
        System.out.println("4. 消息缓冲");
    }

    // ================================================
    // 六、LinkedBlockingQueue详解
    // ================================================
    public static void linkedBlockingQueueDemo() {
        System.out.println("--- LinkedBlockingQueue详解 ---");

        // 【LinkedBlockingQueue的特点】
        // 1. 可选有界的阻塞队列
        // 2. 基于链表实现
        // 3. 线程安全
        // 4. 吞吐量通常高于ArrayBlockingQueue

        // 【1】创建无界队列（默认Integer.MAX_VALUE）
        LinkedBlockingQueue<String> unbounded = new LinkedBlockingQueue<>();
        System.out.println("无界队列容量: " + unbounded.remainingCapacity());

        // 【2】创建有界队列
        LinkedBlockingQueue<String> bounded = new LinkedBlockingQueue<>(100);
        System.out.println("有界队列容量: " + bounded.remainingCapacity());

        // 【3】基本操作
        bounded.offer("Task1");
        bounded.offer("Task2");
        System.out.println("队列内容: " + bounded);

        // 【4】与ArrayBlockingQueue对比
        System.out.println("\n--- vs ArrayBlockingQueue ---");
        System.out.println("LinkedBlockingQueue:");
        System.out.println("  ✓ 吞吐量更高（两把锁）");
        System.out.println("  ✓ 可选有界");
        System.out.println("  ✗ 内存占用稍大");
        System.out.println("\nArrayBlockingQueue:");
        System.out.println("  ✓ 内存占用小");
        System.out.println("  ✓ 必须指定容量");
        System.out.println("  ✗ 吞吐量稍低（一把锁）");

        // 【5】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. Executors.newFixedThreadPool的默认队列");
        System.out.println("2. 高吞吐量的生产者-消费者");
        System.out.println("3. 日志系统");
    }

    // ================================================
    // 七、PriorityBlockingQueue详解
    // ================================================
    public static void priorityBlockingQueueDemo() {
        System.out.println("--- PriorityBlockingQueue详解 ---");

        // 【PriorityBlockingQueue的特点】
        // 1. 无界阻塞优先队列
        // 2. 线程安全
        // 3. 元素按优先级排序
        // 4. 不允许null

        // 【1】创建优先队列
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        queue.offer(5);
        queue.offer(2);
        queue.offer(8);
        queue.offer(1);

        System.out.println("优先队列: " + queue);
        System.out.println("最高优先级: " + queue.peek());

        // 【2】阻塞取出
        System.out.print("出队顺序: ");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();

        // 【3】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 多线程任务调度");
        System.out.println("2. 实时系统中的优先级处理");
        System.out.println("3. 事件驱动系统");
    }

    // ================================================
    // 八、DelayQueue详解
    // ================================================
    public static void delayQueueDemo() {
        System.out.println("--- DelayQueue详解 ---");

        // 【DelayQueue的特点】
        // 1. 无界阻塞延迟队列
        // 2. 元素必须实现Delayed接口
        // 3. 只有到期元素才能被取出
        // 4. 线程安全

        // 【1】创建延迟队列
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();

        // 【2】添加延迟任务
        delayQueue.offer(new DelayedTask("Task1", 2, TimeUnit.SECONDS));
        delayQueue.offer(new DelayedTask("Task2", 1, TimeUnit.SECONDS));
        delayQueue.offer(new DelayedTask("Task3", 3, TimeUnit.SECONDS));

        System.out.println("添加了3个延迟任务");

        // 【3】取出到期任务
        System.out.println("\n等待任务到期...");
        long start = System.currentTimeMillis();
        while (!delayQueue.isEmpty()) {
            try {
                DelayedTask task = delayQueue.take();  // 阻塞直到有到期任务
                long elapsed = System.currentTimeMillis() - start;
                System.out.println("经过" + elapsed + "ms: " + task.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 【4】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 定时任务调度");
        System.out.println("2. 缓存过期清理");
        System.out.println("3. 订单超时取消");
        System.out.println("4. 会话超时管理");
    }

    static class DelayedTask implements Delayed {
        private final String name;
        private final long expireTime;

        DelayedTask(String name, long delay, TimeUnit unit) {
            this.name = name;
            this.expireTime = System.currentTimeMillis() + unit.toMillis(delay);
        }

        public String getName() {
            return name;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expireTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS),
                              o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // ================================================
    // 九、SynchronousQueue详解
    // ================================================
    public static void synchronousQueueDemo() {
        System.out.println("--- SynchronousQueue详解 ---");

        // 【SynchronousQueue的特点】
        // 1. 不存储元素的阻塞队列
        // 2. 每个插入操作必须等待另一个线程的移除
        // 3. 每个移除操作必须等待另一个线程的插入
        // 4. 线程安全

        // 【1】创建SynchronousQueue
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        // 【2】特性说明
        System.out.println("SynchronousQueue不存储元素");
        System.out.println("put和take必须配对执行");
        System.out.println("适合手递手传递数据");

        // 【3】公平模式
        SynchronousQueue<String> fairQueue = new SynchronousQueue<>(true);
        System.out.println("\n公平模式: 按FIFO顺序等待");

        // 【4】应用场景
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. Executors.newCachedThreadPool的默认队列");
        System.out.println("2. 线程间直接传递数据");
        System.out.println("3.  rendezvous（会面点）模式");
    }

    // ================================================
    // 十、Deque（双端队列）详解
    // ================================================
    public static void dequeDemo() {
        System.out.println("--- Deque（双端队列）详解 ---");

        // 【Deque的特点】
        // 1. Double Ended Queue（双端队列）
        // 2. 可从两端添加和移除元素
        // 3. 可作为队列、栈、双端队列使用
        // 4. 主要实现：ArrayDeque、LinkedList

        Deque<String> deque = new ArrayDeque<>();

        // 【1】作为队列（FIFO）
        deque.offerLast("A");
        deque.offerLast("B");
        deque.offerLast("C");
        System.out.println("队列: " + deque);
        System.out.println("出队: " + deque.pollFirst());

        // 【2】作为栈（LIFO）
        Deque<String> stack = new ArrayDeque<>();
        stack.push("X");
        stack.push("Y");
        stack.push("Z");
        System.out.println("\n栈: " + stack);
        System.out.println("弹栈: " + stack.pop());

        // 【3】双端操作
        Deque<String> doubleEnd = new ArrayDeque<>();
        doubleEnd.offerFirst("Front");
        doubleEnd.offerLast("Back");
        System.out.println("\n双端队列: " + doubleEnd);

        // 【4】常用方法对比
        System.out.println("\n--- 方法对比 ---");
        System.out.println("操作       | 抛出异常    | 返回特殊值");
        System.out.println("-----------|------------|----------");
        System.out.println("插入头部   | addFirst   | offerFirst");
        System.out.println("插入尾部   | addLast    | offerLast");
        System.out.println("移除头部   | removeFirst| pollFirst");
        System.out.println("移除尾部   | removeLast | pollLast");
        System.out.println("查看头部   | getFirst   | peekFirst");
        System.out.println("查看尾部   | getLast    | peekLast");
    }

    // ================================================
    // 十一、Queue遍历
    // ================================================
    public static void queueTraversal() {
        System.out.println("--- Queue遍历 ---");

        Queue<String> queue = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));

        // 【方式1】Iterator（推荐，不会移除元素）
        System.out.print("1. Iterator: ");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        System.out.println("遍历后队列: " + queue);

        // 【方式2】forEach（JDK8+）
        System.out.print("2. forEach: ");
        queue.forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 【方式3】Stream API
        System.out.print("3. Stream: ");
        queue.stream().forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 【方式4】逐个poll（会清空队列）
        Queue<String> temp = new LinkedList<>(queue);
        System.out.print("4. poll遍历: ");
        while (!temp.isEmpty()) {
            System.out.print(temp.poll() + " ");
        }
        System.out.println();

        // 【注意】
        System.out.println("\n--- 注意事项 ---");
        System.out.println("1. Queue没有get(index)方法");
        System.out.println("2. 遍历时不要修改队列");
        System.out.println("3. poll/poll会移除元素");
    }

    // ================================================
    // 十二、注意事项
    // ================================================
    public static void cautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】选择合适的Queue实现
        System.out.println("\n1. 选择Queue实现:");
        System.out.println("   - 普通队列: ArrayDeque（性能最优）");
        System.out.println("   - 优先队列: PriorityQueue");
        System.out.println("   - 阻塞队列: LinkedBlockingQueue（通用）");
        System.out.println("   - 有界阻塞: ArrayBlockingQueue");
        System.out.println("   - 延迟队列: DelayQueue");
        System.out.println("   - 线程间传递: SynchronousQueue");

        // 【2】两种操作方式
        System.out.println("\n2. 操作方式:");
        System.out.println("   - 推荐使用offer/poll/peek（返回特殊值）");
        System.out.println("   - 避免使用add/remove/element（抛异常）");

        // 【3】null的处理
        System.out.println("\n3. null处理:");
        System.out.println("   - LinkedList允许null");
        System.out.println("   - ArrayDeque/PriorityQueue不允许null");
        System.out.println("   - 阻塞队列都不允许null");

        // 【4】线程安全
        System.out.println("\n4. 线程安全:");
        System.out.println("   - LinkedList/ArrayDeque/PriorityQueue都不是线程安全的");
        System.out.println("   - 多线程环境使用java.util.concurrent包中的队列");

        // 【5】有界vs无界
        System.out.println("\n5. 有界vs无界:");
        System.out.println("   - 生产环境建议使用有界队列");
        System.out.println("   - 无界队列可能导致OOM");
        System.out.println("   - ArrayBlockingQueue必须有界");

        // 【6】性能考虑
        System.out.println("\n6. 性能考虑:");
        System.out.println("   - ArrayDeque性能优于LinkedList");
        System.out.println("   - LinkedBlockingQueue吞吐量高于ArrayBlockingQueue");
        System.out.println("   - 避免频繁创建销毁队列");

        // 【7】常见错误
        System.out.println("\n7. 常见错误:");
        System.out.println("   - 忘记检查poll返回的null");
        System.out.println("   - 在单线程中使用阻塞队列导致死锁");
        System.out.println("   - 无界队列导致内存溢出");

        // 【8】Stack的替代
        System.out.println("\n8. Stack替代:");
        System.out.println("   - 不要使用Stack类（已过时）");
        System.out.println("   - 使用ArrayDeque作为栈");
        Deque<String> stack = new ArrayDeque<>();
        stack.push("A");
        stack.pop();
    }
}
