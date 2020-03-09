package com.example12306.demo;

import com.example12306.demo.dao.TlineMapper;
import com.example12306.demo.entity.Tline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private TlineMapper tlineMapper;

    @Test
    void contextLoads() {

        /*Tline tline = new Tline();

        tline.setName("k110");
        Object obj = tline;
        if (obj instanceof Tline){
            System.out.println(((Tline) obj).getName());
        }*/

        System.out.println(new BigDecimal(10.10).toString().split(".")[1].length());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new BigDecimal(10.1).toString().split(".")[0]);
        String s = new BigDecimal(10.1).toString().split(".")[1];
        if (s.length() < 2){
            for (int i=s.length();i<2;i++){
                s += "0";
            }
            stringBuilder.append("." + s);
        }
        System.out.println(stringBuilder.toString());
        //tlineMapper.insert(tline);

        //System.out.println("301".equals("30230022000089".substring(0, 3)));
    }

}
