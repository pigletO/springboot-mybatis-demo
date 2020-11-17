package com.hxszd.background.spi;

import com.hxszd.background.service.NormalService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ServiceLoader;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-10-28 16:02
 **/
@SpringBootTest
public class test {

    @Test
    public void test1() {
        while (true) {
            ServiceLoader<NormalService> serviceLoader = ServiceLoader.load(NormalService.class);
            for (NormalService loader : serviceLoader) {
                loader.methodA("1");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
