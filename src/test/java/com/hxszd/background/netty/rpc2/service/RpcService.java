package com.hxszd.background.netty.rpc2.service;

import com.hxszd.background.netty.rpc2.entity.ProtocolObject;

/**
 * @description:
 * @author: pig1etO
 * @create: 2021-01-04 13:46
 **/
public interface RpcService {

    String methodA(String param);

    String methodB(ProtocolObject object);
}
