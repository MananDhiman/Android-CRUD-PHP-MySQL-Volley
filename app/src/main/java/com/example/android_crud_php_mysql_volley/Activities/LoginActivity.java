package com.example.android_crud_php_mysql_volley.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_crud_php_mysql_volley.R;

public class LoginActivity extends AppCompatActivity {
    EditText et_username, et_password;
    Button but_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        et_username = findViewById(R.id.login_et_username);
        et_password = findViewById(R.id.login_et_password);
        but_login = findViewById(R.id.login_but_login);

       but_login.setOnClickListener(v -> checkCredentials());

    }

    private void checkCredentials() {
        if (et_username.getText().toString().equals("l") && et_password.getText().toString().equals("m")){
            Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT).show();
            login();
        }
    }

    private void login() {
        Intent intentLogin = new Intent(LoginActivity.this, AdminActivity.class);
        this.startActivity(intentLogin);
    }

}