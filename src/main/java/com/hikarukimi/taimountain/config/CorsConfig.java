package com.hikarukimi.taimountain.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Hikarukimi
 *
 * 跨域资源共享（CORS）配置类。
 * 该类通过 Spring 的 WebMvcConfigurer 接口实现跨域请求的全局配置。
 * 主要用于解决前后端分离项目中，浏览器因同源策略限制导致的跨域问题。
 */
@Configuration
public class CorsConfig {

    /**
     * @return WebMvcConfigurer 配置跨域规则的对象
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * 配置跨域请求规则。
             */
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry
                        // 指定需要支持跨域的路径模式
                        .addMapping("/**") // 匹配所有路径（包括子路径）
                        // 允许的来源（域名/IP），使用 allowedOriginPatterns 支持通配符
                        .allowedOriginPatterns("*") // 允许所有来源（生产环境中建议指定具体域名）
                        // 允许的 HTTP 方法
                        .allowedMethods("*") // 允许任何方法（GET、POST、PUT、DELETE 等）
                        // 允许的请求头
                        .allowedHeaders("*") // 允许任何请求头
                        // 是否允许发送 Cookie 或其他认证信息
                        .allowCredentials(false) // 不允许发送 Cookie（如果需要支持认证，请设置为 true）
                        // 暴露给客户端的响应头
                        .exposedHeaders(HttpHeaders.SET_COOKIE) // 暴露 Set-Cookie 响应头
                        // 预检请求（OPTIONS）的结果缓存时间（单位：秒）
                        .maxAge(3600L); // 缓存 3600 秒（1 小时），在缓存时间内无需重复发送预检请求
            }
        };
    }
}