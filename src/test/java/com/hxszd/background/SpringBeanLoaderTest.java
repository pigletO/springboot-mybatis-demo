package com.hxszd.background;

import com.hxszd.background.pojo.ClassA;
import com.hxszd.background.pojo.ClassB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: 研究Spring类加载过程
 * @author: pig1etO
 * @create: 2020-05-26 13:18
 **/
@SpringBootTest
public class SpringBeanLoaderTest {

    @Test
    public void test() {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        ClassA a = (ClassA) context.getBean("classA");
        ClassB b = (ClassB) context.getBean("classB");

    }

}
