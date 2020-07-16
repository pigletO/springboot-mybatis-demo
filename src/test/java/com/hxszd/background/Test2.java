package com.hxszd.background;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-06-19 17:56
 **/
@SpringBootTest
public class Test2 {

    public static void main(String[] args) {

        /*Date startDate = new Date();
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

        *//*System.out.println(now.getDayOfWeek().getValue());

        System.out.println(ri);

        System.out.println(ri.getDayOfWeek().getValue() == 7 ? 0 : ri.getDayOfWeek().getValue());


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        calendar.add(Calendar.DATE, -2);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));*//*
//        System.out.println(nowTime.getHour());

        System.out.println("10139122500003".substring(5,9));

        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        System.out.println(sdf.format(new Date()));


        List source = Arrays.asList("1", "2", "3");
        List target = new ArrayList(source.size());
        BeanUtils.copyProperties(source, target);

        System.out.println(target);*/

        /*List<Long> list1 = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        List<Long> list2 = Arrays.asList(5L, 6L, 7L, 8L, 9L);
        for (Long l : list1) {
            if (list2.contains(l)) {

            }
        }

        list1.stream().filter(l1 -> list2.stream().filter(l2 -> Objects.equals(l1, l2)).collect(Collectors.toList())).collect(Collectors.toList());*/


        /*String s = "2020-07-03 15:42:40.0";

        System.out.println(s.substring(0, s.indexOf(".")));*/

        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(3);

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == 3) {
                arrayList.remove(i);
                //i--;
            }
        }

        System.out.println(arrayList.toString());

    }
}
