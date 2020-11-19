package com.example.projectqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class addvehicle extends AppCompatActivity {
    EditText regnum,chassisnum,vehicletype,rcowner;
    Button submit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvehicle);
        submit2=findViewById(R.id.submit);
        regnum=findViewById(R.id.regnum);
        chassisnum=findViewById(R.id.chassisnum);
        vehicletype=findViewById(R.id.vehicletype);
        rcowner=findViewById(R.id.rcowner);


        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrationfunction();
            }
        });
    }




    public void registrationfunction(){

        SharedPreferences prefs = getSharedPreferences("Logindata", MODE_PRIVATE);
        String logid = prefs.getString("logid", "1");//"No name defined" is the default value.




        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("regnum", regnum.getText().toString())
                .add("chassisnum", chassisnum.getText().toString())
                .add("vehicletype",vehicletype.getText().toString())
                .add("rcowner",rcowner.getText().toString())
                .add("logiddddddd",logid)

                .build();
        final Request request = new Request.Builder()
                .url("http://hasbuddy.com?r=toll/register") .post(formBody)
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
                if (s != null ) {






                    if (s.equals("0")) {
                        Toast.makeText(getApplicationContext(), "No data uploaded", Toast.LENGTH_SHORT).show();
                    } else {

//                        SharedPreferences.Editor editor = getSharedPreferences("dataid", MODE_PRIVATE).edit();
//                        editor.putString("id", s.toString());
//                        editor.commit();


                        Intent intent = new Intent(getApplicationContext(), qrcode.class);
                        intent.putExtra("qrvalue",s.toString());  // pass your values and retrieve them in the other Activity using AnyKeyName

                        startActivity(intent);
                    }
                }



            }
        };

        asyncTask.execute();





    }
}
