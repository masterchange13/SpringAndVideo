package com.mao.springandvideo.server;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;


public class WebSocketServer extends TextWebSocketHandler {

    // 使用 Map 维护 username -> WebSocketSession 的映射
    private static final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 获取 URI
        URI uri = session.getUri();

        String username = getUsernameFromUrl(uri);

        if (username != null) {
            userSessions.put(username, session); // 存储用户名与 WebSocketSession 的映射
            System.out.println("新连接：" + session.getId() + " 用户名：" + username);
        } else {
            System.out.println("连接建立，但未提供用户名");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 从 message 中获取 JSON 字符串
        String payload = message.getPayload();

        // 解析 JSON 字符串
        Map<String, Object> map = JSON.parseObject(payload, Map.class);
        // 从解析的 Map 中获取 username
        String receiveUsername = (String) map.get("receiveUsername");
        String sendUsername = (String) map.get("sendUsername");

        System.out.println("reveive username: " + receiveUsername);
        System.out.println("收到消息：" + payload);

        // 广播消息给特定用户
        broadcastToUser(sendUsername, receiveUsername, message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        userSessions.values().removeIf(s -> s.getId().equals(session.getId())); // 移除断开的 session
        System.out.println("连接关闭：" + session.getId());
    }

    private void broadcastToUser(String sendUsername, String receiveUsername, String message) throws IOException {
        WebSocketSession session = userSessions.get(receiveUsername);
        System.out.println("message is " + message);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        } else {
            System.out.println("所有session" + userSessions);
            System.out.println("用户 " + receiveUsername + " 不在线或连接已关闭");
        }
    }

    private static String getUsernameFromUrl(URI uri) throws UnsupportedEncodingException {
        String username = null;

        // 从 URI 中解析查询参数
        if (uri != null && uri.getQuery() != null) {
            String[] params = uri.getQuery().split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if ("username".equals(keyValue[0])) {
                    username = URLDecoder.decode(keyValue[1], "UTF-8"); // 解码用户名
                    break;
                }
            }
        }
        return username;
    }
}
