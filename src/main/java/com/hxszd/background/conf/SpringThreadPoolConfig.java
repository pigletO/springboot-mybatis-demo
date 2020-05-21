package com.hxszd.background.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @description: Spring线程池
 * @author: pig1etO
 * @create: 2020-05-20 17:36
 **/
@Configuration
public class SpringThreadPoolConfig {

    @Autowired
    private Environment env;

    /**
     * 自定义一个spring线程池
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.thread-pool")
    public ThreadPoolTaskExecutor ThreadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
