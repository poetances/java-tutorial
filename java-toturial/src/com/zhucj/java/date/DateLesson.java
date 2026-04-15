package com.zhucj.java.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Java 日期处理学习教程
 *
 * <p>本类涵盖 Java 日期处理的核心知识点：</p>
 * <ul>
 *   <li>传统 API：Date、SimpleDateFormat、Calendar（了解即可）</li>
 *   <li>新日期 API：LocalDateTime、LocalDate、LocalTime（推荐使用）</li>
 *   <li>日期格式化：DateTimeFormatter</li>
 *   <li>日期计算：加减、比较、差值</li>
 *   <li>时间戳：Instant</li>
 *   <li>新旧 API 互转</li>
 * </ul>
 *
 * <p>Java 日期发展历程：</p>
 * <table border="1">
 *   <tr><th>版本</th><th>类</th><th>说明</th></tr>
 *   <tr><td>Java 1.0</td><td>java.util.Date</td><td>最初设计，缺点多</td></tr>
 *   <tr><td>Java 1.1</td><td>java.util.Calendar</td><td>替代 Date，仍然不好用</td></tr>
 *   <tr><td>Java 8+</td><td>java.time.*</td><td>全新的日期API，推荐使用</td></tr>
 * </table>
 *
 * <p>核心类一览：</p>
 * <ul>
 *   <li>LocalDate：日期，如 2026-04-07</li>
 *   <li>LocalTime：时间，如 10:30:45</li>
 *   <li>LocalDateTime：日期时间，如 2026-04-07 10:30:45</li>
 *   <li>Instant：时间戳</li>
 *   <li>Duration：时间段（秒/纳秒）</li>
 *   <li>Period：日期段（天/月/年）</li>
 * </ul>
 *
 * @author zhucj
 * @version 1.0
 * @since 2026-04-07
 * @see <a href="https://docs.oracle.com/javase/tutorial/datetime/index.html">Oracle: 日期时间教程</a>
 */
public class DateLesson {

    public static void main(String[] args) {
        // ========== 传统 API（了解即可）==========
        traditionalDateDemo();

        // ========== 新日期 API（推荐使用）==========
        localDateTimeDemo();
        localDateDemo();
        localTimeDemo();
        instantDemo();

        // ========== 格式化 ==========
        formatterDemo();

        // ========== 日期计算 ==========
        calculationDemo();

        // ========== 新旧 API 互转 ==========
        dateConvertDemo();

        // ========== 项目实战 ==========
        projectPracticeDemo();
    }

