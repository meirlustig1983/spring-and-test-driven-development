package com.ml.springandtestdrivendevelopment.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(Exception e) {
        log.error("Unhandled exception occurred. ", e);
        ApiError apiError = new ApiError("Unknown", "Unhandled exception occurred. Please check the request content.", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("Unhandled exception occurred. Body content isn't readable", e);
        ApiError apiError = new ApiError("/save", "Unhandled exception occurred. Body content isn't readable", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(ApiMethodException.class)
    public ResponseEntity<ApiError> handleApiMethodException(ApiMethodException e) {
        log.error("Unhandled exception occurred. {}", e.getMessage(), e);
        ApiError apiError = new ApiError(e.getPath(), e.getMessage(), e.getStatusCode().value(), e.getLocalDateTime());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleExceptions(Exception e) {
        log.error("Unhandled exception occurred", e);
        ApiError apiError = new ApiError("general", "msg", HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }
}
