package com.hxszd.background.pojo.vo;

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

    /**
     * 验证码唯一标识
     */
    @NotNull(message="参数错误")
    private String captcha;

    /**
     * 验证码
     */
    @NotNull(message="验证码不能为空")
    private String code;
}
