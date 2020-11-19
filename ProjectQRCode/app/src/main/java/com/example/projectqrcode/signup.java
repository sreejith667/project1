package com.example.projectqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class signup extends AppCompatActivity {
    EditText username,password,firstname,lastname,mobile,address,pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button submit=findViewById(R.id.signup);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        firstname=findViewById(R.id.username);
        lastname=findViewById(R.id.password);
        mobile=findViewById(R.id.mobile);
        address=findViewById(R.id.address);
        pincode=findViewById(R.id.pincode);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signupfunction();


            }
        });
    }




    public void signupfunction(){
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("username", username.getText().toString())
                .add("password", password.getText().toString())
                .add("firstname",firstname.getText().toString())
                .add("lastname",lastname.getText().toString())
                .add("mobile",mobile.getText().toString())
                .add("address",address.getText().toString())
                .add("pincode",pincode.getText().toString())
                .build();
        final Request request = new Request.Builder()
                .url("http://hasbuddy.com?r=toll/signup") .post(formBody)
                .build();

        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        return null;
                    }
                    return response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();


            }
        };

        asyncTask.execute();





    }
}
