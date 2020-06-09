package com.hxszd.background;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EntityScan(basePackages = "com.example12306.demo.entity")
/**
 * Mybatis的Mapper.java路径
 */
@MapperScan(basePackages = "com.hxszd.background.mapper")
@SpringBootApplication
//@EnableTransactionManagement
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
