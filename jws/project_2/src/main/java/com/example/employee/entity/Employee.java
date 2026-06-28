package com.example.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "EmployeeNo", length = 20)
    private String employeeNo;

    @Column(name = "FullName", length = 30, nullable = false)
    private String fullName;

    @Column(name = "PlaceOfWork", length = 30, nullable = false)
    private String placeOfWork;

    @Column(name = "PhoneNo", length = 10, nullable = false)
    private String phoneNo;

    public Employee() {
    }

    public Employee(String employeeNo, String fullName, String placeOfWork, String phoneNo) {
        this.employeeNo = employeeNo;
        this.fullName = fullName;
        this.placeOfWork = placeOfWork;
        this.phoneNo = phoneNo;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
