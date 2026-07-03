#!/bin/bash

# Computer Service - JAX-WS GlassFish Deployment Script
# This script automates the build and deployment process

set -e  # Exit on error

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Configuration
PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
GLASSFISH_HOME="${GLASSFISH_HOME:?GLASSFISH_HOME environment variable not set}"
APP_NAME="project_3"
WAR_FILE="${PROJECT_DIR}/target/${APP_NAME}.war"
SERVICE_URL="http://localhost:8080/${APP_NAME}/ComputerService"

echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}Computer Service - Deployment Script${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""

# Check prerequisites
echo -e "${YELLOW}[1/7] Checking prerequisites...${NC}"
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}ERROR: Maven is not installed or not in PATH${NC}"
    exit 1
fi

if [ ! -d "$GLASSFISH_HOME" ]; then
    echo -e "${RED}ERROR: GlassFish directory not found: $GLASSFISH_HOME${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Prerequisites check passed${NC}"
echo ""

# Build project
echo -e "${YELLOW}[2/7] Building project...${NC}"
cd "$PROJECT_DIR"
mvn clean package -DskipTests

if [ ! -f "$WAR_FILE" ]; then
    echo -e "${RED}ERROR: WAR file not created at $WAR_FILE${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Build successful: $WAR_FILE${NC}"
echo ""

# Check if GlassFish is running
echo -e "${YELLOW}[3/7] Checking GlassFish status...${NC}"
if ! "$GLASSFISH_HOME/bin/asadmin" list-applications &> /dev/null; then
    echo -e "${RED}ERROR: GlassFish is not running or asadmin command failed${NC}"
    echo "Start GlassFish with: $GLASSFISH_HOME/bin/asadmin start-domain"
    exit 1
fi

echo -e "${GREEN}✓ GlassFish is running${NC}"
echo ""

# Undeploy existing application if it exists
echo -e "${YELLOW}[4/7] Checking for existing deployment...${NC}"
if "$GLASSFISH_HOME/bin/asadmin" list-applications 2>&1 | grep -q "^$APP_NAME "; then
    echo -e "${YELLOW}Undeploying existing $APP_NAME...${NC}"
    "$GLASSFISH_HOME/bin/asadmin" undeploy "$APP_NAME"
    sleep 2
    echo -e "${GREEN}✓ Previous deployment removed${NC}"
else
    echo -e "${GREEN}✓ No existing deployment found${NC}"
fi
echo ""

# Deploy new application
echo -e "${YELLOW}[5/7] Deploying application...${NC}"
"$GLASSFISH_HOME/bin/asadmin" deploy "$WAR_FILE"
sleep 3

echo -e "${GREEN}✓ Deployment completed${NC}"
echo ""

# Verify deployment
echo -e "${YELLOW}[6/7] Verifying deployment...${NC}"
if "$GLASSFISH_HOME/bin/asadmin" list-applications 2>&1 | grep -q "^$APP_NAME "; then
    echo -e "${GREEN}✓ Application deployed successfully${NC}"
else
    echo -e "${RED}ERROR: Application deployment verification failed${NC}"
    exit 1
fi
echo ""

# Test service accessibility
echo -e "${YELLOW}[7/7] Testing service accessibility...${NC}"
sleep 2

if curl -s "${SERVICE_URL}?wsdl" | grep -q "Envelope"; then
    echo -e "${GREEN}✓ Service is accessible and responding${NC}"
    echo ""
    echo -e "${GREEN}========================================${NC}"
    echo -e "${GREEN}DEPLOYMENT SUCCESSFUL!${NC}"
    echo -e "${GREEN}========================================${NC}"
    echo ""
    echo "Service Information:"
    echo "  Endpoint:  ${SERVICE_URL}"
    echo "  WSDL:      ${SERVICE_URL}?wsdl"
    echo "  Status:    http://localhost:4848 (Admin Console)"
    echo ""
    echo "Next steps:"
    echo "  1. Configure database in: src/main/webapp/WEB-INF/web.xml"
    echo "  2. Run client: mvn exec:java -Dexec.mainClass=\"com.example.client.ComputerClient\""
    echo "  3. Test with SoapUI: Import WSDL from ${SERVICE_URL}?wsdl"
    echo ""
else
    echo -e "${YELLOW}WARNING: Service is deployed but not responding yet${NC}"
    echo "This may be normal - give it a few seconds to initialize"
    echo "Try accessing: ${SERVICE_URL}?wsdl"
    echo ""
    echo "Check logs with: tail -f $GLASSFISH_HOME/domains/domain1/logs/server.log"
fi

echo -e "${GREEN}Deployment script completed${NC}"
exit 0
