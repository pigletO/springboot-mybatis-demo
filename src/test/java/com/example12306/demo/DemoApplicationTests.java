package com.example12306.demo;

import com.example12306.demo.dao.TlineMapper;
import com.example12306.demo.entity.Tline;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private TlineMapper tlineMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Value("${variables:999}")
    private String variables;

    @Autowired
    private Environment env;

    @Test
    void contextLoads() throws InterruptedException {

        /*tlineMapper.updateNameById(2, "K9999");
        Tline tline = tlineMapper.findByIdAndName(2, "K9999");
        log.info(tline.toString());*/
        log.info(tlineMapper.findByIds(Arrays.asList(1L, 2L)).toString());
        /*System.out.println(variables);

        String s = redisTemplate.opsForValue().get("key");

        System.out.println(s);


        tlineMapper.insert(new Tline(5, "k123", new Date(), new Date()));

        Map map = new HashMap();*/




       /* for (int i=0;i<100;i++) {

            Thread.sleep(2000);
            String s = StringUtils.isEmpty(env.getProperty("variables")) ? "kong" : env.getProperty("variables");
            System.out.println(s);
        }*/

        /*Tline tline = new Tline();

        tline.setName("k110");
        Object obj = tline;
        if (obj instanceof Tline){
            System.out.println(((Tline) obj).getName());
        }*/

       /* System.out.println(new BigDecimal(10.10).toString().split(".")[1].length());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new BigDecimal(10.1).toString().split(".")[0]);
        String s = new BigDecimal(10.1).toString().split(".")[1];
        if (s.length() < 2){
            for (int i=s.length();i<2;i++){
                s += "0";
            }
            stringBuilder.append("." + s);
        }
        System.out.println(stringBuilder.toString());*/
        //tlineMapper.insert(tline);

        //System.out.println("301".equals("30230022000089".substring(0, 3)));
    }



}
