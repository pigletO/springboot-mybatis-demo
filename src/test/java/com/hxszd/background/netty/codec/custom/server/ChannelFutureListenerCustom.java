package com.hxszd.background.netty.codec.custom.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * @description: 自定义ChannelFutureListenner
 * @author: pig1etO
 * @create: 2020-07-07 14:04
 **/
public class ChannelFutureListenerCustom implements ChannelFutureListener {

    /**
     * 操作执行成功后自动触发
     * @param future
     * @throws Exception
     */
    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (future.isSuccess()) {
            System.out.println("服务器启动成功：" + future.channel().localAddress().toString());
        }
    }
}
