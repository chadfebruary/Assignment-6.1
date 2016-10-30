package com.milleniumshopping.app.milleniumshopping.activity.internet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.milleniumshopping.app.milleniumshopping.R;
import com.milleniumshopping.app.milleniumshopping.domain.internet.Internet;
import com.milleniumshopping.app.milleniumshopping.repository.internet.Impl.InternetRepositoryImpl;
import com.milleniumshopping.app.milleniumshopping.repository.internet.InternetRepository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ViewInternet extends AppCompatActivity {

    ArrayAdapter adapter;
    ListView listView;
    String[]objects;
    Set<Internet> internetSet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_internet);

        InternetRepository internetRepository = new InternetRepositoryImpl(this.getApplicationContext());

        internetSet = new HashSet<>();
        internetSet = internetRepository.findAll();

        Iterator<Internet> internetIterator = internetSet.iterator();

        if(internetSet.size() > 0)
        {
            objects = new String[internetSet.size()];
            int i = 0;

            while(internetIterator.hasNext())
            {
                objects[i] = internetIterator.next().toString();
                i++;
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, objects);
            listView = (ListView) findViewById(R.id.listView2);
            listView.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(ViewInternet.this, "No data", Toast.LENGTH_SHORT).show();
        }
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
