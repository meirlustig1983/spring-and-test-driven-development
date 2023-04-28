package com.ml.springandtestdrivendevelopment.service;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import com.ml.springandtestdrivendevelopment.repositories.CustomerContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsManagementService {

    @Autowired
    private CustomerContactRepository customerContactRepository;

    public CustomerContact add(CustomerContact aContact) {
        return customerContactRepository.save(aContact);
    }
}
