# College Fee Management Backend

- **Student:** Iradukunda Deborah
- **Student ID:** 27706
- **Project:** College Fee Management
- **Course:** INSY 8322 Web Technology and Internet (AUCA)

## Technologies
Spring Boot, Spring Data JPA, PostgreSQL

## How to run
1. Install JDK, PostgreSQL and Maven (or use the included `mvnw`).
2. Create a PostgreSQL database named `college_fee_db`.
3. Open `src/main/resources/application.properties` and set `spring.datasource.username` and `spring.datasource.password` to your own PostgreSQL values.
4. Run the API:
   - Windows: `.\mvnw spring-boot:run`
   - Mac/Linux: `./mvnw spring-boot:run`
5. The API starts on `http://localhost:8080`.

## Postman
Import `college-fee-management-midterm.postman_collection.json` from the repository root.