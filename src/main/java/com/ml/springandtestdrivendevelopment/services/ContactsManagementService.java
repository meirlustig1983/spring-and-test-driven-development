package com.ml.springandtestdrivendevelopment.services;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import com.ml.springandtestdrivendevelopment.mappers.CustomerContactMapper;
import com.ml.springandtestdrivendevelopment.repositories.CustomerContactRepository;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactsManagementService {

    private static final int CACHE_SIZE = 3;

    private final CustomerContactRepository repository;

    private final CustomerContactMapper mapper;

    @WithSpan
    public CustomerContactDto addCustomerContact(CustomerContactDto dto) {
        log.info("ContactsManagementService.addCustomerContact(...) - add customer contact");
        CustomerContact customerContact = mapper.toEntity(dto);
        customerContact = repository.save(customerContact);
        return dto.withId(customerContact.getId());
    }

    @WithSpan
    @Cacheable("customerContact")
    public List<CustomerContactDto> getAllCustomerContacts() {
        log.info("ContactsManagementService.findCustomerContacts() - retrieving all customer contacts");
        val allCustomerContacts = repository.findAll();
        if (allCustomerContacts.isEmpty()) {
            return Collections.emptyList();
        } else {
            return mapper.toDtos(allCustomerContacts);
        }
    }

    @WithSpan
    @Cacheable(value = "customerContactById", key = "#id")
    public CustomerContactDto getCustomerContactById(Long id) {
        log.info("ContactsManagementService.getCustomerContactById(...) - retrieving customer contact by id. value: {}", id);
        Optional<CustomerContact> customerContact = repository.findCustomerContactById(id);
        return customerContact.map(mapper::toDto).orElse(null);
    }

    @WithSpan
    @Cacheable(value = "customerContactsByIds", key = "#ids")
    public List<CustomerContactDto> getCustomerContactsByIds(List<Long> ids) {
        log.info("ContactsManagementService.getCustomerContactByIds(...) - retrieving customers contact by ids. value: {}", ids);
        val customerContacts = new ArrayList<CustomerContactDto>();
        if (ids.size() < CACHE_SIZE) {
            ids.forEach(id -> {
                CustomerContactDto dto = getCustomerContactById(id);
                if (dto != null) {
                    customerContacts.add(dto);
                }
            });
        } else {
            List<CustomerContact> customerContactList = repository.findAllByIdIn(ids);
            customerContactList.forEach(customerContact -> customerContacts.add(mapper.toDto(customerContact)));
        }
        return customerContacts;
    }

    @WithSpan
    @Cacheable(value = "customerContactByEmail", key = "#email")
    public CustomerContactDto getCustomerContactByEmail(String email) {
        log.info("ContactsManagementService.getCustomerContactByEmail(...) - retrieving customer contact by email. value: {}", email);
        Optional<CustomerContact> customerContact = repository.findCustomerContactByEmail(email);
        return customerContact.map(mapper::toDto).orElse(null);
    }
}