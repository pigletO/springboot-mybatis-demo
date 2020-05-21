package com.hxszd.background.conf;

import com.hxszd.background.conf.datasource.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.sql.Connection;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-05-15 14:28
 **/
@Slf4j
@Configuration
public class MybatisConfig {

    @Autowired
    private Environment env;

    /**
     * 引入数据源
     * @param dataSource
     * @return
     */
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        // 设置mybatis的xml所在位置
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        sqlSessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate SqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
