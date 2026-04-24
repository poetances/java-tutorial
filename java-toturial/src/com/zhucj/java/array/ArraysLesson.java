package com.zhucj.java.array;

import java.util.Arrays;

/**
 * =====================================================
 * Java 数组（Array）详解
 * =====================================================
 *
 * 【一、数组是什么】
 * -----------------
 * 数组是相同类型元素的固定长度有序集合。
 * - 数组是对象，存储在堆内存中
 * - 数组长度固定，创建后不可改变
 * - 数组可以存储基本类型和引用类型
 *
 * 【二、数组的特点】
 * -----------------
 * 1. 固定长度：数组创建时指定长度，不可动态扩展
 * 2. 有序集合：元素按索引顺序存储
 * 3. 索引从0开始
 * 4. 连续内存：数组元素在内存中是连续存储的
 * 5. 随机访问：可以根据索引快速访问任意元素 O(1)
 *
 * =====================================================
 */
public class ArraysLesson {

    public static void main(String[] args) {
        System.out.println("========== 数组基础 ==========");
        arrayBasics();

        System.out.println("\n========== 数组创建 ==========");
        arrayCreation();

        System.out.println("\n========== 数组初始化 ==========");
        arrayInitialization();

        System.out.println("\n========== 数组访问与遍历 ==========");
        arrayAccessAndTraversal();

        System.out.println("\n========== 数组默认值 ==========");
        arrayDefaultValues();

        System.out.println("\n========== 二维数组 ==========");
        twoDimensionalArray();

        System.out.println("\n========== 不规则数组 ==========");
        raggedArray();

        System.out.println("\n========== Arrays工具类 ==========");
        arraysUtility();

        System.out.println("\n========== Arrays常用方法 ==========");
        arraysMethods();

        System.out.println("\n========== 数组复制 ==========");
        arrayCopy();

        System.out.println("\n========== 数组排序 ==========");
        arraySort();

        System.out.println("\n========== 数组查找 ==========");
        arraySearch();

        System.out.println("\n========== 数组填充 ==========");
        arrayFill();

        System.out.println("\n========== 数组转String ==========");
        arrayToString();

        System.out.println("\n========== Arrays.equals vs == ==========");
        arrayEquals();

        System.out.println("\n========== 注意事项 ==========");
        arrayCautions();
    }

    // ================================================
    // 一、数组基础
    // ================================================
    public static void arrayBasics() {
        System.out.println("--- 数组基本概念 ---");

        int[] arr = new int[3];
        // arr instanceof Object → true        // 数组是对象
        System.out.println("数组是对象: " + (arr instanceof Object));
        // arr.getClass().getName() → [I        // [I 表示int数组
        System.out.println("数组类型: " + arr.getClass().getName());
        // arr.length → 3                       // 数组长度
        System.out.println("数组长度: " + arr.length);

        // 数组的引用
        int[] arr1 = new int[3];
        int[] arr2 = arr1;  // arr2指向同一个数组对象
        arr1[0] = 100;
        // arr2[0] → 100                        // 共享同一个数组
        System.out.println("arr2[0]: " + arr2[0]);

        // 数组的索引
        int[] nums = {10, 20, 30, 40, 50};
        // 索引 0 → 10
        // 索引 1 → 20
        // 索引 2 → 30
        // 索引 3 → 40
        // 索引 4 → 50
        System.out.println("索引0=" + nums[0] + ", 索引1=" + nums[1] + ", 索引4=" + nums[4]);
    }

