package com.hxszd.background.service;

import com.hxszd.background.pojo.vo.LoginInfoVo;
import com.hxszd.background.pojo.vo.VerifyCodeVO;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-06-09 11:44
 **/
public interface ILoginService {

    VerifyCodeVO genVerifyCode();

    void login(LoginInfoVo loginInfoVo);

    void delete(Long id);
}
