package com.zhucj.java.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

/**
 * =====================================================
 * Java 并发编程（Concurrency）详解
 * =====================================================
 *
 * 【一、为什么需要多线程】
 * ---------------------
 * 1. 提高CPU利用率 - 单线程等待时CPU空闲
 * 2. 提高响应速度 - 异步处理，不阻塞主线程
 * 3. 充分利用多核 - 并行执行任务
 * 4. 简化建模 - 某些问题天然适合多线程
 *
 * 【二、核心概念】
 * --------------
 * 1. 进程 vs 线程
 *    - 进程：独立的程序实例，有独立内存空间
 *    - 线程：进程内的执行单元，共享进程资源
 *
 * 2. 并发 vs 并行
 *    - 并发（Concurrency）：多个任务交替执行（单核）
 *    - 并行（Parallelism）：多个任务同时执行（多核）
 *
 * 3. 同步 vs 异步
 *    - 同步：调用者等待结果返回
 *    - 异步：调用者立即返回，结果通过回调通知
 *
 * 4. 阻塞 vs 非阻塞
 *    - 阻塞：线程等待资源时挂起
 *    - 非阻塞：线程立即返回，稍后重试
 *
 * 【三、Java线程发展史】
 * --------------------
 * JDK 1.0: Thread, Runnable（基础线程）
 * JDK 1.5: ExecutorService, Lock, Atomic（并发包）
 * JDK 7:   ForkJoinPool（分治框架）
 * JDK 8:   CompletableFuture（异步编程）
 * JDK 21:  Virtual Threads（虚拟线程）
 *
 * 常用的三种创建方式：Thread、Runnable、Callable。
 * 推荐 Runnable，Callable更适合多线程场景。
 * =====================================================
 */
public class ConcurrencyLesson {

