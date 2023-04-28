package com.ml.springandtestdrivendevelopment.repositories;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {
    @NonNull List<CustomerContact> findAll();
}