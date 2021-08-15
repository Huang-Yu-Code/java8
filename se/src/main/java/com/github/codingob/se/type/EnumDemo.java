package com.github.codingob.se.type;

/**
 * 枚举
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class EnumDemo {
    public static void main(String[] args) {
        for (WeekDay weekDay : WeekDay.values()) {
            System.out.println(weekDay.index);
            System.out.println(weekDay.day);
        }
        System.out.println(WeekDay.Monday);
        System.out.println(WeekDay.valueOf("星期一").compareTo(WeekDay.Monday));
    }

    enum WeekDay {
        /**
         * 星期
         */
        Monday("星期一", 1),
        Tuesday("星期二", 2),
        Wednesday("星期三", 3),
        Thursday("星期四", 4),
        Friday("星期五", 5),
        Saturday("星期六", 6),
        Sunday("星期七", 7);

        private final String day;
        private final int index;

        WeekDay(String day, int index) {
            this.day = day;
            this.index = index;
        }

    }
}
