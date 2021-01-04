package com.hxszd.background.netty.rpc2.service;

import com.hxszd.background.netty.rpc2.entity.ProtocolObject;

/**
 * @description: .
 * @author: pig1etO
 * @create: 2021-01-04 13:54
 **/
public class RpcServiceImpl implements RpcService {
    @Override
    public String methodA(String param) {

        return "input: " + param + ", success!";
    }

    @Override
    public String methodB(ProtocolObject object) {
        return "input: " + object.toString() + ", success!";
    }
}
