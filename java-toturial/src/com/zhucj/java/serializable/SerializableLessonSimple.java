package com.zhucj.java.serializable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Java 序列化教程 - 简化版（仅使用 Java 原生序列化）
 * 
 * 序列化：将对象转换为字节流的过程，用于存储或传输
 * 反序列化：将字节流恢复为对象的过程
 */
public class SerializableLessonSimple {
    
    public static void main(String[] args) {
        System.out.println("=== Java 序列化教程（简化版）===\n");
        
        // 1. 基本序列化示例
        basicSerialization();
        
        // 2. transient 关键字
        transientExample();
        
        // 3. 自定义序列化
        customSerialization();
        
        // 4. serialVersionUID 的重要性
        serialVersionUidExample();
        
        // 5. 序列化集合对象
        serializeCollection();
        
        System.out.println("\n=== 所有示例运行完成 ===");
    }
    
    /**
     * 1. 基本序列化示例
     */
    private static void basicSerialization() {
        System.out.println("--- 1. 基本序列化 ---");
        
        // 创建可序列化的对象
        User user = new User("张三", 25, "zhangsan@example.com");
        System.out.println("原始对象: " + user);
        
        // 序列化到文件
        String filename = "user.ser";
        try {
            // 序列化
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
            System.out.println("✓ 对象已序列化到文件: " + filename);
            
            // 反序列化
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            User deserializedUser = (User) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("✓ 对象已反序列化: " + deserializedUser);
            System.out.println();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 2. transient 关键字示例
     * transient 修饰的字段不会被序列化
     */
    private static void transientExample() {
        System.out.println("--- 2. transient 关键字 ---");
        
        Employee emp = new Employee("李四", 30, "secret123", "lisi@example.com");
        System.out.println("原始对象: " + emp);
        
        String filename = "employee.ser";
        try {
            // 序列化
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(emp);
            out.close();
            
            // 反序列化
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            Employee deserializedEmp = (Employee) in.readObject();
            in.close();
            
            System.out.println("反序列化后: " + deserializedEmp);
            System.out.println("注意: password 字段为 null（因为使用了 transient）");
            System.out.println();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 3. 自定义序列化示例
     * 通过 writeObject 和 readObject 方法控制序列化过程
     */
    private static void customSerialization() {
        System.out.println("--- 3. 自定义序列化 ---");
        
        Config config = new Config("数据库配置", "localhost", 3306, "admin", "password123");
        System.out.println("原始对象: " + config);
        
        String filename = "config.ser";
        try {
            // 序列化
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(config);
            out.close();
            
            // 反序列化
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            Config deserializedConfig = (Config) in.readObject();
            in.close();
            
            System.out.println("反序列化后: " + deserializedConfig);
            System.out.println("注意: password 被加密存储，读取时自动解密");
            System.out.println();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 4. serialVersionUID 示例
     */
    private static void serialVersionUidExample() {
        System.out.println("--- 4. serialVersionUID ---");
        System.out.println("serialVersionUID 用于版本控制");
        System.out.println("如果类的结构发生变化，应更新此值");
        System.out.println("建议始终显式声明 serialVersionUID");
        System.out.println();
    }
    
    /**
     * 5. 序列化集合对象
     */
    private static void serializeCollection() {
        System.out.println("--- 5. 序列化集合 ---");
        
        List<User> users = new ArrayList<>();
        users.add(new User("王五", 28, "wangwu@example.com"));
        users.add(new User("赵六", 32, "zhaoliu@example.com"));
        users.add(new User("孙七", 26, "sunqi@example.com"));
        
        System.out.println("原始列表: " + users);
        
        String filename = "users.ser";
        try {
            // 序列化列表
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(users);
            out.close();
            
            // 反序列化列表
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            @SuppressWarnings("unchecked")
            List<User> deserializedUsers = (List<User>) in.readObject();
            in.close();
            
            System.out.println("反序列化列表: " + deserializedUsers);
            System.out.println();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 基本的可序列化类
 * 必须实现 Serializable 接口
 */
class User implements Serializable {
    // 建议显式声明 serialVersionUID
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    private String email;
    
    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}

/**
 * 使用 transient 关键字的类
 * transient 字段不会被序列化
 */
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    private transient String password;  // 敏感信息，不序列化
    private String email;
    
    public Employee(String name, int age, String password, String email) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + 
               ", password='" + password + "', email='" + email + "'}";
    }
}

/**
 * 自定义序列化的类
 * 通过 writeObject 和 readObject 控制序列化过程
 */
class Config implements Serializable {
    private static final long serialVersionUID = 2L;
    
    private String name;
    private String host;
    private int port;
    private String username;
    private transient String password;  // 不直接序列化密码
    
    public Config(String name, String host, int port, String username, String password) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }
    
    /**
     * 自定义序列化逻辑
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        // 写入默认字段
        out.defaultWriteObject();
        // 自定义处理：加密密码后写入
        if (password != null) {
            out.writeObject(encrypt(password));
        } else {
            out.writeObject(null);
        }
    }
    
    /**
     * 自定义反序列化逻辑
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // 读取默认字段
        in.defaultReadObject();
        // 自定义处理：读取并解密密码
        String encryptedPassword = (String) in.readObject();
        if (encryptedPassword != null) {
            this.password = decrypt(encryptedPassword);
        }
    }
    
    // 简单的加密/解密示例（实际应用中应使用更安全的算法）
    private String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append((char)(c + 1));
        }
        return sb.toString();
    }
    
    private String decrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append((char)(c - 1));
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "Config{name='" + name + "', host='" + host + 
               "', port=" + port + ", username='" + username + 
               "', password='" + password + "'}";
    }
}
