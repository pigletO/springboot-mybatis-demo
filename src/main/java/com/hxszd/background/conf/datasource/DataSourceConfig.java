package com.hxszd.background.conf.datasource;

import com.hxszd.background.pojo.enums.DataSourceEnum;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 主数据源
 * @author: pig1etO
 * @create: 2020-05-14 17:40
 **/
@Configuration
public class DataSourceConfig {

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "master.hikari")
    public HikariDataSource masterHikariDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "slave.hikari")
    public HikariDataSource slaveHikariDataSource() {
        return new HikariDataSource();
    }

    /**
     * 动态数据源配置
     * @param master
     * @param slaver
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DynamicDataSource(@Qualifier("masterDataSource") HikariDataSource master, @Autowired(required = false) @Qualifier("slaveDataSource") HikariDataSource slaver) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(master);

        Map<Object, Object> targetDataSource = new HashMap<>(16);
        // 配置多数据源
        targetDataSource.put(DataSourceEnum.MASTER.getName(), master);
        if (slaver != null) {
            targetDataSource.put(DataSourceEnum.SLAVER.getName(), slaver);
        }
        dynamicDataSource.setTargetDataSources(targetDataSource);

        return dynamicDataSource;
    }

}
