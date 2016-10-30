package com.milleniumshopping.app.milleniumshopping.repository.employee;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.EmployeeRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;



/**
 * Write a description of class TestInternet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */



public class EmployeeRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "EMPLOYEE TEST";
    private String employeeID;

    public void createReadUpdateDeleteTest() throws Exception
    {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(this.getContext());

        //Create employee
        Employee createEmployee = new Employee.Builder()
                .employeeID("1234")
                .name("Chad")
                .surname("February")
                .dateOfBirth("29/12/1989")
                .role("Manager")
                .build();
        Employee insertedEntity = employeeRepository.save(createEmployee);
        employeeID = insertedEntity.getEmployeeID();
        Assert.assertNotNull(insertedEntity);

        //Read all employees
        Set<Employee> employeeSet = employeeRepository.findAll();
        Assert.assertTrue(employeeSet.size() > 0);

        //Read one employee
        Employee employee = employeeRepository.findById(employeeID, createEmployee.getEmployeeRole());
        Assert.assertNotNull(employee);

        //Update employee
        Employee updateEmployee = new Employee.Builder()
                .copy(employee)
                .surname("March")
                .build();
        employeeRepository.update(updateEmployee);
        Employee newEmployee = employeeRepository.findById(employeeID, updateEmployee.getEmployeeRole());
        Assert.assertEquals("March", newEmployee.getSurname());
        
        //Delete employee
        employeeRepository.delete(updateEmployee);
        Employee deletedEmployee = employeeRepository.findById(employeeID, updateEmployee.getEmployeeRole());
        Assert.assertNull(deletedEmployee);
    }
}
