package com.zhucj.java;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

    }
}

/*
Java 命名规范总结：
项目名：全小写，中划线分隔 (my-project)
包名：全小写，无分隔符 (com.zhucj.java)
文件夹：随包名，全小写
类名：PascalCase (UserService)
方法/变量：camelCase (getName, userName)
常量：ALL_UPPER_CASE (MAX_VALUE)
java-tutorial
└
    ├── com
    │   └── zhucj
    │       └── java
    │           ├── Main.java        // 主启动类（可留在基础包，也可移到 com.example.javatutorial）
    │           ├── user             // 新增的用户模块包
    │           │   ├── UserEntity.java
    │           │   ├── UserService.java
    │           │   └── UserController.java
    │           └── order            // 未来可能新增的订单模块包
    └── ...（其他原有结构）
* */