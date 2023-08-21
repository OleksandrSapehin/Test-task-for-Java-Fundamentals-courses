REST API service to create and verify a math example and its roots types 2x = 16

The REST API is designed and developed on the stack:

* JPA. Hibernate (Postgres)
* Spring Boot
* Spring validator
* Spring MVC
* Maven

The API implements the following features:
* Create and verify a math example("/new")
* Find examples by roots ("/examples/{roots}")
* Find all equations that have exactly one root("/check-unique-roots")
* Return all examples and roots ("allRootsAndExamples")
