package com.s212037943.KurvinHendricks.databaseapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.s212037943.KurvinHendricks.databaseapplication.repository.DatasourceDAO;
import com.s212037943.KurvinHendricks.databaseapplication.repository.Impl.DatasourceDAOImpl;


public class UserActivity extends Activity {

    private EditText emailAddress;
    private EditText authkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatasourceDAO dao = new DatasourceDAOImpl(this);
        Button userAddButton = (Button) findViewById(R.id.user_add_button);

        emailAddress = (EditText) findViewById(R.id.input_user_email);
        authkey = (EditText) findViewById(R.id.input_user_auth);
        userAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                User user = new User();
                user.setAuth(authkey.getText().toString());
                user.setEmail(emailAddress.getText().toString());
                dao.createUser(user);

                /*startActivity(new Intent(UserActivity.this,
                        SettingsActivity.class));

                /*Log.i("on create here", "user has been created");
                User u = new User();

                */

                User u2 = new User();
                u2 = dao.getUser();

                int size = dao.getUserSize();
                Toast.makeText(getApplicationContext()," " + size + " ", Toast.LENGTH_LONG ).show();

                for(int x = 1; x <= size; x++){
                    User u = new User();
                    u = dao.findUserByID(x);
                    int id = u.getId();
                    String email = u.getEmail();
                    Log.i(id + " ", email );
                    Toast.makeText(getApplicationContext(), id + " " + email, Toast.LENGTH_LONG).show();
                }

                /*Cursor Settings = (Cursor) dao.getSettings();
                ListAdapter settingsAdap = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1 Settings);

                ListView SettingsList = (ListView)findViewById(android.R.id.list);
                SettingsList.setAdapter(settingsAdap);*/
            }
        });

        Button urlCancelButton = (Button) findViewById(R.id.url_cancel);

        urlCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this, SettingsActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
