package com.hxszd.background.controlleradvice;

import com.hxszd.background.pojo.dto.common.ResponseResult;
import com.hxszd.background.pojo.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @description: controller增强器
 * @author: pig1etO
 * @create: 2020-03-11 17:04
 **/
@Slf4j
@RestControllerAdvice(basePackages = "com.hxszd.background.controller")
public class ControllerExceptionAdvicer implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return ResponseResult.Ok(o);
    }

    @ExceptionHandler
    public Object handler(BusinessException e) {
        return new ResponseResult(e.getCode(), e.getMsg(), null);
    }

    @ExceptionHandler
    public Object handler(Exception e) {
        return new ResponseResult(500, "服务器异常", e.getMessage());
    }
}
