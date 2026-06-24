package com.example.math;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class Calculator {

    @WebMethod
    public int add(int a, int b) {
        return a + b;
    }

    @WebMethod
    public int multiply(int a, int b) {
        return a * b;
    }
}
