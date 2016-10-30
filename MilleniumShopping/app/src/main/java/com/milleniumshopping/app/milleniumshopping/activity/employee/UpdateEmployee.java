package com.milleniumshopping.app.milleniumshopping.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.milleniumshopping.app.milleniumshopping.R;
import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.EmployeeRepositoryImpl;

public class UpdateEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);
    }

    public void updateEmployee(View v)
    {
        String employeeID = ((EditText)findViewById(R.id.editText7)).getText().toString();
        String name = ((EditText)findViewById(R.id.editText8)).getText().toString();
        String surname = ((EditText)findViewById(R.id.editText9)).getText().toString();
        String dateOfBirth = ((EditText)findViewById(R.id.editText10)).getText().toString();
        String role = ((EditText)findViewById(R.id.editText11)).getText().toString();

        Employee employee = new Employee.Builder()
                .employeeID(employeeID)
                .name(name)
                .surname(surname)
                .dateOfBirth(dateOfBirth)
                .role(role)
                .build();

        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(this.getApplicationContext());
        employeeRepository.update(employee);

        Intent intent = new Intent(this, EmployeeMenu.class);
        startActivity(intent);
    }
}
