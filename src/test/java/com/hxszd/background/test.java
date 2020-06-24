package com.hxszd.background;

import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-05-14 16:50
 **/
public class test {

    public static void main(String[] args) throws IOException {
        /*String filePath = "H:\\a.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
        //BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        StringBuilder sb = new StringBuilder("");
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + ",");
        }
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());*/

//        // 1 true
//        float a = 0.125f; double b = 0.125d;
//        System.out.println((a - b) == 0.0);


//        // 2 false
//        double c = 0.8; double d = 0.7; double e = 0.6;
//        System.out.println((c-d) == (d-e));

        // 3 Infinity
//        System.out.println(1.0 / 0);

        // 4 NaN
//        System.out.println(0.0 / 0.0);

        // 5 >> 和 >>> 的区别是？ D
//        A. 任何整数没有区别
//        B. 负整数一定没有区别
//        C. 浮点数可以 >> 运算，但是不可以 >>> 运算
//        D. 正整数一定没有区别
        // 6 D java会根据类型匹配 String和Integer都可以为null 编译会报错
//        某个类有两个重载方法：void f(String s) 和 void f(Integer i)，那么 f(null) 的会调用哪个方法？
//
//        A. 前者
//        B. 后者
//        C. 随机调用
//        D. 编译出错

        // 7 A 跟上题一样，java根据类型匹配，1会被自动转换为基本数据类型，并进行自动匹配，1可以被转换为int double long 如果new Integer(1)则调用
//        某个类有两个重载方法：void g(double d) 和 void g(Integer i)，那么 g(1) 的会调用哪个方法？
//
//        A. 前者
//        B. 后者
//        C. 随机调用
//        D. 编译出错


       /* UnderWayOrder order = new UnderWayOrder();
        order.mothod(new Integer(1));


        String a = null;*/

        /*InputStream inputStream = null;
        ServerSocket server = null;
        Socket socket = null;

       try {
           // 监听指定的端口
           int port = 55533;
           server = new ServerSocket(port);

           while (true) {

               // server将一直等待连接的到来
               System.out.println("server将一直等待连接的到来");
               socket = server.accept();
               // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
               inputStream = socket.getInputStream();
               byte[] bytes = new byte[1024];
               int len;
               StringBuilder sb = new StringBuilder();
               while ((len = inputStream.read(bytes)) != -1) {
                   //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                   sb.append(new String(bytes, 0, len, "UTF-8"));
               }
               System.out.println("get message from client: " + sb);
           }
       }finally {
           inputStream.close();
           socket.close();
           server.close();
       }*/
        /*Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, -2);
        Date d3 = calendar.getTime();

        List<Date> timeList = Arrays.asList(null, d1, d2, d3);

        System.out.println(timeList);
        List<Date> time = timeList.stream().sorted(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return o1.getTime() > o2.getTime() ? 1 : (o1.getTime() == o2.getTime() ? 0 : -1);
            }
        }).collect(Collectors.toList());

        System.out.println();
        System.out.println(time);*/

        /*String s1 = "aaa";
        String s2 = "bbb" + "ccc";
        String s3 = s1 + "bbb";
        //String s1 = "aaa";
        String s4 = new String("aaa");*/
        //System.out.println();

        /*StringBuffer b = new StringBuffer("111");
        b.append("222");*/
        /*System.out.println(new StringBuffer().append("111").append("222"));
        System.out.println(new StringBuilder().append("aaa").append("bbb"));*/

        /*List a = new ArrayList();
        a.iterator();*/

        /*List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            String x = "第" + i + "条数";
            list.add(x);
        }
        long st1 = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {

        }
        long st2 = System.currentTimeMillis();
        System.out.println("for : " + (st2 - st1));
        st1 = System.currentTimeMillis();
        for (String s : list) {

        }
        st2 = System.currentTimeMillis();
        System.out.println("for : " + (st2 - st1));
        st1 = System.currentTimeMillis();
        list.forEach(item -> {

        });
        st2 = System.currentTimeMillis();
        System.out.println("for : " + (st2 - st1));
        st1 = System.currentTimeMillis();
        list.stream().forEach(item -> {

        });
        st2 = System.currentTimeMillis();
        System.out.println("for : " + (st2 - st1));*/

        // System.out.println(LocalDate.now().plus(1, ChronoUnit.HOURS));
        /*System.out.println(LocalDate.now());
        System.out.println(LocalTime.now().plus(2, ChronoUnit.HOURS));
        System.out.println(YearMonth.from(LocalDate.now()));
        System.out.println(MonthDay.from(LocalDate.now()));
        //System.out.println(MonthDay.from(LocalDateTime.now().getLong(Instant.)));

        //System.out.println(Date.from(Instant.from(LocalDate.now())));
        System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());*/

        System.out.println(3 << 2);


        LocalDateTime time = LocalDateTime.now();
        LocalDateTime midday = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0));

        System.out.println(midday);
        if (time.compareTo(midday) == -1) {
            System.out.println(time);
        } else {
            System.out.println(time.plusDays(1));
        }




    }
}
