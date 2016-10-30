package com.milleniumshopping.app.milleniumshopping.services.employee.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;
import com.milleniumshopping.app.milleniumshopping.repository.employee.EmployeeRepository;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.EmployeeRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.services.employee.EmployeeService;

import java.io.Serializable;

/**
 * Created by cfebruary on 2016/08/22.
 */
public class EmployeeServiceImpl extends IntentService implements EmployeeService {

    private static final String ACTION_ADD = "com.milleniuminfinity.app.milleniuminfintity.services.employee.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniuminfinity.app.milleniuminfintity.services.employee.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniuminfinity.app.milleniuminfintity.services.employee.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniuminfinity.app.milleniuminfintity.services.employee.Impl.action.UPDATE";

    private static EmployeeServiceImpl service = null;

    private EmployeeServiceImpl()
    {
        super("EmployeeServiceImpl");
    }

    public static EmployeeServiceImpl getInstance()
    {
        if(service == null)
            service = new EmployeeServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final Employee employee = (Employee) intent.getSerializableExtra(EXTRA_ADD);
                    saveEmployee(employee);
            } else if (ACTION_UPDATE.equals(action)) {
                final Employee employee = (Employee) intent.getSerializableExtra(EXTRA_UPDATE);
                updateEmployee(employee);
            }
        }
    }

    private void updateEmployee(Employee employee)
    {
        //Post and Save local
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(getBaseContext());
        employeeRepository.update(employee);
    }

    private void saveEmployee(Employee employee)
    {
        //Post and Save local
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(getBaseContext());
        employeeRepository.save(employee);
    }

    public void deleteAll()
    {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(getBaseContext());

        try
        {
            employeeRepository.deleteAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addEmployee(Context context, Employee employee)
    {
        Intent intent = new Intent(context, EmployeeServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, (Serializable) employee);
        context.startService(intent);
    }

    @Override
    public void updateEmployee(Context context, Employee employee)
    {
        Intent intent = new Intent(context, EmployeeServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, (Serializable) employee);
        context.startService(intent);
    }

    @Override
    public void deleteEmployee(Context context, Employee employee)
    {

    }
}
