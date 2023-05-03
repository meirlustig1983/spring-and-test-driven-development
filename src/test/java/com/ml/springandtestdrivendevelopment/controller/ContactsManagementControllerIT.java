package com.ml.springandtestdrivendevelopment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.springandtestdrivendevelopment.dto.CustomerContactDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/data/test-datasets.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/data/delete-datasets.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ContactsManagementControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(scripts = "/data/delete-datasets.sql")
    public void addCustomerContact() throws Exception {
        CustomerContactDto customerContactDto = new CustomerContactDto(
                null,
                "Meir",
                "Lustig",
                "ml@gmail.com",
                "1407 W 32TH ST",
                null,
                "Brooklyn",
                "NY",
                "763215",
                new Date());

        mockMvc.perform(post("/api/v1/contacts/save")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customerContactDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.firstName").value("Meir"))
                .andExpect(jsonPath("$.lastName").value("Lustig"))
                .andExpect(jsonPath("$.email").value("ml@gmail.com"))
                .andExpect(jsonPath("$.addressLine1").value("1407 W 32TH ST"))
                .andExpect(jsonPath("$.addressLine2").isEmpty())
                .andExpect(jsonPath("$.city").value("Brooklyn"))
                .andExpect(jsonPath("$.state").value("NY"))
                .andExpect(jsonPath("$.zipCode").value("763215"))
                .andExpect(jsonPath("$.createdDate").exists());
    }

    @Test
    public void addCustomerContactTest_ShouldFailed() throws Exception {
        HashMap<String, Object> hashMap = new HashMap<>();

        mockMvc.perform(post("/api/v1/contacts/save")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(hashMap)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.path").value("/save"))
                .andExpect(jsonPath("$.message").value("Unhandled exception occurred. Body content isn't readable"))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.path").isNotEmpty());
    }

    @Test
    @Sql(scripts = "/data/delete-datasets.sql")
    public void getAllCustomerContacts_BodyIsNull() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void getAllCustomerContacts() throws Exception {
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
    public void getCustomerContactById() throws Exception {
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
    public void getCustomerContactById_CustomerContactIdIsNotExists_ShouldFailed() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search/4/customerContactId"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.path").value("/search/{customerContactId}/customerContactId"))
                .andExpect(jsonPath("$.message").value("Search method has been failed, please check your input."))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.path").isNotEmpty());
    }

    @Test
    public void getCustomerContactById_CustomerContactIdIsString_ShouldFailed() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search/a/customerContactId"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.path").value("Unknown"))
                .andExpect(jsonPath("$.message").value("Unhandled exception occurred. Please check the request content."))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.path").isNotEmpty());
    }

    @Test
    public void getCustomerContactsByIds() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search?ids=1,2,3"))
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
    public void getCustomerContactsByIds_CustomerContactIdIsNotExists_ShouldFailed() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search?ids=4"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.path").value("/search"))
                .andExpect(jsonPath("$.message").value("Search method has been failed, please check your input."))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.path").isNotEmpty());
    }

    @Test
    public void getCustomerContactsByIds_CustomerContactIdIsString_ShouldFailed() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search?ids=4g"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.path").value("Unknown"))
                .andExpect(jsonPath("$.message").value("Unhandled exception occurred. Please check the request content."))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.path").isNotEmpty());
    }

    @Test
    public void getCustomerContactByEmail() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search/email?email=jd@gmail.com"))
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
    public void getCustomerContactByEmail_EmailIsNotExists_ShouldFailed() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search/email?email=jdfv@gmail.com"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.path").value("/search/{email}/email"))
                .andExpect(jsonPath("$.message").value("Search method has been failed, please check your input."))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.path").isNotEmpty());
    }

    @Test
    public void getCustomerContactByEmail_EmailValueIsNull_ShouldFailed() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/search/email"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.path").value("Unknown"))
                .andExpect(jsonPath("$.message").value("Unhandled exception occurred. Please check the request content."))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.path").isNotEmpty());
    }
}