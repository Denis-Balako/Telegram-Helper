# Telegram Helper API

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](http://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-with-grammas-recipe.svg)](http://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/powered-by-coffee.svg)](http://forthebadge.com)

A ready-to-use **Telegram Helper API** for helping in daily before sleeping questions.

## Table of Contents

- [👨‍💻Tech stack](#technologies-and-tools)
- [⚡Quick start](#quick-start)
- [‍✈️Controllers](#controllers)
- [🧑‍🚀Postman Collection](#postman-collection)
- [🎥Video Presentation](#video-presentation)
- [🏁Conclusion](#conclusion)
- [📝License](#license)

## 👨‍💻Tech stack

Here's a brief high-level overview of the tech stack the **BookStore API** uses:

- [Spring Boot](https://spring.io/projects/spring-boot): provides a set of pre-built templates and conventions for creating stand-alone, production-grade Spring-based applications.
- [Hibernate](https://hibernate.org/): simplifies the interaction between Java applications and databases by mapping Java objects to database tables and vice versa.
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html): provides features like authentication, authorization, and protection against common security threats.
- [Spring Web](https://spring.io/projects/spring-ws#overview): includes tools for handling HTTP requests, managing sessions, and processing web-related tasks.
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/): provides a higher-level abstraction for working with databases and includes support for JPA (Java Persistence API).
- [Lombok](https://projectlombok.org/): helps reduce boilerplate code by automatically generating common code constructs (like getters, setters, constructors, etc.) during compile time.
- [Mapstruct](https://mapstruct.org/): generates mapping code based on annotations, reducing the need for manual, error-prone mapping code.
- [Liquibase](https://www.liquibase.org/): helps manage database schema changes over time, making it easier to track and deploy database updates.
- [Swagger](https://swagger.io/): provides a framework for generating interactive API documentation, allowing developers to understand, test, and use APIs more easily.
- [Docker](https://www.docker.com/): provides a consistent and reproducible way to deploy applications across different environments.
- [Postman](https://www.postman.com/): allows developers to create and send HTTP requests to test APIs, monitor responses, and automate testing workflows.
- [Telegram API](https://core.telegram.org/bots/api): provides an integration between Java and Telegram messaging app.
- [Chat GPT](https://chat.openai.com/): generates answers for user prompt questions. 

## ⚡️ Quick start

First, download a [repository][repo_url].
You can use git console command:

```bash
git clone https://github.com/Denis-Balako/Telegram-Helper.git
```

Build a project using **Maven**:
```bash
mvn clean install
```
Then, rise a **Docker** container of your app:
```bash
docker build -t {your-image-name} .
docker-compose build
docker-compose up
```
Also, you can run this project without docker, but before that, you need to configure the connection to your local database in the application properties. Run this command after that:
```bash
 mvn spring-boot:run
```

That's all you need to know to start! 🎉

## ‍✈️Controllers

- [AuthenticationController](auth_controller): handles user registration and authorization.
- [TelegramChatController](telegram_chat_controller): manages telegram chat operations, such as finding all, find by ID and get correspondence.
- [TelegramMessageController](telegram_message_controller): manages telegram messages operations, allows to get all messages, find message by ID and sending messages.
- [TelegramUserController](telegram_user_controller): manages telegram users, allows admins finding all, finding by ID and get all user messages.

## 🧑‍🚀Postman Collection

For easy test, I've created a Postman [collection](postman_collection) and [environment](postman_environment), that includes all user and admin requests.

For access to all endpoints use *admin login* in *admin endpoints* folder or use admin credentials below:<br>

Username: denis@example.com <br>
Password: qwe123456789

## 🎥Video Presentation

This video shows the operation of the application in detail.
Here we will see how the search for books and categories works, the ability of the admin to change them,
the user functionality,
and how the shopping cart and orders work.

Link : https://www.loom.com/share/635cd98a88154d85899f239fbc9781fc?sid=b2e89892-97ec-4dc1-b294-1acd46b7e286

## 🏁Conclusion

The **Telegram Helper API** offers a helper for daily things and answer different questions.

## 📝License

This project is licensed under the MIT license. Feel free to edit and distribute this template as you like.


<!-- Repository -->
[repo_url]: https://github.com/Denis-Balako/Telegram-Helper.git
[auth_controller]: https://github.com/Denis-Balako/Telegram-Helper/blob/main/src/main/java/com/balako/telegramhelper/controller/AuthenticationController.java
[telegram_chat_controller]: https://github.com/Denis-Balako/Telegram-Helper/blob/main/src/main/java/com/balako/telegramhelper/controller/TelegramChatController.java
[telegram_message_controller]: https://github.com/Denis-Balako/Telegram-Helper/blob/main/src/main/java/com/balako/telegramhelper/controller/TelegramMessageController.java
[telegram_user_controller]: https://github.com/Denis-Balako/Telegram-Helper/blob/main/src/main/java/com/balako/telegramhelper/controller/TelegramUserController.java
[postman_collection]: https://github.com/Denis-Balako/Telegram-Helper/blob/master/Telegram%20Helper.postman_collection.json
[postman_environment]: https://github.com/Denis-Balako/Telegram-Helper/blob/master/Telegram%20helper%20Environment.postman_environment.json
