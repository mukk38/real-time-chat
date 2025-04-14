# Real-Time Chat Application

A real-time chat application built with **Spring Boot** for the backend and **ReactJS** for the frontend, using **WebSockets** for real-time communication and **JWT (JSON Web Token)** for authentication. This project aims to create a scalable and responsive chat system where users can send and receive messages instantly.

## Features

- **Real-Time Messaging**: Users can send and receive messages in real-time using WebSockets.
- **JWT Authentication**: Secure user authentication with JSON Web Tokens (JWT).
- **Private and Group Chat**: Support for both private messages and group chat functionality.
- **Frontend (ReactJS)**: User interface built using ReactJS, leveraging WebSocket for message handling.
- **Backend (Spring Boot)**: Backend server developed with Spring Boot, handling WebSocket connections, message routing, and authentication.

## Tech Stack

- **Frontend**: ReactJS, SockJS, StompJS
- **Backend**: Spring Boot, Spring WebSocket, JWT (JSON Web Token)
- **Database**: (Add your database technology here, if used)
- **Authentication**: JWT Token-based Authentication

## Installation

### Backend (Spring Boot)

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/chat-application.git
    cd chat-application/backend
    ```

2. Install dependencies (if any):
    ```bash
    mvn install
    ```

3. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```

4. The backend server will be running on `http://localhost:8080`.

### Frontend (ReactJS)

1. Clone the repository (if you haven't already):
    ```bash
    git clone https://github.com/your-username/chat-application.git
    cd chat-application/frontend
    ```

2. Install dependencies:
    ```bash
    npm install
    ```

3. Start the React development server:
    ```bash
    npm start
    ```

4. The frontend will be running on `http://localhost:3000`.

## Usage

1. **JWT Authentication**: To authenticate a user, obtain a JWT token from the backend API and store it in `localStorage`. The token should be included in the `Authorization` header for WebSocket connections.

2. **Messaging**: Once authenticated, users can join group chats or send private messages. The app supports real-time message delivery through WebSocket.

3. **Send and Receive Messages**: Messages are sent using the WebSocket endpoint `/app/group/{groupName}`, and they are received from the topic `/topic/group/{groupName}`.

4. **Group and Private Chat**: Users can specify group names to join group chats and send messages to all members of that group.

## Project Structure

- **Backend**: The backend consists of:
    - WebSocket configuration and JWT authentication.
    - Controllers and services handling the business logic and message routing.

- **Frontend**: The frontend consists of:
    - React components for managing the user interface and interaction.
    - A WebSocket client handling the connection and message exchanges.

## Contributing

Feel free to fork this project, open issues, and submit pull requests. Any contributions are welcome!

### Steps to Contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Note:** Remember to update the repository URL, JWT implementation, and any other specifics based on your project's actual configuration.
