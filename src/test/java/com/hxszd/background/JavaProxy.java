package com.hxszd.background;

import com.hxszd.background.service.NormalService;
import com.hxszd.background.service.impl.NormalServiceImpl;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-08-11 16:39
 **/
public class JavaProxy {

    static NormalService normalService = new NormalServiceImpl();

    public static void main(String[] args) {

        NormalService proxyObject = (NormalService)Proxy.newProxyInstance(JavaProxy.class.getClassLoader(), new Class<?>[]{NormalService.class},
                (proxy, method, params) -> {

                    System.out.println("代理工作了");

                    Object result = method.invoke(normalService, params);
                    System.out.println("执行结果" + result.toString());
                    return result;
        });

        proxyObject.methodA("A");

        proxyObject.methodB(2);

    }
}
