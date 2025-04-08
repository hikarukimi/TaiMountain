package com.hikarukimi.taimountain.entity;

/**
 * @author Hikarukimi
 */
// 自定义业务异常类
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
