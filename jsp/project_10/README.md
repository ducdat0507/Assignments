# Event Management System

A JSP/Servlet web application for managing events and attendees using JPA and MySQL with internationalization support (English and Vietnamese).

## Prerequisites

- Java 8 or higher
- MySQL Server
- Maven
- A servlet container like Tomcat

## Setup

1. **Database Setup:**
   - Create a MySQL database named `eventdb`.
   - Run the SQL script `create_tables.sql` to create the necessary tables.

2. **Configure Database Connection:**
   - Update `src/main/resources/META-INF/persistence.xml` with your MySQL credentials:
     - Change `javax.persistence.jdbc.url` to your database URL.
     - Change `javax.persistence.jdbc.user` and `javax.persistence.jdbc.password` to your credentials.

3. **Build the Project:**
   - Run `mvn clean compile` to compile the project.

4. **Deploy to Tomcat:**
   - Run `mvn clean package` to create the WAR file.
   - Deploy the generated `project_10.war` file to your Tomcat server.

5. **Access the Application:**
   - Open your browser and go to `http://localhost:8080/project_10/` (adjust port if necessary).
   - Use the language links at the bottom to switch between English and Vietnamese.

## Features

- Add new events with validation.
- View all events in a table.
- Delete events.
- Add attendees to events with validation.
- View attendees for a specific event.
- Internationalization: English and Vietnamese language support.

## Technologies Used

- Java Servlets
- JSP with JSTL
- JPA (Hibernate)
- MySQL
- Bean Validation
- Maven