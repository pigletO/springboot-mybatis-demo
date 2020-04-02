package com.hxszd.background.pojo.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-03-11 17:01
 **/
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {

    private Integer code;

    private String msg;

    private Object data;

    public static ResponseResult Ok(){
        return new ResponseResult(200, "请求成功", null);
    }

    public static ResponseResult Ok(Object data){
        return new ResponseResult(200, "请求成功", data);
    }
}
