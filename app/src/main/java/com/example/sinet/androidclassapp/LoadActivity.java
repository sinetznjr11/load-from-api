package com.example.sinet.androidclassapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class LoadActivity extends AppCompatActivity {

   Button button;

   TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        textView=findViewById(R.id.textView);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add_data(null);
            }
        });

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://merogyan.com/college/request.php?datacount=1";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray jsonArray= new JSONArray(response);
                            for(int i=0; i<jsonArray.length();i++){
                                Log.d("Server Data: ", response);
                                String name= jsonArray.getJSONObject(i).getString("name");
                                String email=jsonArray.getJSONObject(i).getString("email");
                                String url=jsonArray.getJSONObject(i).getString("url");
                                //server ko string name in json
                              textView.setText(name+" "+email+" "+url);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }

    public void add_data(View view) {

        Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                String source = msg.obj.toString();
                Log.d("Server Data: ", source);
               // Toast.makeText(LoadActivity.this, "Server data"+source, Toast.LENGTH_SHORT).show();

                try {
                    JSONArray jsonArray= new JSONArray(source);
                    for(int i=0; i<jsonArray.length();i++){
                        String name= jsonArray.getJSONObject(i).getString("name");
                        String email=jsonArray.getJSONObject(i).getString("email");//server ko string name in json


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        HttpSourceRequest httpSourceRequest =
                new HttpSourceRequest(handler, "https://merogyan.com/college/request.php?datacount=all");


    }


}
