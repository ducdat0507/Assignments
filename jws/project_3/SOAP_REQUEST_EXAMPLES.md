# SOAP Request Examples

These are example SOAP requests you can use to test the Computer Service web service.

## Service Information

- **Endpoint**: http://localhost:8080/project_3/ComputerService
- **WSDL**: http://localhost:8080/project_3/ComputerService?wsdl
- **Namespace**: http://com.example.service/

## 1. Get All Computers

### SOAP Request

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:web="http://com.example.service/">
    <soap:Body>
        <web:getAllComputers/>
    </soap:Body>
</soap:Envelope>
```

### cURL Command

```bash
curl -X POST \
  -H "Content-Type: text/xml; charset=UTF-8" \
  -H "SOAPAction: " \
  -d '<?xml version="1.0" encoding="UTF-8"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://com.example.service/"><soap:Body><web:getAllComputers/></soap:Body></soap:Envelope>' \
  http://localhost:8080/project_3/ComputerService
```

### Expected Response

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:getAllComputersResponse xmlns:ns2="http://com.example.service/">
            <return>
                <comID>C001</comID>
                <comName>Dell XPS 13</comName>
                <manufacturer>Dell</manufacturer>
                <price>1299.99</price>
            </return>
            <return>
                <comID>C002</comID>
                <comName>HP Pavilion</comName>
                <manufacturer>HP</manufacturer>
                <price>799.99</price>
            </return>
        </ns2:getAllComputersResponse>
    </soap:Body>
</soap:Envelope>
```

## 2. Find Computer by ID

### SOAP Request

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:web="http://com.example.service/">
    <soap:Body>
        <web:findComputerById>
            <comID>C001</comID>
        </web:findComputerById>
    </soap:Body>
</soap:Envelope>
```

### cURL Command

```bash
curl -X POST \
  -H "Content-Type: text/xml; charset=UTF-8" \
  -H "SOAPAction: " \
  -d '<?xml version="1.0" encoding="UTF-8"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://com.example.service/"><soap:Body><web:findComputerById><comID>C001</comID></web:findComputerById></soap:Body></soap:Envelope>' \
  http://localhost:8080/project_3/ComputerService
```

### Expected Response (Found)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:findComputerByIdResponse xmlns:ns2="http://com.example.service/">
            <return>
                <comID>C001</comID>
                <comName>Dell XPS 13</comName>
                <manufacturer>Dell</manufacturer>
                <price>1299.99</price>
            </return>
        </ns2:findComputerByIdResponse>
    </soap:Body>
</soap:Envelope>
```

### Expected Response (Not Found)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:findComputerByIdResponse xmlns:ns2="http://com.example.service/">
            <return/>
        </ns2:findComputerByIdResponse>
    </soap:Body>
</soap:Envelope>
```

## 3. Add Computer

### SOAP Request

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:web="http://com.example.service/">
    <soap:Body>
        <web:addComputer>
            <computer>
                <comID>C004</comID>
                <comName>MacBook Pro</comName>
                <manufacturer>Apple</manufacturer>
                <price>2499.99</price>
            </computer>
        </web:addComputer>
    </soap:Body>
</soap:Envelope>
```

### cURL Command

```bash
curl -X POST \
  -H "Content-Type: text/xml; charset=UTF-8" \
  -H "SOAPAction: " \
  -d '<?xml version="1.0" encoding="UTF-8"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://com.example.service/"><soap:Body><web:addComputer><computer><comID>C004</comID><comName>MacBook Pro</comName><manufacturer>Apple</manufacturer><price>2499.99</price></computer></web:addComputer></soap:Body></soap:Envelope>' \
  http://localhost:8080/project_3/ComputerService
```

### Expected Response (Success)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:addComputerResponse xmlns:ns2="http://com.example.service/">
            <return>true</return>
        </ns2:addComputerResponse>
    </soap:Body>
</soap:Envelope>
```

### Expected Response (Failure)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <soap:Fault>
            <faultcode>Server</faultcode>
            <faultstring>Database error: Violation of PRIMARY KEY constraint...</faultstring>
        </soap:Fault>
    </soap:Body>
</soap:Envelope>
```

