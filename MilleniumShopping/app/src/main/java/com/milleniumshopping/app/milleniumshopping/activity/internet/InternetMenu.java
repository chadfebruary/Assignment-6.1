package com.milleniumshopping.app.milleniumshopping.activity.internet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.milleniumshopping.app.milleniumshopping.R;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.InternetRepositoryImpl;

public class InternetMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_menu);
    }

    public void addInternet(View v)
    {
        Intent intent = new Intent(this, AddInternet.class);
        startActivity(intent);
    }

    public void viewInternet(View v)
    {
        Intent intent = new Intent(this, ViewInternet.class);
        startActivity(intent);
    }

    public void deleteAll(View v)
    {
        InternetRepositoryImpl internetRepository = new InternetRepositoryImpl(this.getApplicationContext());
        int deleted = internetRepository.deleteAll();

        if(deleted > 0)
        {
            Toast.makeText(InternetMenu.this, "Internet services deleted successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(InternetMenu.this, "No internet services to delete", Toast.LENGTH_SHORT).show();
        }
    }
}
