package com.milleniumshopping.app.milleniumshopping.activity.internet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.milleniumshopping.app.milleniumshopping.R;
import com.milleniumshopping.app.milleniumshopping.domain.internet.Internet;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.InternetRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.internet.InternetRepository;

public class DeleteInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_internet);
    }

    public void deleteByID(View v)
    {
        String ipAddress = ((EditText)findViewById(R.id.editText84)).getText().toString();
        InternetRepository internetRepository = new InternetRepositoryImpl(this.getApplicationContext());
        Internet internet = new Internet.Builder()
                .ipAddress(ipAddress)
                .build();
        internetRepository.delete(internet);
    }

    public void deleteAll(View v)
    {
        InternetRepository internetRepository = new InternetRepositoryImpl(this.getApplicationContext());
        int deleted = internetRepository.deleteAll();

        if(deleted > 0)
        {
            Toast.makeText(DeleteInternet.this, "Employees deleted successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(DeleteInternet.this, "No employees to delete", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, InternetMenu.class);
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
