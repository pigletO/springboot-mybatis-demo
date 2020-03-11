package com.example12306.demo.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-03-11 17:31
 **/
@Data
public class LoginInfoVo {
    @NotNull(message="用户名不允许为空")
    private String username;

    @NotNull(message="密码不允许为空")
    private String password;
}
