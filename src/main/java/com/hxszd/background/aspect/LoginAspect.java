package com.hxszd.background.aspect;

import com.hxszd.background.pojo.dto.common.ResponseResult;
import com.hxszd.background.pojo.vo.LoginInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @description: 登录切面
 * @author: pig1etO
 * @create: 2020-03-25 15:37
 **/
@Slf4j
@Aspect
@Component
public class LoginAspect {

    @Pointcut("execution(* com.hxszd.background.controller.LoginController.login(..))")
    public void method(){}

    @Before("method()")
    public void before(JoinPoint joinPoint){
        log.info("【Before】，进入before切面,getDeclaringTypeName {},getDeclaringType {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getDeclaringType());
    }

    @After("method()")
    public void after(){
        log.info("【After】,进入after切面");
    }

    //@Around("method()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        log.info("【Around】,进入Around切面");
        LoginInfoVo loginInfoVo = null;
        if (proceedingJoinPoint.getArgs()[0] instanceof LoginInfoVo){
            loginInfoVo = (LoginInfoVo)proceedingJoinPoint.getArgs()[0];
            log.info("前端入参：{}", loginInfoVo.toString());
        } else {
            return new ResponseResult(500, "登录失败！传参错误", null);
        }
        try {
            loginInfoVo.setUsername("dujingsheng");
            loginInfoVo.setPassword("111");
            // 执行登录方法
            return proceedingJoinPoint.proceed(new Object[]{loginInfoVo});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "";
    }

    /*@AfterReturning(value = "method()", returning = "responseResult")
    public void afterReturning(Pointcut pointcut, Object responseResult){
        log.info("【AfterReturning】,进入AfterReturning切面");
    }*/

    @AfterThrowing("method()")
    public void afterThrowing(){
        log.info("【AfterThrowing】,进入AfterThrowing切面");
    }
}

