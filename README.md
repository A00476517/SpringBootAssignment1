# SpringBootAssignment1

# Spring Boot Hotel Management App

Welcome to the Spring Boot Hotel Management App! This application allows you to manage information about hotels, including their names, locations, stars, and total rooms. Follow the instructions below to set up the database and run the application.

## Database Configuration

- **Database Name:** Your preferred database name (not specified in the provided information).
- **Table Name:** hotel

**Query to Create Table:**
```sql
CREATE TABLE hotel (
    hotel_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    stars INT,
    total_rooms INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
## Running the Application

1. **Clone this repository to your local machine:**
    ```bash
    git clone https://github.com/A00476517/SpringBootAssignment1.git
    ```

2. **Navigate to the project directory:**
    ```bash
    cd SpringBootAssignment1
    ```

3. **Run the application using the following command:**
    ```bash
    ./mvnw spring-boot:run
    ```

    The application will run on [http://localhost:8080](http://localhost:8080).

## API Endpoints

- **Get Hotels:**
  - **Method:** GET
  - **Endpoint:** [http://localhost:8080/api/hotels](http://localhost:8080/api/hotels)

- **Add Hotel:**
  - **Method:** POST
  - **Endpoint:** [http://localhost:8080/api/addHotels](http://localhost:8080/api/addHotels)

## Screenshots of Running App:
- **Get Hotels:**

![Screenshot (1180)](https://github.com/A00476517/SpringBootAssignment1/assets/144840145/4f017633-9b07-409a-b0b2-d3a4a2143050)

- **Add Hotel:**
![Screenshot (1181)](https://github.com/A00476517/SpringBootAssignment1/assets/144840145/f74d4cb3-bec0-4a0e-993c-59f384c84795)
