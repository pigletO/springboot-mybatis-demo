package com.hxszd.background.netty.codec.custom.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @description: channel入站处理类
 *  S->C为出站操作，C->S为入站操作，入站处理客户端发来的数据
 * @author: pig1etO
 * @create: 2020-07-07 14:46
 **/
public class ChannelStringInBoundHandler extends SimpleChannelInboundHandler<String> {

    // channel组可以管理多个连接
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("调用了StringChannelHandler");
        System.out.println("数据为" + msg);

    }


    /**
     * 新的channel连接打进来的时候就会将当前handler加入进pipeline，此时调用当前方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
        System.out.println("【服务器】" + ctx.channel().remoteAddress() + "上线了");
    }

    /**
     * 连接断开时，会移除此pipeline的当前handler，触发当前方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【服务器】" + ctx.channel().remoteAddress() + "离线了");
    }

    /**
     * 异常执行 不需要维护channelGroup调用remove方法，异常后会自动移除对应channel
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
