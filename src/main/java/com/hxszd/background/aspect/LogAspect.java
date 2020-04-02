package com.hxszd.background.aspect;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @description: 日志切面
 * @author: pig1etO
 * @create: 2020-03-18 17:10
 **/
@Aspect
@Slf4j
@Component
public class LogAspect {

    private long startTime;

    @Pointcut("execution(public * com.example12306.demo.controller..*.*(..))")
    public void Aspect(){}

    @Before("Aspect()")
    public void before(JoinPoint point){
        startTime = System.currentTimeMillis();
        log.info(MarkerFactory.getMarker("MERCH_MARKER"), "  url: " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName()+ "      参数为：" + Arrays.toString(point.getArgs()));
        log.info("日志【请求】－－－－－－－－－－－方法为:" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "      参数为：" + Arrays.toString(point.getArgs()));
    }

    @AfterReturning(value = "Aspect()", returning = "returnValue")
    public void afterReturning(JoinPoint point, Object returnValue){
        log.info(MarkerFactory.getMarker("MERCH_MARKER"), "  url: " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName()+ "     返回值为：" + returnValue);
        log.info("日志【返回】－－－－－－－－－－－方法为:" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "      返回值为：" + returnValue + "【共耗时-" + (System.currentTimeMillis() - startTime) + "-毫秒】");
    }
}
