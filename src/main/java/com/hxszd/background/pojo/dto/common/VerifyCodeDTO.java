package com.hxszd.background.pojo.dto.common;

import lombok.Data;

/**
 * @description: 验证码DTO
 * @author: pig1etO
 * @create: 2020-04-01 16:42
 **/
@Data
public class VerifyCodeDTO {
    private String code;

    private byte[] imgBytes;

    private long expireTime;
}
