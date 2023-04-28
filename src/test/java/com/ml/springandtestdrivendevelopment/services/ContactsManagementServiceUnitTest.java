package com.ml.springandtestdrivendevelopment.services;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import com.ml.springandtestdrivendevelopment.repositories.CustomerContactRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class ContactsManagementServiceUnitTest {

    @Mock
    private CustomerContactRepository customerContactRepository;

    @InjectMocks
    private ContactsManagementService service;

    @BeforeTestMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {

        CustomerContact customerContact = CustomerContact.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .addressLine1("123 Main St")
                .city("Anytown")
                .state("CA")
                .zipCode("12345")
                .build();

        when(customerContactRepository.save(customerContact)).thenReturn(customerContact);

        CustomerContact savedContact = service.addCustomerContact(customerContact);

        assertEquals("John", savedContact.getFirstName());
        assertEquals("Doe", savedContact.getLastName());
        assertEquals("johndoe@example.com", savedContact.getEmail());
        assertEquals("123 Main St", savedContact.getAddressLine1());
        assertEquals("Anytown", savedContact.getCity());
        assertEquals("CA", savedContact.getState());
        assertEquals("12345", savedContact.getZipCode());
        assertNotNull(savedContact.getCreatedDate());
    }
}