    /**
     * 传统 Date 类演示（了解即可，不推荐使用）
     */
    public static void traditionalDateDemo() {
        System.out.println("=== 传统 Date 类 ===");

        // 创建当前时间
        Date date = new Date();
        System.out.println("当前时间: " + date);

        // 获取时间戳
        long time = date.getTime();
        System.out.println("时间戳: " + time);

        // 比较
        Date date1 = new Date();
        Date date2 = new Date(1000);  // 1970-01-01 00:00:01
        System.out.println("date1.after(date2): " + date1.after(date2));
        System.out.println("date1.before(date2): " + date1.before(date2));

        // 格式化（SimpleDateFormat 非线程安全，注意！）
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("格式化: " + sdf.format(date));

        // 解析
        try {
            Date parsed = sdf.parse("2026-04-07 10:30:45");
            System.out.println("解析: " + parsed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    /**
     * Calendar 类演示（了解即可）
     */
    public static void calendarDemo() {
        System.out.println("=== Calendar 类 ===");

        // 获取 Calendar 实例
        java.util.Calendar cal = java.util.Calendar.getInstance();
        Date date = cal.getTime();
        System.out.println("当前时间: " + date);

        // 获取各字段（注意：月份从 0 开始）
        System.out.println("年: " + cal.get(java.util.Calendar.YEAR));
        System.out.println("月: " + (cal.get(java.util.Calendar.MONTH) + 1));  // +1
        System.out.println("日: " + cal.get(java.util.Calendar.DAY_OF_MONTH));
        System.out.println("时(24h): " + cal.get(java.util.Calendar.HOUR_OF_DAY));
        System.out.println("分: " + cal.get(java.util.Calendar.MINUTE));
        System.out.println("秒: " + cal.get(java.util.Calendar.SECOND));

        // 设置
        cal.set(java.util.Calendar.YEAR, 2025);
        cal.set(java.util.Calendar.MONTH, 0);  // 1月
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);

        // 加减
        cal.add(java.util.Calendar.DAY_OF_MONTH, 7);  // 加7天
        cal.add(java.util.Calendar.HOUR_OF_DAY, -2);   // 减2小时

        System.out.println();
    }

    /**
     * LocalDateTime 演示（最常用）
     */
    public static void localDateTimeDemo() {
        System.out.println("=== LocalDateTime（最常用）===");

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间: " + now);

        // 创建指定时间
        LocalDateTime dt = LocalDateTime.of(2026, 4, 7, 10, 30, 45);
        System.out.println("指定时间: " + dt);

        // 获取各字段
        System.out.println("年: " + now.getYear());
        System.out.println("月(数字): " + now.getMonthValue());
        System.out.println("月(枚举): " + now.getMonth());       // APRIL
        System.out.println("日: " + now.getDayOfMonth());
        System.out.println("星期: " + now.getDayOfWeek());       // TUESDAY
        System.out.println("时: " + now.getHour());
        System.out.println("分: " + now.getMinute());
        System.out.println("秒: " + now.getSecond());
        System.out.println("纳秒: " + now.getNano());

        // 修改（返回新对象，原对象不变）
        System.out.println("\n--- 时间修改 ---");
        System.out.println("加7天: " + now.plusDays(7));
        System.out.println("减2小时: " + now.minusHours(2));
        System.out.println("修改年份: " + now.withYear(2025));
        System.out.println("修改月份: " + now.withMonth(12));

        // 比较
        System.out.println("\n--- 时间比较 ---");
        LocalDateTime other = now.plusDays(1);
        System.out.println("isAfter: " + now.isAfter(other));
        System.out.println("isBefore: " + now.isBefore(other));
        System.out.println("isEqual: " + now.isEqual(other));

        System.out.println();
    }

    /**
     * LocalDate 演示（只有日期）
     */
    public static void localDateDemo() {
        System.out.println("=== LocalDate（只有日期）===");

        // 获取当前日期
        LocalDate today = LocalDate.now();
        System.out.println("今天: " + today);

        // 创建指定日期
        LocalDate date = LocalDate.of(2026, 4, 7);
        System.out.println("指定日期: " + date);

        // 解析字符串
        LocalDate parsed = LocalDate.parse("2026-04-07");
        System.out.println("解析: " + parsed);

        // 获取字段
        System.out.println("年: " + today.getYear());
        System.out.println("月: " + today.getMonth());
        System.out.println("日: " + today.getDayOfMonth());
        System.out.println("星期: " + today.getDayOfWeek());

        // 判断
        System.out.println("是否闰年: " + today.isLeapYear());
        System.out.println("是否周末: " + (today.getDayOfWeek() == DayOfWeek.SATURDAY 
                                            || today.getDayOfWeek() == DayOfWeek.SUNDAY));

        // 日期加减
        System.out.println("加30天: " + today.plusDays(30));
        System.out.println("减1个月: " + today.minusMonths(1));
        System.out.println("加1年: " + today.plusYears(1));

        // 比较
        LocalDate tomorrow = today.plusDays(1);
        System.out.println("是否相同: " + today.isEqual(tomorrow));

        System.out.println();
    }

    /**
     * LocalTime 演示（只有时间）
     */
    public static void localTimeDemo() {
        System.out.println("=== LocalTime（只有时间）===");

        // 获取当前时间
        LocalTime now = LocalTime.now();
        System.out.println("当前时间: " + now);

        // 创建指定时间
        LocalTime time = LocalTime.of(10, 30, 45);
        System.out.println("指定时间: " + time);

        // 解析
        LocalTime parsed = LocalTime.parse("10:30:45");
        System.out.println("解析: " + parsed);

        // 获取字段
        System.out.println("时: " + now.getHour());
        System.out.println("分: " + now.getMinute());
        System.out.println("秒: " + now.getSecond());
        System.out.println("纳秒: " + now.getNano());

        // 时间比较
        LocalTime later = now.plusHours(1);
        System.out.println("isAfter: " + now.isBefore(later));

        System.out.println();
    }

    /**
     * Instant 时间戳演示
     */
    public static void instantDemo() {
        System.out.println("=== Instant（时间戳）===");

        // 获取当前时间戳
        Instant now = Instant.now();
        System.out.println("当前时间戳: " + now);
        System.out.println("秒: " + now.getEpochSecond());
        System.out.println("毫秒: " + now.toEpochMilli());

        // 创建指定时间戳
        Instant instant = Instant.ofEpochMilli(1744074645000L);
        System.out.println("指定毫秒: " + instant);

        // 时间戳 → LocalDateTime
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("时间戳 → LocalDateTime: " + ldt);

        // LocalDateTime → 时间戳
        long ts = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println("当前时间戳(毫秒): " + ts);

        System.out.println();
    }

    /**
     * 日期格式化演示
     */
    public static void formatterDemo() {
        System.out.println("=== DateTimeFormatter（格式化）===");

        LocalDateTime now = LocalDateTime.now();

        // 常用格式
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter f3 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        DateTimeFormatter f4 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        // LocalDateTime → String
        System.out.println("yyyy-MM-dd: " + now.format(f1));
        System.out.println("yyyy-MM-dd HH:mm:ss: " + now.format(f2));
        System.out.println("yyyy年MM月dd日 HH:mm:ss: " + now.format(f3));
        System.out.println("yyyyMMddHHmmss: " + now.format(f4));

        // String → LocalDateTime
        String str = "2026-04-07 10:30:45";
        LocalDateTime dt = LocalDateTime.parse(str, f2);
        System.out.println("解析: " + dt);

        // ISO 格式直接解析（无需指定格式）
        LocalDateTime isoDt = LocalDateTime.parse("2026-04-07T10:30:45");
        System.out.println("ISO解析: " + isoDt);

        System.out.println();
    }

    /**
     * 日期计算演示
     */
    public static void calculationDemo() {
        System.out.println("=== 日期计算 ===");

        LocalDate d1 = LocalDate.of(2026, 4, 1);
        LocalDate d2 = LocalDate.of(2026, 4, 15);
        LocalDateTime dt1 = LocalDateTime.of(2026, 4, 7, 10, 0);
        LocalDateTime dt2 = LocalDateTime.of(2026, 4, 7, 12, 30);

        // ChronoUnit 计算差值
        long days = ChronoUnit.DAYS.between(d1, d2);
        long hours = ChronoUnit.HOURS.between(dt1, dt2);
        long minutes = ChronoUnit.MINUTES.between(dt1, dt2);
        System.out.println("天数差: " + days);
        System.out.println("小时差: " + hours);
        System.out.println("分钟差: " + minutes);

        // Duration（时间段）
        Duration duration = Duration.between(dt1, dt2);
        System.out.println("Duration.toHours(): " + duration.toHours());
        System.out.println("Duration.toMinutes(): " + duration.toMinutes());

        // Period（日期段）
        LocalDate birth = LocalDate.of(2000, 1, 1);
        Period period = Period.between(birth, LocalDate.now());
        System.out.println("年龄: " + period.getYears() + "岁 " + period.getMonths() + "月 " + period.getDays() + "天");

        // 判断是否到期
        LocalDateTime deadline = LocalDateTime.of(2026, 4, 7, 23, 59);
        System.out.println("是否到期: " + LocalDateTime.now().isAfter(deadline));

        // 获取当天开始/结束时间
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59, 999999999);
        System.out.println("当天开始: " + startOfDay);
        System.out.println("当天结束: " + endOfDay);

        System.out.println();
    }

    /**
     * 新旧 API 互转
     */
    public static void dateConvertDemo() {
        System.out.println("=== 新旧 API 互转 ===");

        // LocalDateTime → Date
        LocalDateTime ldt = LocalDateTime.now();
        Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDateTime → Date: " + date);

        // Date → LocalDateTime
        Date now = new Date();
        LocalDateTime ldt2 = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("Date → LocalDateTime: " + ldt2);

        // LocalDate → Date（需要时间）
        LocalDate ld = LocalDate.now();
        Date dateFromLd = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDate → Date: " + dateFromLd);

        // Date → LocalDate
        LocalDate ld2 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("Date → LocalDate: " + ld2);

        System.out.println();
    }

    /**
     * 项目实战演示
     */
    public static void projectPracticeDemo() {
        System.out.println("=== 项目实战 ===");

        // 1. 订单过期检查
        System.out.println("\n--- 订单过期检查 ---");
        LocalDateTime orderTime = LocalDateTime.now().minusHours(2);
        LocalDateTime expireTime = orderTime.plusHours(24);
        boolean expired = LocalDateTime.now().isAfter(expireTime);
        System.out.println("订单是否过期: " + expired);

        // 2. 会员到期计算
        System.out.println("\n--- 会员到期计算 ---");
        LocalDateTime vipStart = LocalDateTime.now();
        LocalDateTime vipEnd = vipStart.plusYears(1).minusDays(1);
        System.out.println("会员有效期: " + vipStart.toLocalDate() + " 至 " + vipEnd.toLocalDate());

        // 3. 生日计算
        System.out.println("\n--- 生日计算 ---");
        LocalDate birthday = LocalDate.of(2000, 4, 7);
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = birthday.withYear(today.getYear());
        if (thisYearBirthday.isBefore(today)) {
            thisYearBirthday = thisYearBirthday.plusYears(1);
        }
        long daysUntil = ChronoUnit.DAYS.between(today, thisYearBirthday);
        System.out.println("距离下一个生日还有: " + daysUntil + " 天");

        // 4. 定时任务执行时间
        System.out.println("\n--- 定时任务 ---");
        LocalDateTime taskTime = LocalDateTime.now().plusHours(1).withMinute(0).withSecond(0);
        System.out.println("下次整点执行时间: " + taskTime);

        // 5. 日志时间格式化
        System.out.println("\n--- 日志格式化 ---");
        DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String logTime = LocalDateTime.now().format(logFormatter);
        System.out.println("日志时间: [" + logTime + "] INFO xxx");

        System.out.println();
    }
}
