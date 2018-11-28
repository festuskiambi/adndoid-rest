package com.example.festus.resttuitorial.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.festus.resttuitorial.R;
import com.example.festus.resttuitorial.reposlist.ListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String STRING_USERNAME = "STRING_USERNAME";
    private static final String BUNDLE_EXTRA = "BUNDLE_EXTRA";

    private EditText nameInput;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameInput = findViewById(R.id.edt_main_submit);
        submit = findViewById(R.id.btn_main_submit);
        submit.setOnClickListener(this);

        checkInternetPermission();
    }

    private void checkInternetPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.INTERNET},
                    0);
        }
    }

    @Override
    public void onClick(View v) {
        String userName = nameInput.getText().toString();

        if (userName.length() < 1) {
            Toast.makeText(this, "Enter a valid Github Username.", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Intent i = new Intent(this, ListActivity.class);

            Bundle extras = new Bundle();
            extras.putString(STRING_USERNAME, userName);

            i.putExtra(BUNDLE_EXTRA, extras);
            startActivity(i);
        }

    }
}
