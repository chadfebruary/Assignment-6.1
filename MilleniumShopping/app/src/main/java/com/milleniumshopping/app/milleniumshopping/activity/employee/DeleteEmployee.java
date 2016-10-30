package com.milleniumshopping.app.milleniumshopping.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.milleniumshopping.app.milleniumshopping.R;
import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.EmployeeRepositoryImpl;

public class DeleteEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);
    }

    public void deleteByID(View v)
    {
        String employeeID = ((EditText)findViewById(R.id.editText84)).getText().toString();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(this.getApplicationContext());
        Employee employee = new Employee.Builder()
                .employeeID(employeeID)
                .build();
        employeeRepository.delete(employee);
    }

    public void deleteAll(View v)
    {
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(this.getApplicationContext());
        int deleted = employeeRepository.deleteAll();

        if(deleted > 0)
        {
            Toast.makeText(DeleteEmployee.this, "Employees deleted successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(DeleteEmployee.this, "No employees to delete", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, EmployeeMenu.class);
        startActivity(intent);
    }

    /*public class AccountRegisterTask extends AsyncTask<Void, Void, Employee> {

        private final Account account;

        AccountRegisterTask(Account account) {
            this.account = account;
        }

        @Override
        protected Employee doInBackground(Void... params) {

            final String uri = "http://148.100.5.84:8080/employee/";

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
            HttpEntity<Account> requestEntity = new HttpEntity<>(account, requestHeaders);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            ResponseEntity<Account> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Account.class);
            return result.getBody();
        }

        @Override
        protected void onPostExecute(final Account success) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getBaseContext(), "Account successfully added!", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }*/
}
