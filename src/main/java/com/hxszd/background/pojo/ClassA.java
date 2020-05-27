package com.hxszd.background.pojo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 学习spring循环依赖使用，可以删除
 * @author: pig1etO
 * @create: 2020-05-26 13:23
 **/
@Component
@Slf4j
public class ClassA {

    @Autowired
    private ClassB classB;
}
