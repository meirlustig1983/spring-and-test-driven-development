package com.ml.springandtestdrivendevelopment.repositories;

import com.ml.springandtestdrivendevelopment.dta.CustomerContact;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {
    @NonNull List<CustomerContact> findAll();

    @Query("SELECT c FROM CustomerContact c WHERE c.id = :id")
    Optional<CustomerContact> findCustomerContactById(@Param("id") Long id);

    @Query("SELECT c FROM CustomerContact c WHERE c.email = :email")
    Optional<CustomerContact> findCustomerContactByEmail(String email);

    @Query("SELECT c FROM CustomerContact c WHERE c.id IN :ids")
    List<CustomerContact> findAllByIdIn(List<Long> ids);
}