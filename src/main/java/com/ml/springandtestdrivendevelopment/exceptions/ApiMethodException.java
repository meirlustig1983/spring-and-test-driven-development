package com.ml.springandtestdrivendevelopment.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Value
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(force = true)
public class ApiMethodException extends RuntimeException {

    String path;
    HttpStatus statusCode;
    LocalDateTime localDateTime;

    public ApiMethodException(String path, String message, HttpStatus statusCode, LocalDateTime localDateTime) {
        super(message);
        this.path = path;
        this.statusCode = statusCode;
        this.localDateTime = localDateTime;
    }
}
