package com.ml.springandtestdrivendevelopment.controller;

import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import com.ml.springandtestdrivendevelopment.services.ContactsManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
class ContactsManagementControllerUnitTest {

    @Mock
    private ContactsManagementService service;

    @InjectMocks
    private ContactsManagementController controller;

    @BeforeTestMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCustomerContactTest() {
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

        when(service.addCustomerContact(customerContactDto)).thenReturn(customerContactDto);

        ResponseEntity<CustomerContactDto> result = controller.addCustomerContact(customerContactDto);
        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        assertEquals("John", Objects.requireNonNull(result.getBody()).firstName());
        assertEquals("Doe", Objects.requireNonNull(result.getBody()).lastName());
        assertEquals("jd@gmail.com", Objects.requireNonNull(result.getBody()).email());
        assertEquals("1234 N 12TH ST", Objects.requireNonNull(result.getBody()).addressLine1());
        assertEquals("New York", Objects.requireNonNull(result.getBody()).city());
        assertEquals("NY", Objects.requireNonNull(result.getBody()).state());
        assertEquals("12345", Objects.requireNonNull(result.getBody()).zipCode());
        assertNotNull(Objects.requireNonNull(result.getBody()).createdDate());
    }

    @Test
    void addCustomerContactShouldFailedTest() {
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

        when(service.addCustomerContact(customerContactDto)).thenReturn(null);

        ResponseEntity<CustomerContactDto> result = controller.addCustomerContact(customerContactDto);
        assertNotNull(result);
        assertEquals(400, result.getStatusCode().value());
    }

    @Test
    void listCustomerContactsTest() {

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

        when(service.getAllCustomerContacts()).thenReturn(List.of(customerContactDto));

        ResponseEntity<List<CustomerContactDto>> result = controller.getCustomerContacts();
        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        assertEquals("John", Objects.requireNonNull(result.getBody()).get(0).firstName());
        assertEquals("Doe", Objects.requireNonNull(result.getBody()).get(0).lastName());
        assertEquals("jd@gmail.com", Objects.requireNonNull(result.getBody()).get(0).email());
        assertEquals("1234 N 12TH ST", Objects.requireNonNull(result.getBody()).get(0).addressLine1());
        assertEquals("New York", Objects.requireNonNull(result.getBody()).get(0).city());
        assertEquals("NY", Objects.requireNonNull(result.getBody()).get(0).state());
        assertEquals("12345", Objects.requireNonNull(result.getBody()).get(0).zipCode());
        assertNotNull(Objects.requireNonNull(result.getBody()).get(0).createdDate());
    }

    @Test
    void listCustomerContactsShouldFailedTest() {
        when(service.getAllCustomerContacts()).thenReturn(List.of());

        ResponseEntity<List<CustomerContactDto>> result = controller.getCustomerContacts();
        assertNotNull(result);
        assertEquals(404, result.getStatusCode().value());
    }

    @Test
    void getCustomerContactTest() {
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

        when(service.getCustomerContact(1L)).thenReturn(customerContactDto);

        ResponseEntity<CustomerContactDto> result = controller.getCustomerContact(1L);
        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        assertEquals("John", Objects.requireNonNull(result.getBody()).firstName());
        assertEquals("Doe", Objects.requireNonNull(result.getBody()).lastName());
        assertEquals("jd@gmail.com", Objects.requireNonNull(result.getBody()).email());
        assertEquals("1234 N 12TH ST", Objects.requireNonNull(result.getBody()).addressLine1());
        assertEquals("New York", Objects.requireNonNull(result.getBody()).city());
        assertEquals("NY", Objects.requireNonNull(result.getBody()).state());
        assertEquals("12345", Objects.requireNonNull(result.getBody()).zipCode());
        assertNotNull(Objects.requireNonNull(result.getBody()).createdDate());
    }

    @Test
    void getCustomerContactShouldFailedTest() {
        when(service.getCustomerContact(1L)).thenReturn(null);

        ResponseEntity<CustomerContactDto> result = controller.getCustomerContact(1L);
        assertNotNull(result);
        assertEquals(404, result.getStatusCode().value());
    }
}