    public static void main(String[] args) {
        try {
            System.out.println("========== 创建线程 ==========");
            createThreads();

            System.out.println("\n========== 线程状态 ==========");
            threadStates();

            System.out.println("\n========== 线程安全 ==========");
            threadSafety();

            System.out.println("\n========== synchronized ==========");
            synchronizedDemo();

            System.out.println("\n========== Lock锁 ==========");
            lockDemo();

            System.out.println("\n========== volatile ==========");
            volatileDemo();

            System.out.println("\n========== 原子类 ==========");
            atomicDemo();

            System.out.println("\n========== 线程池 ==========");
            threadPoolDemo();

            System.out.println("\n========== Callable & Future ==========");
            callableFutureDemo();

            System.out.println("\n========== CompletableFuture ==========");
            completableFutureDemo();

            System.out.println("\n========== 线程通信 ==========");
            threadCommunication();

            System.out.println("\n========== 死锁 ==========");
            deadlockDemo();

            System.out.println("\n========== ThreadLocal ==========");
            threadLocalDemo();

            System.out.println("\n========== 注意事项 ==========");
            cautions();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================================================
    // 一、创建线程
    // ================================================
    public static void createThreads() throws InterruptedException {
        System.out.println("--- 创建线程的4种方式 ---");

        // 【方式1】继承Thread类
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread-1: " + Thread.currentThread().getName());
            }
        };
        thread1.start();
        thread1.join();

        // 【方式2】实现Runnable接口（推荐）
        Runnable runnable = () -> {
            System.out.println("Runnable: " + Thread.currentThread().getName());
        };
        Thread thread2 = new Thread(runnable);
        thread2.start();
        thread2.join(); // 等待线程结束。 否则主线程会先结束，子线程会继续执行

        // 【方式3】实现Callable接口（有返回值）
        // Callable属于 java.util.concurrent 包中。
        Callable<String> callable = () -> {
            return "Callable result from " + Thread.currentThread().getName();
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread3 = new Thread(futureTask);
        thread3.start();
        try {
            System.out.println("Callable结果: " + futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread3.join();

        // 【方式4】使用线程池（最推荐）
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            System.out.println("ThreadPool: " + Thread.currentThread().getName());
        });
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        // 【对比总结】
        System.out.println("\n--- 创建方式对比 ---");
        System.out.println("1. Thread类: 简单，但不能继承其他类");
        System.out.println("2. Runnable: 推荐，解耦，可继承其他类");
        System.out.println("3. Callable: 有返回值，可抛异常");
        System.out.println("4. 线程池: 生产环境首选，资源可控");

    }

    // ================================================
    // 二、线程状态
    // ================================================
    public static void threadStates() throws InterruptedException {
        System.out.println("--- 线程6种状态 ---");

        Thread thread = new Thread(() -> {
            try {
                // RUNNABLE
                System.out.println("状态: " + Thread.currentThread().getState());
                
                // TIMED_WAITING
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // NEW - 新建
        System.out.println("NEW: " + thread.getState());

        // RUNNABLE - 运行
        thread.start();
        Thread.sleep(100);
        System.out.println("RUNNABLE: " + thread.getState());

        // TIMED_WAITING - 计时等待
        Thread.sleep(100);
        System.out.println("TIMED_WAITING: " + thread.getState());

        // TERMINATED - 终止
        thread.join();
        System.out.println("TERMINATED: " + thread.getState());

        // 【状态说明】
        System.out.println("\n--- 状态说明 ---");
        System.out.println("NEW: 新建，未启动");
        System.out.println("RUNNABLE: 运行中（包括就绪和运行）");
        System.out.println("BLOCKED: 阻塞，等待锁");
        System.out.println("WAITING: 无限期等待");
        System.out.println("TIMED_WAITING: 计时等待");
        System.out.println("TERMINATED: 终止");
    }

    // ================================================
    // 三、线程安全问题
    // ================================================
    public static void threadSafety() throws InterruptedException {
        System.out.println("--- 线程安全问题 ---");

        // 【问题演示】多个线程同时修改共享变量
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 期望结果: 20000
        // 实际结果: 可能小于20000（线程不安全）
        System.out.println("期望: 20000");
        System.out.println("实际: " + counter.getCount());
        System.out.println("问题: ++ 不是原子操作（读取-修改-写入）");

        // 【解决方案】
        System.out.println("\n--- 解决方案 ---");
        System.out.println("1. synchronized - 内置锁");
        System.out.println("2. Lock - 显式锁");
        System.out.println("3. Atomic类 - 原子操作");
        System.out.println("4. ThreadLocal - 线程隔离");
    }

    static class Counter {
        private int count = 0;

        public void increment() {
            count++;  // 非原子操作
        }

        public int getCount() {
            return count;
        }
    }

    // ================================================
    // 四、synchronized
    // ================================================
    public static void synchronizedDemo() throws InterruptedException {
        System.out.println("--- synchronized关键字 ---");

        SynchronizedCounter counter = new SynchronizedCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("synchronized结果: " + counter.getCount());

        // 【三种用法】
        System.out.println("\n--- 三种用法 ---");
        System.out.println("1. 修饰实例方法 - 锁当前对象(this)");
        System.out.println("2. 修饰静态方法 - 锁Class对象");
        System.out.println("3. 修饰代码块 - 锁指定对象");

        // 【示例】
        SyncExample example = new SyncExample();

        // 同步代码块
        example.methodWithSyncBlock();

        // 【特点】
        System.out.println("\n--- 特点 ---");
        System.out.println("✓ 自动加锁/解锁");
        System.out.println("✓ 可重入");
        System.out.println("✓ JVM优化（偏向锁、轻量级锁、重量级锁）");
        System.out.println("✗ 不可中断");
        System.out.println("✗ 无法尝试获取锁");
        System.out.println("✗ 公平性不可控");
    }

    static class SynchronizedCounter {
        private int count = 0;

        // 同步方法 - 锁this
        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    static class SyncExample {
        private final Object lock = new Object();

        // 同步代码块 - 锁lock对象
        public void methodWithSyncBlock() {
            synchronized (lock) {
                System.out.println("同步代码块执行");
            }
        }
    }

    // ================================================
    // 五、Lock锁
    // ================================================
    public static void lockDemo() throws InterruptedException {
        System.out.println("--- Lock接口 ---");

        LockCounter counter = new LockCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Lock结果: " + counter.getCount());

        // 【Lock vs synchronized】
        System.out.println("\n--- Lock vs synchronized ---");
        System.out.println("Lock优势:");
        System.out.println("  ✓ 可尝试获取锁（tryLock）");
        System.out.println("  ✓ 可中断（lockInterruptibly）");
        System.out.println("  ✓ 可设置超时");
        System.out.println("  ✓ 可实现公平锁");
        System.out.println("  ✓ 可绑定多个Condition");
        System.out.println("\nsynchronized优势:");
        System.out.println("  ✓ 简洁，不易出错");
        System.out.println("  ✓ JVM自动优化");
        System.out.println("  ✓ 无需手动释放锁");

        // 【ReadWriteLock - 读写锁】
        System.out.println("\n--- ReadWriteLock ---");
        ReadWriteCache cache = new ReadWriteCache();

        // 写线程
        Thread writer = new Thread(() -> {
            cache.put("key", "value");
            System.out.println("写入完成");
        });

        // 读线程
        Thread reader1 = new Thread(() -> {
            String value = cache.get("key");
            System.out.println("读取: " + value);
        });

        Thread reader2 = new Thread(() -> {
            String value = cache.get("key");
            System.out.println("读取: " + value);
        });

        writer.start();
        writer.join();
        reader1.start();
        reader2.start();
        reader1.join();
        reader2.join();
    }

    static class LockCounter {
        private int count = 0;
        private final ReentrantLock lock = new ReentrantLock();

        public void increment() {
            lock.lock();  // 加锁
            try {
                count++;
            } finally {
                lock.unlock();  // 必须手动解锁
            }
        }

        public int getCount() {
            return count;
        }
    }

    static class ReadWriteCache {
        private final java.util.Map<String, String> map = new java.util.HashMap<>();
        private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        private final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        private final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        public void put(String key, String value) {
            writeLock.lock();
            try {
                map.put(key, value);
            } finally {
                writeLock.unlock();
            }
        }

        public String get(String key) {
            readLock.lock();
            try {
                return map.get(key);
            } finally {
                readLock.unlock();
            }
        }
    }

    // ================================================
    // 六、volatile
    // ================================================
    public static void volatileDemo() throws InterruptedException {
        System.out.println("--- volatile关键字 ---");

        VolatileFlag flag = new VolatileFlag();

        // 线程1：修改标志
        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag.setRunning(false);
            System.out.println("writer: 设置running=false");
        });

        // 线程2：读取标志
        Thread reader = new Thread(() -> {
            while (flag.isRunning()) {
                // 空循环
            }
            System.out.println("reader: 检测到running=false，退出循环");
        });

        reader.start();
        writer.start();
        writer.join();
        reader.join();

        // 【volatile的作用】
        System.out.println("\n--- volatile保证 ---");
        System.out.println("1. 可见性 - 一个线程修改，其他线程立即可见");
        System.out.println("2. 有序性 - 禁止指令重排序");
        System.out.println("3. 不保证原子性 - ++操作仍不安全");

        // 【适用场景】
        System.out.println("\n--- 适用场景 ---");
        System.out.println("✓ 状态标志（如running）");
        System.out.println("✓ 双重检查锁定（DCL）单例");
        System.out.println("✓ 一次性安全发布");
        System.out.println("✗ 计数器等复合操作");
    }

    static class VolatileFlag {
        private volatile boolean running = true;

        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }
    }

    // ================================================
    // 七、原子类
    // ================================================
    public static void atomicDemo() throws InterruptedException {
        System.out.println("--- 原子类（Atomic） ---");

        AtomicCounter counter = new AtomicCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Atomic结果: " + counter.getCount());

        // 【常用原子类】
        System.out.println("\n--- 常用原子类 ---");
        AtomicInteger atomicInt = new AtomicInteger(0);
        atomicInt.incrementAndGet();  // ++i
        atomicInt.getAndIncrement();  // i++
        System.out.println("AtomicInteger: " + atomicInt.get());


        AtomicLong atomicLong = new AtomicLong(0);
        atomicLong.addAndGet(10);
        System.out.println("AtomicLong: " + atomicLong.get());

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        atomicBoolean.compareAndSet(false, true);  // CAS操作
        System.out.println("AtomicBoolean: " + atomicBoolean.get());

        AtomicReference<String> atomicRef = new AtomicReference<>("initial");
        atomicRef.compareAndSet("initial", "updated");
        System.out.println("AtomicReference: " + atomicRef.get());

        // 【CAS原理】
        System.out.println("\n--- CAS（Compare And Swap） ---");
        System.out.println("原理: 比较并交换，硬件级别原子操作");
        System.out.println("优点: 无锁，性能好");
        System.out.println("缺点: ABA问题，自旋消耗CPU");
    }

    static class AtomicCounter {
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();  // 原子自增
        }

        public int getCount() {
            return count.get();
        }
    }

    // ================================================
    // 八、线程池
    // ================================================
    public static void threadPoolDemo() throws InterruptedException {
        System.out.println("--- 线程池 ---");

        // 【1】创建线程池（不推荐Executors）
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,                      // 核心线程数
            4,                      // 最大线程数
            60L,                    // 空闲线程存活时间
            TimeUnit.SECONDS,       // 时间单位
            new LinkedBlockingQueue<>(10),  // 工作队列
            Executors.defaultThreadFactory(), // 线程工厂
            new ThreadPoolExecutor.CallerRunsPolicy()  // 拒绝策略
        );

        // 【2】提交任务
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("任务" + taskId + " 执行中: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 【3】关闭线程池
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        // 【线程池参数说明】
        System.out.println("\n--- 线程池7大参数 ---");
        System.out.println("1. corePoolSize: 核心线程数（常驻）");
        System.out.println("2. maximumPoolSize: 最大线程数");
        System.out.println("3. keepAliveTime: 空闲线程存活时间");
        System.out.println("4. unit: 时间单位");
        System.out.println("5. workQueue: 工作队列");
        System.out.println("6. threadFactory: 线程工厂");
        System.out.println("7. handler: 拒绝策略");

        // 【工作流程】
        System.out.println("\n--- 工作流程 ---");
        System.out.println("1. 任务到达，核心线程未满 → 创建核心线程执行");
        System.out.println("2. 核心线程已满 → 放入工作队列");
        System.out.println("3. 队列已满 → 创建非核心线程执行");
        System.out.println("4. 达到最大线程数 → 执行拒绝策略");

        // 【拒绝策略】
        System.out.println("\n--- 拒绝策略 ---");
        System.out.println("AbortPolicy: 抛异常（默认）");
        System.out.println("CallerRunsPolicy: 调用者线程执行");
        System.out.println("DiscardPolicy: 直接丢弃");
        System.out.println("DiscardOldestPolicy: 丢弃最老任务");

        // 【为什么不推荐Executors】
        System.out.println("\n--- 为什么不推荐Executors ---");
        System.out.println("Executors.newFixedThreadPool:");
        System.out.println("  ✗ 队列无界，可能OOM");
        System.out.println("Executors.newSingleThreadExecutor:");
        System.out.println("  ✗ 队列无界，可能OOM");
        System.out.println("Executors.newCachedThreadPool:");
        System.out.println("  ✗ 线程数无界，可能OOM");
        System.out.println("\n推荐: 手动创建ThreadPoolExecutor");
    }

    // ================================================
    // 九、Callable & Future
    // ================================================
    public static void callableFutureDemo() throws Exception {
        System.out.println("--- Callable & Future ---");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 【1】提交Callable任务
        Future<Integer> future1 = executor.submit(() -> {
            Thread.sleep(100);
            return 100;
        });

        Future<Integer> future2 = executor.submit(() -> {
            Thread.sleep(100);
            return 200;
        });

        // 【2】获取结果（阻塞）
        Integer result1 = future1.get();
        Integer result2 = future2.get();
        System.out.println("结果1: " + result1);
        System.out.println("结果2: " + result2);
        System.out.println("总和: " + (result1 + result2));

        // 【3】带超时的get
        try {
            Integer result = future1.get(1, TimeUnit.SECONDS);
            System.out.println("超时获取: " + result);
        } catch (TimeoutException e) {
            System.out.println("获取超时");
        }

        // 【4】取消任务
        Future<Integer> future3 = executor.submit(() -> {
            Thread.sleep(1000);
            return 300;
        });
        boolean cancelled = future3.cancel(true);  // 尝试中断
        System.out.println("任务取消: " + cancelled);

        executor.shutdown();

        // 【Future局限性】
        System.out.println("\n--- Future局限性 ---");
        System.out.println("✗ get()阻塞，无法异步回调");
        System.out.println("✗ 无法组合多个Future");
        System.out.println("✗ 无法手动完成");
        System.out.println("✓ 解决: 使用CompletableFuture");
    }

    // ================================================
    // 十、CompletableFuture
    // ================================================
    public static void completableFutureDemo() throws Exception {
        System.out.println("--- CompletableFuture ---");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 【1】异步执行
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }, executor);

