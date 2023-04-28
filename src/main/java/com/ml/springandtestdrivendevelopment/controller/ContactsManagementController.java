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

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerContactDto>> listCustomerContacts() {
        log.info("ContactsManagementController.listCustomerContacts() - return all customer contacts");
        val customerContactDtoList = service.getAllCustomerContacts();
        if (customerContactDtoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customerContactDtoList);
    }
}
