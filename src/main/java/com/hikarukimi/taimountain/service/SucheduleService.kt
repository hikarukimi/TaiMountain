package com.hikarukimi.taimountain.service

import com.hikarukimi.taimountain.controller.WebSocket
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

/**
 * ScheduleService 负责定时任务的调度。
 *
 * 定期通过 WebSocket 发送消息。
 *
 * @author Hikarukimi
 */
@EnableScheduling
@Component
class ScheduleService  @Autowired constructor(private val webSocket: WebSocket) {

    /**
     * 日志记录器实例。
     */
    private val logger = LoggerFactory.getLogger(ScheduleService::class.java)

    /**
     *每5秒执行一次 sendMessage 方法。
    * 在方法内部，首先打印 WebSocket 实例信息（用于调试），
    * 然后调用 WebSocket 的 sendMessage 方法发送消息。
    */
    @Scheduled(cron = "*/5 * * * * ?")
    fun sendMessage() {

        try {
            logger.info("现在是 ${LocalDateTime.now()}")
            // 尝试发送消息
            webSocket.sendMessage()
        } catch (e: Exception) {
            // 如果在发送消息时发生异常，记录错误日志
            logger.error("Failed to send message via WebSocket", e)
        }
    }

    /**
     *
     */

    @Scheduled(cron = "0 0 2 * * ?")
    fun cleanUpExpiredData() {
        logger.info("Starting cleanup of expired data.")
        // 执行清理操作
        logger.info("Finished cleanup of expired data.")
    }
}