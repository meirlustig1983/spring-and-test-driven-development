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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

        CustomerContactDto result = service.addCustomerContact(customerContactDto);

        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals("John", result.firstName());
        assertEquals("Doe", result.lastName());
        assertEquals("jd@gmail.com", result.email());
        assertEquals("1234 N 12TH ST", result.addressLine1());
        assertEquals("New York", result.city());
        assertEquals("NY", result.state());
        assertEquals("12345", result.zipCode());
        assertNotNull(result.createdDate());

        verify(repository).save(any(CustomerContact.class));
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

        List<CustomerContactDto> result = service.getAllCustomerContacts();
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(repository).findAll();
    }

    @Test
    public void testGetCustomerContact() {

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

        when(repository.findCustomerContactById(1L)).thenReturn(customerContact);

        CustomerContactDto result = service.getCustomerContact(1L);

        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals("John", result.firstName());
        assertEquals("Doe", result.lastName());
        assertEquals("jd@gmail.com", result.email());
        assertEquals("1234 N 12TH ST", result.addressLine1());
        assertEquals("New York", result.city());
        assertEquals("NY", result.state());
        assertEquals("12345", result.zipCode());
        assertNotNull(result.createdDate());

        verify(repository).findCustomerContactById(1L);
    }

    @Test
    public void testGetCustomerContactShouldFailed() {
        when(repository.findCustomerContactById(1L)).thenReturn(null);

        CustomerContactDto result = service.getCustomerContact(1L);

        assertNull(result);
        verify(repository).findCustomerContactById(1L);
    }
}
