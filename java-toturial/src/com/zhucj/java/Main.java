package com.zhucj.java;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
        }
    }
}

/*
java-tutorial
└── src
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