    // ================================================
    // 二、数组创建
    // ================================================
    public static void arrayCreation() {
        System.out.println("--- 数组创建方式 ---");

        // 【方式1】动态创建
        int[] arr1 = new int[5];
        // arr1.length → 5
        System.out.println("动态创建: length=" + arr1.length);

        // 【方式2】静态创建
        int[] arr2 = new int[]{1, 2, 3, 4, 5};
        // Arrays.toString(arr2) → [1, 2, 3, 4, 5]
        System.out.println("静态创建: " + java.util.Arrays.toString(arr2));

        // 【方式3】简写形式
        int[] arr3 = {10, 20, 30};
        // Arrays.toString(arr3) → [10, 20, 30]
        System.out.println("简写形式: " + java.util.Arrays.toString(arr3));

        // 【方式4】字符数组
        char[] chars = {'H', 'e', 'l', 'l', 'o'};
        // new String(chars) → "Hello"
        System.out.println("字符数组: " + new String(chars));

        // 【方式5】字符串数组
        String[] names = {"Alice", "Bob", "Charlie"};
        // Arrays.toString(names) → [Alice, Bob, Charlie]
        System.out.println("String数组: " + java.util.Arrays.toString(names));

        // 【方式6】boolean数组
        boolean[] flags = {true, false, true};
        // Arrays.toString(flags) → [true, false, true]
        System.out.println("boolean数组: " + java.util.Arrays.toString(flags));

        // 【方式7】二维数组
        int[][] matrix = new int[3][4];  // 3行4列
        // matrix.length → 3, matrix[0].length → 4
        System.out.println("二维数组: " + matrix.length + "x" + matrix[0].length);

        // 【长度0的空数组】
        int[] empty = new int[0];
        // empty.length → 0
        System.out.println("空数组: length=" + empty.length);
    }

    // ================================================
    // 三、数组初始化
    // ================================================
    public static void arrayInitialization() {
        System.out.println("--- 数组初始化 ---");

        // 【1】默认初始化
        int[] arr1 = new int[3];
        // arr1 → [0, 0, 0]
        System.out.println("默认初始化: " + java.util.Arrays.toString(arr1));

        // 【2】静态初始化
        int[] arr2 = {1, 2, 3, 4, 5};
        // arr2 → [1, 2, 3, 4, 5]
        System.out.println("静态初始化: " + java.util.Arrays.toString(arr2));

        // 【3】动态初始化
        int[] arr3 = new int[5];
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = (i + 1) * 10;
        }
        // arr3 → [10, 20, 30, 40, 50]
        System.out.println("动态初始化: " + java.util.Arrays.toString(arr3));

