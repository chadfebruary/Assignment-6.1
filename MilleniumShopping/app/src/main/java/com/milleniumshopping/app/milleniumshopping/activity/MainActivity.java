package com.milleniumshopping.app.milleniumshopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.milleniumshopping.app.milleniumshopping.R;
import com.milleniumshopping.app.milleniumshopping.activity.employee.EmployeeMenu;
import com.milleniumshopping.app.milleniumshopping.activity.employee.ViewEmployee;
import com.milleniumshopping.app.milleniumshopping.activity.internet.InternetMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void employeeMenu(View v) throws Exception {
        Intent intent = new Intent(this, EmployeeMenu.class);
        startActivity(intent);
    }

    public void internetMenu(View v)
    {
        Intent intent = new Intent(this, InternetMenu.class);
        startActivity(intent);
    }

    public void shopMenu(View v)
    {
        Intent intent = new Intent(this, ViewEmployee.class);
        startActivity(intent);
    }
}
