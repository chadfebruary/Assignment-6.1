package com.milleniumshopping.app.milleniumshopping.repository.employee;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.employee.SalesRepresentative;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.SalesRepresentativeRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class SalesRepresentativeRepositoryTest extends AndroidTestCase {
    private static final String TAG = "SALES REPRESENTATIVE TEST";
    private String employeeID;

    public void createReadUpdateDeleteTest() throws Exception
    {
        SalesRepresentativeRepository salesRepresentativeRepository = new SalesRepresentativeRepositoryImpl(this.getContext());

        //Create employee
        SalesRepresentative createEmployee = new SalesRepresentative.Builder()
                .employeeID("1234")
                .name("Videogame")
                .surname("Dunkey")
                .dateOfBirth("29/12/1989")
                .role("Manager")
                .build();
        SalesRepresentative insertedEntity = salesRepresentativeRepository.save(createEmployee);
        employeeID = insertedEntity.getEmployeeID();
        Assert.assertNotNull(insertedEntity);

        //Read all employees
        Set<SalesRepresentative> employeeSet = salesRepresentativeRepository.findAll();
        Assert.assertTrue(employeeSet.size() > 0);

        //Read one employee
        SalesRepresentative employee = salesRepresentativeRepository.findById(employeeID, createEmployee.getEmployeeRole());
        Assert.assertNotNull(employee);

        //Update employee
        SalesRepresentative updateEmployee = new SalesRepresentative.Builder()
                .copy(employee)
                .surname("March")
                .build();
        salesRepresentativeRepository.update(updateEmployee);
        SalesRepresentative newEmployee = salesRepresentativeRepository.findById(employeeID, updateEmployee.getEmployeeRole());
        Assert.assertEquals("March", newEmployee.getSurname());

        //Delete employee
        salesRepresentativeRepository.delete(updateEmployee);
        SalesRepresentative deletedEmployee = salesRepresentativeRepository.findById(employeeID, updateEmployee.getEmployeeRole());
        Assert.assertNull(deletedEmployee);
    }
}
