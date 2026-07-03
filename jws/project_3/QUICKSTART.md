# Quick Start Guide - JAX-WS Computer Service on GlassFish

## 1. Prerequisites

Ensure you have:
- JDK 1.8+ installed
- GlassFish Server 4.x+ installed
- Maven 3.x installed
- SQL Server running with a `ComputerDB` database

## 2. Setup Database

```sql
-- Create database (if not exists)
CREATE DATABASE ComputerDB;

-- Create table
CREATE TABLE TblComputer (
    comID VARCHAR(50) PRIMARY KEY,
    comName VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    manufacturer VARCHAR(100) NOT NULL
);

-- Insert sample data
INSERT INTO TblComputer VALUES ('C001', 'Dell XPS 13', 1299.99, 'Dell');
INSERT INTO TblComputer VALUES ('C002', 'HP Pavilion', 799.99, 'HP');
INSERT INTO TblComputer VALUES ('C003', 'Lenovo ThinkPad', 999.99, 'Lenovo');
```

## 3. Update Configuration

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

## 4. Build the Project

```bash
cd /path/to/project_3
mvn clean package
```

Output: `target/project_3.war`

## 5. Start GlassFish

```bash
# If GlassFish is not running
$GLASSFISH_HOME/bin/asadmin start-domain

# Default: http://localhost:4848 (Admin Console)
# Default: http://localhost:8080 (Applications)
```

## 6. Deploy the Application

### Option A: Command Line (Recommended)

```bash
$GLASSFISH_HOME/bin/asadmin deploy target/project_3.war
```

### Option B: Admin Console

1. Go to http://localhost:4848
2. Login (default: admin / admin)
3. Click **Applications** → **Deploy**
4. Upload `target/project_3.war`
5. Click **Deploy**

## 7. Verify Deployment

Check if the service is running:

```bash
curl http://localhost:8080/project_3/ComputerService?wsdl
```

Or visit in browser: http://localhost:8080/project_3/ComputerService?wsdl

You should see the WSDL (XML Web Service Description).

## 8. Test with Client

Run the Swing GUI client:

```bash
# Terminal 1: Client runs on localhost:8080
mvn exec:java -Dexec.mainClass="com.example.client.ComputerClient"
```

The client window should open and display all computers from the database.

## 9. Test Web Service Operations

### Option A: Using the GUI Client

1. **Show All**: Click "Show All" button to load all computers
2. **Find**: Click "Find" button and enter a computer ID (e.g., C001)
3. **Add**: Fill in form and click "Add" to insert a new computer

### Option B: Using SoapUI (Recommended for testing)

1. Download SoapUI from https://www.soapui.org/
2. Create new SOAP Project
3. Set WSDL URL: `http://localhost:8080/project_3/ComputerService?wsdl`
4. Create requests and test operations

### Option C: Using curl

```bash
# Get WSDL
curl -s http://localhost:8080/project_3/ComputerService?wsdl | head -20

# Test via SOAP (XML body)
curl -X POST \
  -H "Content-Type: text/xml" \
  -d @soap-request.xml \
  http://localhost:8080/project_3/ComputerService
```

## 10. Troubleshooting

### Service not accessible

```bash
# Check if application is deployed
$GLASSFISH_HOME/bin/asadmin list-applications

# Redeploy if needed
$GLASSFISH_HOME/bin/asadmin undeploy project_3
$GLASSFISH_HOME/bin/asadmin deploy target/project_3.war
```

### Database connection errors

```bash
# Verify SQL Server is running
# Check web.xml credentials
# Test with query tool
sqlcmd -S localhost -U sa -P password -Q "SELECT * FROM ComputerDB.dbo.TblComputer"
```

### Check GlassFish logs

```bash
tail -f $GLASSFISH_HOME/domains/domain1/logs/server.log
```

## 11. Web Service Details

| Item | Value |
|------|-------|
| **Service Name** | ComputerService |
| **Endpoint** | http://localhost:8080/project_3/ComputerService |
| **WSDL** | http://localhost:8080/project_3/ComputerService?wsdl |
| **Namespace** | http://com.example.service/ |
| **Operations** | getAllComputers, findComputerById, addComputer |

## 12. Undeploy (if needed)

```bash
$GLASSFISH_HOME/bin/asadmin undeploy project_3
```

## Next Steps

- Implement additional operations (update, delete)
- Add authentication/authorization
- Implement error handling with SOAP faults
- Add logging and monitoring
- Deploy to production GlassFish cluster

## Common Commands

```bash
# Start GlassFish
asadmin start-domain

# Stop GlassFish
asadmin stop-domain

# Deploy
asadmin deploy target/project_3.war

# Undeploy
asadmin undeploy project_3

# Restart domain
asadmin restart-domain

# View logs
tail -f $GLASSFISH_HOME/domains/domain1/logs/server.log

# Check deployed apps
asadmin list-applications
```

Enjoy your JAX-WS service on GlassFish!
