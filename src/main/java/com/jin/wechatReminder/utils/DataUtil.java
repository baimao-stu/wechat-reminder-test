package com.jin.wechatReminder.utils;

import cn.hutool.core.date.ChineseDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author admin
 * @create 2022/8/7 22:07
 */
public class DataUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

    /**
     * 返回一个2022年10月20日格式的日期
     *
     * @return 日期字符串
     */
    public static String getNowDayCh() {
        return LocalDate.now().format(formatter);
    }

    /**
     * 返回今天是星期几
     *
     * @return 星期
     */
    public static String getNowWeekCh() {
        return LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINA);
    }

    /**
     * 返回 2022年10月20日 星期一格式的日期数据
     *
     * @return
     */
    public static String getNowDayAndWeekCh() {
        return getNowDayCh() + " " + getNowWeekCh();
    }

    /**
     * 返回农历信息
     *
     * @return 类似于正月初十
     */
    public static String getGregorianDay() {
        ChineseDate chineseDate = new ChineseDate(LocalDate.now());
        return chineseDate.getChineseMonth() + chineseDate.getChineseDay();
    }

    /**
     * 返回当天的时间，包含公历、星期、农历
     *
     * @return 日期
     */
    public static String getNowDayAndWeekGregorian() {
        return getNowDayCh() + " " + getNowWeekCh() + " 农历 " + getGregorianDay();
    }


    /**
     * 获取当前日期和指定日期之间相差的天数
     *
     * @param date 指定日期
     * @return 天数
     */
    public static String getDiffDays(LocalDate date) {
        LocalDate now = LocalDate.now();
        return String.valueOf(now.toEpochDay() - date.toEpochDay() + 1);
    }

    /**
     * 获取一个日期的每年的周期时间，如结婚纪念日，再获取当前时间至下一个年周期的时间差
     *
     * @param date 指定日期
     * @return 天数
     */
    public static String getDiffDaysYears(LocalDate date) {
        int year = LocalDate.now().getYear();
        LocalDate dayThisYear = LocalDate.of(year, date.getMonth(), date.getDayOfMonth());
        LocalDate now = LocalDate.now();
        if (now.isAfter(date)) {
            dayThisYear = dayThisYear.plusYears(1);
        }
        return String.valueOf(dayThisYear.toEpochDay() - now.toEpochDay());
    }

    /**
     * 根据出生日期，获取对应的公历时间，再获取到该公历的时间的差
     *
     * @param date 出生年月日（公历）
     * @return 相差的天数
     */
    public static String getBirthdayGregorian(LocalDate date) {
        ChineseDate nowChinese = new ChineseDate(LocalDate.now());
        ChineseDate birthday = new ChineseDate(date);
        ChineseDate birthdayNowYear = new ChineseDate(nowChinese.getChineseYear(), birthday.getMonth(), birthday.getDay());
        LocalDate birthdayGregorian = LocalDate.of(birthdayNowYear.getGregorianYear(), birthdayNowYear.getGregorianMonthBase1(), birthdayNowYear.getGregorianDay());
        return getDiffDaysYears(birthdayGregorian);
    }

    /**
     * 获取当前日期和指定日期相差多少年
     *
     * @param date 指定日期
     * @return
     */
    public static String getTwoDayYears(LocalDate date) {
        return String.valueOf(LocalDate.now().getYear() - date.getYear());
    }

    /**
     * 判断是否是工作日
     *
     * @return true workday
     */
    public static boolean isWorkDay() {
        String name = LocalDate.now().getDayOfWeek().name();
        return !("SATURDAY".equalsIgnoreCase(name) || "SUNDAY".equalsIgnoreCase(name));
    }
}
