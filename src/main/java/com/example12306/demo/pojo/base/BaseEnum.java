package com.example12306.demo.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * enum父类
 */
@Getter
@AllArgsConstructor
public class BaseEnum {

    private String code;

    private String label;
}
