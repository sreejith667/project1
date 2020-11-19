package com.example.projectqrcode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

//import androidx.annotation;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final int TAG_EMAIL = 12;
    private static final int TAG_PASSWORD = 2234;
    EditText username;
    EditText password;
    ProgressBar mProgressBar;
    Button login;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);





//        setupToolBar();

//        setupForm();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                loginfunction();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(getBaseContext(),signup.class));
            }
        });


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void setupToolBar() {
//
//        final ActionBar actionBar = getSupportActionBar();
//
//        if (actionBar != null) {
//            ((ActionBar) actionBar).setDisplayShowTitleEnabled(false);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeButtonEnabled(true);
//        }
//
//    }
//
//    private void setupForm() {
//
////        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
////        mFormBuilder = new FormBuilder(this, mRecyclerView);
//
//        username = EditText.createInstance().setTag(TAG_EMAIL).setTitle("Username").setRequired(true);
//        password = EditText.createInstance().setTag(TAG_PASSWORD).setTitle("Password").setRequired(true);
//
//        List<BaseFormElement> formItems = new ArrayList<>();
//        formItems.add(username);
//        formItems.add(password);
//
////        mFormBuilder.addFormElements(formItems);
//
//        // mFormBuilder.refreshView();
//
//    }


    public void loginfunction() {
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("username", username.getText().toString())
                .add("password", password.getText().toString())
                .build();
        final Request request = new Request.Builder()
                .url("http://hasbuddy.com?r=toll/login").post(formBody)
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
                mProgressBar.setVisibility(View.INVISIBLE);
                if (s != null ) {






                    if (s.equals("0")) {
                        Toast.makeText(getApplicationContext(), "invalid username or password", Toast.LENGTH_SHORT).show();
                    } else {

                        SharedPreferences.Editor editor = getSharedPreferences("Logindata", MODE_PRIVATE).edit();
                        editor.putString("logid", s.toString());
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), upload.class);
                        startActivity(intent);
                    }
                }



            }
        };
        asyncTask.execute();



    }}





