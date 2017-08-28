package com.s212037943.KurvinHendricks.databaseapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.s212037943.KurvinHendricks.databaseapplication.repository.DatasourceDAO;
import com.s212037943.KurvinHendricks.databaseapplication.repository.Impl.DatasourceDAOImpl;

/**
 * Created by Home on 8/17/2014.
 */
public class UrlAddActivity extends Activity {
    private EditText serverAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatasourceDAO dao = new DatasourceDAOImpl(this);

        serverAddress = (EditText) findViewById(R.id.input_site_address_url);
        Button urlAddButton = (Button) findViewById(R.id.url_add_button);
        urlAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                final Settings settings = new Settings();
                settings.setUrl(serverAddress.getText().toString());
                dao.createSettings(settings);
                startActivity(new Intent(UrlAddActivity.this, SettingsActivity.class));
            }
        });

        Button urlCancelButton = (Button) findViewById(R.id.url_cancel);

        urlCancelButton.setOnClickListener(new View.OnClickListener()
            {
               public void onClick(View view){
                   startActivity(new Intent(UrlAddActivity.this, SettingsActivity.class));
               }
            }
        );
    }
}