## Testing with SoapUI

### Steps to Test

1. Download and install SoapUI from https://www.soapui.org/

2. Create new SOAP Project:
   - **Project Name**: Computer Service
   - **WSDL URL**: http://localhost:8080/project_3/ComputerService?wsdl
   - Click **OK**

3. SoapUI automatically generates test requests for each operation

4. In the request editor:
   - Modify the request parameters
   - Click the **Play** button to execute
   - View the response in the response pane

5. You can save requests and create test suites

## Testing with Postman

### Steps to Test

1. Download and install Postman from https://www.postman.com/

2. Create new request:
   - **Method**: POST
   - **URL**: http://localhost:8080/project_3/ComputerService

3. Set Headers:
   - **Content-Type**: text/xml; charset=UTF-8
   - **SOAPAction**: (empty)

4. In Body (raw):
   - Select XML format
   - Paste one of the SOAP request examples above

5. Click **Send** to execute

## Testing with Command Line (bash)

### Save SOAP Request to File

Create `get-all.xml`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
               xmlns:web="http://com.example.service/">
    <soap:Body>
        <web:getAllComputers/>
    </soap:Body>
</soap:Envelope>
```

### Execute Request

```bash
curl -X POST \
  -H "Content-Type: text/xml; charset=UTF-8" \
  -H "SOAPAction: " \
  -d @get-all.xml \
  http://localhost:8080/project_3/ComputerService | xmllint --format -
```

The `xmllint --format -` command nicely formats the XML response.

## Error Responses

### Missing Required Parameter

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <soap:Fault>
            <faultcode>Server</faultcode>
            <faultstring>Computer object cannot be null</faultstring>
        </soap:Fault>
    </soap:Body>
</soap:Envelope>
```

### Database Connection Error

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <soap:Fault>
            <faultcode>Server</faultcode>
            <faultstring>Database error: Connection refused</faultstring>
        </soap:Fault>
    </soap:Body>
</soap:Envelope>
```

### Service Not Initialized

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <soap:Fault>
            <faultcode>Server</faultcode>
            <faultstring>Service not initialized</faultstring>
        </soap:Fault>
    </soap:Body>
</soap:Envelope>
```

## Response Codes

| HTTP Code | Meaning |
|-----------|---------|
| 200 OK | Successful SOAP response |
| 400 Bad Request | Malformed SOAP request |
| 404 Not Found | Service endpoint not found |
| 500 Internal Server Error | Server-side error (check fault string) |
| 503 Service Unavailable | Service temporarily unavailable |

## Debugging Tips

### Enable SOAP Logging

In Java system properties:
```bash
-Dcom.sun.xml.ws.transport.http.HttpAdapter.dump=true
```

### View Server Logs

```bash
tail -f $GLASSFISH_HOME/domains/domain1/logs/server.log
```

### Validate WSDL

```bash
curl -s http://localhost:8080/project_3/ComputerService?wsdl | xmllint --format - | head -50
```

### Test Endpoint Accessibility

```bash
curl -v http://localhost:8080/project_3/ComputerService?wsdl
```

## Sample Data for Testing

```sql
-- Insert sample computers
INSERT INTO TblComputer VALUES ('C001', 'Dell XPS 13', 1299.99, 'Dell');
INSERT INTO TblComputer VALUES ('C002', 'HP Pavilion', 799.99, 'HP');
INSERT INTO TblComputer VALUES ('C003', 'Lenovo ThinkPad', 999.99, 'Lenovo');
INSERT INTO TblComputer VALUES ('C004', 'ASUS VivoBook', 699.99, 'ASUS');
INSERT INTO TblComputer VALUES ('C005', 'MacBook Air', 1199.99, 'Apple');

-- Verify data
SELECT * FROM TblComputer;
```

## More Information

- **SOAP Specification**: https://www.w3.org/TR/soap/
- **WSDL Specification**: https://www.w3.org/TR/wsdl/
- **JAX-WS Tutorial**: https://docs.oracle.com/cd/E24938_01/doc.1096/e24996/
- **GlassFish Web Services**: https://glassfish.org/

---

Happy testing! 🚀
