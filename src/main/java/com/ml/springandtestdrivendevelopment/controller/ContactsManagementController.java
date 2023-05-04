package com.ml.springandtestdrivendevelopment.controller;

import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import com.ml.springandtestdrivendevelopment.exceptions.ApiMethodException;
import com.ml.springandtestdrivendevelopment.services.ContactsManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
            throw new ApiMethodException("/save", "Save method has been failed, please check your input.", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        return ResponseEntity.ok().body(customerContactFromDb);
    }

    @GetMapping("/search/getAll")
    public ResponseEntity<List<CustomerContactDto>> getAllCustomerContacts() {
        log.info("ContactsManagementController.getCustomerContacts() - return all customer contacts");
        val customerContactDtoList = service.getAllCustomerContacts();
        return ResponseEntity.ok().body(customerContactDtoList);
    }

    @GetMapping("/search/{id}/customerContactId")
    public ResponseEntity<CustomerContactDto> getCustomerContactById(@PathVariable Long id) {
        log.info("ContactsManagementController.getCustomerContactById() - return customer contacts by id. value: {}", id);
        val customerContactDto = service.getCustomerContactById(id);
        if (customerContactDto == null) {
            throw new ApiMethodException("/search/{customerContactId}/customerContactId", "Search method has been failed, please check your input.", HttpStatus.NOT_FOUND, LocalDateTime.now());
        }
        return ResponseEntity.ok().body(customerContactDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerContactDto>> getCustomerContactsByIds(@RequestParam List<Long> ids) {
        log.info("ContactsManagementController.getCustomerContactsByIds() - return customer contacts by id. value: {}", ids);
        val customerContactDtoList = service.getCustomerContactsByIds(ids);
        if (customerContactDtoList.isEmpty()) {
            throw new ApiMethodException("/search", "Search method has been failed, please check your input.", HttpStatus.NOT_FOUND, LocalDateTime.now());
        }
        return ResponseEntity.ok().body(customerContactDtoList);
    }

    @GetMapping("/search/email")
    public ResponseEntity<CustomerContactDto> getCustomerContactByEmail(@RequestParam String email) {
        log.info("ContactsManagementController.getCustomerContactByEmail() - return customer contacts by email. value: {}", email);
        val customerContactDto = service.getCustomerContactByEmail(email);
        if (customerContactDto == null) {
            throw new ApiMethodException("/search/{email}/email", "Search method has been failed, please check your input.", HttpStatus.NOT_FOUND, LocalDateTime.now());
        }
        return ResponseEntity.ok().body(customerContactDto);
    }
}
