package com.hikarukimi.taimountain.service;

import com.hikarukimi.taimountain.controller.WebSocket;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ScheduleService 负责定时任务的调度。
 * 定期通过 WebSocket 发送消息。
 *
 * @author Hikarukimi
 */
@EnableScheduling
@Component
public class ScheduleService {

    /**
     * 日志记录器实例。
     */
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    private final WebSocket webSocket;

    @Autowired
    public ScheduleService(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    /**
     * 每5秒执行一次 sendMessage 方法。
     * 在方法内部，首先打印 WebSocket 实例信息（用于调试），
     * 然后调用 WebSocket 的 sendMessage 方法发送消息。
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void sendMessage() {
        try {
            // 尝试发送消息
            webSocket.sendMessage();
        } catch (Exception e) {
            // 如果在发送消息时发生异常，记录错误日志
            logger.error("Failed to send message via WebSocket", e);
        }
    }

    /**
     * 每天凌晨2点执行数据清理
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanUpExpiredData() {
        logger.info("Starting cleanup of expired data.");
        // 执行清理操作
        logger.info("Finished cleanup of expired data.");
    }
} 