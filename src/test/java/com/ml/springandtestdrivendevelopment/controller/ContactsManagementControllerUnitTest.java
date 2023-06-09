package com.ml.springandtestdrivendevelopment.controller;

import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import com.ml.springandtestdrivendevelopment.exceptions.ApiMethodException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class ContactsManagementControllerUnitTest {

    @Mock
    private ContactsManagementService service;

    @InjectMocks
    private ContactsManagementController controller;

    @BeforeTestMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addCustomerContact() {
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

        verify(service).addCustomerContact(customerContactDto);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void addCustomerContact_ShouldFailed() {
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
        assertThrows(ApiMethodException.class, () -> controller.addCustomerContact(customerContactDto));
        verify(service).addCustomerContact(customerContactDto);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getAllCustomerContacts() {

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

        ResponseEntity<List<CustomerContactDto>> result = controller.getAllCustomerContacts();
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

        verify(service).getAllCustomerContacts();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getAllCustomerContacts_ShouldFailed() {
        when(service.getAllCustomerContacts()).thenReturn(List.of());

        ResponseEntity<List<CustomerContactDto>> result = controller.getAllCustomerContacts();
        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        assertTrue(Objects.requireNonNull(result.getBody()).isEmpty());

        verify(service).getAllCustomerContacts();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getCustomerContactById() {
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

        when(service.getCustomerContactById(1L)).thenReturn(customerContactDto);

        ResponseEntity<CustomerContactDto> result = controller.getCustomerContactById(1L);
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

        verify(service).getCustomerContactById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getCustomerContactById_ShouldFailed() {
        when(service.getCustomerContactById(1L)).thenReturn(null);
        assertThrows(ApiMethodException.class, () -> controller.getCustomerContactById(1L));
        verify(service).getCustomerContactById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getCustomerContactsByIds() {

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

        when(service.getCustomerContactsByIds(List.of(1L))).thenReturn(List.of(customerContactDto));

        ResponseEntity<List<CustomerContactDto>> result = controller.getCustomerContactsByIds(List.of(1L));
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

        verify(service).getCustomerContactsByIds(List.of(1L));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getCustomerContactsByIds_ShouldFailed() {
        when(service.getCustomerContactsByIds(List.of(1L))).thenReturn(List.of());
        assertThrows(ApiMethodException.class, () -> controller.getCustomerContactsByIds(List.of(1L)));
        verify(service).getCustomerContactsByIds(List.of(1L));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getCustomerContactByEmail() {
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

        when(service.getCustomerContactByEmail("jd@gmail.com")).thenReturn(customerContactDto);

        ResponseEntity<CustomerContactDto> result = controller.getCustomerContactByEmail("jd@gmail.com");
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

        verify(service).getCustomerContactByEmail("jd@gmail.com");
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getCustomerContactByEmail_ShouldFailed() {
        when(service.getCustomerContactByEmail("jd@gmail.com")).thenReturn(null);
        assertThrows(ApiMethodException.class, () -> controller.getCustomerContactByEmail("jd@gmail.com"));
        verify(service).getCustomerContactByEmail("jd@gmail.com");
        verifyNoMoreInteractions(service);
    }
}