package com.ml.springandtestdrivendevelopment.services;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import com.ml.springandtestdrivendevelopment.repositories.CustomerContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContactsManagementService {

    private final CustomerContactRepository customerContactRepository;

    public CustomerContact addCustomerContact(CustomerContact aContact) {
        return customerContactRepository.save(aContact);
    }
}