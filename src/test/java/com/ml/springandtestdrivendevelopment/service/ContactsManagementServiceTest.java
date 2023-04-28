package com.ml.springandtestdrivendevelopment.service;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@Transactional
class ContactsManagementServiceTest {

    @Autowired
    private ContactsManagementService contactsManagementService;

    @Test
    public void testAddCustomerContact() {
        CustomerContact.CustomerContactBuilder builder = CustomerContact.builder();
        CustomerContact newContact = builder
                .firstName("John")
                .lastName("Doe")
                .email("jd@gmail.com")
                .addressLine1("1234 N 12TH ST")
                .city("New York")
                .state("NY")
                .zipCode("12345")
                .build();

        CustomerContact savedContact = contactsManagementService.addCustomerContact(newContact);
        assertNotNull(savedContact);
        assertEquals(1L, savedContact.getId());
        assertEquals("John", savedContact.getFirstName());
        assertEquals("Doe", savedContact.getLastName());
        assertEquals("jd@gmail.com", savedContact.getEmail());
        assertEquals("1234 N 12TH ST", savedContact.getAddressLine1());
        assertEquals("New York", savedContact.getCity());
        assertEquals("NY", savedContact.getState());
        assertEquals("12345", savedContact.getZipCode());
    }
}