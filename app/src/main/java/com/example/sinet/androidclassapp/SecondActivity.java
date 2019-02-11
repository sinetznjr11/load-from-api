package com.example.sinet.androidclassapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    String username,phno;
    Button btnDial,btnVisit;
    EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //username=getIntent().getExtras().get("keyUsername").toString().trim();
        phone=findViewById(R.id.phone);
        btnDial=findViewById(R.id.btnDial);
        btnVisit=findViewById(R.id.btnVisit);
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phno=phone.getText().toString().trim();
                Toast.makeText(SecondActivity.this, username, Toast.LENGTH_SHORT).show();
                Intent dialIntent= new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:"+phno));
                startActivity(dialIntent);

            }
        });

        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visitIntent=new Intent(Intent.ACTION_DIAL);
                startActivity(visitIntent);
            }
        });
    }
}
