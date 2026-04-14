package com.zhucj.java.control;

/**
 * Java 中的流程控制语句
 *
 * <p>本类涵盖 Java 流程控制的核心知识点：</p>
 * <ul>
 *   <li>条件语句：if-else、switch</li>
 *   <li>循环语句：for、while、do-while、增强 for</li>
 *   <li>跳转语句：break、continue、return</li>
 *   <li>循环标签：label</li>
 * </ul>
 *
 * @author zhucj
 * @version 1.0
 * @since 2026-04-06
 */
public class Control {

    public static void main(String[] args) {
        // 条件语句
        ifElseDemo(85);
        switchDemo("B");

        // 循环语句
        forDemo();
        whileDemo();
        doWhileDemo();
        enhancedForDemo();

        // 跳转语句
        breakDemo();
        continueDemo();
    }

    /**
     * if-else 条件语句演示
     *
     * @param score 分数
     */
    public static void ifElseDemo(int score) {
        System.out.println("=== if-else 条件语句 ===");

        if (score >= 90) {
            System.out.println("优秀");
        } else if (score >= 80) {
            System.out.println("良好");
        } else if (score >= 60) {
            System.out.println("及格");
        } else {
            System.out.println("不及格");
        }
        System.out.println();
    }

    /**
     * switch 条件语句演示
     *
     * <p>switch 支持的类型：</p>
     * <ul>
     *   <li>byte、short、int、char</li>
     *   <li>枚举类型 (enum)</li>
     *   <li>字符串 (String, Java 7+)</li>
     * </ul>
     *
     * @param grade 等级
     */
    public static void switchDemo(String grade) {
        System.out.println("=== switch 条件语句 ===");

        switch (grade) {
            case "A":
                System.out.println("90-100分");
                break;
            case "B":
                System.out.println("80-89分");
                break;
            case "C":
                System.out.println("70-79分");
                break;
            case "D":
                System.out.println("60-69分");
                break;
            default:
                System.out.println("60分以下");
        }
        System.out.println();
    }

    /**
     * for 循环演示
     */
    public static void forDemo() {
        System.out.println("=== for 循环 ===");

        // 基本 for 循环
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 变体1：初始化多个变量
        for (int i = 0, j = 10; i < 3; i++, j--) {
            System.out.println("i=" + i + ", j=" + j);
        }

        // 变体2：省略某些部分（可能造成死循环）
        // for (; ; ) { }  // 死循环
        System.out.println();
    }

    /**
     * while 循环演示
     *
     * <p>特点：先判断条件，再执行循环体</p>
     */
    public static void whileDemo() {
        System.out.println("=== while 循环 ===");

        int i = 0;
        while (i < 5) {
            System.out.print(i + " ");
            i++;
        }
        System.out.println();
        System.out.println();
    }

    /**
     * do-while 循环演示
     *
     * <p>特点：先执行一次循环体，再判断条件（至少执行一次）</p>
     */
    public static void doWhileDemo() {
        System.out.println("=== do-while 循环 ===");

        int i = 0;
        do {
            System.out.print(i + " ");
            i++;
        } while (i < 5);
        System.out.println();

        // do-while 至少执行一次
        int j = 10;
        do {
            System.out.println("j=" + j);  // 至少打印一次
        } while (j < 5);  // 条件不满足，但已执行过一次
        System.out.println();
    }

    /**
     * 增强 for 循环（for-each）
     *
     * <p>用于遍历数组或集合</p>
     *
     * @see #forDemo()
     */
    public static void enhancedForDemo() {
        System.out.println("=== 增强 for 循环 ===");

        // 遍历数组
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 遍历字符串数组
        String[] names = {"Alice", "Bob", "Charlie"};
        for (String name : names) {
            System.out.println("Hello, " + name);
        }
        System.out.println();
    }

    /**
     * break 语句演示
     *
     * <p>break 用于：</p>
     * <ul>
     *   <li>switch 中终止 case</li>
     *   <li>循环中立即终止整个循环</li>
     * </ul>
     */
    public static void breakDemo() {
        System.out.println("=== break 语句 ===");

        // 找到第一个偶数就停止
        int[] numbers = {1, 3, 5, 6, 7, 8, 9};
        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.println("找到第一个偶数: " + num);
                break;
            }
        }

        // 使用标签跳出多层循环
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    break outer;  // 跳出外层循环
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
        System.out.println();
    }

    /**
     * continue 语句演示
     *
     * <p>continue 跳过本次循环，继续下一次循环</p>
     */
    public static void continueDemo() {
        System.out.println("=== continue 语句 ===");

        // 跳过所有偶数，只打印奇数
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue;  // 跳过本次循环
            }
            System.out.print(i + " ");  // 输出：1 3 5 7 9
        }
        System.out.println();

        // 跳过指定条件
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                continue;
            }
            System.out.println("i=" + i);
        }
        System.out.println();
    }
}


