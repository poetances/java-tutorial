# Java 系统学习总结

## 一、Java 基础概念

### 1.1 JDK、JRE、JVM 的关系

#### JVM (Java Virtual Machine) - Java虚拟机
- Java程序运行的虚拟计算机
- 负责将字节码转换为机器码并执行
- 是Java"一次编写,到处运行"的核心
- 不同操作系统有不同的JVM实现

#### JRE (Java Runtime Environment) - Java运行环境
- 包含 JVM + Java核心类库
- 提供Java程序运行所需的环境
- 如果你只需要**运行**Java程序,安装JRE就够了
- 组成: `JRE = JVM + 核心类库(如java.lang, java.util等)`

#### JDK (Java Development Kit) - Java开发工具包
- 包含 JRE + 开发工具(编译器、调试器等)
- 如果你需要**开发**Java程序,必须安装JDK
- 组成: `JDK = JRE + 开发工具(javac、java、javadoc等)`

#### 三者关系
```
JDK = JRE + 开发工具
JRE = JVM + 核心类库
```

**包含关系**: JDK ⊃ JRE ⊃ JVM

---

### 1.2 Java 编译过程

Java代码从编写到执行经历以下4个阶段:

#### 阶段1: 编写源代码 (.java文件)
```java
// HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

#### 阶段2: 编译 (javac命令)
- 使用 `javac` 编译器将 `.java` 源文件编译成 `.class` 字节码文件
- 命令: `javac HelloWorld.java`
- 生成: `HelloWorld.class` (字节码文件)
- 字节码是一种中间代码,不针对任何特定处理器

#### 阶段3: 类加载 (Class Loading)
JVM的类加载器将 `.class` 文件加载到内存中,包括:
- **加载**: 查找并加载类的二进制数据
- **验证**: 确保字节码的正确性
- **准备**: 为静态变量分配内存
- **解析**: 将符号引用转换为直接引用
- **初始化**: 执行静态代码块

#### 阶段4: 执行 (java命令)
- 使用 `java` 命令运行程序
- 命令: `java HelloWorld`
- JVM通过以下方式执行字节码:
  - **解释执行**: 逐行解释字节码为机器码
  - **JIT编译** (Just-In-Time): 将热点代码编译成本地机器码,提高性能

#### 完整流程图
```
.java源代码 
    ↓ (javac编译)
.class字节码文件
    ↓ (类加载器加载)
JVM内存中的字节码
    ↓ (JVM执行: 解释器 + JIT编译器)
机器码 → CPU执行
```

#### 关键优势
1. **跨平台性**: 字节码可以在任何安装了JVM的系统上运行
2. **安全性**: JVM提供沙箱机制,验证字节码
3. **性能优化**: JIT编译器动态优化热点代码
