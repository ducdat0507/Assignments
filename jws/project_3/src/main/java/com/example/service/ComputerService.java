package com.example.service;

import com.example.model.Computer;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "ComputerService", targetNamespace = "http://com.example.service/")
public interface ComputerService {

    @WebMethod(operationName = "getAllComputers")
    List<Computer> getAllComputers();

    @WebMethod(operationName = "findComputerById")
    Computer findComputerById(String comID);

    @WebMethod(operationName = "addComputer")
    boolean addComputer(Computer computer);
}
