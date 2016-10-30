package com.milleniumshopping.app.milleniumshopping.activity.employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.milleniumshopping.app.milleniumshopping.R;

public class EmployeeMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_menu);
    }

    public void addEmployee(View v){
        Intent intent = new Intent(this, AddEmployee.class);
        startActivity(intent);
    }

    public void viewEmployees(View v){
        Intent intent = new Intent(this, ViewEmployee.class);
        startActivity(intent);
    }

    public void deleteEmployees(View v){

        Intent intent = new Intent(this, DeleteEmployee.class);
        startActivity(intent);
        //EmployeeServiceImpl employeeService = EmployeeServiceImpl.getInstance();
        //employeeService.deleteAll();


    }

    public void updateEmployee(View v)
    {
        Intent intent = new Intent(this, UpdateEmployee.class);
        startActivity(intent);
    }
}
