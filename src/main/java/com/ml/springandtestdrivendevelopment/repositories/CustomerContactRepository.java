package com.ml.springandtestdrivendevelopment.repositories;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import org.springframework.data.repository.CrudRepository;


public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {

}
