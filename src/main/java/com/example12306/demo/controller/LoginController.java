package com.example12306.demo.controller;

import com.example12306.demo.dao.TlineMapper;
import com.example12306.demo.entity.Tline;
import com.example12306.demo.pojo.dto.common.VerifyCodeDTO;
import com.example12306.demo.pojo.exception.BusinessException;
import com.example12306.demo.pojo.vo.LoginInfoVo;
import com.example12306.demo.service.common.IVerifyCodeGenerater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * @description: 登录controller
 * @author: pig1etO
 * @create: 2020-03-11 17:17
 **/
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private TlineMapper tlineMapper;

    @Autowired
    private IVerifyCodeGenerater verifyCodeGenerater;

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    @GetMapping("/getVerifyCode")
    public Map genVerifyCode(HttpServletRequest request, HttpServletResponse response){
        try {
            //设置长宽
            VerifyCodeDTO verifyCode = verifyCodeGenerater.generate(80, 28);
            String code = verifyCode.getCode();
            log.info("【验证码】生成: {}", code);
            BASE64Encoder encoder = new BASE64Encoder();
            //转换成base64串
            String png_base64 = encoder.encodeBuffer(verifyCode.getImgBytes()).trim();
            //删除 \r\n
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
            Map<String, Object> result = new HashMap<>(16);
            result.put("captcha", UUID.randomUUID().toString());
            result.put("checkCode", "data:image/jpeg;base64," + png_base64);
            return result;
        } catch (IOException e) {
            log.info("【验证码】生成失败！", e);
        }
        return null;
    }

    @PostMapping(value = "/login")
    public void login(@Valid @RequestBody LoginInfoVo loginInfoVo) {
        if (!Objects.equals("dujingsheng", loginInfoVo.getUsername()) || !Objects.equals("111", loginInfoVo.getPassword())) {
            String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
            log.info(message);
            throw new BusinessException(message);
        }
    }

    @GetMapping("/get/{id}")
    public Tline getDate(@PathVariable("id") Integer id){
        return tlineMapper.selectByPrimaryKey(id);
    }

    @GetMapping("/getAll")
    public List<Tline> getDate(){
        return tlineMapper.selectAll();
    }

}
