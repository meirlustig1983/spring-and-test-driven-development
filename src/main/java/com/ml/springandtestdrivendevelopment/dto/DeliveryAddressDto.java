package com.ml.springandtestdrivendevelopment.dto;

import lombok.NonNull;

import java.util.Date;

public record DeliveryAddressDto(
        Long id,
        @NonNull String addressLine1,
        String addressLine2,
        @NonNull String city,
        @NonNull String state,
        @NonNull String zipCode,
        @NonNull Date createdDate) {
}
