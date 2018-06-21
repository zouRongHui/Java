package org.rone.study.java.grammar.java8;

import java.time.*;

/**
 * Created by zouRongHui on 2018/6/19.
 * java8 的时间API
 */
public class TimeTest {
    public static void main(String[] args) {
        // Get the system clock as UTC offset
        Clock clock = Clock.systemUTC();
        System.out.println( clock.instant() );
        System.out.println( clock.millis() );
        /*
        2018-06-19T12:34:42.537Z
        1529411682585
         */

        // Get the local date
        // 仅日期信息
        LocalDate date = LocalDate.now();
        LocalDate dateFromClock = LocalDate.now( clock );
        System.out.println( date );
        System.out.println( dateFromClock );
        /*
        2018-06-19
        2018-06-19
         */

        // Get the local time
        // 仅时间信息
        LocalTime time = LocalTime.now();
        LocalTime timeFromClock = LocalTime.now( clock );
        System.out.println( time );
        System.out.println( timeFromClock );
        /*
        20:34:42.593
        12:34:42.593
         */

        // Get the local date/time
        LocalDateTime datetime = LocalDateTime.now();
        LocalDateTime datetimeFromClock = LocalDateTime.now( clock );
        System.out.println( datetime );
        System.out.println( datetimeFromClock );
        /*
        2018-06-19T20:34:42.594
        2018-06-19T12:34:42.594
         */

        // Get the zoned date/time
        // 获取当前时区的时间
        ZonedDateTime zonedDatetime = ZonedDateTime.now();
        ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now( clock );
        ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );
        System.out.println( zonedDatetime );
        System.out.println( zonedDatetimeFromClock );
        System.out.println( zonedDatetimeFromZone );
        /*
        2018-06-19T20:34:42.594+08:00[Asia/Shanghai]
        2018-06-19T12:34:42.594Z
        2018-06-19T05:34:42.595-07:00[America/Los_Angeles]
         */

        // Get duration between two dates
        LocalDateTime from = LocalDateTime.of( 2014, Month.APRIL, 16, 0, 0, 0 );
        LocalDateTime to = LocalDateTime.of( 2015, Month.APRIL, 16, 23, 59, 59 );
        Duration duration = Duration.between( from, to );
        System.out.println( "Duration in days: " + duration.toDays() );
        System.out.println( "Duration in hours: " + duration.toHours() );
        /*
        Duration in days: 365
        Duration in hours: 8783
         */
    }
}
