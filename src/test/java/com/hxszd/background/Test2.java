package com.hxszd.background;

import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-06-19 17:56
 **/
@SpringBootTest
public class Test2 {

    public static void main(String[] args) {

        Date startDate = new Date();
        LocalDate start = startDate.toInstant().atZone(ZoneId.of("GMT+8")).toLocalDate();
        LocalDate start2 = startDate.toInstant().atZone(ZoneId.of("GMT-12")).toLocalDate();
        LocalDate now = LocalDate.now();
        LocalDateTime nowTime = LocalDateTime.now();

//        System.out.println(start);
//        System.out.println(start2);
//
//        System.out.println(start.equals(now));
        LocalDate ri = LocalDate.of(2020, 6, 21);

        System.out.println(ChronoUnit.DAYS.between(ri, now));

        /*System.out.println(now.getDayOfWeek().getValue());

        System.out.println(ri);

        System.out.println(ri.getDayOfWeek().getValue() == 7 ? 0 : ri.getDayOfWeek().getValue());


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        calendar.add(Calendar.DATE, -2);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));*/
//        System.out.println(nowTime.getHour());

        System.out.println("10139122500003".substring(5,9));

        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        System.out.println(sdf.format(new Date()));
    }
}
