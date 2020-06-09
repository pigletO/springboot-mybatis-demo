package com.hxszd.background.service.impl;

import com.hxszd.background.mapper.TLineMapper;
import com.hxszd.background.pojo.constant.RedisConstants;
import com.hxszd.background.pojo.dto.common.VerifyCodeDTO;
import com.hxszd.background.pojo.enums.ExceptionCodeEnum;
import com.hxszd.background.pojo.exception.BusinessException;
import com.hxszd.background.pojo.vo.LoginInfoVo;
import com.hxszd.background.pojo.vo.VerifyCodeVO;
import com.hxszd.background.service.ILoginService;
import com.hxszd.background.service.common.IRedisService;
import com.hxszd.background.service.common.IVerifyCodeGenerater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-06-09 11:45
 **/
@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private IVerifyCodeGenerater verifyCodeGenerater;

    @Autowired
    private TLineMapper tlineMapper;

    @Override
    public VerifyCodeVO genVerifyCode() {
        try {
            //设置长宽
            VerifyCodeDTO verifyCode = verifyCodeGenerater.generate(80, 28);
            String code = verifyCode.getCode();
            log.info("【验证码】生成: {}", code);
            BASE64Encoder encoder = new BASE64Encoder();
            //转换成base64串
            String pngBase64 = encoder.encodeBuffer(verifyCode.getImgBytes()).trim();
            //删除 \r\n
            pngBase64 = pngBase64.replaceAll("\n", "").replaceAll("\r", "");
            VerifyCodeVO verifyCodeVO = new VerifyCodeVO();
            // 设置唯一标识
            verifyCodeVO.setCaptcha(UUID.randomUUID().toString());
            verifyCodeVO.setCheckCode("data:image/jpeg;base64," + pngBase64);
            // 存储验证码并设置过期时间
            redisService.saveStr(RedisConstants.Captcha + verifyCodeVO.getCaptcha(), code, 30L);
            return verifyCodeVO;
        } catch (IOException e) {
            log.error("【验证码】生成失败！errorInfo: {}",e);
            throw new BusinessException(ExceptionCodeEnum.GEN_VERIFY_CODE_ERROR);
        }
    }

    @Override
    public void login(LoginInfoVo loginInfoVo) {
        // 获取验证码
        String verifyCode;
        if ((verifyCode = redisService.getStr(RedisConstants.Captcha + loginInfoVo.getCaptcha())) == null){
            throw new BusinessException(ExceptionCodeEnum.VERIFY_CODE_TIME_OUT_ERROR.getLabel());
        }
        if (!verifyCode.equalsIgnoreCase(loginInfoVo.getCode())){
            throw new BusinessException(ExceptionCodeEnum.VERIFY_CODE_ERROR.getLabel());
        }
        if (!Objects.equals("dujingsheng", loginInfoVo.getUsername()) && !Objects.equals("111", loginInfoVo.getPassword())) {
            throw new BusinessException(ExceptionCodeEnum.LOGIN_PARAM_ERROR.getLabel());
        }
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void delete(Long id) {

        tlineMapper.delete(id);
        throw new BusinessException("123");
    }
}
