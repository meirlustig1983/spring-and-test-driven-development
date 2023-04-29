# Spring And Test Driven Development

### Branches list:

#### 1: Write integration tests for the service class [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/1)]

* Add [ContactsManagementService](https://github.com/meirlustig1983/spring-and-test-driven-development/blob/main/src/main/java/com/ml/springandtestdrivendevelopment/services/ContactsManagementService.java) java class.
* Create [Integration Test](https://github.com/meirlustig1983/spring-and-test-driven-development/blob/main/src/test/java/com/ml/springandtestdrivendevelopment/services/ContactsManagementServiceTest.java) for 'ContactsManagementService' using @SpringBootTest.  

#### 2: Write unit tests for the service class [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/2)]

* Create [Unit Test](https://github.com/meirlustig1983/spring-and-test-driven-development/blob/main/src/test/java/com/ml/springandtestdrivendevelopment/services/ContactsManagementServiceUnitTest.java) for 'ContactsManagementService' using @SpringJUnitConfig.

#### 3: Write unit tests and integration tests for the controller [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/4)]

* Add support for '/api/v1/contacts/save' in order to save new 'CustomerContact'.
* Add support for '/api/v1/contacts/search/getAll' in order to get all 'CustomerContact' objects.
* Add support for '/api/v1/contacts/search/{customerContactId}/customerContactId' in order to get 'CustomerContact' by id.
* Add unit tests and integration tests