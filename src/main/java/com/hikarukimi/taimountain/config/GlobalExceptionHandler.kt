package com.hikarukimi.taimountain.config

import com.hikarukimi.taimountain.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


/**
 * 全局异常处理，用于统一处理未捕获的异常。
 *
 * 这个类使用了@RestControllerAdvice注解，对所有@RestController标注的控制器进行增强，
 * 提供统一的异常处理机制。
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * 处理方法中抛出的所有未被捕获的异常，并返回一个友好的错误响应。
     *
     * @param exception 抛出的异常
     * @return ResponseEntity 包含错误信息的响应实体
     */
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<Response> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.error("服务器内部错误"))
    }
}