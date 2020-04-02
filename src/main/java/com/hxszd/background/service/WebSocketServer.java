package com.hxszd.background.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@Slf4j
@ServerEndpoint(value = "/websocket/{token}")
@Component
public class WebSocketServer {
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String token;

    private static Map<String, Session> sessionPool = new ConcurrentHashMap<>();
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) {
        this.token = token;
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //session.setMaxIdleTimeout(10000);
        sessionPool.put(token, session);
        try {
            session.getBasicRemote().sendText("【后端发送】连接成功");
            log.info("【websocket消息】连接成功，总数为:"+webSocketSet.size());
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() throws IOException {
        //session.close();
        //从set中删除
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开，总数为:"+webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        //Map map = JSONObject.parseObject(message, Map.class);
        //session.setMaxIdleTimeout(10000);
        log.info("【websocket消息】收到客户端消息:"+message+"    ");

    }

    /**
     * 群发消息通知
     * @param message
     */
    public void sendAllMessage(String message) {
        for(WebSocketServer webSocket : webSocketSet) {
            System.out.println("【websocket消息】广播消息:"+message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 群发消息通知
     * @param message
     */
    public void sendMessageByToken(String token, String message) {
        Session session = sessionPool.get(token);
        if (session != null){
            session.getAsyncRemote().sendText(message);
            System.out.println("【成功】【websocket消息】广播消息:"+message);
        } else {
            System.out.println("【失败】【websocket消息】广播消息:"+message);
        }
    }

    /**
     * 调整session超时时间
     * @param time
     */
    public void updateSessionTimeOutParam(long time){
        for(WebSocketServer webSocket : webSocketSet) {
            System.out.println("【session调整】过期时间"+time);
            try {
                webSocket.session.setMaxIdleTimeout(time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
}