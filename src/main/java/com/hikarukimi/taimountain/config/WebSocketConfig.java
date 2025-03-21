package com.hikarukimi.taimountain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置类。
 * 该配置类用于配置WebSocket相关的Bean，确保使用了@ServerEndpoint注解的WebSocket端点能够被Spring容器正确识别并注册。
 *
 * @author Hikarukimi
 */
@Configuration
public class WebSocketConfig {

    /**
     * 创建一个ServerEndpointExporter Bean。扫描带有@ServerEndpoint注解的类，并将它们注册为WebSocket端点。
     *
     * @return ServerEndpointExporter实例
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}