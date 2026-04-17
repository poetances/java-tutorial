package com.zhucj.java.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.stream.Stream;

/**
 * =====================================================
 * Java NIO Files类详解
 * =====================================================
 *
 * 【一、Files类是什么】
 * -------------------
 * Files是java.nio.file包下的工具类，提供了操作文件系统的静态方法。
 * - JDK7引入（NIO.2）
 * - 所有方法都是static
 * - 替代了传统的java.io.File类
 * - 提供了更现代、更高效的文件操作API
 *
 * 【二、Files vs File】
 * ------------------
 * File (java.io):
 *   - JDK1.0引入，老旧API
 *   - 功能有限，错误处理差
 *   - 不支持符号链接、文件属性等高级特性
 *
 * Files (java.nio.file):
 *   - JDK7引入，现代API
 *   - 功能丰富，异常处理清晰
 *   - 支持符号链接、文件属性、文件监视等
 *   - 性能更好，支持批量操作
 *
 * 【三、核心概念】
 * --------------
 * 1. Path      - 路径对象，表示文件或目录的位置
 * 2. Files     - 文件操作工具类
 * 3. FileSystem - 文件系统抽象
 * 4. WatchService - 文件监视服务
 *
 * JDK 1.0 (1996)  →  java.io (BIO - Blocking IO)
 * JDK 1.4 (2002)  →  java.nio (NIO - New/Non-blocking IO)
 * JDK 7   (2011)  →  java.nio.file (NIO.2 - Files/Path API)
 * JDK 13+ (2019+) →  虚拟线程 + NIO 结合
 *
 * 所以现在开发，几乎都用 NIO。
 * BIO即 blocking io，使用 InutputStream/OutputStream，不支持异步、多线程、文件锁等特性。
 *
 * NIO即 non-blocking io，使用 Path/Files/FileChannel，支持异步、多线程、文件锁等特性。
 * =====================================================
 */
public class FilesLesson {

