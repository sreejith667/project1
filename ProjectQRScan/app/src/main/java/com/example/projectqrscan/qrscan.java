package com.example.projectqrscan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class qrscan extends AppCompatActivity {
    String amount,tollname,reults;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) { //ask for authorisation
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
            //start your camera
        }
        SharedPreferences sh
                = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

        //   String vehicleid = sh.getString("vehicleid", "");
        amount = sh.getString("amount", "10");
        tollname = sh.getString("tollname", "10");

        qrScan = new IntentIntegrator(qrscan.this);
        qrScan.initiateScan();






    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                //   scanvechicle(result.getContents(),tollplazzaname);
                recordtransacton(result.getContents(),tollname,amount);

                Toast.makeText(this, "vechile id passed", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }




    public void recordtransacton(String vehicleid, String tollname, String amount) {

        Toast.makeText(getApplicationContext(), "function called", Toast.LENGTH_LONG).show();


        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("vehicleid",vehicleid)
                .add("amount",amount)
                .add("tollname",tollname)
                .build();
        final Request request = new Request.Builder()
                .url("http://hasbuddy.com?r=toll/transaction").post(formBody)
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
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();




            }
        };
        asyncTask.execute();




    }}

