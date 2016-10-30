package com.milleniumshopping.app.milleniumshopping.repository.employee;

import android.test.AndroidTestCase;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Cleaner;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.CleanerRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/10/31.
 */
public class CleanerRepositoryTest extends AndroidTestCase {
    private static final String TAG = "CLEANER TEST";
    private String employeeID;

    public void createReadUpdateDeleteTest() throws Exception
    {
        CleanerRepository cleanerRepository = new CleanerRepositoryImpl(this.getContext());

        //Create employee
        Cleaner createEmployee = new Cleaner.Builder()
                .employeeID("21351")
                .name("Abe")
                .surname("Lincoln")
                .dateOfBirth("29/12/1989")
                .role("Cleaner")
                .build();
        Cleaner insertedEntity = cleanerRepository.save(createEmployee);
        employeeID = insertedEntity.getEmployeeID();
        Assert.assertNotNull(insertedEntity);

        //Read all employees
        Set<Cleaner> employeeSet = cleanerRepository .findAll();
        Assert.assertTrue(employeeSet.size() > 0);

        //Read one employee
        Cleaner cleaner = cleanerRepository .findById(employeeID, createEmployee.getEmployeeRole());
        Assert.assertNotNull(cleaner);

        //Update employee
        Cleaner updateEmployee = new Cleaner.Builder()
                .copy(cleaner)
                .surname("March")
                .build();
        cleanerRepository .update(updateEmployee);
        Cleaner newEmployee = cleanerRepository .findById(employeeID, updateEmployee.getEmployeeRole());
        Assert.assertEquals("March", newEmployee.getSurname());

        //Delete employee
        cleanerRepository .delete(updateEmployee);
        Cleaner deletedEmployee = cleanerRepository .findById(employeeID, updateEmployee.getEmployeeRole());
        Assert.assertNull(deletedEmployee);
    }
}
