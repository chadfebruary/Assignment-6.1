package com.milleniumshopping.app.milleniumshopping.services.employee;

import android.content.Context;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;


/**
 * Created by Chad on 5/8/2016.
 */
public interface EmployeeService {

    void addEmployee(Context context, Employee employee);
    void updateEmployee(Context context, Employee employee);
    void deleteEmployee(Context context, Employee employee);
}
