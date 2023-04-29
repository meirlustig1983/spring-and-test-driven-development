package com.ml.springandtestdrivendevelopment.controller;

import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import com.ml.springandtestdrivendevelopment.services.ContactsManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = {"/api/v1/contacts"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactsManagementController {

    private final ContactsManagementService service;

    @PostMapping("/save")
    public ResponseEntity<CustomerContactDto> addCustomerContact(@RequestBody CustomerContactDto dto) {
        log.info("ContactsManagementController.addCustomerContact() - add customer contact");
        val customerContactFromDb = service.addCustomerContact(dto);
        if (customerContactFromDb == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(customerContactFromDb);
    }

    @GetMapping("/search/getAll")
    public ResponseEntity<List<CustomerContactDto>> getCustomerContacts() {
        log.info("ContactsManagementController.getCustomerContacts() - return all customer contacts");
        val customerContactDtoList = service.getAllCustomerContacts();
        if (customerContactDtoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customerContactDtoList);
    }

    @GetMapping("/search/{customerContactId}/customerContactId")
    public ResponseEntity<CustomerContactDto> getCustomerContact(@PathVariable Long customerContactId) {
        log.info("ContactsManagementController.getCustomerContact() - return customer contacts by id. value: {}", customerContactId);
        val customerContactDto = service.getCustomerContact(customerContactId);
        if (customerContactDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customerContactDto);
    }
}
