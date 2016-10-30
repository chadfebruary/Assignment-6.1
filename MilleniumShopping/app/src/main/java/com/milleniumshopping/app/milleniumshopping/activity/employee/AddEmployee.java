package com.milleniumshopping.app.milleniumshopping.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.milleniumshopping.app.milleniumshopping.R;
import com.milleniumshopping.app.milleniumshopping.activity.MainActivity;
import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;

public class AddEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
    }

    public void returnHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void previewEmployee(View v) {
        Intent intent = new Intent(this, PreviewEmployee.class);

        String name = ((EditText) findViewById(R.id.editText90)).getText().toString();
        String surname = ((EditText) findViewById(R.id.editText91)).getText().toString();
        String dateOfBirth = ((EditText) findViewById(R.id.editText92)).getText().toString();
        String role = ((EditText) findViewById(R.id.editText93)).getText().toString();

        Employee employee = new Employee.Builder()
                .name(name)
                .surname(surname)
                .dateOfBirth(dateOfBirth)
                .role(role)
                .build();

        intent.putExtra("EMPLOYEE", employee);

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
