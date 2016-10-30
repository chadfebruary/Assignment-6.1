package com.milleniumshopping.app.milleniumshopping.activity.internet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.milleniumshopping.app.milleniumshopping.R;
import com.milleniumshopping.app.milleniumshopping.domain.internet.Internet;

public class AddInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_internet);
    }

    public void previewInternet(View v)
    {
        Intent intent = new Intent(this, PreviewInternet.class);

        String isp = ((EditText)findViewById(R.id.editText80)).getText().toString();
        String planName = ((EditText)findViewById(R.id.editText80)).getText().toString();
        String price = ((EditText)findViewById(R.id.editText82)).getText().toString();
        String dataAllowance = ((EditText)findViewById(R.id.editText83)).getText().toString();
        String type = ((EditText)findViewById(R.id.editText84)).getText().toString();

        Internet internet = new Internet.Builder()
                .ISP(isp)
                .planName(planName)
                .price(price)
                .dataAllowance(dataAllowance)
                .type(type)
                .build();

        intent.putExtra("INTERNET", internet);
        startActivity(intent);
    }

    /*public class AccountRegisterTask extends AsyncTask<Void, Void, Employee> {

        private final Account account;

        AccountRegisterTask(Account account) {
            this.account = account;
        }

        @Override
        protected Employee doInBackground(Void... params) {

            final String uri = "http://148.100.5.84:8080/internetService/";

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
