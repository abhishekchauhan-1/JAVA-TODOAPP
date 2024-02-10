
```markdown
# ToDoApp

ToDoApp is a simple web application for managing your to-do tasks. It provides endpoints to create, update, retrieve, and delete tasks for authenticated users. The application is secured using JSON Web Tokens (JWT) for user authentication.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Author](#author)

## Features

- User authentication and authorization using JWT.
- Create, update, retrieve, and delete tasks (To-Dos).
- User signup and login functionality.
- Secure endpoints accessible only to authenticated users.

## Technologies Used

- Spring Boot
- Spring Security
- MongoDB
- Lombok
- JSON Web Tokens (JWT)
- Postman (for testing API endpoints)

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/ToDoApp.git
   cd ToDoApp
   ```

2. **Set up MongoDB:**
    - Install and run MongoDB locally or provide the MongoDB connection details in the application properties.

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the application:**
    - Open your web browser and navigate to `http://localhost:8080`.

## Usage

- Use Postman or any API testing tool to interact with the provided API endpoints.
- Create a user using the `/public/create-user` endpoint.
- Login using the `/public/login` endpoint to obtain a JWT token.
- Use the obtained token to access secured endpoints for managing your to-do tasks.

## API Endpoints

- **Public Endpoints:**
    - `POST /public/create-user`: Create a new user (Signup).
        - **Description:** Used for user registration. Requires a JSON payload with user details.
        - **Request Example:**
          ```json
          {
            "name": "John Doe",
            "userEmail": "john@example.com",
            "password": "password123"
          }
          ```

    - `POST /public/login`: Authenticate and obtain a JWT token (Login).
        - **Description:** Used for user login. Requires a JSON payload with user credentials.
        - **Request Example:**
          ```json
          {
            "userEmail": "john@example.com",
            "password": "password123"
          }
          ```

- **ToDo Endpoints:**
    - `POST /todos`: Create a new ToDo task.
        - **Description:** Used to add a new task to the authenticated user's to-do list. Requires a JSON payload with task details.
        - **Request Example:**
          ```json
          {
            "title": "Task 1",
            "description": "Complete Task 1",
            "status": false,
            "dueDate": "2024-02-10T12:00:00"
          }
          ```

    - `GET /todos`: Retrieve all ToDo tasks for the authenticated user.
        - **Description:** Fetches all tasks associated with the authenticated user.

    - `PUT /todos/{todoId}`: Update an existing ToDo task.
        - **Description:** Modifies the details of an existing task identified by `todoId`. Requires a JSON payload with updated task details.
        - **Request Example:**
          ```json
          {
            "title": "Updated Task 1",
            "description": "Updated description",
            "status": true,
            "dueDate": "2024-02-15T14:00:00"
          }
          ```

    - `DELETE /todos/{todoId}`: Delete a ToDo task.
        - **Description:** Removes the task identified by `todoId` from the user's to-do list.

- **User Endpoints:**
    - `GET /User`: Retrieve information about the authenticated user.
        - **Description:** Fetches details about the currently authenticated user.

    - `PUT /User`: Update user details.
        - **Description:** Modifies user details for the currently authenticated user. Requires a JSON payload with updated user details.
        - **Request Example:**
          ```json
          {
            "name": "Updated Name",
            "userEmail": "updated.email@example.com",
            "password": "newpassword123"
          }
          ```

## Author

Abhishek Singh Chauhan
```

