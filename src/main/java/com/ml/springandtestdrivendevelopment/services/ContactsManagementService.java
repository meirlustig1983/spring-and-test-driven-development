package com.ml.springandtestdrivendevelopment.services;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import com.ml.springandtestdrivendevelopment.repositories.CustomerContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactsManagementService {

    private final CustomerContactRepository customerContactRepository;

    public CustomerContactDto addCustomerContact(CustomerContactDto dto) {
        log.info("ContactsManagementService.addCustomerContact(...) - add customer contact");
        CustomerContact customerContact = customerContactRepository.save(convert(dto));
        return convert(customerContact);
    }

    public List<CustomerContactDto> getAllCustomerContacts() {
        log.info("ContactsManagementService.findCustomerContacts() - retrieving all customer contacts");
        val allCustomerContacts = customerContactRepository.findAll();
        if (allCustomerContacts.isEmpty()) {
            return Collections.emptyList();
        } else {
            return convert(allCustomerContacts);
        }
    }

    public CustomerContactDto getCustomerContact(Long customerContactId) {
        log.info("ContactsManagementService.getCustomerContact(...) - get customer contact by id. value: {}", customerContactId);
        CustomerContact customerContact = customerContactRepository.findCustomerContactById(customerContactId);
        if (customerContact == null) {
            return null;
        } else {
            return convert(customerContact);
        }
    }

    private List<CustomerContactDto> convert(List<CustomerContact> allCustomerContacts) {
        return allCustomerContacts.stream().map(this::convert).toList();
    }

    private CustomerContactDto convert(CustomerContact customerContact) {
        return new CustomerContactDto(
                customerContact.getId(),
                customerContact.getFirstName(),
                customerContact.getLastName(),
                customerContact.getEmail(),
                customerContact.getAddressLine1(),
                customerContact.getAddressLine2(),
                customerContact.getCity(),
                customerContact.getState(),
                customerContact.getZipCode(),
                customerContact.getCreatedDate()
        );
    }

    private CustomerContact convert(CustomerContactDto customerContactDto) {

        CustomerContact.CustomerContactBuilder builder = CustomerContact.builder();
        return builder
                .firstName(customerContactDto.firstName())
                .lastName(customerContactDto.lastName())
                .email(customerContactDto.email())
                .addressLine1(customerContactDto.addressLine1())
                .addressLine2(customerContactDto.addressLine2())
                .city(customerContactDto.city())
                .state(customerContactDto.state())
                .zipCode(customerContactDto.zipCode())
                .build();
    }
}