    public static void main(String[] args) {
        try {
            System.out.println("========== Path基础 ==========");
            pathBasics();

            System.out.println("\n========== 文件创建 ==========");
            fileCreation();

            System.out.println("\n========== 文件读取 ==========");
            fileReading();

            System.out.println("\n========== 文件写入 ==========");
            fileWriting();

            System.out.println("\n========== 文件复制移动 ==========");
            fileCopyMove();

            System.out.println("\n========== 文件删除 ==========");
            fileDeletion();

            System.out.println("\n========== 文件属性 ==========");
            fileAttributes();

            System.out.println("\n========== 目录操作 ==========");
            directoryOperations();

            System.out.println("\n========== 文件遍历 ==========");
            fileTraversal();

            System.out.println("\n========== 文件查找 ==========");
            fileSearch();

            System.out.println("\n========== 符号链接 ==========");
            symbolicLinks();

            System.out.println("\n========== 临时文件 ==========");
            temporaryFiles();

            System.out.println("\n========== 文件监视 ==========");
            fileWatch();

            System.out.println("\n========== 注意事项 ==========");
            cautions();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================================================
    // 一、Path基础
    // ================================================
    public static void pathBasics() throws IOException {
        System.out.println("--- Path基础 ---");

        // 【1】创建Path对象
        Path path1 = Paths.get("/tmp/test.txt");
        Path path2 = Path.of("/tmp/test.txt");  // JDK11+
        System.out.println("Path: " + path1);

        // 【2】Path信息获取
        Path path = Paths.get("/home/user/docs/file.txt");
        System.out.println("文件名: " + path.getFileName());           // file.txt
        System.out.println("父目录: " + path.getParent());             // /home/user/docs
        System.out.println("根目录: " + path.getRoot());               // /
        System.out.println("名称数量: " + path.getNameCount());         // 3
        System.out.println("第1个名称: " + path.getName(0));            // home
        System.out.println("绝对路径: " + path.toAbsolutePath());       // /home/user/docs/file.txt

        // 【3】Path转换
        Path relative = Paths.get("docs/file.txt");
        Path absolute = relative.toAbsolutePath();
        System.out.println("相对转绝对: " + absolute);

        // 【4】Path解析
        Path normalized = Paths.get("/home/./user/../user/docs/file.txt").normalize();
        System.out.println("标准化: " + normalized);  // /home/user/docs/file.txt

        // 【5】Path组合
        Path base = Paths.get("/home/user");
        Path combined = base.resolve("docs/file.txt");
        System.out.println("组合路径: " + combined);  // /home/user/docs/file.txt

        // 【6】Path相对化
        Path pathA = Paths.get("/home/user/docs");
        Path pathB = Paths.get("/home/user/pictures");
        Path relativePath = pathA.relativize(pathB);
        System.out.println("相对路径: " + relativePath);  // ../pictures

        // 【7】Path判断
        Path existingPath = Paths.get("/tmp");
        System.out.println("是否存在: " + Files.exists(existingPath));
        System.out.println("是否绝对: " + existingPath.isAbsolute());
    }

    // ================================================
    // 二、文件创建
    // ================================================
    public static void fileCreation() throws IOException {
        System.out.println("--- 文件创建 ---");

        Path tempDir = Paths.get("/tmp/files-lesson");

        // 【1】创建目录
        if (!Files.exists(tempDir)) {
            Files.createDirectory(tempDir);
            System.out.println("创建目录: " + tempDir);
        }

        // 【2】创建多级目录
        Path multiLevel = tempDir.resolve("a/b/c");
        Files.createDirectories(multiLevel);
        System.out.println("创建多级目录: " + multiLevel);

        // 【3】创建文件
        Path newFile = tempDir.resolve("test.txt");
        if (!Files.exists(newFile)) {
            Files.createFile(newFile);
            System.out.println("创建文件: " + newFile);
        }

        // 【4】创建文件并指定权限（Unix系统）
        // Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-r--r--");
        // Files.createFile(tempDir.resolve("restricted.txt"), PosixFilePermissions.asFileAttribute(perms));

        // 【5】创建临时文件
        Path tempFile = Files.createTempFile(tempDir, "prefix-", ".txt");
        System.out.println("临时文件: " + tempFile);

        // 【6】创建临时目录
        Path tempDir2 = Files.createTempDirectory(tempDir, "temp-dir-");
        System.out.println("临时目录: " + tempDir2);

        // 清理
        Files.deleteIfExists(tempFile);
        Files.deleteIfExists(tempDir2);
    }

    // ================================================
    // 三、文件读取
    // ================================================
    public static void fileReading() throws IOException {
        System.out.println("--- 文件读取 ---");

        Path testFile = Paths.get("/tmp/files-lesson/test.txt");

        // 先写入测试内容
        Files.writeString(testFile, "Hello World\nJava NIO\nFiles API");

        // 【1】读取所有字节
        byte[] bytes = Files.readAllBytes(testFile);
        System.out.println("读取字节数: " + bytes.length);

        // 【2】读取所有行
        java.util.List<String> lines = Files.readAllLines(testFile);
        System.out.println("读取行数: " + lines.size());
        lines.forEach(line -> System.out.println("  " + line));

        // 【3】逐行读取（适合大文件）
        System.out.println("\n逐行读取:");
        try (Stream<String> stream = Files.lines(testFile)) {
            stream.forEach(line -> System.out.println("  " + line));
        }

        // 【4】使用BufferedReader
        System.out.println("\nBufferedReader读取:");
        try (java.io.BufferedReader reader = Files.newBufferedReader(testFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  " + line);
            }
        }

        // 【5】读取小文本文件（JDK11+）
        String content = Files.readString(testFile);
        System.out.println("\nreadString: " + content);

        // 【6】带选项读取
        String utf8Content = Files.readString(testFile, java.nio.charset.StandardCharsets.UTF_8);
        System.out.println("UTF-8读取: " + utf8Content);
    }

    // ================================================
    // 四、文件写入
    // ================================================
    public static void fileWriting() throws IOException {
        System.out.println("--- 文件写入 ---");

        Path testFile = Paths.get("/tmp/files-lesson/write-test.txt");

        // 【1】写入字节数组
        byte[] data = "Hello Bytes".getBytes();
        Files.write(testFile, data);
        System.out.println("写入字节: " + Files.size(testFile) + " bytes");

        // 【2】写入字符串（JDK11+）
        Files.writeString(testFile, "Hello String");
        System.out.println("写入字符串: " + Files.readString(testFile));

        // 【3】写入多行文本
        java.util.List<String> lines = java.util.Arrays.asList(
            "Line 1",
            "Line 2",
            "Line 3"
        );
        Files.write(testFile, lines);
        System.out.println("写入多行:");
        Files.readAllLines(testFile).forEach(line -> System.out.println("  " + line));

        // 【4】追加写入
        Files.writeString(testFile, "\nAppended Line", StandardOpenOption.APPEND);
        System.out.println("\n追加后:");
        System.out.println(Files.readString(testFile));

        // 【5】使用BufferedWriter
        try (java.io.BufferedWriter writer = Files.newBufferedWriter(testFile)) {
            writer.write("Buffered Writer");
            writer.newLine();
            writer.write("Second Line");
        }
        System.out.println("\nBufferedWriter写入: " + Files.readString(testFile));

        // 【6】带选项写入
        Files.writeString(testFile, "New Content",
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE);
        System.out.println("带选项写入: " + Files.readString(testFile));

        // 清理
        Files.deleteIfExists(testFile);
    }

    // ================================================
    // 五、文件复制移动
    // ================================================
    public static void fileCopyMove() throws IOException {
        System.out.println("--- 文件复制移动 ---");

        Path source = Paths.get("/tmp/files-lesson/source.txt");
        Path target = Paths.get("/tmp/files-lesson/target.txt");
        Path backup = Paths.get("/tmp/files-lesson/backup.txt");

        // 创建源文件
        Files.writeString(source, "Original Content");

        // 【1】复制文件
        Files.copy(source, target);
        System.out.println("复制文件: " + target);
        System.out.println("目标内容: " + Files.readString(target));

        // 【2】复制时覆盖
        Files.writeString(target, "Old Content");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("覆盖复制: " + Files.readString(target));

        // 【3】复制保留属性
        Files.copy(source, backup, StandardCopyOption.COPY_ATTRIBUTES);
        System.out.println("保留属性复制: " + backup);

        // 【4】移动/重命名文件
        Path moved = Paths.get("/tmp/files-lesson/moved.txt");
        Files.move(target, moved);
        System.out.println("移动文件: " + moved);
        System.out.println("原文件存在: " + Files.exists(target));

        // 【5】移动时覆盖
        Files.writeString(target, "Another Content");
        Files.move(target, moved, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("覆盖移动: " + Files.readString(moved));

        // 【6】原子移动（同文件系统）
        try {
            Files.move(moved, backup, StandardCopyOption.ATOMIC_MOVE);
            System.out.println("原子移动成功");
        } catch (AtomicMoveNotSupportedException e) {
            System.out.println("不支持原子移动");
        }

        // 清理
        Files.deleteIfExists(source);
        Files.deleteIfExists(backup);
    }

    // ================================================
    // 六、文件删除
    // ================================================
    public static void fileDeletion() throws IOException {
        System.out.println("--- 文件删除 ---");

        Path testFile = Paths.get("/tmp/files-lesson/delete-test.txt");
        Path testDir = Paths.get("/tmp/files-lesson/delete-dir");

        // 创建测试文件和目录
        Files.writeString(testFile, "To be deleted");
        Files.createDirectory(testDir);

        // 【1】删除文件
        Files.delete(testFile);
        System.out.println("删除文件: " + testFile);
        System.out.println("文件存在: " + Files.exists(testFile));

        // 【2】安全删除（不存在时不抛异常）
        Files.deleteIfExists(testFile);
        System.out.println("安全删除（已不存在）: " + !Files.exists(testFile));

        // 【3】删除空目录
        Files.delete(testDir);
        System.out.println("删除目录: " + testDir);

        // 【4】递归删除目录（需要自己实现）
        Path dirToDelete = Paths.get("/tmp/files-lesson/dir-to-delete");
        Files.createDirectories(dirToDelete.resolve("subdir"));
        Files.writeString(dirToDelete.resolve("file.txt"), "content");

        deleteRecursively(dirToDelete);
        System.out.println("递归删除目录: " + dirToDelete);
        System.out.println("目录存在: " + Files.exists(dirToDelete));
    }

    // 递归删除目录
    private static void deleteRecursively(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (Stream<Path> paths = Files.list(path)) {
                paths.forEach(p -> {
                    try {
                        deleteRecursively(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        Files.delete(path);
    }

    // ================================================
    // 七、文件属性
    // ================================================
    public static void fileAttributes() throws IOException {
        System.out.println("--- 文件属性 ---");

        Path testFile = Paths.get("/tmp/files-lesson/test.txt");
        if (!Files.exists(testFile)) {
            Files.writeString(testFile, "Test content");
        }

        // 【1】基本属性
        BasicFileAttributes attrs = Files.readAttributes(testFile, BasicFileAttributes.class);
        System.out.println("文件大小: " + attrs.size() + " bytes");
        System.out.println("创建时间: " + attrs.creationTime());
        System.out.println("最后修改: " + attrs.lastModifiedTime());
        System.out.println("最后访问: " + attrs.lastAccessTime());
        System.out.println("是否文件: " + attrs.isRegularFile());
        System.out.println("是否目录: " + attrs.isDirectory());
        System.out.println("是否符号链接: " + attrs.isSymbolicLink());

        // 【2】文件类型判断
        System.out.println("\n文件类型判断:");
        System.out.println("exists: " + Files.exists(testFile));
        System.out.println("isRegularFile: " + Files.isRegularFile(testFile));
        System.out.println("isDirectory: " + Files.isDirectory(testFile));
        System.out.println("isReadable: " + Files.isReadable(testFile));
        System.out.println("isWritable: " + Files.isWritable(testFile));
        System.out.println("isExecutable: " + Files.isExecutable(testFile));
        System.out.println("isHidden: " + Files.isHidden(testFile));

        // 【3】文件大小
        long size = Files.size(testFile);
        System.out.println("\n文件大小: " + size + " bytes");

        // 【4】文件所有者（Unix系统）
        try {
            java.nio.file.attribute.PosixFileAttributes posixAttrs =
                Files.readAttributes(testFile, java.nio.file.attribute.PosixFileAttributes.class);
            System.out.println("所有者: " + posixAttrs.owner().getName());
            System.out.println("组: " + posixAttrs.group().getName());
            System.out.println("权限: " + java.nio.file.attribute.PosixFilePermissions.toString(posixAttrs.permissions()));
        } catch (UnsupportedOperationException e) {
            System.out.println("当前系统不支持POSIX属性");
        }

        // 【5】修改文件时间
        FileTime now = FileTime.fromMillis(System.currentTimeMillis());
        Files.setLastModifiedTime(testFile, now);
        System.out.println("\n修改最后访问时间后: " + Files.getLastModifiedTime(testFile));
    }

    // ================================================
    // 八、目录操作
    // ================================================
    public static void directoryOperations() throws IOException {
        System.out.println("--- 目录操作 ---");

        Path testDir = Paths.get("/tmp/files-lesson/dir-ops");
        Files.createDirectories(testDir);

        // 创建测试文件
        Files.writeString(testDir.resolve("file1.txt"), "Content 1");
        Files.writeString(testDir.resolve("file2.txt"), "Content 2");
        Files.createDirectory(testDir.resolve("subdir"));

        // 【1】列出目录内容
        System.out.println("目录内容:");
        try (Stream<Path> paths = Files.list(testDir)) {
            paths.forEach(path -> {
                String type = Files.isDirectory(path) ? "[DIR]" : "[FILE]";
                System.out.println("  " + type + " " + path.getFileName());
            });
        }

        // 【2】过滤目录内容
        System.out.println("\n只列文件:");
        try (Stream<Path> paths = Files.list(testDir)) {
            paths.filter(Files::isRegularFile)
                 .forEach(path -> System.out.println("  " + path.getFileName()));
        }

        // 【3】检查目录是否为空
        boolean isEmpty = Files.list(testDir).findAny().isEmpty();
        System.out.println("\n目录是否为空: " + isEmpty);

        // 【4】创建符号链接目录
        Path linkDir = Paths.get("/tmp/files-lesson/link-dir");
        if (!Files.exists(linkDir)) {
            Files.createSymbolicLink(linkDir, testDir);
            System.out.println("\n创建符号链接目录: " + linkDir + " -> " + testDir);
        }

        // 清理
        Files.deleteIfExists(linkDir);
    }

    // ================================================
    // 九、文件遍历
    // ================================================
    public static void fileTraversal() throws IOException {
        System.out.println("--- 文件遍历 ---");

        Path testDir = Paths.get("/tmp/files-lesson/traversal");
        Files.createDirectories(testDir.resolve("subdir1/subdir2"));
        Files.writeString(testDir.resolve("file1.txt"), "Content 1");
        Files.writeString(testDir.resolve("subdir1/file2.txt"), "Content 2");
        Files.writeString(testDir.resolve("subdir1/subdir2/file3.txt"), "Content 3");

        // 【1】浅层遍历（只遍历当前目录）
        System.out.println("浅层遍历:");
        try (Stream<Path> paths = Files.list(testDir)) {
            paths.forEach(path -> System.out.println("  " + path));
        }

        // 【2】深层遍历（递归遍历所有子目录）
        System.out.println("\n深层遍历:");
        try (Stream<Path> paths = Files.walk(testDir)) {
            paths.forEach(path -> {
                int depth = testDir.relativize(path).getNameCount();
                String indent = "  ".repeat(depth);
                String type = Files.isDirectory(path) ? "[DIR]" : "[FILE]";
                System.out.println(indent + type + " " + path.getFileName());
            });
        }

        // 【3】限制深度的遍历
        System.out.println("\n限制深度为1:");
        try (Stream<Path> paths = Files.walk(testDir, 1)) {
            paths.forEach(path -> System.out.println("  " + path.getFileName()));
        }

        // 【4】文件树遍历（更灵活）
        System.out.println("\n文件树遍历:");
        Files.walkFileTree(testDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.println("  文件: " + testDir.relativize(file));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println("  目录: " + testDir.relativize(dir));
                return FileVisitResult.CONTINUE;
            }
        });

        // 清理
        deleteRecursively(testDir);
    }

    // ================================================
    // 十、文件查找
    // ================================================
    public static void fileSearch() throws IOException {
        System.out.println("--- 文件查找 ---");

        Path searchDir = Paths.get("/tmp/files-lesson/search");
        Files.createDirectories(searchDir);
        Files.writeString(searchDir.resolve("test.java"), "Java code");
        Files.writeString(searchDir.resolve("test.txt"), "Text file");
        Files.writeString(searchDir.resolve("readme.md"), "Markdown");

        // 【1】按扩展名查找
        System.out.println("查找.java文件:");
        try (Stream<Path> paths = Files.find(searchDir, 10,
                (path, attrs) -> path.toString().endsWith(".java"))) {
            paths.forEach(path -> System.out.println("  " + path.getFileName()));
        }

        // 【2】按文件大小查找
        System.out.println("\n查找大于0字节的文件:");
        try (Stream<Path> paths = Files.find(searchDir, 10,
                (path, attrs) -> attrs.size() > 0 && attrs.isRegularFile())) {
            paths.forEach(path -> System.out.println("  " + path.getFileName() ));
        }

        // 【3】按修改时间查找
        System.out.println("\n查找最近修改的文件:");
        FileTime oneDayAgo = FileTime.fromMillis(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        try (Stream<Path> paths = Files.find(searchDir, 10,
                (path, attrs) -> attrs.lastModifiedTime().compareTo(oneDayAgo) > 0)) {
            paths.forEach(path -> System.out.println("  " + path.getFileName()));
        }

        // 【4】复杂条件查找
        System.out.println("\n查找.txt或.md文件:");
        try (Stream<Path> paths = Files.find(searchDir, 10,
                (path, attrs) -> (path.toString().endsWith(".txt") || path.toString().endsWith(".md"))
                    && attrs.isRegularFile())) {
            paths.forEach(path -> System.out.println("  " + path.getFileName()));
        }

        // 清理
        deleteRecursively(searchDir);
    }

    // ================================================
    // 十一、符号链接
    // ================================================
    public static void symbolicLinks() throws IOException {
        System.out.println("--- 符号链接 ---");

        Path original = Paths.get("/tmp/files-lesson/original.txt");
        Path link = Paths.get("/tmp/files-lesson/link.txt");

        // 创建原文件
        Files.writeString(original, "Original content");

        // 【1】创建符号链接
        if (!Files.exists(link)) {
            Files.createSymbolicLink(link, original);
            System.out.println("创建符号链接: " + link + " -> " + original);
        }

        // 【2】读取符号链接目标
        Path target = Files.readSymbolicLink(link);
        System.out.println("链接目标: " + target);

        // 【3】判断是否是符号链接
        System.out.println("是符号链接: " + Files.isSymbolicLink(link));
        System.out.println("是符号链接: " + Files.isSymbolicLink(original));

        // 【4】通过符号链接读写
        String content = Files.readString(link);
        System.out.println("通过链接读取: " + content);

        Files.writeString(link, "Modified via link");
        System.out.println("通过链接写入后: " + Files.readString(original));

        // 【5】删除符号链接（不删除原文件）
        Files.delete(link);
        System.out.println("\n删除符号链接后原文件仍存在: " + Files.exists(original));

        // 清理
        Files.deleteIfExists(original);
    }

    // ================================================
    // 十二、临时文件
    // ================================================
    public static void temporaryFiles() throws IOException {
        System.out.println("--- 临时文件 ---");

        // 【1】创建临时文件
        Path tempFile = Files.createTempFile("myapp-", ".tmp");
        System.out.println("临时文件: " + tempFile);
        Files.writeString(tempFile, "Temporary data");

        // 【2】创建临时目录
        Path tempDir = Files.createTempDirectory("myapp-");
        System.out.println("临时目录: " + tempDir);

        // 【3】在指定目录创建临时文件
        Path customTemp = Files.createTempFile(Paths.get("/tmp"), "custom-", ".dat");
        System.out.println("自定义临时文件: " + customTemp);

        // 【4】注册删除钩子（JVM退出时删除）
        tempFile.toFile().deleteOnExit();
        tempDir.toFile().deleteOnExit();

        System.out.println("\n提示: 临时文件应在不再使用时手动删除");

        // 手动清理
        Files.deleteIfExists(tempFile);
        Files.deleteIfExists(customTemp);
        deleteRecursively(tempDir);
    }

    // ================================================
    // 十三、文件监视
    // ================================================
    public static void fileWatch() throws IOException {
        System.out.println("--- 文件监视 ---");

        Path watchDir = Paths.get("/tmp/files-lesson/watch");
        Files.createDirectories(watchDir);

        System.out.println("文件监视示例（演示API，实际使用需单独线程）");

        // 【1】创建WatchService
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            // 【2】注册监视目录
            WatchKey key = watchDir.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

            System.out.println("已注册监视: " + watchDir);
            System.out.println("监视事件: CREATE, DELETE, MODIFY");

            // 【3】处理事件（简化示例，实际需要循环监听）
            System.out.println("\n注意: 实际使用需要在单独线程中循环调用take()/poll()");
            System.out.println("示例代码:");
            System.out.println("  while (true) {");
            System.out.println("    WatchKey key = watchService.take();");
            System.out.println("    for (WatchEvent<?> event : key.pollEvents()) {");
            System.out.println("      System.out.println(event.kind() + event.context());");
            System.out.println("    }");
            System.out.println("    key.reset();");
            System.out.println("  }");
        }

        // 清理
        deleteRecursively(watchDir);
    }

    // ================================================
    // 十四、注意事项
    // ================================================
    public static void cautions() throws IOException {
        System.out.println("--- 注意事项 ---");

        // 【1】资源管理
        System.out.println("\n1. 资源管理:");
        System.out.println("   - Stream必须关闭（使用try-with-resources）");
        System.out.println("   - BufferedReader/Writer必须关闭");
        System.out.println("   - WatchService必须关闭");

        // 【2】异常处理
        System.out.println("\n2. 异常处理:");
        System.out.println("   - Files方法抛出IOException");
        System.out.println("   - 使用try-catch或throws");
        System.out.println("   - NoSuchFileException: 文件不存在");
        System.out.println("   - FileAlreadyExistsException: 文件已存在");
        System.out.println("   - AccessDeniedException: 权限不足");

        // 【3】路径安全
        System.out.println("\n3. 路径安全:");
        System.out.println("   - 验证用户输入的路径");
        System.out.println("   - 防止路径遍历攻击（../）");
        System.out.println("   - 使用normalize()标准化路径");

        // 【4】性能考虑
        System.out.println("\n4. 性能考虑:");
        System.out.println("   - 大文件使用Stream逐行读取");
        System.out.println("   - 避免readAllBytes/readAllLines读取大文件");
        System.out.println("   - 批量操作比逐个操作快");

        // 【5】跨平台兼容
        System.out.println("\n5. 跨平台:");
        System.out.println("   - 使用Path而不是字符串拼接路径");
        System.out.println("   - 使用resolve()组合路径");
        System.out.println("   - POSIX属性只在Unix系统可用");

        // 【6】符号链接
        System.out.println("\n6. 符号链接:");
        System.out.println("   - 某些操作会自动跟随符号链接");
        System.out.println("   - 使用NOFOLLOW_LINKS选项控制");
        System.out.println("   - Windows需要管理员权限创建符号链接");

        // 【7】并发访问
        System.out.println("\n7. 并发访问:");
        System.out.println("   - Files不是线程安全的");
        System.out.println("   - 多个线程操作同一文件需要同步");
        System.out.println("   - 考虑使用FileLock");

        // 【8】编码问题
        System.out.println("\n8. 编码:");
        System.out.println("   - 明确指定字符编码");
        System.out.println("   - 默认编码可能因平台而异");
        System.out.println("   - 推荐使用StandardCharsets.UTF_8");

        // 【9】临时文件清理
        System.out.println("\n9. 临时文件:");
        System.out.println("   - 及时删除不再需要的临时文件");
        System.out.println("   - deleteOnExit()只在JVM正常退出时生效");
        System.out.println("   - 最好在finally块中删除");

        // 【10】Files vs File
        System.out.println("\n10. Files vs File:");
        System.out.println("   - 优先使用Files API");
        System.out.println("   - File.toPath()可以转换");
        System.out.println("   - Path.toFile()可以转换");

        // 演示转换
        java.io.File oldFile = new java.io.File("/tmp/test.txt");
        Path newPath = oldFile.toPath();
        java.io.File backToFile = newPath.toFile();
        System.out.println("   File -> Path -> File: " + backToFile.getPath());
    }
}
