package com.ml.springandtestdrivendevelopment.services;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import com.ml.springandtestdrivendevelopment.repositories.CustomerContactRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class ContactsManagementServiceUnitTest {

    @Mock
    private CustomerContactRepository repository;

    @InjectMocks
    private ContactsManagementService service;

    @BeforeTestMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCustomerContact() {

        CustomerContactDto customerContactDto = new CustomerContactDto(
                null,
                "John",
                "Doe",
                "jd@gmail.com",
                "1234 N 12TH ST",
                null,
                "New York",
                "NY",
                "12345",
                new Date());

        CustomerContact customerContact = CustomerContact.builder()
                .id(1L)
                .firstName(customerContactDto.firstName())
                .lastName(customerContactDto.lastName())
                .email(customerContactDto.email())
                .addressLine1(customerContactDto.addressLine1())
                .city(customerContactDto.city())
                .state(customerContactDto.state())
                .zipCode(customerContactDto.zipCode())
                .createdDate(customerContactDto.createdDate())
                .build();

        when(repository.save(any(CustomerContact.class))).thenReturn(customerContact);

        CustomerContactDto savedContact = service.addCustomerContact(customerContactDto);

        assertNotNull(savedContact);
        assertNotNull(savedContact.id());
        assertEquals("John", savedContact.firstName());
        assertEquals("Doe", savedContact.lastName());
        assertEquals("jd@gmail.com", savedContact.email());
        assertEquals("1234 N 12TH ST", savedContact.addressLine1());
        assertEquals("New York", savedContact.city());
        assertEquals("NY", savedContact.state());
        assertEquals("12345", savedContact.zipCode());
        assertNotNull(savedContact.createdDate());
    }

    @Test
    public void testGetAllCustomerContacts() {

        CustomerContactDto customerContactDto = new CustomerContactDto(
                null,
                "John",
                "Doe",
                "jd@gmail.com",
                "1234 N 12TH ST",
                null,
                "New York",
                "NY",
                "12345",
                new Date());

        CustomerContact customerContact = CustomerContact.builder()
                .id(1L)
                .firstName(customerContactDto.firstName())
                .lastName(customerContactDto.lastName())
                .email(customerContactDto.email())
                .addressLine1(customerContactDto.addressLine1())
                .city(customerContactDto.city())
                .state(customerContactDto.state())
                .zipCode(customerContactDto.zipCode())
                .createdDate(customerContactDto.createdDate())
                .build();

        when(repository.findAll()).thenReturn(Collections.singletonList(customerContact));

        List<CustomerContactDto> customerContactDtoList = service.getAllCustomerContacts();
        assertNotNull(customerContactDtoList);
        assertEquals(1, customerContactDtoList.size());
    }
}
