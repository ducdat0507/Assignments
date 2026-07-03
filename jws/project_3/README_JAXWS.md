# Computer Service - JAX-WS on GlassFish

This project has been converted from a servlet-based HTTP service to a **JAX-WS (Java API for XML Web Services)** implementation that runs on GlassFish server.

## Project Overview

The Computer Service provides a web service for managing computer inventory with the following operations:

- **getAllComputers()**: Retrieve all computers from the database
- **findComputerById(comID)**: Find a specific computer by ID
- **addComputer(Computer)**: Add a new computer to the database

## Architecture

### Components

1. **ComputerService** (Interface) - JAX-WS service interface defining web service operations
2. **ComputerServiceImpl** (Implementation) - JAX-WS service implementation
3. **Computer** (Model) - JAXB-annotated data model for XML serialization
4. **ComputerDAO** - Data Access Object for database operations
5. **ComputerClient** - Swing GUI client using JAX-WS proxy

### Technology Stack

- **JAX-WS 2.2**: Java API for XML Web Services
- **JAXB 2.2**: Java Architecture for XML Binding
- **GlassFish Server**: Application server
- **Maven**: Build tool
- **SQL Server**: Database (configurable)

## Prerequisites

- Java Development Kit (JDK) 1.8 or higher
- GlassFish Server 4.x or higher (comes with JAX-WS and JAXB)
- Apache Maven 3.x
- SQL Server (or configure different JDBC driver)

## Configuration

### Database Configuration

Edit `src/main/webapp/WEB-INF/web.xml` to set your database connection parameters:

```xml
<context-param>
    <param-name>jdbc.url</param-name>
    <param-value>jdbc:sqlserver://your-server:1433;databaseName=ComputerDB</param-value>
</context-param>
<context-param>
    <param-name>jdbc.user</param-name>
    <param-value>your-user</param-value>
</context-param>
<context-param>
    <param-name>jdbc.password</param-name>
    <param-value>your-password</param-value>
</context-param>
```

Alternatively, set Java system properties:
```bash
-Djdbc.url=jdbc:sqlserver://localhost:1433;databaseName=ComputerDB
-Djdbc.user=sa
-Djdbc.password=your-password
```

### Database Setup

Create the required table:

```sql
CREATE TABLE TblComputer (
    comID VARCHAR(50) PRIMARY KEY,
    comName VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    manufacturer VARCHAR(100) NOT NULL
);
```

## Building the Project

```bash
mvn clean package
```

This generates `target/project_3.war` which can be deployed to GlassFish.

## Deploying to GlassFish

### Method 1: Using GlassFish Admin Console

1. Start GlassFish:
   ```bash
   asadmin start-domain
   ```

2. Open GlassFish Admin Console: http://localhost:4848

3. Navigate to **Applications** → **Deploy**

4. Select the `target/project_3.war` file and click **Deploy**

5. The application will be deployed and the web service will be available at:
   ```
   http://localhost:8080/project_3/ComputerService
   ```

### Method 2: Using Command Line

```bash
asadmin deploy target/project_3.war
```

### Method 3: Copy WAR to Auto-deploy Directory

```bash
cp target/project_3.war $GLASSFISH_HOME/domains/domain1/autodeploy/
```

## Accessing the Web Service

### WSDL (Web Service Description Language)

The WSDL file is available at:
```
http://localhost:8080/project_3/ComputerService?wsdl
```

### Web Service Endpoint

```
http://localhost:8080/project_3/ComputerService
```

## Running the Client

The project includes a Swing GUI client (`ComputerClient`):

```bash
mvn exec:java -Dexec.mainClass="com.example.client.ComputerClient"
```

Or compile and run directly:
```bash
javac -cp target/classes src/main/java/com/example/client/ComputerClient.java
java -cp target/classes com.example.client.ComputerClient
```

## Using Web Service from External Clients

### Generate Web Service Stubs (Optional)

You can generate client stubs using wsimport:

```bash
wsimport -keep http://localhost:8080/project_3/ComputerService?wsdl
```

### Using SoapUI

1. Download and install SoapUI
2. Create a new SOAP project with WSDL: `http://localhost:8080/project_3/ComputerService?wsdl`
3. Test the web service operations directly

## Project Structure

```
project_3/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── client/
│   │   │       │   └── ComputerClient.java        # JAX-WS Client
│   │   │       ├── dao/
│   │   │       │   └── ComputerDAO.java           # Data Access Layer
│   │   │       ├── model/
│   │   │       │   └── Computer.java              # JAXB Model
│   │   │       └── service/
│   │   │           ├── ComputerService.java       # Web Service Interface
│   │   │           └── ComputerServiceImpl.java    # Web Service Implementation
│   │   └── webapp/
│   │       ├── index.jsp
│   │       └── WEB-INF/
│   │           ├── web.xml                        # Deployment Descriptor
│   │           └── sun-jaxws.xml                  # JAX-WS Configuration
│   └── test/
└── target/
    └── project_3.war
```

## Troubleshooting

### Service Not Found (404)

- Verify the WAR file is deployed: Check GlassFish Admin Console
- Check the application name matches the URL path
- Restart GlassFish: `asadmin restart-domain`

### Database Connection Errors

- Verify JDBC driver is on the classpath
- Check database credentials in `web.xml` or system properties
- Ensure the database table `TblComputer` exists
- Test connection: `asadmin ping-connection-pool`

### WSDL Generation Issues

- Ensure all model classes have proper JAXB annotations
- Check that the service implementation class has `@WebService` annotation
- Verify `sun-jaxws.xml` endpoint configuration

## Key Files Modified

- **pom.xml**: Added JAX-WS and JAXB dependencies
- **web.xml**: Configured JAX-WS listener and servlet
- **sun-jaxws.xml**: JAX-WS endpoint configuration (new)
- **ComputerService.java**: JAX-WS service interface (new)
- **ComputerServiceImpl.java**: JAX-WS implementation (replaces servlet)
- **Computer.java**: Added JAXB annotations
- **ComputerClient.java**: Converted to JAX-WS proxy client

## Notes

- The service is now SOA-compliant with standard WSDL/SOAP interface
- Database credentials are managed through system properties or `web.xml`
- All operations are properly documented with JavaDoc
- The service uses automatic proxy generation for client communication
- JAXB handles XML serialization/deserialization automatically

## Support

For GlassFish documentation, visit: https://glassfish.org/
For JAX-WS documentation, visit: https://javaee.github.io/jaxws-ri/
