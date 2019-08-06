package com.platform.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.common.utils.Constant;
import com.platform.modules.sys.entity.SysUserEntity;
import com.platform.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 李鹏军
 */
@Slf4j
@Service
public class MySocketHandler implements WebSocketHandler {
    @Autowired
    SysUserService userService;

    /**
     * 为了保存在线用户信息，在方法中新建一个list存储一下【实际项目依据复杂度，可以存储到数据库或者缓存】
     */
    private final static List<WebSocketSession> SESSIONS = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS.add(session);
        SysUserEntity user = (SysUserEntity) session.getAttributes().get(Constant.WEBSOCKET_USER);
        if (user != null) {
            log.info("有新窗口开始监听：userId:" + user.getUserId() + "，当前在线人数为" + SESSIONS.size());
            JSONObject obj = new JSONObject();
            // 统计一下当前登录系统的用户有多少个
            obj.put("count", SESSIONS.size());
            users(obj);
            session.sendMessage(new TextMessage(obj.toJSONString()));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        JSONObject msg = JSON.parseObject(message.getPayload().toString());
        log.info("处理要发送的消息：" + msg);
        JSONObject obj = new JSONObject();
        SysUserEntity user = (SysUserEntity) session.getAttributes().get(Constant.WEBSOCKET_USER);
        obj.put("from", user.getUserId());
        if (msg.getInteger("type") == 1) {
            //给所有人
            obj.put("msg", msg.getString("msg"));
            sendMessageToUsers(new TextMessage(obj.toJSONString()));
        } else {
            //指定人发送
            List<String> to = msg.getObject("to", List.class);
            obj.put("msg", msg.getString("msg"));
            to.forEach(item -> sendMessageToUser(item, new TextMessage(obj.toJSONString())));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        log.info("链接出错，关闭链接......");
        SESSIONS.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        log.info("有一连接关闭！当前在线人数为" + SESSIONS.size());
        SESSIONS.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : SESSIONS) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(String userId, TextMessage message) {
        for (WebSocketSession session : SESSIONS) {
            if (userId.equals(((SysUserEntity) session.getAttributes().get(Constant.WEBSOCKET_USER)).getUserId())) {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 将系统中的用户传送到前端
     *
     * @param obj
     */
    private void users(JSONObject obj) {
        List<SysUserEntity> userIds = new ArrayList<>();
        for (WebSocketSession webSocketSession : SESSIONS) {
            userIds.add((SysUserEntity) webSocketSession.getAttributes().get(Constant.WEBSOCKET_USER));
        }
        obj.put("users", userIds);
    }
}
