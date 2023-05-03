package com.ml.springandtestdrivendevelopment.repositories;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerContactRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerContactRepository repository;

    @Test
    void findAll_ShouldReturnAllCustomerContacts() {
        // Given

        CustomerContact.CustomerContactBuilder builder = CustomerContact.builder();
        CustomerContact customerContact1 = builder
                .firstName("John")
                .lastName("Doe")
                .email("jd@gmail.com")
                .addressLine1("12001 E 42TH ST")
                .city("New York")
                .state("NY")
                .zipCode("12345")
                .build();

        CustomerContact customerContact2 = builder
                .firstName("Miki")
                .lastName("Cohen")
                .email("mc@gmail.com")
                .addressLine1("16002 W 57TH ST")
                .city("New York")
                .state("NY")
                .zipCode("14006")
                .build();

        entityManager.persist(customerContact1);
        entityManager.persist(customerContact2);

        entityManager.flush();

        // When
        List<CustomerContact> contacts = repository.findAll();

        // Then
        assertThat(contacts).hasSize(2);
        assertThat(contacts).contains(customerContact1, customerContact2);
    }

    @Test
    void findCustomerContactById_ShouldReturnCustomerContactWithGivenId() {
        // Given

        CustomerContact.CustomerContactBuilder builder = CustomerContact.builder();
        CustomerContact customerContact1 = builder
                .firstName("John")
                .lastName("Doe")
                .email("jd@gmail.com")
                .addressLine1("12001 E 42TH ST")
                .city("New York")
                .state("NY")
                .zipCode("12345")
                .build();

        CustomerContact customerContact2 = builder
                .firstName("Miki")
                .lastName("Cohen")
                .email("mc@gmail.com")
                .addressLine1("16002 W 57TH ST")
                .city("New York")
                .state("NY")
                .zipCode("14006")
                .build();

        entityManager.persist(customerContact1);
        entityManager.persist(customerContact2);

        entityManager.flush();

        // When
        Optional<CustomerContact> contact = repository.findCustomerContactById(customerContact2.getId());

        // Then
        assertThat(contact.get()).isEqualTo(customerContact2);
    }

    @Test
    void findCustomerContactByEmail_ShouldReturnCustomerContactWithGivenEmail() {
        // Given

        CustomerContact.CustomerContactBuilder builder = CustomerContact.builder();
        CustomerContact customerContact1 = builder
                .firstName("John")
                .lastName("Doe")
                .email("jd@gmail.com")
                .addressLine1("12001 E 42TH ST")
                .city("New York")
                .state("NY")
                .zipCode("12345")
                .build();

        CustomerContact customerContact2 = builder
                .firstName("Miki")
                .lastName("Cohen")
                .email("mc@gmail.com")
                .addressLine1("16002 W 57TH ST")
                .city("New York")
                .state("NY")
                .zipCode("14006")
                .build();

        entityManager.persist(customerContact1);
        entityManager.persist(customerContact2);

        entityManager.flush();

        // When
        CustomerContact contact = repository.findCustomerContactByEmail(customerContact2.getEmail());

        // Then
        assertThat(contact).isEqualTo(customerContact2);
    }

    @Test
    void findAllByIdIn_ShouldReturnAllCustomerContactsInGivenIds() {
        // Given

        CustomerContact.CustomerContactBuilder builder = CustomerContact.builder();
        CustomerContact customerContact1 = builder
                .firstName("John")
                .lastName("Doe")
                .email("jd@gmail.com")
                .addressLine1("12001 E 42TH ST")
                .city("New York")
                .state("NY")
                .zipCode("12345")
                .build();

        CustomerContact customerContact2 = builder
                .firstName("Miki")
                .lastName("Cohen")
                .email("mc@gmail.com")
                .addressLine1("16002 W 57TH ST")
                .city("New York")
                .state("NY")
                .zipCode("14006")
                .build();

        entityManager.persist(customerContact1);
        entityManager.persist(customerContact2);

        entityManager.flush();

        // When
        List<CustomerContact> contacts = repository.findAllByIdIn(List.of(customerContact1.getId(), customerContact2.getId()));

        // Then
        assertThat(contacts).hasSize(2);
        assertThat(contacts).contains(customerContact1, customerContact2);
    }
}