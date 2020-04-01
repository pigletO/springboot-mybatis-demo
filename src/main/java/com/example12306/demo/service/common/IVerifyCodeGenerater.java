package com.example12306.demo.service.common;

import com.example12306.demo.pojo.dto.common.VerifyCodeDTO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description: 验证码生成接口
 * @author: pig1etO
 * @create: 2020-04-01 16:44
 **/
public interface IVerifyCodeGenerater {
    /**
     * 生成验证码并返回code，将图片写的os中
     *
     * @param width
     * @param height
     * @param os
     * @return
     * @throws IOException
     */
    String generate(int width, int height, OutputStream os) throws IOException;

    /**
     * 生成验证码对象
     *
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    VerifyCodeDTO generate(int width, int height) throws IOException;
}
