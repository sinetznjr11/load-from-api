package com.example.sinet.androidclassapp;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imageView;
    TeacherAdapter teacherAdapter;

    final ArrayList<TeacherModel> arrayList= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView=findViewById(R.id.teacherRecyclerView);
       arrayList.add(new TeacherModel("Rajesh Kamar","DSA","https://www.merogyan.com/college/img/saroj_giri.jpg"));

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://merogyan.com/college/request.php?datacount=all";

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

                                Log.d(">>>>",name+" "+email);

                                arrayList.add(new TeacherModel(name,email,url));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListActivity.this, "Didnt Work", Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


        teacherAdapter= new TeacherAdapter(arrayList);
        teacherAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(teacherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageView=findViewById(R.id.imageView12);

        RequestOptions options = new RequestOptions()
                .centerCrop()
               ;



        Glide.with(this).load(R.drawable.source).apply(options).into(imageView);


    }

    public class TeacherModel{
        String name;
        String subject;
        String imageURL;

        public TeacherModel(String name, String subject, String imageURL) {
            this.name = name;
            this.subject=subject;
            this.imageURL=imageURL;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }
    }

    public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>{
        ArrayList <TeacherModel> arraylist= new ArrayList<>();

        public TeacherAdapter(ArrayList<TeacherModel> arrayList) {
            this.arraylist = arrayList;
        }

        @NonNull
        @Override
        public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           View designSupport= getLayoutInflater().inflate(R.layout.single_contact_list,viewGroup,false);
            TeacherViewHolder teacherViewHolder= new TeacherViewHolder(designSupport);
            return teacherViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TeacherViewHolder teacherViewHolder, int i) {

            teacherViewHolder.txtname.setText(arraylist.get(i).getName());
            teacherViewHolder.txtSubject.setText(arraylist.get(i).getSubject());
            //Toast.makeText(ListActivity.this,arraylist.get(i).getImageURL() , Toast.LENGTH_SHORT).show();

            RequestOptions options1 = new RequestOptions()
                    .centerCrop();
            Glide.with(ListActivity.this).load(arraylist.get(i).getImageURL()).apply(options1)
                    .into(teacherViewHolder.userImage);



        }

        @Override
        public int getItemCount() {
            return arraylist.size();
        }

        public class TeacherViewHolder extends RecyclerView.ViewHolder{
            TextView  txtname,txtSubject;
            ImageView userImage;
            public TeacherViewHolder(@NonNull View itemView) {
                super(itemView);
                txtname=itemView.findViewById(R.id.textname);
                txtSubject=itemView.findViewById(R.id.subject);
                userImage=itemView.findViewById(R.id.userImage);
            }

        }




    }

}
