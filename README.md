# Java Tutorial

一个通过**可运行的示例代码**来系统学习 Java 核心知识的教程项目，基于 Maven 构建。

> 项目源码位于仓库 `java-tutorial/` 子目录下，采用标准 Maven 目录结构。

## ✨ 环境要求

| 工具 | 版本 |
|------|------|
| JDK | 11+ |
| Maven | 3.6+ |
| IDE | IntelliJ IDEA（推荐） |

## 🚀 快速开始

```bash
# 进入项目目录
cd java-tutorial

# 编译项目
mvn clean compile

# 运行主程序
mvn exec:java

# 运行测试
mvn test

# 运行单个测试类
mvn test -Dtest=StringUtilsTest

# 构建 JAR 包
mvn package
```

> 建议：在 IntelliJ IDEA 中打开项目后，直接点击每个课程类 `main` 方法旁的运行按钮即可查看运行效果。

## 📂 项目结构

```
src/
├── main/
│   ├── java/com/zhucj/tutorial/   # 源码根包
│   │   ├── Main.java              # 主入口类
│   │   ├── basicdata/             # 基本数据类型
│   │   ├── numbers/               # 数值处理
│   │   ├── string/                # 字符串
│   │   ├── control/               # 流程控制
│   │   ├── function/              # 方法
│   │   ├── jclass/                # 类与面向对象
│   │   ├── javalang/              # java.lang 包
│   │   ├── array/                 # 数组与集合框架
│   │   ├── generics/              # 泛型
│   │   ├── date/                  # 日期时间
│   │   ├── files/                 # 文件 I/O
│   │   ├── concurrency/           # 并发编程
│   │   └── serializable/          # 序列化
│   └── resources/                 # 资源目录
└── test/java/com/zhucj/tutorial/  # 单元测试（JUnit 5）
```

## 📚 课程目录

按学习顺序排列：

| 模块 | 类 | 学习内容 |
|------|----|---------|
| 基础 | `Main` | 程序入口、命名规范、注释 |
| 基本数据类型 | `basicdata.BasicData` | byte/short/int/long/float/double/char/boolean、类型转换、包装类 |
| 数值处理 | `numbers.NumbersLesson` | 包装类型、自动装箱拆箱、Math 工具类、大数值（BigDecimal/BigInteger） |
| 字符串 | `string.StringLesson` | String 不可变性、常用 API |
| 字符串 | `string.StringBuilderLesson` | 可变字符串、与 String 的性能对比 |
| 字符串 | `string.StringBufferLesson` | 线程安全的可变字符串 |
| 流程控制 | `control.Control` | if-else/switch、for/while、break/continue、循环标签 |
| 方法 | `function.FunctionLesson` | 方法定义、重载、递归、可变参数 |
| 面向对象 | `jclass.ClassLesson` | 类与对象、继承、多态、封装、接口、抽象类 |
| 核心包 | `javalang.JavaLang` | Object、String、System、Math、包装类、异常体系 |
| 数组 | `array.ArraysLesson` | 一维/二维数组、Arrays 工具类 |
| 集合 | `array.ListLesson` | List 接口、ArrayList/LinkedList |
| 集合 | `array.SetLesson` | Set 接口、HashSet/LinkedHashSet/TreeSet |
| 集合 | `array.MapLesson` | Map 接口、HashMap/LinkedHashMap/TreeMap |
| 集合 | `array.QueueLesson` | Queue/Deque 接口、PriorityQueue、ArrayDeque |
| 集合 | `array.CollectionsLesson` | Collections 工具类、并发集合 |
| 泛型 | `generics.GenericsLesson` | 泛型类/方法/接口、类型通配符、类型擦除 |
| 日期时间 | `date.DateLesson` | Date/Calendar 旧 API、LocalDate/LocalDateTime 新 API、格式化 |
| 文件 I/O | `files.FilesLesson` | File、IO 流、NIO、Files 工具类 |
| 并发 | `concurrency.ConcurrencyLesson` | 线程创建、线程池、锁、原子类、并发容器 |
| 序列化 | `serializable.SerializableLesson` | Serializable、serialVersionUID、transient |
| 序列化 | `serializable.SerializableLessonSimple` | 序列化简化实战 |

## 🧪 单元测试

- `StringUtilsTest` - 基于 JUnit 5 的字符串工具方法测试示例

## 📦 依赖

| 依赖 | 版本 | 用途 |
|------|------|------|
| JUnit Jupiter | 5.9.2 | 单元测试框架 |
| Apache Commons Lang3 | 3.12.0 | 常用工具类库 |
| Jackson Databind | 2.15.2 | JSON 序列化/反序列化 |

## 🎯 学习建议

1. **顺序学习**：按上表从上到下依次运行每个课程类，理解后再进入下一章
2. **动手实践**：在示例代码基础上修改参数，观察输出变化
3. **先运行再理解**：每个类都有独立的 `main` 方法，先看输出结果，再读注释讲解
4. **善用单测**：使用 `mvn test` 验证自己对代码行为的理解
