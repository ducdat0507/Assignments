package com.example.employee.dao;

import com.example.employee.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class EmployeeDAO {

    @PersistenceContext(unitName = "EmployeePU")
    private EntityManager em;

    public void add(Employee employee) {
        em.persist(employee);
    }

    public void delete(String employeeNo) {
        Employee employee = em.find(Employee.class, employeeNo);
        if (employee != null) {
            em.remove(employee);
        }
    }

    public List<Employee> findAll() {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }
}
