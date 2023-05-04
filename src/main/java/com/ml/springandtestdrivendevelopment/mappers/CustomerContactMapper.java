package com.ml.springandtestdrivendevelopment.mappers;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerContactMapper {

    @Cacheable(value = "ToCustomerContactDto", key = "#entity")
    public CustomerContactDto toDto(CustomerContact entity) {
        return new CustomerContactDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getState(),
                entity.getZipCode(),
                entity.getCreatedDate());
    }

    public List<CustomerContactDto> toDtos(List<CustomerContact> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Cacheable(value = "ToCustomerContact", key = "#dto")
    public CustomerContact toEntity(CustomerContactDto dto) {
        CustomerContact.CustomerContactBuilder builder = CustomerContact.builder();
        return builder
                .id(dto.id())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .addressLine1(dto.addressLine1())
                .addressLine2(dto.addressLine2())
                .city(dto.city())
                .state(dto.state())
                .zipCode(dto.zipCode())
                .build();
    }

    public List<CustomerContact> toEntities(List<CustomerContactDto> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
