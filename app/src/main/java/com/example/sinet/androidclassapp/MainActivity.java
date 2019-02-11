package com.example.sinet.androidclassapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText username,password;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton=findViewById(R.id.btnlogin);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        checkBox=findViewById(R.id.checkBox);

        sharedPreferences=getApplicationContext().getSharedPreferences("SharedData",MODE_PRIVATE);
        editor=sharedPreferences.edit();


        loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                String getUsername=username.getText().toString().trim();
                String getPassword=password.getText().toString().trim();
                Toast.makeText(MainActivity.this, getPassword+"iyuiy "+getUsername, Toast.LENGTH_SHORT).show();

                if(getUsername.equals("admin") && getPassword.equals("admin")) {

                    if (checkBox.isChecked()) {
                        editor.putString("savedUsername", getUsername);
                        editor.putString("savedPassword", getPassword);
                        editor.apply();
                    }
                    Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
                else{

                    Toast.makeText(MainActivity.this, "Invalid Credentials!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onStart() {

        if(!TextUtils.isEmpty(sharedPreferences.getString("savedUsername",null)) &&
                !TextUtils.isEmpty(sharedPreferences.getString("savedPassword",null)))
        {
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        super.onStart();
    }
}
