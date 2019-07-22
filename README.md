# LEOVEGAS coding challenge - Player transactions

* The solution can be imported as Maven project in IDE and started via Wallet.java OR
* Application can be built in terminal with `mvn clean package && java -jar target/wallet-microservice-1.0-SNAPSHOT.war`

It uses an H2 database, currently configured in-memory. But it can be configured in application.properties to use an existing remote instance.

Swagger is used, therefore after application startup everything is accessible at `http://localhost:8080/swagger-ui.html#/api`
