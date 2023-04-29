package com.ml.springandtestdrivendevelopment.config;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import com.ml.springandtestdrivendevelopment.repositories.CustomerContactRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class InitDataConfig {

    @Bean
    public CommandLineRunner initData(CustomerContactRepository customerContactRepository) {
        return args -> {

            val builder = CustomerContact.builder();
            val customerContact1 = builder
                    .firstName("John")
                    .lastName("Doe")
                    .email("jd@gmail.com")
                    .addressLine1("12001 E 42TH ST")
                    .city("New York")
                    .state("NY")
                    .zipCode("12345")
                    .build();

            val customerContact2 = builder
                    .firstName("Miki")
                    .lastName("Cohen")
                    .email("mc@gmail.com")
                    .addressLine1("16002 W 57TH ST")
                    .city("New York")
                    .state("NY")
                    .zipCode("14006")
                    .build();

            customerContactRepository.save(customerContact1);
            customerContactRepository.save(customerContact2);
        };
    }
}