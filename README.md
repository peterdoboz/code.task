# Pet Service API Project

## Overview
This project is a RESTful API for managing pet information, specifically Cats and Dogs. It includes functionality for adding pets, fetching all pets, and fetching pets that are outside a defined zone. The application uses an H2 in-memory database for data persistence, Lombok for reducing boilerplate code, and MapStruct for object mapping.

## Technologies Used
- **Spring Boot**: For building the RESTful API.
- **H2 Database**: An in-memory database used for quick development and testing.
- **Lombok**: To reduce boilerplate code, such as getters, setters, and constructors.
- **MapStruct**: For mapping between entities (`Pet`, `Cat`, `Dog`) and Data Transfer Objects (`PetDto`).

## Endpoints

### 1. Add a Pet
**POST** `/api/pets`
- Adds a new pet (either a Cat or Dog).
- Request Body: JSON representing `PetDto`.

Example:
```bash
curl -X POST http://localhost:8080/api/pets \
  -H "Content-Type: application/json" \
  -d '{
        "petType": "CAT",
        "trackerType": "S",
        "ownerId": 101,
        "inZone": true
      }'
```

### 2. Get Pets Outside the Zone
**GET** `/api/pets/outside-zone`
- Fetches a breakdown of the number of pets that are outside the zone, grouped by pet type and tracker type.
- Response: A nested map with `PetType` as the key and a map of `TrackerType` to count as the value.

Example:
```bash
curl -X GET "http://localhost:8080/api/pets/outside-zone"
```
- Example Response:
```json
{
  "CAT": {
    "S": 3,
    "L": 2
  },
  "DOG": {
    "S": 1,
    "M": 4,
    "L": 2
  }
}
```

### 3. Get All Pets
**GET** `/api/pets`
- Fetches all pets.

Example:
```bash
curl -X GET http://localhost:8080/api/pets
```

## Database Initialization
The application uses H2 as an in-memory database. The schema and initial data are loaded using `schema.sql` and `data.sql` respectively.
- **`schema.sql`**: Defines the database structure.
- **`data.sql`**: Populates the database with initial test data.

## Running the Application
1. **Clone the Repository**: Clone the project from your version control system.
2. **Build the Project**: Run `mvn clean install` to build the project.
3. **Run the Application**: Use `mvn spring-boot:run` or run the `main` method in your IDE.
4. **Access the H2 Console**: Navigate to `http://localhost:8080/h2-console` to access the in-memory database. Use the JDBC URL `jdbc:h2:mem:testdb`.

## Testing
The project includes both unit and integration tests to verify the functionality of the REST endpoints. Use the following command to run tests:
```bash
mvn test
```

### Test Classes
- **`PetMapperTest`**: Tests the `PetMapper` class.
- **`PetServiceTest`**: Tests the `PetService` class.
- **`PetControllerIntegrationTest`**: Integration test for the `PetController`.
