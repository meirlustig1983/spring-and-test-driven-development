package com.ml.springandtestdrivendevelopment.services;

import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@Transactional
@Sql(scripts = "/data/test-datasets.sql")
class ContactsManagementServiceIT {

    @Autowired
    private ContactsManagementService service;

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
    public void getAllCustomerContacts() {
        List<CustomerContactDto> customerContactDtoList = service.getAllCustomerContacts();
        assertNotNull(customerContactDtoList);
        assertEquals(2, customerContactDtoList.size());
    }

    @Test
    public void getCustomerContactById() {
        CustomerContactDto customerContactDto = service.getCustomerContactById(1L);
        assertNotNull(customerContactDto);
        assertNotNull(customerContactDto.id());
        assertEquals("John", customerContactDto.firstName());
        assertEquals("Doe", customerContactDto.lastName());
        assertEquals("jd@gmail.com", customerContactDto.email());
        assertEquals("12001 E 42TH ST", customerContactDto.addressLine1());
        assertEquals("New York", customerContactDto.city());
        assertEquals("NY", customerContactDto.state());
        assertEquals("12345", customerContactDto.zipCode());
        assertNotNull(customerContactDto.createdDate());
    }

    @Test
    public void getCustomerContact_ShouldFailed() {
        CustomerContactDto customerContactDto = service.getCustomerContactById(3L);
        assertNull(customerContactDto);
    }

    @Test
    public void getCustomerContactsByIds() {
        List<CustomerContactDto> customerContactDtoList = service.getCustomerContactsByIds(List.of(1L, 2L, 3L, 4L));
        assertNotNull(customerContactDtoList);
        assertEquals(2, customerContactDtoList.size());
    }

    @Test
    public void getCustomerContactsByIds_ShouldFailed() {
        List<CustomerContactDto> customerContactDtoList = service.getCustomerContactsByIds(List.of(3L, 4L));
        assertNotNull(customerContactDtoList);
        assertEquals(0, customerContactDtoList.size());
    }

    @Test
    public void getCustomerContactByEmail() {
        CustomerContactDto customerContactDto = service.getCustomerContactByEmail("jd@gmail.com");
        assertNotNull(customerContactDto);
        assertNotNull(customerContactDto.id());
        assertEquals("John", customerContactDto.firstName());
        assertEquals("Doe", customerContactDto.lastName());
        assertEquals("jd@gmail.com", customerContactDto.email());
        assertEquals("12001 E 42TH ST", customerContactDto.addressLine1());
        assertEquals("New York", customerContactDto.city());
        assertEquals("NY", customerContactDto.state());
        assertEquals("12345", customerContactDto.zipCode());
        assertNotNull(customerContactDto.createdDate());
    }

    @Test
    public void getCustomerContactByEmail_ShouldFailed() {
        CustomerContactDto customerContactDto = service.getCustomerContactByEmail("3L");
        assertNull(customerContactDto);
    }
}