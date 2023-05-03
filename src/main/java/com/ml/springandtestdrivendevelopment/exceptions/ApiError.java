package com.ml.springandtestdrivendevelopment.exceptions;

import java.time.LocalDateTime;

public record ApiError(String path, String message, int statusCode, LocalDateTime localDateTime) {
}
