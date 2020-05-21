package com.hxszd.background;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-05-20 18:33
 **/
@Slf4j
@SpringBootTest
public class test2 {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void test(){

        System.out.println(threadPoolTaskExecutor);
    }
}
