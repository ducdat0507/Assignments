# Configuration Guide

## Overview

This guide helps you properly configure the Computer Service for your environment.

## Database Configuration

### 1. SQL Server Setup

#### Create Database

```sql
CREATE DATABASE ComputerDB;
GO
```

#### Create Table

```sql
USE ComputerDB;
GO

CREATE TABLE TblComputer (
    comID VARCHAR(50) PRIMARY KEY,
    comName VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    manufacturer VARCHAR(100) NOT NULL
);
GO
```

#### Insert Sample Data (Optional)

```sql
INSERT INTO TblComputer VALUES ('C001', 'Dell XPS 13', 1299.99, 'Dell');
INSERT INTO TblComputer VALUES ('C002', 'HP Pavilion', 799.99, 'HP');
INSERT INTO TblComputer VALUES ('C003', 'Lenovo ThinkPad', 999.99, 'Lenovo');
INSERT INTO TblComputer VALUES ('C004', 'ASUS VivoBook', 699.99, 'ASUS');
INSERT INTO TblComputer VALUES ('C005', 'MacBook Air', 1199.99, 'Apple');
GO
```

### 2. Connection String Format

For SQL Server 2019+:
```
jdbc:sqlserver://localhost:1433;databaseName=ComputerDB
```

For SQL Server 2012-2017:
```
jdbc:sqlserver://localhost:1433;databaseName=ComputerDB;authentication=SqlPassword
```

For SQL Server with instance name:
```
jdbc:sqlserver://server.domain.com:1433;instanceName=SQLEXPRESS;databaseName=ComputerDB
```

For SQL Server with integrated authentication:
```
jdbc:sqlserver://localhost:1433;databaseName=ComputerDB;integratedSecurity=true
```

### 3. JDBC Driver Configuration

The project uses `mssql-jdbc` version 6.2.2 (for Java 7/8).

If using a newer SQL Server version, update `pom.xml`:

```xml
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <version>10.2.0.jre11</version>  <!-- For Java 11+ -->
</dependency>
```

## Web Application Configuration

### Method 1: Update web.xml (Recommended for Development)

Edit `src/main/webapp/WEB-INF/web.xml`:

```xml
<context-param>
    <param-name>jdbc.url</param-name>
    <param-value>jdbc:sqlserver://localhost:1433;databaseName=ComputerDB</param-value>
</context-param>
<context-param>
    <param-name>jdbc.user</param-name>
    <param-value>sa</param-value>
</context-param>
<context-param>
    <param-name>jdbc.password</param-name>
    <param-value>your-password-here</param-value>
</context-param>
```

### Method 2: Java System Properties (Recommended for Production)

Pass system properties to JVM:

```bash
-Djdbc.url=jdbc:sqlserver://localhost:1433;databaseName=ComputerDB
-Djdbc.user=sa
-Djdbc.password=your-password-here
```

#### Configure GlassFish Domain

1. Edit `$GLASSFISH_HOME/domains/domain1/config/domain.xml`

2. Find the `<java-config>` element and add:

```xml
<jvm-options>-Djdbc.url=jdbc:sqlserver://localhost:1433;databaseName=ComputerDB</jvm-options>
<jvm-options>-Djdbc.user=sa</jvm-options>
<jvm-options>-Djdbc.password=your-password</jvm-options>
```

3. Restart GlassFish:

```bash
asadmin restart-domain
```

#### Or Set via Command Line

```bash
asadmin create-jvm-options "-Djdbc.url=jdbc:sqlserver://localhost:1433;databaseName=ComputerDB"
asadmin create-jvm-options "-Djdbc.user=sa"
asadmin create-jvm-options "-Djdbc.password=your-password"
```

### Method 3: Environment Variables

Create a script to set environment variables before starting GlassFish:

```bash
#!/bin/bash

export JDBC_URL="jdbc:sqlserver://localhost:1433;databaseName=ComputerDB"
export JDBC_USER="sa"
export JDBC_PASSWORD="your-password"

export AS_JAVA_OPTS="
  -Djdbc.url=${JDBC_URL}
  -Djdbc.user=${JDBC_USER}
  -Djdbc.password=${JDBC_PASSWORD}
"

$GLASSFISH_HOME/bin/asadmin start-domain
```

## Web Service Configuration

### WSDL Namespace

The web service uses the following namespace:

```
http://com.example.service/
```

To change it, edit `ComputerService.java`:

```java
@WebService(
    serviceName = "ComputerService",
    targetNamespace = "http://your.company.domain/"  // Change here
)
```

Also update `ComputerServiceImpl.java`:

```java
@WebService(
    serviceName = "ComputerService",
    targetNamespace = "http://your.company.domain/",  // Match above
    endpointInterface = "com.example.service.ComputerService"
)
```

### Service Endpoint Path

The service endpoint is mapped to `/ComputerService` in `web.xml`:

```xml
<servlet-mapping>
    <servlet-name>ComputerServiceServlet</servlet-name>
    <url-pattern>/ComputerService</url-pattern>  <!-- Change here if needed -->
</servlet-mapping>
```

### SOAP Binding

Current binding: `DOCUMENT/LITERAL` (default for JAX-WS)

This is the standard and recommended SOAP binding style.

## GlassFish Configuration

### Server Port Configuration

Default ports:
- HTTP: 8080
- HTTPS: 8181
- Admin: 4848
- Derby: 1527

To change HTTP port to 9090:

