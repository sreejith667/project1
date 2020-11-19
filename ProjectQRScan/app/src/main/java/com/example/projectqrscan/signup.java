package com.example.projectqrscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    EditText username,password,reenterpassword,tollname,amount;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button submit=findViewById(R.id.submit);
        username=findViewById(R.id.usernameinput);
        password=findViewById(R.id.passwordinput);
        reenterpassword=findViewById(R.id.passwordinput2);
        tollname=findViewById(R.id.tollname);
        amount=findViewById(R.id.amount);


        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("amount", amount.getText().toString());
        editor.putString("tollname", tollname.getText().toString());


        editor.commit();

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
                .add("reenterpassword",reenterpassword.getText().toString())
                .add("tollname",tollname.getText().toString())
                .add("amount",amount.getText().toString())
                .build();
        final Request request = new Request.Builder()
                .url("http://hasbuddy.com?r=toll/adminsignup") .post(formBody)
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
                SharedPreferences.Editor editor = getSharedPreferences("Logindata", MODE_PRIVATE).edit();
                editor.putString("vehicleid", s.toString());
                editor.commit();

                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();


            }
        };

        asyncTask.execute();





    }
}
