package com.hxszd.background.controller;

import com.hxszd.background.entity.TLine;
import com.hxszd.background.mapper.TLineMapper;
import com.hxszd.background.pojo.annotation.DbSelector;
import com.hxszd.background.pojo.constant.RedisConstants;
import com.hxszd.background.pojo.dto.common.VerifyCodeDTO;
import com.hxszd.background.pojo.enums.DataSourceEnum;
import com.hxszd.background.pojo.enums.ExceptionCodeEnum;
import com.hxszd.background.pojo.exception.BusinessException;
import com.hxszd.background.pojo.vo.LoginInfoVo;
import com.hxszd.background.pojo.vo.VerifyCodeVO;
import com.hxszd.background.service.ILoginService;
import com.hxszd.background.service.common.IRedisService;
import com.hxszd.background.service.common.IVerifyCodeGenerater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @description: 登录controller
 * @author: pig1etO
 * @create: 2020-03-11 17:17
 **/
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private TLineMapper tlineMapper;

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    @GetMapping("/getVerifyCode")
    public VerifyCodeVO genVerifyCode(HttpServletRequest request, HttpServletResponse response){
        return loginService.genVerifyCode();
    }

    /**
     * 用户登录
     * @param loginInfoVo
     */
    @PostMapping(value = "/login")
    public void login(@Valid @RequestBody LoginInfoVo loginInfoVo, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            loginService.login(loginInfoVo);
        } else {
            log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
            throw new BusinessException(ExceptionCodeEnum.INPUT_PARAMS_ERROR.getLabel());
        }
    }

    @GetMapping("/masterGet/{id}")
    public TLine masterGet(@PathVariable("id") Integer id){
        return tlineMapper.findByid(id);
    }

    @DbSelector(DataSourceEnum.SLAVER)
    @GetMapping("/slaverGet/{id}")
    public TLine slaverGet(@PathVariable("id") Integer id){
        return tlineMapper.findByid(id);
    }

    @DeleteMapping("/masterDelete/{id}")
    public void masterDelete(@PathVariable("id") Long id){
        loginService.delete(id);
    }

}