```bash
asadmin set server.network-listeners.http-listener-1.port=9090
asadmin restart-domain
```

### Memory Configuration

Default: 512 MB heap

For production with large datasets:

```bash
asadmin create-jvm-options "-Xmx2g"  # 2 GB heap
```

### Logging Configuration

View detailed logs:

```bash
tail -f $GLASSFISH_HOME/domains/domain1/logs/server.log
```

Configure log level in Admin Console:
1. Navigate to **Configurations** → **server-config** → **Logger Settings**
2. Set appropriate log levels

## Security Configuration

### HTTPS Configuration

Generate self-signed certificate:

```bash
keytool -genkey -alias s1as \
  -keyalg RSA \
  -keystore $GLASSFISH_HOME/domains/domain1/config/keystore.jks \
  -validity 365
```

### Authentication Configuration

To add basic authentication, edit `web.xml`:

```xml
<security-constraint>
    <web-resource-collection>
        <web-resource-name>ComputerService</web-resource-name>
        <url-pattern>/ComputerService</url-pattern>
        <http-method>POST</http-method>
        <http-method>GET</http-method>
    </web-resource-collection>
    <auth-constraint>
        <role-name>admin</role-name>
    </auth-constraint>
    <user-data-constraint>
        <transport-guarantee>CONFIDENTIAL</transport-guarantee>
    </user-data-constraint>
</security-constraint>

<login-config>
    <auth-method>BASIC</auth-method>
    <realm-name>file</realm-name>
</login-config>

<security-role>
    <role-name>admin</role-name>
</security-role>
```

## Client Configuration

### Proxy Configuration

If behind a corporate proxy, add to client code:

```java
// Set proxy for WSDL download
System.setProperty("http.proxyHost", "proxy.company.com");
System.setProperty("http.proxyPort", "8080");

// Create service with proxy
URL wsdlLocation = new URL("http://localhost:8080/project_3/ComputerService?wsdl");
QName serviceName = new QName("http://com.example.service/", "ComputerService");
Service service = Service.create(wsdlLocation, serviceName);
ComputerService proxy = service.getPort(ComputerService.class);
```

### Timeout Configuration

Set connection timeouts:

```java
BindingProvider bindingProvider = (BindingProvider) proxy;
Map<String, Object> requestContext = bindingProvider.getRequestContext();

// Connection timeout: 30 seconds
requestContext.put("com.sun.xml.ws.connect.timeout", 30000);

// Request timeout: 60 seconds
requestContext.put("com.sun.xml.ws.request.timeout", 60000);
```

## Testing Configuration

### SoapUI Configuration

1. Create SOAP Project with WSDL: `http://localhost:8080/project_3/ComputerService?wsdl`

2. Configure timeout in SoapUI:
   - **File** → **Preferences** → **HTTP Settings**
   - **Socket Timeout**: 60000 ms
   - **Connection Timeout**: 30000 ms

### Load Testing

For load testing with multiple concurrent requests:

```bash
# Using Apache JMeter
jmeter -n -t load_test.jmx -l results.jtl

# Or using SoapUI
soapui -c 10 -r -R Project.xml
```

## Troubleshooting Configuration

### Enable Detailed Logging

Set Java system property:

```bash
-Dcom.sun.xml.ws.transport.http.HttpAdapter.dump=true
```

### Check WSDL Generation

```bash
curl -v http://localhost:8080/project_3/ComputerService?wsdl
```

### Validate Configuration

```bash
# Test database connection
sqlcmd -S localhost -U sa -P password -Q "SELECT @@VERSION"

# Test web service endpoint
curl -I http://localhost:8080/project_3/ComputerService

# Check GlassFish deployment
asadmin list-applications
```

## Configuration Checklist

- [ ] SQL Server is running
- [ ] ComputerDB database created
- [ ] TblComputer table exists
- [ ] JDBC credentials verified
- [ ] GlassFish is running
- [ ] Project builds successfully (`mvn clean package`)
- [ ] WAR file deployed (`asadmin deploy`)
- [ ] WSDL is accessible (`curl ...?wsdl`)
- [ ] Service responds to requests
- [ ] Client can connect to service
- [ ] Sample data in database

## Common Configuration Issues

### "Database Connection URL not configured"

**Solution**: 
- Ensure `web.xml` contains all three `<context-param>` entries
- Or set Java system properties before starting GlassFish

### "com.sun.xml.ws.client.ClientTransportException"

**Solution**:
- Verify service endpoint URL is correct
- Check firewall allows port 8080
- Ensure GlassFish is running

### "java.sql.DriverNotFoundException: com.microsoft.sqlserver.jdbc.SQLServerDriver"

**Solution**:
- Verify `mssql-jdbc` dependency in `pom.xml`
- Rebuild WAR: `mvn clean package`
- Redeploy to GlassFish

### WSDL not generated

**Solution**:
- Verify `@WebService` annotation present
- Check `sun-jaxws.xml` endpoint configuration
- Restart GlassFish domain

## Further Reading

- [GlassFish Configuration](https://glassfish.org/docs)
- [JAX-WS Configuration](https://docs.oracle.com/cd/E24938_01/doc.1096/e24996/)
- [JDBC Driver Documentation](https://docs.microsoft.com/en-us/sql/connect/jdbc/overview-of-the-jdbc-driver)
- [Maven Configuration](https://maven.apache.org/guides/)

---

For questions or issues, refer to `README_JAXWS.md` or `QUICKSTART.md`
