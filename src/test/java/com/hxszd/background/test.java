package com.hxszd.background;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-05-14 16:50
 **/
public class test {

    static int num = 0;
    static AtomicInteger n = new AtomicInteger(0);
    public static void main(String[] args) throws Exception {
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

        /*System.out.println(3 << 2);


        LocalDateTime time = LocalDateTime.now();
        LocalDateTime midday = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0));

        System.out.println(midday);
        if (time.compareTo(midday) == -1) {
            System.out.println(time);
        } else {
            System.out.println(time.plusDays(1));
        }*/

                /*if ( false == true ) {
                    //these characters are magic: \u000a\u007d\u007b
                    System.out.println("false is true!");
                }*/

        /*List list = Arrays.asList(1, 2, 3);
        System.out.println(list.subList(0, 1));
        System.out.println(list.subList(1, 2));

        Collections.reverse(list);

        System.out.println(list);*/


        /*SimpleDateFormat sdf = new SimpleDateFormat("M月dd日 HH:mm");
        //Date date = new Date(System.currentTimeMillis());

        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 12, 17, 48, 22);
        Date date = Date.from(localDateTime.atZone(ZoneId.of("GMT+8")).toInstant());

        System.out.println(sdf.format(date));
*/

            /*byte[] encryptedData = Base64.decodeBase64("通过小程序授权获取到的encryptedData");
            byte[] ivData = Base64.decodeBase64("通过小程序授权获取到的iv");
            byte[] session_key = Base64.decodeBase64("通过自建服务器请求微信后台获取到的session_key");
            System.out.println(decrypt(session_key,ivData,encryptedData));*/


        /*LocalDateTime localDateTime = LocalDateTime.now();

        LocalDateTime nextMidNight = LocalDateTime.of(localDateTime.toLocalDate().plusDays(1), LocalTime.MIDNIGHT);

        Long count = Duration.between(localDateTime, nextMidNight).toMinutes();

        System.out.println(nextMidNight);

        System.out.println(count);*/

        /*int a = 1000,b=1000;
        Integer c = 1000,d = 1000;


        System.out.println(a==b);

        System.out.println(c==d);*/



        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime time = LocalDateTime.parse("2020-09-30 05:29:50.000Z", formatter);

        System.out.println(time);*/

        /*LocalDate localDate = LocalDate.of(2020, 10, 6);
        LocalDate localDate1 = LocalDate.of(2018, 1, 1);

        System.out.println(ChronoUnit.DAYS.between(localDate1, localDate));
        System.out.println(ChronoUnit.MONTHS.between(localDate1, localDate));
        Period p = Period.between(localDate1, localDate);
        System.out.println("years:"+p.getYears()+" months:"+p.getMonths()+" days:"+p.getDays());

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 1, 0, 0);

        System.out.println(Duration.between(localDateTime1, localDateTime).toMinutes());

        LocalDateTime now = LocalDateTime.now();
        Date nowDate = Date.from(now.atZone(ZoneId.of("GMT+8")).toInstant());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(nowDate));

        now = nowDate.toInstant().atZone(ZoneId.of("GMT+8")).toLocalDateTime();
        System.out.println(now);*/

        /*List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "aaa"));
        userList.add(new User(2L, "bbb"));
        userList.add(new User(3L, "ccc"));
        userList.add(new User(2L, "ddd"));
        userList.add(new User(3L, "eee"));

        Map<Long, String> map = userList.stream().collect(Collectors.toMap(User::getId, User::getUserName, (v1, v2) -> v1));

        System.out.println(map);*/

        /*List list = Arrays.asList(-2, 6, -1, 5, 4, -7, 2, 3);

        //Integer[] number = new Integer[]{-2, 6, -1, 5, 4, -7, 2, 3};

        Integer[] number = new Integer[]{-2, 1, 4, -4, 5, 4, -7, 2, 3};
        int thisnum = 0;
        int maxnum = 0;
        for (int i = 0; i < number.length; i++) {
            thisnum += number[i];
            if (thisnum > maxnum) {
                maxnum = thisnum;
            } else if (thisnum < 0) {
                thisnum = 0;
            }
        }



        System.out.println(maxnum);*/

        List<Integer> statusAllowList = new ArrayList<>();
        getAllowList(statusAllowList);
        System.out.println(statusAllowList);


        /*IntStream.range(0, 8000).parallel().forEach(each -> {
            num++;
        });
        System.out.println(num);

        IntStream.range(0, 8000).parallel().forEach(each -> {
            n.incrementAndGet();
        });
        System.out.println(n);*/

        for (int i = 0; ++i < 10;) {
            System.out.println(i);
        }


    }

    private static void getAllowList(List<Integer> statusAllowList) {
        statusAllowList.addAll(Arrays.asList(1,2,4,5));
        statusAllowList.add(3);


    }
    public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        //解析解密后的字符串
        return new String(cipher.doFinal(encData),"UTF-8");
    }
}
