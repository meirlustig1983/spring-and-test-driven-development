package com.ml.springandtestdrivendevelopment.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class ApiMethodException extends RuntimeException {

    private final String path;
    private final HttpStatus statusCode;
    private final LocalDateTime localDateTime;

    public ApiMethodException(String path, String message, HttpStatus statusCode, LocalDateTime localDateTime) {
        super(message);
        this.path = path;
        this.statusCode = statusCode;
        this.localDateTime = localDateTime;
    }
}
