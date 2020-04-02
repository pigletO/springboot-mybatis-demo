package com.hxszd.background.pojo.vo;

import lombok.Data;

/**
 * 验证码VO
 */
@Data
public class VerifyCodeVO {

    /**
     * 验证码唯一标识
     */
    private String captcha;

    /**
     * 验证码base64数据
     */
    private String checkCode;
}
