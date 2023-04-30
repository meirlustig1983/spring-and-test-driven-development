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

#### 4: Write integration tests for the repository [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/5)]

* Add support for '/api/v1/contacts/save' in order to save new 'CustomerContact'.
* Add support for '/api/v1/contacts/search' in order to get all 'CustomerContact' objects by ids.
* Add support for '/api/v1/contacts/search/{email}/email' in order to get 'CustomerContact' by email.
* Add unit tests and integration tests

#### 5: Use SQL scripts in order to load DataSets to the local H2 DB [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/6)]

* Create 'test-datasets.sql' SQL file in order to load the DataSet to H2.
* Create 'delete-datasets.sql' SQL file in order to clean the DB.
* Edit all IT classes to use the DataSet files using @Sql