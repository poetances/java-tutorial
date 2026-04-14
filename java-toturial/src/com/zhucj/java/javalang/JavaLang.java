package com.zhucj.java.javalang;

/**
 * java.lang包介绍：
 * <p>java.lang包是Java语言自带的包，包含了一些基本类和接口，如Object、String、Math、System等。</p>
 * <p>该包中的类和接口都是Java语言自带的，不需要导入。</p>
 *
 * java.lang包内容庞大，以下是最常用的核心组件分类：
 * <ul>Object类：所有 Java 类的根父类（除 Object自身外），定义了 equals()、hashCode()、toString()等通用方法。</ul>
 * <ul>字符串与字符类：String（不可变字符串）、StringBuilder/StringBuffer（可变字符串）、Character（字符包装类）。</ul>
 * <ul>基本数据类型的包装类：Integer、Long、Double、Boolean等（将基本类型封装为对象，支持泛型等操作）。</ul>
 * <ul>数学工具类：Math（提供平方根、三角函数、随机数等静态方法）、StrictMath（高精度数学计算）。</ul>
 * <ul>系统交互类：System（访问系统属性、输入输出流，如 System.out.println()）、Runtime（获取 JVM 运行时信息）。</ul>
 * <ul>多线程类：Thread（线程类）、Runnable（线程任务接口）、ThreadLocal（线程局部变量）。</ul>
 *
 * 2. 异常与错误类：
 * <ul>基础异常：Exception（所有受检异常的父类）、RuntimeException（所有非受检异常的父类，如 NullPointerException、ArrayIndexOutOfBoundsException）。</ul>
 * <ul>系统错误：Error（严重错误，如 OutOfMemoryError、StackOverflowError）。</ul>
 *
 * 3. 接口与注解
 * <ul>接口：Comparable（比较接口）、Cloneable（克隆接口）、Serializable（可序列化接口）。</ul>
 * <ul>注解：@Override（覆盖父类方法）、@Deprecated（过时）、@SuppressWarnings（忽略警告）。</ul>
 *
 * 4. 其他基础组件
 * Class类：代表 Java 类的元数据（反射机制核心）。
 * Enum类：枚举类型的基类（enum关键字的底层实现）。
 * Void类：表示“无返回值”的占位类（用于反射等场景）。
 * @author zhucj
 * */
public class JavaLang {

    public static void main(String[] args) {


    }
}
