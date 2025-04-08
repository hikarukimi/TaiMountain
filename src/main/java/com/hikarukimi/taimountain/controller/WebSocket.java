package com.hikarukimi.taimountain.controller;

import com.alibaba.fastjson2.JSON;
import com.hikarukimi.taimountain.service.WeatherService;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket 控制器，用于处理客户端的连接、消息接收、发送等操作。
 *
 * @author Hikarukimi
 */
@Slf4j
@Component
@ServerEndpoint("/ws/{location}")
public class WebSocket implements ApplicationContextAware {

    // 使用 CopyOnWriteArraySet存储 session，避免并发修改异常
    private static final CopyOnWriteArraySet<Session> WEB_SOCKET_SET = new CopyOnWriteArraySet<>();

    private static ApplicationContext springApplication;

    private static WeatherService weatherService;

    /**
     * 设置 Spring 的 ApplicationContext 到静态变量中。
     */
    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        WebSocket.springApplication = applicationContext;
    }

    /**
     * 处理收到的消息。
     *
     * @param session 当前会话
     * @param message 收到的消息内容
     */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        log.info("[websocket] 收到消息：id={}，message={}", session.getId(), message);

        // 回复客户端当前时间戳和消息内容
        session.getAsyncRemote().sendText("[" + Instant.now().toEpochMilli() + "] Hello " + message);
    }

    /**
     * 连接打开时触发。
     *
     * @param session 当前会话
     * @param endpointConfig 端点配置
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig, @PathParam("location") String location){
        if (weatherService == null) {
            weatherService = springApplication.getBean(WeatherService.class);
        }

        // 将 location 存储到 Session 的用户属性中
        session.getUserProperties().put("location", location);

        // 将新会话添加到集合中
        WEB_SOCKET_SET.add(session);

        log.info("[websocket] 新的连接：id={}", session.getId());
    }

    /**
     * 连接关闭时触发。
     *
     * @param session 当前会话
     * @param closeReason 关闭原因
     */
    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        log.info("[websocket] 连接断开：id={}，reason={}", session.getId(), closeReason);
        // 移除已关闭的会话
        WEB_SOCKET_SET.remove(session);
    }

    /**
     * 连接发生错误时触发。
     *
     * @param session 当前会话
     * @param throwable 异常信息
     */
    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        log.error("[websocket] 连接异常：id={}，throwable={}", session.getId(), throwable.getMessage());

        // 关闭连接，并给出异常原因
        session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, throwable.getMessage()));
    }

    /**
     * 发送消息给所有连接的客户端。
     */
    public void sendMessage() throws IOException {
        if (WEB_SOCKET_SET.isEmpty()) {
            return;
        }
        if (weatherService == null) {
            weatherService = springApplication.getBean(WeatherService.class);
        }

        for (Session session : WEB_SOCKET_SET) {
            if (session.isOpen()) {
                // 从 Session 的用户属性中获取 location
                String location = (String) session.getUserProperties().get("location");
                if (location == null) {
                    continue;
                }

                // 根据 location 获取天气信息
                String message = JSON.toJSONString(weatherService.getForecast(location).getData());

                session.getAsyncRemote().sendText(message);
            }
        }
    }
}
