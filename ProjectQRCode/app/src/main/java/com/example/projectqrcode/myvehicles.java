package com.example.projectqrcode;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class myvehicles extends AppCompatActivity {
  //  List<String>vehicles =new ArrayList<>();
     ArrayList<MyListData> listdata=new ArrayList<MyListData>();;
    MyListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvehicles);
        vehiclefunction();




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
         adapter = new MyListAdapter(listdata);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }




    public void vehiclefunction(){
        Toast.makeText(getApplicationContext(), "function called", Toast.LENGTH_SHORT).show();
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://hasbuddy.com?r=toll/myvehicles")

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


                try {
                    JSONArray serverresponse=new JSONArray(s);

                    JSONArray jArray = (JSONArray)serverresponse;
                    if (jArray != null) {
                        for (int i=0;i<jArray.length();i++){
                            try {

                                JSONObject datas=new JSONObject(jArray.getString(i));
                              listdata.add(new MyListData(datas.getString("id"),datas.getString("rcowner"),datas.getString("vehicle_type"),datas.getString("chassisnum"),datas.getString("id"),datas.getString("chassisnum"),datas.getString("id")));
                            //spinnerMap.put(datas.getInt("id"),datas.getString("plane_name"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

            }


        };


        asyncTask.execute();
    }


}
