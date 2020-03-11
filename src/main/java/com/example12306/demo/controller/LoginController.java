package com.example12306.demo.controller;

import com.example12306.demo.pojo.exception.BusinessException;
import com.example12306.demo.pojo.vo.LoginInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @description: 登录controller
 * @author: pig1etO
 * @create: 2020-03-11 17:17
 **/
@Slf4j
@RestController
//@RequestMapping("/login")
public class LoginController {

    /**
     * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
     * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
     * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
     */
    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public void login(@Valid @RequestBody LoginInfoVo loginInfoVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            log.info(message);
            throw new BusinessException(message);
        }
        if (!Objects.equals("dujingsheng", loginInfoVo.getUsername()) || !Objects.equals("111", loginInfoVo.getPassword())) {
            String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
            log.info(message);
            throw new BusinessException(message);
        }
    }

}
