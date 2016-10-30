package com.milleniumshopping.app.milleniumshopping.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.milleniumshopping.app.milleniumshopping.R;
import com.milleniumshopping.app.milleniumshopping.activity.MainActivity;
import com.milleniumshopping.app.milleniumshopping.domain.employee.Employee;
import com.milleniumshopping.app.milleniumshopping.repository.employee.EmployeeRepository;
import com.milleniumshopping.app.milleniumshopping.repository.employee.Impl.EmployeeRepositoryImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ViewEmployee extends AppCompatActivity {

    ArrayAdapter adapter;
    ListView listView;
    String[] names;
    Set<Employee> employeeSet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(this.getApplicationContext());

        employeeSet = new HashSet<>();
        employeeSet = employeeRepository.findAll();

        Iterator<Employee> employeeIterator = employeeSet.iterator();

        if(employeeSet.size()>0)
        {
            names = new String[employeeSet.size()];
            int i = 0;

            while(employeeIterator.hasNext())
            {
                names[i] = employeeIterator.next().toString();
                i++;
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
            listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(ViewEmployee.this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    public void returnHome(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
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
