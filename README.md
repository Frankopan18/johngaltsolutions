# SolutionForApiApplication

This application tracks and manages city-related data while maintaining a history of all changes (INSERT, UPDATE, DELETE) for auditing purposes. The primary table used is `city_info`, and all historical data is logged in the `city_info_history` table.

## How to Run the Application

### Start the Application
To start the application, simply run:

### SolutionForApiApplication.java

### Run with Docker
To run the application using Docker, follow these steps:
1. Build the project: mvn clean package
2. Start the Docker containers: docker compose up --build

## API Documentation
The API documentation is provided via **Swagger**. Once the application is running, you can access the Swagger UI at:
http://localhost:8080/swagger-ui.html


## Database Structure

### Main Table
The `city_info` table is the primary table where all city-related data is stored. It includes fields such as temperature, city name, country code, and other related information.

### History Table
The `city_info_history` table tracks all changes made to the `city_info` table. Each operation (INSERT, UPDATE, DELETE) is logged along with a timestamp, allowing you to monitor the history of modifications.

## Application Features

1. **Scheduled Updates**:
    - The application updates the `city_info` table every 60 minutes. This interval is configured via the application properties.

2. **Change Tracking**:
    - All changes to the `city_info` table are automatically logged into the `city_info_history` table using PostgreSQL triggers.

3. **Swagger API Documentation**:
    - The application provides a user-friendly Swagger UI for testing and interacting with APIs.

4. **Docker Support**:
    - The application can be easily containerized and deployed using Docker.

## Key Application Property
The scheduling and schema updates are controlled via the `application.properties` file. Ensure the following configuration is set to prevent loss of manually created tables or triggers:


## API Examples

### 1. **GET Request: Retrieve City Information**
Use the following endpoint to retrieve city information:


- **Query Parameters**:
    - `cities`: Specifies the cities to fetch information for. Multiple cities can be provided.
    - `export`: If set to `true`, a `.csv` file containing the results will be generated in the root of the project.

- **Example**:
    - Request:
      ```
      http://localhost:8080/api/city-info?cities=London&cities=Paris&export=false
      ```
    - Response:
      ```json
      [
        {
          "id": 1,
          "city": "London",
          "temperature": 15.0,
          "countryCode": "GB",
          "language": "English",
          "borderingCountries": "France, Ireland",
          "topNews": "Latest updates from London"
        },
        {
          "id": 2,
          "city": "Paris",
          "temperature": 20.0,
          "countryCode": "FR",
          "language": "French",
          "borderingCountries": "Belgium, Germany",
          "topNews": "Breaking news from Paris"
        }
      ]
      ```

### 2. **GET Request with Export**
To generate a `.csv` file with the retrieved data, set `export=true` in the query parameters:

- **Request**: http://localhost:8080/api/city-info?cities=London&cities=Paris&export=true

- **Result**:
  A `.csv` file will be generated in the root of the project containing the requested data.

---




### Enjoy using SolutionForApiApplication!
