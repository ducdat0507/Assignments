package com.example.service;

import com.example.dao.ComputerDAO;
import com.example.model.Computer;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletConfig;
import javax.xml.ws.WebServiceException;

@WebService(
    serviceName = "ComputerService",
    name = "ComputerService",
    targetNamespace = "http://com.example.service/",
    endpointInterface = "com.example.service.ComputerService"
)
public class ComputerServiceImpl implements ComputerService {

    private ComputerDAO dao;
    private ServletConfig config;

    /**
     * Initialize the service with database connection parameters.
     * Called automatically by the container after instantiation.
     */
    @PostConstruct
    public void init() {
        try {
            // Initialize DAO with database configuration
            // For GlassFish, you can configure these via environment variables or system properties
            String url = System.getProperty("jdbc.url", "jdbc:sqlserver://localhost:1433;databaseName=ComputerDB");
            String user = System.getProperty("jdbc.user", "sa");
            String password = System.getProperty("jdbc.password", "");

            if (url == null || url.isEmpty()) {
                throw new WebServiceException(
                    "Database connection URL not configured. Set jdbc.url system property."
                );
            }

            dao = new ComputerDAO(url, user, password);
        } catch (Exception e) {
            throw new WebServiceException("Failed to initialize ComputerService: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves all computers from the database.
     *
     * @return a list of all computers
     * @throws WebServiceException if a database error occurs
     */
    @Override
    @WebMethod(operationName = "getAllComputers")
    public List<Computer> getAllComputers() {
        try {
            if (dao == null) {
                throw new WebServiceException("Service not initialized");
            }
            return dao.getAllComputers();
        } catch (SQLException | ClassNotFoundException e) {
            throw new WebServiceException("Database error: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a computer by its ID.
     *
     * @param comID the computer ID to search for
     * @return the Computer object if found, null otherwise
     * @throws WebServiceException if a database error occurs
     */
    @Override
    @WebMethod(operationName = "findComputerById")
    public Computer findComputerById(String comID) {
        try {
            if (dao == null) {
                throw new WebServiceException("Service not initialized");
            }
            if (comID == null || comID.trim().isEmpty()) {
                throw new WebServiceException("comID parameter is required");
            }
            return dao.findComputerById(comID.trim());
        } catch (SQLException | ClassNotFoundException e) {
            throw new WebServiceException("Database error: " + e.getMessage(), e);
        }
    }

    /**
     * Adds a new computer to the database.
     *
     * @param computer the Computer object to add
     * @return true if the computer was added successfully, false otherwise
     * @throws WebServiceException if a database error occurs
     */
    @Override
    @WebMethod(operationName = "addComputer")
    public boolean addComputer(Computer computer) {
        try {
            if (dao == null) {
                throw new WebServiceException("Service not initialized");
            }
            if (computer == null) {
                throw new WebServiceException("Computer object cannot be null");
            }
            if (computer.getComID() == null || computer.getComID().trim().isEmpty()) {
                throw new WebServiceException("Computer ID is required");
            }
            if (computer.getComName() == null || computer.getComName().trim().isEmpty()) {
                throw new WebServiceException("Computer name is required");
            }
            if (computer.getManufacturer() == null || computer.getManufacturer().trim().isEmpty()) {
                throw new WebServiceException("Manufacturer is required");
            }
            if (computer.getPrice() < 0) {
                throw new WebServiceException("Price must be a positive number");
            }
            return dao.addComputer(computer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new WebServiceException("Database error: " + e.getMessage(), e);
        }
    }
}
