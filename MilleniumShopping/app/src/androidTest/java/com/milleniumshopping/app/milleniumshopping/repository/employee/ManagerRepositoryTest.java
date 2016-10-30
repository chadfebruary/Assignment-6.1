package com.milleniumshopping.app.milleniumshopping.repository.employee;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Manager;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.ManagerRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class ManagerRepositoryTest extends AndroidTestCase{
    private static final String TAG = "MANAGER TEST";
    private String employeeID;

    public void createReadUpdateDeleteTest() throws Exception
    {
        ManagerRepository managerRepository = new ManagerRepositoryImpl(this.getContext());

        //Create employee
        Manager createEmployee = new Manager.Builder()
                .employeeID("1234")
                .name("Duck")
                .surname("Four")
                .dateOfBirth("29/12/1989")
                .role("Manager")
                .build();
        Manager insertedEntity = managerRepository.save(createEmployee);
        employeeID = insertedEntity.getEmployeeID();
        Assert.assertNotNull(insertedEntity);

        //Read all employees
        Set<Manager> employeeSet = managerRepository.findAll();
        Assert.assertTrue(employeeSet.size() > 0);

        //Read one employee
        Manager employee = managerRepository.findById(employeeID, createEmployee.getEmployeeRole());
        Assert.assertNotNull(employee);

        //Update employee
        Manager updateEmployee = new Manager.Builder()
                .copy(employee)
                .surname("March")
                .build();
        managerRepository.update(updateEmployee);
        Manager newEmployee = managerRepository.findById(employeeID, updateEmployee.getEmployeeRole());
        Assert.assertEquals("March", newEmployee.getSurname());

        //Delete employee
        managerRepository.delete(updateEmployee);
        Manager deletedEmployee = managerRepository.findById(employeeID, updateEmployee.getEmployeeRole());
        Assert.assertNull(deletedEmployee);
    }
}
