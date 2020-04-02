package com.hxszd.background.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 错误代码
 */
@Getter
@AllArgsConstructor
@ToString(callSuper = true)
public enum  ExceptionCodeEnum {

    SYSTEM_ERROR(5000, "系统异常!"),
    GEN_VERIFY_CODE_ERROR(5001, "生成验证码失败！"),
    INPUT_PARAMS_ERROR(5002, "入参错误！"),
    VERIFY_CODE_TIME_OUT_ERROR(5003, "验证码超时失效！"),
    VERIFY_CODE_ERROR(5004, "验证码错误！"),
    LOGIN_PARAM_ERROR(5005, "用户名或密码错误！");

    private Integer code;

    private String label;

    public static String getLabelByCode(Integer code){
        for (ExceptionCodeEnum e : ExceptionCodeEnum.values()){
            if (e.getCode().equals(code)){
                return e.getLabel();
            }
        }
        return "";
    }
}