        // 【4】匿名数组
        printArray(new int[]{7, 8, 9});
    }

    public static void printArray(int[] arr) {
        // 匿名数组: [7, 8, 9]
        System.out.println("匿名数组: " + java.util.Arrays.toString(arr));
    }

    // ================================================
    // 四、数组访问与遍历
    // ================================================
    public static void arrayAccessAndTraversal() {
        System.out.println("--- 数组访问与遍历 ---");

        int[] nums = {10, 20, 30, 40, 50};

        // 【方式1】普通for循环
        System.out.print("普通for循环: ");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");  // 10 20 30 40 50
        }
        System.out.println();

        // 【方式2】增强for循环
        System.out.print("增强for循环: ");
        for (int num : nums) {
            System.out.print(num + " ");  // 10 20 30 40 50
        }
        System.out.println();

        // 【方式3】Stream遍历
        System.out.print("Stream遍历: ");
        java.util.Arrays.stream(nums).forEach(n -> System.out.print(n + " "));  // 10 20 30 40 50
        System.out.println();

        // 【反向遍历】
        System.out.print("反向遍历: ");
        for (int i = nums.length - 1; i >= 0; i--) {
            System.out.print(nums[i] + " ");  // 50 40 30 20 10
        }
        System.out.println();

        // 【间隔遍历】
        System.out.print("间隔遍历(偶索引): ");
        for (int i = 0; i < nums.length; i += 2) {
            System.out.print(nums[i] + " ");  // 10 30 50
        }
        System.out.println();
    }

    // ================================================
    // 五、数组默认值
    // ================================================
    public static void arrayDefaultValues() {
        System.out.println("--- 数组默认值 ---");

        byte[] bArr = new byte[1];
        short[] sArr = new short[1];
        int[] iArr = new int[1];
        long[] lArr = new long[1];
        float[] fArr = new float[1];
        double[] dArr = new double[1];
        char[] cArr = new char[1];
        boolean[] boolArr = new boolean[1];
        String[] strArr = new String[1];

        // byte[]    → 0
        System.out.println("byte[]    默认值: " + bArr[0]);
        // short[]   → 0
        System.out.println("short[]   默认值: " + sArr[0]);
        // int[]     → 0
        System.out.println("int[]     默认值: " + iArr[0]);
        // long[]    → 0
        System.out.println("long[]    默认值: " + lArr[0]);
        // float[]   → 0.0
        System.out.println("float[]   默认值: " + fArr[0]);
        // double[]  → 0.0
        System.out.println("double[]  默认值: " + dArr[0]);
        // char[]    → '' (空字符)
        System.out.println("char[]    默认值: '" + cArr[0] + "'");
        // boolean[] → false
        System.out.println("boolean[] 默认值: " + boolArr[0]);
        // String[]  → null
        System.out.println("String[]  默认值: " + strArr[0]);
    }

    // ================================================
    // 六、二维数组
    // ================================================
    public static void twoDimensionalArray() {
        System.out.println("--- 二维数组 ---");

        // 【方式1】直接创建
        int[][] matrix1 = new int[3][4];
        // matrix1.length → 3, matrix1[0].length → 4
        System.out.println("matrix1尺寸: " + matrix1.length + "x" + matrix1[0].length);

        // 【方式2】先创建外层数组
        int[][] matrix2 = new int[3][];
        matrix2[0] = new int[2];
        matrix2[1] = new int[3];
        matrix2[2] = new int[4];
        // matrix2[0].length → 2, matrix2[1].length → 3, matrix2[2].length → 4

        // 【方式3】静态初始化
        int[][] matrix3 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        // matrix3[1][1] → 5
        System.out.println("matrix3[1][1]: " + matrix3[1][1]);

        // 【遍历二维数组】
        System.out.println("遍历二维数组:");
        // 输出:
        // 1 2 3
        // 4 5 6
        // 7 8 9
        for (int i = 0; i < matrix3.length; i++) {
            for (int j = 0; j < matrix3[i].length; j++) {
                System.out.print(matrix3[i][j] + " ");
            }
            System.out.println();
        }

        // 【三维数组】
        int[][][] cube = new int[2][3][4];
        // cube.length → 2, cube[0].length → 3, cube[0][0].length → 4
        System.out.println("三维数组尺寸: " + cube.length + "x" + cube[0].length + "x" + cube[0][0].length);
    }

    // ================================================
    // 七、不规则数组
    // ================================================
    public static void raggedArray() {
        System.out.println("--- 不规则数组 ---");

        int[][] ragged = new int[3][];
        ragged[0] = new int[]{1, 2};
        ragged[1] = new int[]{3, 4, 5, 6};
        ragged[2] = new int[]{7, 8, 9};

        System.out.println("不规则数组:");
        // 行0 (长度2): 1 2
        // 行1 (长度4): 3 4 5 6
        // 行2 (长度3): 7 8 9
        for (int i = 0; i < ragged.length; i++) {
            System.out.print("行" + i + " (长度" + ragged[i].length + "): ");
            for (int j = 0; j < ragged[i].length; j++) {
                System.out.print(ragged[i][j] + " ");
            }
            System.out.println();
        }

        // 【三角形数组】
        int[][] triangle = new int[5][];
        for (int i = 0; i < triangle.length; i++) {
            triangle[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = (i + 1) * 10 + j;
            }
        }
        // 输出:
        // 10
        // 20 21
        // 30 31 32
        // 40 41 42 43
        // 50 51 52 53 54
        System.out.println("三角形数组:");
        for (int[] row : triangle) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // ================================================
    // 八、Arrays工具类介绍
    // ================================================
    public static void arraysUtility() {
        System.out.println("--- Arrays工具类 ---");

        // Arrays是java.util包下的工具类
        // Arrays.class.getName() → java.util.Arrays
        System.out.println("类名: " + java.util.Arrays.class.getName());
        // Arrays.class.getPackage().getName() → java.util
        System.out.println("包名: " + java.util.Arrays.class.getPackage().getName());

        System.out.println("\n主要功能:");
        System.out.println("1. toString()        - 数组转字符串");
        System.out.println("2. sort()            - 数组排序");
        System.out.println("3. binarySearch()     - 二分查找");
        System.out.println("4. fill()            - 填充数组");
        System.out.println("5. copyOf/copyOfRange - 数组复制");
        System.out.println("6. equals()          - 比较数组");
        System.out.println("7. hashCode()        - 计算哈希值");
        System.out.println("8. deepToString()    - 多维数组转字符串");
        System.out.println("9. parallelSort()    - 并行排序");
        System.out.println("10. setAll()         - 设置所有元素");
    }

    // ================================================
    // 九、Arrays常用方法
    // ================================================
    public static void arraysMethods() {
        System.out.println("--- Arrays常用方法详解 ---");

        int[] nums = {5, 2, 8, 1, 9, 3, 7, 4, 6};

        // 【1】toString - 数组转字符串
        // Arrays.toString(nums) → [5, 2, 8, 1, 9, 3, 7, 4, 6]
        System.out.println("toString: " + java.util.Arrays.toString(nums));

        // 【2】sort - 排序
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);
        // Arrays.toString(sorted) → [1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println("排序后: " + java.util.Arrays.toString(sorted));

        // 【3】binarySearch - 二分查找
        // Arrays.binarySearch(sorted, 5) → 4           // 找到，索引4
        System.out.println("元素5的索引: " + java.util.Arrays.binarySearch(sorted, 5));
        // Arrays.binarySearch(sorted, 6) → -7          // 未找到，返回-(插入点+1)

        System.out.println("元素6的索引: " + java.util.Arrays.binarySearch(sorted, 6));

        // 【4】fill - 填充
        int[] filled = new int[5];
        java.util.Arrays.fill(filled, 99);
        // Arrays.toString(filled) → [99, 99, 99, 99, 99]
        System.out.println("填充后: " + java.util.Arrays.toString(filled));

        // 【5】部分填充
        int[] partial = new int[5];
        java.util.Arrays.fill(partial, 1, 3, 88);
        // Arrays.toString(partial) → [0, 88, 88, 0, 0]
        System.out.println("部分填充[1,3): " + java.util.Arrays.toString(partial));

        // 【6】copyOf - 复制并扩展
        int[] original = {1, 2, 3, 4, 5};
        int[] copied = java.util.Arrays.copyOf(original, 10);
        // Arrays.toString(copied) → [1, 2, 3, 4, 5, 0, 0, 0, 0, 0]
        System.out.println("复制并扩展: " + java.util.Arrays.toString(copied));

        // 【7】copyOf - 复制并截断
        int[] truncated = java.util.Arrays.copyOf(original, 3);
        // Arrays.toString(truncated) → [1, 2, 3]
        System.out.println("复制并截断: " + java.util.Arrays.toString(truncated));

        // 【8】copyOfRange - 范围复制
        int[] range = java.util.Arrays.copyOfRange(original, 1, 4);
        // Arrays.toString(range) → [2, 3, 4]
        System.out.println("复制[1,4): " + java.util.Arrays.toString(range));

        // 【9】equals - 数组比较
        int[] a1 = {1, 2, 3};
        int[] a2 = {1, 2, 3};
        int[] a3 = {1, 2, 4};
        // Arrays.equals(a1, a2) → true
        System.out.println("a1 equals a2: " + java.util.Arrays.equals(a1, a2));
        // Arrays.equals(a1, a3) → false
        System.out.println("a1 equals a3: " + java.util.Arrays.equals(a1, a3));

        // 【10】hashCode
        // Arrays.hashCode(a1) → 30817
        System.out.println("a1的hashCode: " + java.util.Arrays.hashCode(a1));

        // 【11】deepToString - 多维数组
        int[][] multi = {{1, 2}, {3, 4}};
        // Arrays.deepToString(multi) → [[1, 2], [3, 4]]
        System.out.println("多维数组deepToString: " + java.util.Arrays.deepToString(multi));

        // 【12】parallelSort - 并行排序
        int[] large = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        java.util.Arrays.parallelSort(large);
        // Arrays.toString(large) → [1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println("并行排序: " + java.util.Arrays.toString(large));

        // 【13】setAll - 设置所有元素
        int[] squares = new int[5];
        java.util.Arrays.setAll(squares, i -> i * i);
        // Arrays.toString(squares) → [0, 1, 4, 9, 16]
        System.out.println("平方数列: " + java.util.Arrays.toString(squares));

        // 【14】mismatch - 查找不匹配位置（JDK9+）
        int[] m1 = {1, 2, 3, 4};
        int[] m2 = {1, 2, 4, 4};
        // Arrays.mismatch(m1, m2) → 2
        System.out.println("第一个不匹配位置: " + java.util.Arrays.mismatch(m1, m2));

        // 【15】compare - 字典序比较（JDK9+）
        // Arrays.compare(m1, m2) → -1             // m1 < m2
        System.out.println("compare([1,2], [1,3]): " + java.util.Arrays.compare(m1, m2));
    }

    // ================================================
    // 十、数组复制
    // ================================================
    public static void arrayCopy() {
        System.out.println("--- 数组复制方法 ---");

        int[] original = {1, 2, 3, 4, 5};

        // 【方式1】for循环
        int[] copy1 = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy1[i] = original[i];
        }
        // Arrays.toString(copy1) → [1, 2, 3, 4, 5]
        System.out.println("for循环复制: " + java.util.Arrays.toString(copy1));

        // 【方式2】clone()
        int[] copy2 = original.clone();
        // Arrays.toString(copy2) → [1, 2, 3, 4, 5]
        System.out.println("clone复制: " + java.util.Arrays.toString(copy2));

        // 【方式3】Arrays.copyOf()
        int[] copy3 = java.util.Arrays.copyOf(original, original.length);
        // Arrays.toString(copy3) → [1, 2, 3, 4, 5]
        System.out.println("copyOf复制: " + java.util.Arrays.toString(copy3));

        // 【方式4】Arrays.copyOfRange()
        int[] copy4 = java.util.Arrays.copyOfRange(original, 1, 4);
        // Arrays.toString(copy4) → [2, 3, 4]
        System.out.println("copyOfRange[1,4): " + java.util.Arrays.toString(copy4));

        // 【方式5】System.arraycopy()
        int[] copy5 = new int[original.length];
        System.arraycopy(original, 0, copy5, 0, original.length);
        // Arrays.toString(copy5) → [1, 2, 3, 4, 5]
        System.out.println("System.arraycopy: " + java.util.Arrays.toString(copy5));

        // 【复制并扩展】
        int[] expanded = java.util.Arrays.copyOf(original, original.length * 2);
        // Arrays.toString(expanded) → [1, 2, 3, 4, 5, 0, 0, 0, 0, 0]
        System.out.println("复制并扩展: " + java.util.Arrays.toString(expanded));

        // 【复制并截断】
        int[] trunc = java.util.Arrays.copyOf(original, 3);
        // Arrays.toString(trunc) → [1, 2, 3]
        System.out.println("复制并截断: " + java.util.Arrays.toString(trunc));
    }

    // ================================================
    // 十一、数组排序
    // ================================================
    public static void arraySort() {
        System.out.println("--- 数组排序 ---");

        // 【1】基本类型排序
        int[] nums = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        java.util.Arrays.sort(nums);
        // Arrays.toString(nums) → [1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println("基本排序: " + java.util.Arrays.toString(nums));

        // 【2】部分排序
        int[] partial = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        java.util.Arrays.sort(partial, 2, 6);
        // Arrays.toString(partial) → [9, 8, 1, 2, 3, 4, 3, 2, 1]
        System.out.println("部分排序[2,6): " + java.util.Arrays.toString(partial));

        // 【3】降序排序（JDK8+）
        Integer[] nums2 = {5, 2, 8, 1, 9};
        java.util.Arrays.sort(nums2, java.util.Collections.reverseOrder());
        // Arrays.toString(nums2) → [9, 8, 5, 2, 1]
        System.out.println("降序排序: " + java.util.Arrays.toString(nums2));

        // 【4】自定义排序（按长度）
        String[] names = {"Charlie", "Alice", "Bob"};
        java.util.Arrays.sort(names, (a, b) -> a.length() - b.length());
        // Arrays.toString(names) → [Bob, Alice, Charlie]
        System.out.println("按长度排序: " + java.util.Arrays.toString(names));

        // 【5】并行排序 vs 普通排序
        int[] large1 = new int[10000];
        int[] large2 = new int[10000];
        java.util.Random rand = new java.util.Random();
        java.util.Arrays.setAll(large1, i -> rand.nextInt());
        System.arraycopy(large1, 0, large2, 0, large1.length);

        long start = System.nanoTime();
        java.util.Arrays.parallelSort(large1);
        long parallelTime = System.nanoTime() - start;

        start = System.nanoTime();
        java.util.Arrays.sort(large2);
        long normalTime = System.nanoTime() - start;

        System.out.println("并行排序10000元素: " + parallelTime / 1000000 + " ms");
        System.out.println("普通排序10000元素: " + normalTime / 1000000 + " ms");

        // 【6】对象数组排序
        Student[] students = {
            new Student("Alice", 20),
            new Student("Bob", 18),
            new Student("Charlie", 22)
        };
        java.util.Arrays.sort(students);
        System.out.println("学生按年龄排序:");
        // Bob-18, Alice-20, Charlie-22
        for (Student s : students) {
            System.out.println("  " + s.name + " - " + s.age);
        }
    }

    static class Student implements Comparable<Student> {
        String name;
        int age;

        Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Student o) {
            return this.age - o.age;
        }
    }

    // ================================================
    // 十二、数组查找
    // ================================================
    public static void arraySearch() {
        System.out.println("--- 数组查找 ---");

        // 【1】二分查找（必须先排序）
        int[] sorted = {1, 3, 5, 7, 9, 11, 13};

        // Arrays.binarySearch(sorted, 7) → 3
        System.out.println("查找7: " + java.util.Arrays.binarySearch(sorted, 7));
        // Arrays.binarySearch(sorted, 6) → -4       // 未找到，返回-(插入点+1)
        System.out.println("查找6: " + java.util.Arrays.binarySearch(sorted, 6));

        // 【2】范围查找
        // Arrays.binarySearch(sorted, 2, 5, 5) → -3
        System.out.println("在[2,5)范围查找5: " + java.util.Arrays.binarySearch(sorted, 2, 5, 5));

        // 【3】自定义对象查找
        Student[] students = {
            new Student("Alice", 20),
            new Student("Bob", 18),
            new Student("Charlie", 22)
        };
        java.util.Arrays.sort(students);
        // Arrays.binarySearch(students, new Student("Bob", 18)) → 0
        int idx = java.util.Arrays.binarySearch(students, new Student("Bob", 18));
        System.out.println("查找Bob: " + idx);

        // 【4】线性查找
        int[] arr = {4, 2, 7, 1, 9, 3};
        int linearIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 7) {
                linearIndex = i;
                break;
            }
        }
        // linearIndex → 2
        System.out.println("线性查找7: " + linearIndex);
    }

    // ================================================
    // 十三、数组填充
    // ================================================
    public static void arrayFill() {
        System.out.println("--- 数组填充 ---");

        // 【1】全部填充
        int[] arr1 = new int[5];
        java.util.Arrays.fill(arr1, 100);
        // Arrays.toString(arr1) → [100, 100, 100, 100, 100]
        System.out.println("全部填充100: " + java.util.Arrays.toString(arr1));

        // 【2】部分填充
        int[] arr2 = new int[5];
        java.util.Arrays.fill(arr2, 1, 4, 50);
        // Arrays.toString(arr2) → [0, 50, 50, 50, 0]
        System.out.println("部分填充[1,4): " + java.util.Arrays.toString(arr2));

        // 【3】setAll填充
        int[] arr3 = new int[5];
        java.util.Arrays.setAll(arr3, i -> (i + 1) * 10);
        // Arrays.toString(arr3) → [10, 20, 30, 40, 50]
        System.out.println("setAll填充: " + java.util.Arrays.toString(arr3));

        // 【4】parallelSetAll
        int[] arr4 = new int[5];
        java.util.Arrays.parallelSetAll(arr4, i -> (i + 1) * 100);
        // Arrays.toString(arr4) → [100, 200, 300, 400, 500]
        System.out.println("parallelSetAll: " + java.util.Arrays.toString(arr4));
    }

    // ================================================
    // 十四、数组转String
    // ================================================
    public static void arrayToString() {
        System.out.println("--- 数组转String ---");

        int[] nums = {1, 2, 3, 4, 5};

        // 【1】直接打印 → [I@7a81197d    // 内存地址，不是想要的结果
        System.out.println("直接打印: " + nums);

        // 【2】Arrays.toString → [1, 2, 3, 4, 5]
        System.out.println("toString: " + java.util.Arrays.toString(nums));

        // 【3】StringBuilder → [1, 2, 3, 4, 5]
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i < nums.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println("StringBuilder: " + sb);

        // 【4】多维数组
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        // Arrays.toString(matrix) → [[I@xxx, [I@yyy]     // 外层数组的地址
        System.out.println("多维toString: " + java.util.Arrays.toString(matrix));
        // Arrays.deepToString(matrix) → [[1, 2, 3], [4, 5, 6]]
        System.out.println("多维deepToString: " + java.util.Arrays.deepToString(matrix));
    }

    // ================================================
    // 十五、Arrays.equals vs ==
    // ================================================
    public static void arrayEquals() {
        System.out.println("--- Arrays.equals vs == ---");

        int[] a1 = {1, 2, 3};
        int[] a2 = {1, 2, 3};
        int[] a3 = a1;

        // a1 == a2 → false           // 引用不同
        System.out.println("a1 == a2: " + (a1 == a2));
        // a1 == a3 → true            // 引用相同
        System.out.println("a1 == a3: " + (a1 == a3));

        // Arrays.equals(a1, a2) → true
        System.out.println("Arrays.equals(a1, a2): " + java.util.Arrays.equals(a1, a2));
        // Arrays.equals(a1, a3) → true
        System.out.println("Arrays.equals(a1, a3): " + java.util.Arrays.equals(a1, a3));

        // 【多维数组比较】
        int[][] m1 = {{1, 2}, {3, 4}};
        int[][] m2 = {{1, 2}, {3, 4}};

        // Arrays.equals(m1, m2) → false    // 只比较第一层引用
        System.out.println("Arrays.equals: " + java.util.Arrays.equals(m1, m2));
        // Arrays.deepEquals(m1, m2) → true // 递归比较
        System.out.println("Arrays.deepEquals: " + java.util.Arrays.deepEquals(m1, m2));
    }

    // ================================================
    // 十六、注意事项
    // ================================================
    public static void arrayCautions() {
        System.out.println("--- 注意事项 ---");

        // 【1】数组长度固定
        int[] arr = {1, 2, 3};
        // arr.length → 3
        System.out.println("数组长度: " + arr.length);
        // arr.length = 10 → 编译错误！length是final的

        // 【2】索引越界
        int[] safe = {1, 2, 3};
        try {
            int x = safe[10];  // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("越界抛出: ArrayIndexOutOfBoundsException");
        }

        // 【3】数组是引用类型
        int[] arr1 = {1, 2, 3};
        int[] arr2 = arr1;
        arr2[0] = 100;
        // arr1[0] → 100                // 被arr2修改了
        System.out.println("arr1[0]: " + arr1[0]);

        // 【4】数组传参
        int[] param = {1, 2, 3};
        modifyArray(param);
        // param → [999, 2, 3]
        System.out.println("修改后: " + java.util.Arrays.toString(param));

        // 【5】空数组 vs null
        int[] empty = new int[0];
        int[] nullArr = null;
        // empty.length → 0
        System.out.println("空数组length: " + empty.length);
        // nullArr.length → NullPointerException!

        // 【6】数组clone是浅拷贝
        int[][] original = {{1, 2}, {3, 4}};
        int[][] cloned = original.clone();
        cloned[0][0] = 100;
        // original[0][0] → 100          // 被影响了！
        System.out.println("original[0][0]: " + original[0][0]);
        System.out.println("结论: 基本类型是深拷贝，引用类型是浅拷贝");
    }

    public static void modifyArray(int[] arr) {
        arr[0] = 999;
    }
}
