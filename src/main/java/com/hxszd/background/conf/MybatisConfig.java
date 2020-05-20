package com.hxszd.background.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-05-15 14:28
 **/
@Slf4j
@Configuration
public class MybatisConfig {

    /*@Bean
    public SqlSessionFactory getSqlSessionFactory() {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations();
        return sqlSessionFactoryBean;

    }*/
}
