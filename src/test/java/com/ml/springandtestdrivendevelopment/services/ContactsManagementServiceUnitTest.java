package com.ml.springandtestdrivendevelopment.services;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import com.ml.springandtestdrivendevelopment.mappers.CustomerContactMapper;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class ContactsManagementServiceUnitTest {

    @Mock
    private CustomerContactRepository repository;

    @Mock
    private CustomerContactMapper mapper;

    @InjectMocks
    private ContactsManagementService service;

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

        when(repository.save(customerContact)).thenReturn(customerContact);
        when(mapper.toEntity(customerContactDto)).thenReturn(customerContact);
        when(mapper.toDto(customerContact)).thenReturn(customerContactDto.withId(1L));

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

        verify(repository).save(customerContact);
        verify(mapper).toEntity(customerContactDto);
        verify(mapper).toDto(customerContact);
        verifyNoMoreInteractions(repository, mapper);
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
        when(mapper.toDtos(List.of(customerContact))).thenReturn(List.of(customerContactDto.withId(1L)));

        List<CustomerContactDto> result = service.getAllCustomerContacts();
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(repository).findAll();
        verify(mapper).toDtos(List.of(customerContact));
        verifyNoMoreInteractions(repository, mapper);
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

        when(repository.findCustomerContactById(1L)).thenReturn(Optional.of(customerContact));
        when(mapper.toDto(customerContact)).thenReturn(customerContactDto.withId(1L));

        CustomerContactDto result = service.getCustomerContactById(1L);

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
        verify(mapper).toDto(customerContact);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    public void getCustomerContactById_ShouldFailed() {
        when(repository.findCustomerContactById(1L)).thenReturn(Optional.empty());

        CustomerContactDto result = service.getCustomerContactById(1L);

        assertNull(result);
        verify(repository).findCustomerContactById(1L);
        verifyNoMoreInteractions(repository, mapper);
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

        when(repository.findAllByIdIn(List.of(1L))).thenReturn(Collections.singletonList(customerContact));
        when(mapper.toDtos(List.of(customerContact))).thenReturn(List.of(customerContactDto.withId(1L)));

        List<CustomerContactDto> result = service.getCustomerContactsByIds(List.of(1L));
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(repository).findAllByIdIn(List.of(1L));
        verify(mapper).toDtos(List.of(customerContact));
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    public void getCustomerContactsByIds_ShouldFailed() {
        when(repository.findAllByIdIn(List.of(1L))).thenReturn(List.of());

        List<CustomerContactDto> result = service.getCustomerContactsByIds(List.of(1L));
        assertNotNull(result);
        assertEquals(0, result.size());

        verify(repository).findAllByIdIn(List.of(1L));
        verifyNoMoreInteractions(repository, mapper);
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

        when(repository.findCustomerContactByEmail("jd@gmail.com")).thenReturn(Optional.of(customerContact));
        when(mapper.toDto(customerContact)).thenReturn(customerContactDto.withId(1L));

        CustomerContactDto result = service.getCustomerContactByEmail("jd@gmail.com");

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

        verify(repository).findCustomerContactByEmail("jd@gmail.com");
        verify(mapper).toDto(customerContact);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    public void getCustomerContactByEmail_ShouldFailed() {
        when(repository.findCustomerContactByEmail("jd@gmail.com")).thenReturn(Optional.empty());

        CustomerContactDto result = service.getCustomerContactByEmail("jd@gmail.com");

        assertNull(result);
        verify(repository).findCustomerContactByEmail("jd@gmail.com");
        verifyNoMoreInteractions(repository, mapper);
    }
}
