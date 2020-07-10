package com.hxszd.background.netty.protobuf.client;

import com.hxszd.background.netty.protobuf.proto.pojo.ProtoBufPOJO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-07-07 19:02
 **/
public class ChannelInBoundHandler extends SimpleChannelInboundHandler<String> {


    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【客户端】" + ctx.channel().localAddress());

        int random = new Random().nextInt(3);
        if (random == 0) {
            ProtoBufPOJO.summaryMessage object = ProtoBufPOJO.summaryMessage.newBuilder().setDataTypeValue(random).setObjectA(ProtoBufPOJO.ObjectA.newBuilder().setUserId(123).setUserName("我是djs").build()).build();
            ctx.channel().writeAndFlush(object);
        } else if (random == 1) {
            ProtoBufPOJO.summaryMessage object1 = ProtoBufPOJO.summaryMessage.newBuilder().setDataTypeValue(random).setObjectB(ProtoBufPOJO.ObjectB.newBuilder().setUserId(999).setPhoneNum("12345678910").build()).build();
            ctx.channel().writeAndFlush(object1);
        }


        // TODO 客户端怎么做到非阻塞读取消息呢？
    }
}
