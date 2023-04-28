package com.ml.springandtestdrivendevelopment.services;

import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@Transactional
class ContactsManagementServiceTest {

    @Autowired
    private ContactsManagementService service;

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
        List<CustomerContactDto> customerContactDtoList = service.getAllCustomerContacts();
        assertNotNull(customerContactDtoList);
        assertEquals(2, customerContactDtoList.size());
    }
}