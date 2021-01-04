package com.hxszd.background.netty.rpc2.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-11-20 14:38
 **/
@Data
public class ProtocolObject implements Serializable {

    private Object args_0;

    private Object args_1;

    private Object args_2;

    private Object args_3;

    private Object args_4;

    /*@Override
    public String toString() {
        return "ProtocolObject{" +
                "args_0=" + args_0 +
                ", args_1=" + args_1 +
                ", args_2=" + args_2 +
                ", args_3=" + args_3 +
                ", args_4=" + args_4 +
                '}';
    }*/
}
