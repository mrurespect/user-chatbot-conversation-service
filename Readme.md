# Chatbot and User Conversation REST Service

This project is a REST service for managing conversations between a chatbot and users. The chatbot has the default ID 1000. Users can have multiple conversations with the chatbot. It is based on the Spring Boot framework.

## Documentation

- To access the OpenAPI documentation, you can use the Swagger console available via the endpoint: /swagger-ui/index.html
- REST API documentation can be accessed via the endpoint :v3/api-docs


### API Endpoints

| Endpoint           | HTTP Method | Description                                                  |
|--------------------|-------------|--------------------------------------------------------------|
| `/login`           | GET         | Check the login status of a user                             |
| `/login`           | POST        | Login a user                                                 |
| `/register`        | POST        | Register a new user                                          |
| `/conversations`   | GET         | Retrieve all conversations of the logged-in user             |
| `/conversation/{id}` | GET      | Retrieve all messages in a conversation by its ID            |
| `/remove`          | GET         | Log out the current user                                     |
| `/info`            | GET         | Retrieve information about the logged-in user                |
| `/conversation`    | POST        | Create a new conversation                                    |
| `/message`         | POST        | Add a new message to a conversation                          |

### Dependencies

The project uses the following dependencies:

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Springdoc OpenAPI Starter WebMVC UI
- Lombok
- MySQL Connector Java
- Spring Boot Devtools

### Database Configuration

The application uses MySQL as the database. The configuration for the database is in the `application.properties` file. 

