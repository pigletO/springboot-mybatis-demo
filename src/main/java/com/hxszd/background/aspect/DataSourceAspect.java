package com.hxszd.background.aspect;

import com.hxszd.background.conf.datasource.DataSourceHolder;
import com.hxszd.background.pojo.annotation.DbSelector;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description: 动态数据源切面
 * @author: pig1etO
 * @create: 2020-05-21 17:29
 **/
@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.hxszd.background.pojo.annotation.DbSelector)")
    public void method() {
    }

    @Around("method()")
    public Object  around(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean clear = false;
        try {
            Method method = getMethod(joinPoint);
            DbSelector annotation = method.getAnnotation(DbSelector.class);
            // 获取重置标识
            clear = annotation.clear();
            // 选取指定数据源
            DataSourceHolder.set(annotation.value().getName());
            log.info("【数据源切换】url: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + ",选取的数据源：" + annotation.value().getName());
            return joinPoint.proceed();
        } finally {
            if (clear) {
                DataSourceHolder.clear();
            }
        }
    }

    /**
     * 获取方法代理对象
     * @param pjp
     * @return
     */
    private Method getMethod(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        return signature.getMethod();
    }
}
