package com.hxszd.background.service.impl;

import com.hxszd.background.service.NormalService;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-11 16:44
 **/
public class NormalServiceImpl implements NormalService {
    @Override
    public String methodA(String param) {
        System.out.println("调用了methodA,result:" + param);
        return param;
    }

    @Override
    public Integer methodB(Integer param) {
        System.out.println("调用了methodB");
        return param;
    }
}
