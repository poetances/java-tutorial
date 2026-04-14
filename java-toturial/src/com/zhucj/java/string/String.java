package com.zhucj.java.string;

public class String {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Integer a = 127;  // 自动装箱，调用Integer.valueOf(127) → 复用缓存对象
        Integer b = 127;  // 复用同一个缓存对象
        System.out.println(a == b);
    }

}
