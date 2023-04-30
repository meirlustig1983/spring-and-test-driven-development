package com.ml.springandtestdrivendevelopment;

import com.ml.springandtestdrivendevelopment.controller.ContactsManagementControllerIT;
import com.ml.springandtestdrivendevelopment.repositories.CustomerContactRepositoryIT;
import com.ml.springandtestdrivendevelopment.services.ContactsManagementServiceIT;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ContactsManagementControllerIT.class, CustomerContactRepositoryIT.class, ContactsManagementServiceIT.class})
public class CustomerContactSuiteTest {
    // intentionally empty
}