        // 【2】thenApply - 转换结果
        CompletableFuture<String> transformed = future.thenApply(result -> {
            return result + " World";
        });

        // 【3】thenAccept - 消费结果
        transformed.thenAccept(result -> {
            System.out.println("结果: " + result);
        });

        // 【4】组合多个CompletableFuture
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 100, executor);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 200, executor);

        // thenCombine - 组合两个结果
        CompletableFuture<Integer> combined = future1.thenCombine(future2, (r1, r2) -> {
            return r1 + r2;
        });
        System.out.println("组合结果: " + combined.get());

        // allOf - 等待所有完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);
        allOf.get();  // 等待所有完成
        System.out.println("所有任务完成");

        // anyOf - 任一完成
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2);
        System.out.println("最快完成: " + anyOf.get());

        // 【5】异常处理
        CompletableFuture<String> withException = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Error!");
            return "OK";
        }, executor).exceptionally(ex -> {
            System.out.println("异常: " + ex.getMessage());
            return "Fallback";
        });
        System.out.println("异常处理结果: " + withException.get());

        // 【6】链式调用
        CompletableFuture<String> chain = CompletableFuture.supplyAsync(() -> "Hello", executor)
            .thenApply(s -> s + " World")
            .thenApply(String::toUpperCase)
            .thenApply(s -> s + "!");
        System.out.println("链式调用: " + chain.get());

        executor.shutdown();

        // 【优势】
        System.out.println("\n--- CompletableFuture优势 ---");
        System.out.println("✓ 非阻塞异步编程");
        System.out.println("✓ 支持函数式编程");
        System.out.println("✓ 可组合、可链式调用");
        System.out.println("✓ 完善的异常处理");
        System.out.println("✓ JDK8+推荐使用");
    }

    // ================================================
    // 十一、线程通信
    // ================================================
    public static void threadCommunication() throws InterruptedException {
        System.out.println("--- 线程通信 ---");

        // 【1】wait/notify】
        WaitNotifyExample waitNotify = new WaitNotifyExample();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                waitNotify.produce(i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                waitNotify.consume();
            }
        });

        consumer.start();
        producer.start();
        producer.join();
        consumer.join();

        // 【2】Condition】
        System.out.println("\n--- Condition ---");
        ConditionExample conditionExample = new ConditionExample();

        Thread producer2 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                conditionExample.produce(i);
            }
        });

        Thread consumer2 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                conditionExample.consume();
            }
        });

        consumer2.start();
        producer2.start();
        producer2.join();
        consumer2.join();

        // 【3】BlockingQueue】
        System.out.println("\n--- BlockingQueue ---");
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread producer3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    queue.put(i);
                    System.out.println("生产: " + i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    Integer item = queue.take();
                    System.out.println("消费: " + item);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        consumer3.start();
        producer3.start();
        producer3.join();
        consumer3.join();
    }

    static class WaitNotifyExample {
        private int data;
        private boolean hasData = false;

        public synchronized void produce(int value) {
            while (hasData) {
                try {
                    wait();  // 等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data = value;
            hasData = true;
            System.out.println("生产: " + value);
            notify();  // 通知消费者
        }

        public synchronized void consume() {
            while (!hasData) {
                try {
                    wait();  // 等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费: " + data);
            hasData = false;
            notify();  // 通知生产者
        }
    }

    static class ConditionExample {
        private int data;
        private boolean hasData = false;
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();

        public void produce(int value) {
            lock.lock();
            try {
                while (hasData) {
                    notFull.await();
                }
                data = value;
                hasData = true;
                System.out.println("生产: " + value);
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void consume() {
            lock.lock();
            try {
                while (!hasData) {
                    notEmpty.await();
                }
                System.out.println("消费: " + data);
                hasData = false;
                notFull.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    // ================================================
    // 十二、死锁
    // ================================================
    public static void deadlockDemo() throws InterruptedException {
        System.out.println("--- 死锁 ---");

        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("T1: 持有lock1，等待lock2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("T1: 获取lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("T2: 持有lock2，等待lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("T2: 获取lock1");
                }
            }
        });

        t1.start();
        t2.start();

        // 等待一段时间，检测死锁
        Thread.sleep(1000);

        System.out.println("\n--- 死锁条件 ---");
        System.out.println("1. 互斥条件 - 资源独占");
        System.out.println("2. 请求与保持 - 持有资源并请求新资源");
        System.out.println("3. 不可剥夺 - 资源不能强制释放");
        System.out.println("4. 循环等待 - 形成环路");

        System.out.println("\n--- 避免死锁 ---");
        System.out.println("1. 固定锁顺序 - 按相同顺序获取锁");
        System.out.println("2. 使用tryLock - 超时放弃");
        System.out.println("3. 减少锁粒度 - 缩小同步范围");
        System.out.println("4. 使用高级工具 - ConcurrentHashMap等");

        // 注意: 上面的代码会产生死锁，实际运行时需注释掉t1.start()和t2.start()
        System.out.println("\n提示: 上面的代码演示了死锁场景，实际运行会卡住");
    }

    // ================================================
    // 十三、ThreadLocal
    // ================================================
    public static void threadLocalDemo() {
        System.out.println("--- ThreadLocal ---");

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-1 Data");
            System.out.println("T1: " + threadLocal.get());
            threadLocal.remove();  // 防止内存泄漏
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-2 Data");
            System.out.println("T2: " + threadLocal.get());
            threadLocal.remove();  // 防止内存泄漏
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 【应用场景】
        System.out.println("\n--- 应用场景 ---");
        System.out.println("1. 数据库连接管理");
        System.out.println("2. Session管理");
        System.out.println("3. 用户上下文传递");
        System.out.println("4. SimpleDateFormat线程安全");

        // 【SimpleDateFormat线程安全示例】
        System.out.println("\n--- SimpleDateFormat线程安全 ---");
        ThreadLocal<java.text.SimpleDateFormat> dateFormat = ThreadLocal.withInitial(
            () -> new java.text.SimpleDateFormat("yyyy-MM-dd")
        );

        Thread t3 = new Thread(() -> {
            java.text.SimpleDateFormat sdf = dateFormat.get();
            System.out.println("T3格式化: " + sdf.format(new java.util.Date()));
        });

        Thread t4 = new Thread(() -> {
            java.text.SimpleDateFormat sdf = dateFormat.get();
            System.out.println("T4格式化: " + sdf.format(new java.util.Date()));
        });

        t3.start();
        t4.start();

        // 【注意事项】
        System.out.println("\n--- 注意事项 ---");
        System.out.println("⚠️ 必须调用remove()防止内存泄漏");
        System.out.println("⚠️ 线程池中使用时要特别小心");
        System.out.println("⚠️ 父子线程不共享（可用InheritableThreadLocal）");
    }

    // ================================================
    // 十四、注意事项
    // ================================================
    public static void cautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】线程安全
        System.out.println("\n1. 线程安全:");
        System.out.println("   - 优先使用不可变对象");
        System.out.println("   - 使用线程安全的集合（ConcurrentHashMap）");
        System.out.println("   - 最小化共享可变状态");

        // 【2】锁的使用
        System.out.println("\n2. 锁的使用:");
        System.out.println("   - 尽量减小同步范围");
        System.out.println("   - 避免在锁内执行耗时操作");
        System.out.println("   - 优先使用高级并发工具");

        // 【3】线程池
        System.out.println("\n3. 线程池:");
        System.out.println("   - 不要使用Executors创建线程池");
        System.out.println("   - 根据任务类型选择合适的队列");
        System.out.println("   - 合理设置线程数（CPU密集型: N+1, IO密集型: 2N）");
        System.out.println("   - 记得关闭线程池");

        // 【4】volatile
        System.out.println("\n4. volatile:");
        System.out.println("   - 只保证可见性和有序性");
        System.out.println("   - 不保证原子性");
        System.out.println("   - 适合状态标志，不适合计数器");

        // 【5】ThreadLocal
        System.out.println("\n5. ThreadLocal:");
        System.out.println("   - 必须在finally中调用remove()");
        System.out.println("   - 线程池中使用时要清理");
        System.out.println("   - 避免内存泄漏");

        // 【6】中断处理
        System.out.println("\n6. 中断处理:");
        System.out.println("   - 不要吞掉InterruptedException");
        System.out.println("   - 恢复中断状态: Thread.currentThread().interrupt()");
        System.out.println("   - 定期检查isInterrupted()");

        // 【7】守护线程
        System.out.println("\n7. 守护线程:");
        System.out.println("   - 守护线程随JVM退出而终止");
        System.out.println("   - 不要在守护线程中执行重要任务");
        System.out.println("   - 设置: thread.setDaemon(true)");

        // 【8】最佳实践
        System.out.println("\n8. 最佳实践:");
        System.out.println("   - 优先使用并发包工具（java.util.concurrent）");
        System.out.println("   - 避免直接使用Thread");
        System.out.println("   - 使用CompletableFuture进行异步编程");
        System.out.println("   - 使用线程池管理线程生命周期");
        System.out.println("   - 编写单元测试验证线程安全");

        // 【9】性能考虑
        System.out.println("\n9. 性能考虑:");
        System.out.println("   - 减少锁竞争");
        System.out.println("   - 使用读写锁优化读多写少场景");
        System.out.println("   - 考虑无锁数据结构（Atomic类）");
        System.out.println("   - 避免频繁创建销毁线程");

        // 【10】调试技巧
        System.out.println("\n10. 调试技巧:");
        System.out.println("   - 使用jstack查看线程栈");
        System.out.println("   - 使用VisualVM监控线程");
        System.out.println("   - 添加日志记录线程行为");
        System.out.println("   - 使用Thread.setName()标识线程");
    }
}
