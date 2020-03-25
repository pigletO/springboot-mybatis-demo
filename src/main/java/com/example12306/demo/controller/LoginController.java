package com.example12306.demo.controller;

import com.example12306.demo.dao.TlineMapper;
import com.example12306.demo.entity.Tline;
import com.example12306.demo.pojo.exception.BusinessException;
import com.example12306.demo.pojo.vo.LoginInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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
