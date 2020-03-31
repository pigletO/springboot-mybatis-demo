package com.example12306.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


//@EntityScan(basePackages = "com.example12306.demo.entity")
/**
 * Mybatis的Mapper.java路径
 */
@MapperScan(basePackages = "com.example12306.demo.dao")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
