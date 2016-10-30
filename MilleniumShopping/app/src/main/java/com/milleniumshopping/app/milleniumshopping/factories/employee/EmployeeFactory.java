package com.milleniumshopping.app.milleniumshopping.factories.employee;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;

/**
 * Created by Admin on 2016/04/15.
 */


public class EmployeeFactory {

    private static EmployeeFactory factory = null;

    public static Employee getInstance(String role, String name, String surname, String dateOfBirth, String employeeID)
    {
            Employee employee = new Employee.Builder()
                    .employeeID(employeeID)
                    .name(name)
                    .surname(surname)
                    .dateOfBirth(dateOfBirth)
                    .role(role)
                    .build();

        return employee;
    }
}
