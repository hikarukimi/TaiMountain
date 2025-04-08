package com.hikarukimi.taimountain.config;

import com.hikarukimi.taimountain.Response;
import com.hikarukimi.taimountain.entity.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hikarukimi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 处理自定义业务异常
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response> handleBusinessException(BusinessException ex) {
        logger.error("Business Exception: {}", ex.getMessage());
        Response response = Response.custom(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 处理参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Response response = Response.custom(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 处理404异常
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NoHandlerFoundException ex) {
        Response response = Response.custom(
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 处理所有其他异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGlobalException(Exception ex) {
        logger.error("Internal server error: ", ex);

        Response response = Response.error(
                ex.getMessage() != null ? ex.getMessage() : "Internal server error"
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}