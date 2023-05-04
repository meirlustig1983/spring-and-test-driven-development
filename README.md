# Spring And Test Driven Development

### Features list:

#### 1: Write integration tests for the service class [[#1](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/1)]

* New service has been added,
  a [ContactsManagementService](https://github.com/meirlustig1983/spring-and-test-driven-development/blob/main/src/main/java/com/ml/springandtestdrivendevelopment/services/ContactsManagementService.java)
  java class.
* [Integration Test](https://github.com/meirlustig1983/spring-and-test-driven-development/blob/main/src/test/java/com/ml/springandtestdrivendevelopment/services/ContactsManagementServiceTest.java)
  for 'ContactsManagementService' using @SpringBootTest has been added.

#### 2: Write unit tests for the service class [[#2](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/2)]

* New [Unit Tests](https://github.com/meirlustig1983/spring-and-test-driven-development/blob/main/src/test/java/com/ml/springandtestdrivendevelopment/services/ContactsManagementServiceUnitTest.java)
for 'ContactsManagementService' using @SpringJUnitConfig.

#### 3: Write unit tests and integration tests for the controller [[#4](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/4)]

* New method has been added, '/api/v1/contacts/save' in order to save a new 'CustomerContact' object.
* New method has been added, '/api/v1/contacts/search/getAll' in order to return all 'CustomerContact' objects.
* New method has been added, '/api/v1/contacts/search/{customerContactId}/customerContactId' in order to return '
  CustomerContact' object by id.
* Unit tests and integration tests has been added.

#### 4: Write integration tests for the repository [[#5](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/5)]

* New method has been added, '/api/v1/contacts/search/{email}/email' in order to return 'CustomerContact' object by
  email.
* Unit tests and integration tests has been added.

#### 5: Use SQL scripts in order to load DataSets to the local H2 DB [[#6](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/6)]

* New SQL script has been created, the script 'test-datasets.sql' will create data-set of 'CustomerContact' objects
  for ITs.
* New SQL script has been created, the script 'delete-datasets.sql' will clean the DB after each method.
* Modify all IT classes to use the DataSet files using @Sql

#### 6: Add a SuiteTest for CustomerContent [[#7](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/7)]

* Modify 'build.gradle' file with dependencies for
  SuiteTest. [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/7/files#diff-49a96e7eea8a94af862798a45174e6ac43eb4f8b4bd40759b5da63ba31ec3ef7R29)]
* Create new suite test
  class [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/7/files#diff-7b196dd5e5e6fa473e94bbe5d13833e3c06e700afd6f12927794c9da660b7122R1)]

#### 7: Start to use application.yml file [[#8](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/8)]

* 'application.properties' file has been converted to 'application.yml'

#### 8: Add 'ExceptionHandler' [[#9](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/9)]

* New 'Record' has been added. The ['ApiError'](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/9/files#diff-f9e08e93418871579789755a3b47f9223e3e4090bda2306e37d223b688712270R1) is a return object when exception has benn thrown.
* New 'Exception' has been added. The ['ApiMethodException'](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/9/files#diff-0c5da622f29bde94216012a0e1ab9669225620b52517f2594091ecc8eea71733R1) will be thrown when a lack of information condition occurs.
* ['DefaultExceptionHandler'](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/9/files#diff-61376bb15e54d97ee062ac5aab7a266c70afe0f31448bf49596afa3dc13c7912R1) has been added.
* Unit tests and integration tests has been added.

#### 9: Improve Performance [[#11](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/11)]

* Clean old code.
* Add new @Index to @Table("customer_contact") [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/11/files#diff-98c07a35ad1972afa552c71a64bb3b60d1ef733cf9951396d30fce543880c4cbR15)]
* Set all @Indexes as 'unique' [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/11/files#diff-98c07a35ad1972afa552c71a64bb3b60d1ef733cf9951396d30fce543880c4cbR15)]
* Use @With in 'Record' [[link](https://github.com/meirlustig1983/spring-and-test-driven-development/pull/11/files#diff-57329cf1fd7e1fe6c7cc9f16ee9e198e872f8ae04256176f4b8f1bddc228674fR9)]
* Unit tests and integration tests has been added.