# Catalog Service

The book catalog backend for Polar Bookshop. It stores books in PostgreSQL using Spring Data JDBC, applies Flyway migrations, validates book data, and protects catalog changes with Spring Security OAuth2 resource server support.

## API

| Method | Path | Purpose |
| --- | --- | --- |
| GET | `/books` | List books |
| GET | `/books/{isbn}` | Find a book |
| POST | `/books` | Add a book |
| DELETE | `/books/{isbn}` | Remove a book |

Book reads are public. Writes require the `ROLE_employee` authority under the current security rules. The token authority mapping must match the identity provider's configuration.

A book submission contains `isbn`, `title`, `author`, `price`, and `publisher`. ISBN must match the configured ISBN pattern, text fields must be nonblank, and price must be positive.

## Run locally

Start PostgreSQL and Keycloak using the [project setup](https://github.com/Aymanhamad2002/edge-service#run-locally). The database `polardb_catalog` must exist.

```powershell
.\gradlew.bat bootRun
```

The service listens on `http://localhost:9001`. The Gradle `bootRun` task enables the `testdata` profile to load demo books. Health and metrics are available at `/actuator/health` and `/actuator/prometheus`.

| Environment override | Default |
| --- | --- |
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5432/polardb_catalog` |
| `SPRING_DATASOURCE_USERNAME` / `SPRING_DATASOURCE_PASSWORD` | Local demo database credentials |
| `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI` | `http://localhost:8080/realms/PolarBookshop` |

## Related repositories

| Repository | Responsibility |
| --- | --- |
| [edge-service](https://github.com/Aymanhamad2002/edge-service) | Gateway, login, sessions, and full project overview |
| [catalog-service](https://github.com/Aymanhamad2002/catalog-service) | Book catalog and PostgreSQL persistence |
| [order-service](https://github.com/Aymanhamad2002/order-service) | Orders and asynchronous dispatch tracking |
| [dispatcher-service](https://github.com/Aymanhamad2002/dispatcher-service) | Event-driven packing and labeling |

## Build and test

Java 17 is configured in the Gradle toolchain. Use the included Gradle wrapper:

```powershell
.\gradlew.bat test
.\gradlew.bat bootJar
.\gradlew.bat bootBuildImage
```

On macOS/Linux, use `./gradlew` instead. Building container images requires Docker. Catalog and order tests include database integration tests; have Docker available for Testcontainers.

## Development configuration

The values in `src/main/resources/application.yml` are local development defaults. Demo credentials such as `user` / `password` are public, disposable values for local infrastructure; never reuse them for shared environments or real data.

Spring Boot allows environment variables to override YAML values without changing the file. For any shared or production environment, supply unique credentials externally through deployment configuration or a secret manager, and do not commit them.

See [Spring Boot externalized configuration](https://docs.spring.io/spring-boot/reference/features/external-config.html) and [OWASP secrets management](https://cheatsheetseries.owasp.org/cheatsheets/Secrets_Management_Cheat_Sheet.html).

