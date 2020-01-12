package com.fauzi.barcodeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fauzi.barcodeproject.Model.Model;

public class Login extends AppCompatActivity {
    Button btnLogin, btnRegis;
    EditText etUsername, etPassword;
    Intent i;
    String[] auth;
    EditText username, password;
    String userpass = "pkkmbikmi2019,sukses";
    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sh = getSharedPreferences("myprefe", 0);
        editor = sh.edit();
        String loginStatus = sh.getString(Model.loginStatus, null);

        if(loginStatus == null) {
            setContentView(R.layout.activity_login);

            username = findViewById(R.id.etUsername);
            password = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);
            auth = getResources().getStringArray(R.array.auth);
        } else {
            startActivity(new Intent(Login.this, MainActivity.class));
        }

        if(loginStatus == null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doLogin();
                }
            });
        }
    }

    public void doLogin() {
       String usr ="username";
       String pass ="password";

        if(username.getText().toString().equals("")) {
            Toast.makeText(this, "Please input username", Toast.LENGTH_SHORT).show();
        } else {
           usr  = username.getText().toString();
        }

        if(password.getText().toString().equals("")) {
            Toast.makeText(this, "Please input password", Toast.LENGTH_SHORT).show();
        } else {
            pass = password.getText().toString();
        }

        if(userpass.split(",")[0].toString().equals(usr)) {
            if(userpass.split(",")[1].toString().equals(pass)) {
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }

        for(int i = 0; i < auth.length; i++) {
            if(auth[i].split(",")[0].toString().equals(usr)) {
                if(auth[i].split(",")[1].toString().equals(pass)) {
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                    editor.putString(Model.loginStatus, "true");
                    editor.putString(Model.username, auth[i].split(",")[0]);
                    editor.commit();
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
