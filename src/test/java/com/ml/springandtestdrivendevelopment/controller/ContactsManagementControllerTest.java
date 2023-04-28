package com.ml.springandtestdrivendevelopment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactsManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addCustomerContactTest() throws Exception {
        CustomerContactDto customerContactDto = new CustomerContactDto(
                null,
                "John",
                "Doe",
                "jd@gmail.com",
                "1234 N 12TH ST",
                null,
                "New York",
                "NY",
                "12345",
                new Date());

        mockMvc.perform(post("/api/v1/contacts/save")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customerContactDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("jd@gmail.com"))
                .andExpect(jsonPath("$.addressLine1").value("1234 N 12TH ST"))
                .andExpect(jsonPath("$.addressLine2").isEmpty())
                .andExpect(jsonPath("$.city").value("New York"))
                .andExpect(jsonPath("$.state").value("NY"))
                .andExpect(jsonPath("$.zipCode").value("12345"))
                .andExpect(jsonPath("$.createdDate").exists());
    }

    @Test
    void addCustomerContactTestShouldFailed() throws Exception {
        HashMap<String, Object> hashMap = new HashMap<>();

        mockMvc.perform(post("/api/v1/contacts/save")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(hashMap)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listCustomerContactsTest() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("jd@gmail.com"))
                .andExpect(jsonPath("$[0].addressLine1").value("12001 E 42TH ST"))
                .andExpect(jsonPath("$[0].addressLine2").isEmpty())
                .andExpect(jsonPath("$[0].city").value("New York"))
                .andExpect(jsonPath("$[0].state").value("NY"))
                .andExpect(jsonPath("$[0].zipCode").value("12345"))
                .andExpect(jsonPath("$[0].createdDate").exists())
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].firstName").value("Miki"))
                .andExpect(jsonPath("$[1].lastName").value("Cohen"))
                .andExpect(jsonPath("$[1].email").value("mc@gmail.com"))
                .andExpect(jsonPath("$[1].addressLine1").value("16002 W 57TH ST"))
                .andExpect(jsonPath("$[1].addressLine2").isEmpty())
                .andExpect(jsonPath("$[1].city").value("New York"))
                .andExpect(jsonPath("$[1].state").value("NY"))
                .andExpect(jsonPath("$[1].zipCode").value("14006"))
                .andExpect(jsonPath("$[1].createdDate").exists());
    }

    @Test
    void getCustomerContactTest() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search/1/customerContactId"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("jd@gmail.com"))
                .andExpect(jsonPath("$.addressLine1").value("12001 E 42TH ST"))
                .andExpect(jsonPath("$.addressLine2").isEmpty())
                .andExpect(jsonPath("$.city").value("New York"))
                .andExpect(jsonPath("$.state").value("NY"))
                .andExpect(jsonPath("$.zipCode").value("12345"))
                .andExpect(jsonPath("$.createdDate").exists());
    }

    @Test
    void getCustomerContactTestShouldFailed() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search/4/customerContactId"))
                .andExpect(status().isNotFound());
    }
}