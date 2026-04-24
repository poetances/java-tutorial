package com.zhucj.java.serializable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java 序列化教程
 * 
 * 序列化：将对象转换为字节流的过程，用于存储或传输
 * 反序列化：将字节流恢复为对象的过程
 */
public class SerializableLesson {
    
    public static void main(String[] args) {
        System.out.println("=== Java 序列化教程 ===\n");
        
        // 1. 基本序列化示例（Java原生）
        basicSerialization();
        
        // 2. transient 关键字（Java原生）
        transientExample();
        
        // 3. 自定义序列化（Java原生）
        customSerialization();
        
        // 4. serialVersionUID 的重要性
        serialVersionUidExample();
        
        // 5. 序列化集合对象（Java原生）
        serializeCollection();
        
        // 6. Jackson JSON序列化 - 基本用法
        jacksonBasicExample();
        
        // 7. Jackson JSON序列化 - 注解使用
        jacksonAnnotationsExample();
        
        // 8. Jackson JSON序列化 - 复杂对象
        jacksonComplexObjectExample();
        
        // 9. Jackson vs Java原生序列化对比
        comparisonExample();
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
     * 5. 序列化集合对象（Java原生）
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
    
    /**
     * 6. Jackson JSON序列化 - 基本用法
     */
    private static void jacksonBasicExample() {
        System.out.println("--- 6. Jackson JSON 基本用法 ---");
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            // 创建对象
            Product product = new Product("iPhone 15", 7999.0, "Apple");
            
            // 对象 -> JSON 字符串（序列化）
            String json = mapper.writeValueAsString(product);
            System.out.println("JSON 字符串: " + json);
            
            // JSON 字符串 -> 对象（反序列化）
            Product deserializedProduct = mapper.readValue(json, Product.class);
            System.out.println("反序列化对象: " + deserializedProduct);
            
            // 格式化输出（美化）
            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(product);
            System.out.println("\n格式化 JSON:");
            System.out.println(prettyJson);
            System.out.println();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 7. Jackson JSON序列化 - 注解使用
     */
    private static void jacksonAnnotationsExample() {
        System.out.println("--- 7. Jackson 注解使用 ---");
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            // 创建带注解的对象
            Book book = new Book("Java编程思想", "Bruce Eckel", 108.00, "secret-content");
            
            // 序列化 - 会忽略标记为 @JsonIgnore 的字段
            String json = mapper.writeValueAsString(book);
            System.out.println("JSON: " + json);
            System.out.println("注意: content 字段被 @JsonIgnore 忽略");
            
            // 反序列化
            Book deserializedBook = mapper.readValue(json, Book.class);
            System.out.println("反序列化: " + deserializedBook);
            System.out.println();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 8. Jackson JSON序列化 - 复杂对象
     */
    private static void jacksonComplexObjectExample() {
        System.out.println("--- 8. Jackson 复杂对象 ---");
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            // 创建复杂对象（订单包含多个商品）
            Order order = new Order();
            order.setOrderId("ORD-2024-001");
            order.setCustomerName("张三");
            order.setOrderDate(LocalDateTime.now());
            
            List<Product> products = new ArrayList<>();
            products.add(new Product("iPhone 15", 7999.0, "Apple"));
            products.add(new Product("MacBook Pro", 14999.0, "Apple"));
            products.add(new Product("AirPods Pro", 1899.0, "Apple"));
            order.setProducts(products);
            
            Map<String, String> metadata = new HashMap<>();
            metadata.put("source", "online");
            metadata.put("payment", "alipay");
            order.setMetadata(metadata);
            
            // 序列化为 JSON
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
            System.out.println("订单 JSON:");
            System.out.println(json);
            
            // 从 JSON 文件读写
            File file = new File("order.json");
            mapper.writeValue(file, order);
            System.out.println("\n✓ 订单已保存到文件: " + file.getAbsolutePath());
            
            Order loadedOrder = mapper.readValue(file, Order.class);
            System.out.println("✓ 从文件加载订单: " + loadedOrder.getOrderId());
            System.out.println();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 9. Jackson vs Java原生序列化对比
     */
    private static void comparisonExample() {
        System.out.println("--- 9. Jackson vs Java原生序列化对比 ---");
        
        User user = new User("王五", 28, "wangwu@example.com");
        
        try {
            // Java 原生序列化
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
            byte[] javaSerialized = baos.toByteArray();
            oos.close();
            
            // Jackson JSON 序列化
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(user);
            byte[] jsonBytes = jsonString.getBytes();
            
            System.out.println("Java 原生序列化大小: " + javaSerialized.length + " bytes");
            System.out.println("Jackson JSON 序列化大小: " + jsonBytes.length + " bytes");
            System.out.println("JSON 可读性: ✓ 人类可读");
            System.out.println("Java 原生可读性: ✗ 二进制格式");
            System.out.println("跨语言支持: Jackson ✓ | Java原生 ✗");
            System.out.println();
            
        } catch (Exception e) {
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

/**
 * Jackson 示例 - 简单产品类
 */
class Product {
    private String name;
    private double price;
    private String brand;
    
    // Jackson 需要无参构造函数
    public Product() {}
    
    public Product(String name, double price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", brand='" + brand + "'}";
    }
}

/**
 * Jackson 示例 - 使用注解的书籍类
 */
class Book {
    @JsonProperty("title")  // 自定义 JSON 字段名
    private String title;
    
    @JsonProperty("author")
    private String author;
    
    private double price;
    
    @JsonIgnore  // 忽略此字段，不序列化
    private String content;
    
    public Book() {}
    
    public Book(String title, String author, double price, String content) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.content = content;
    }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + 
               "', price=" + price + ", content='" + content + "'}";
    }
}

/**
 * Jackson 示例 - 复杂订单类（包含日期、集合、Map）
 */
class Order {
    private String orderId;
    private String customerName;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 自定义日期格式
    private LocalDateTime orderDate;
    
    private List<Product> products;
    private Map<String, String> metadata;
    
    public Order() {}
    
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
    public Map<String, String> getMetadata() { return metadata; }
    public void setMetadata(Map<String, String> metadata) { this.metadata = metadata; }
    
    @Override
    public String toString() {
        return "Order{orderId='" + orderId + "', customer='" + customerName + 
               "', date=" + orderDate + ", products=" + (products != null ? products.size() : 0) + "}";
    }
}
