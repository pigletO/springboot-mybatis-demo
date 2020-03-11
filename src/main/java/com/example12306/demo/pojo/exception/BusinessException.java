package com.example12306.demo.pojo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: BusinessException
 * @author: pig1etO
 * @create: 2020-03-11 17:46
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessException extends RuntimeException {

    private int code;

    private String msg;

    public BusinessException(String message) {
        this.code = 5999;
        this.msg = message;
    }
}
