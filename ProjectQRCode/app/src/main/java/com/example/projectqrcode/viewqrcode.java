package com.example.projectqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class viewqrcode extends AppCompatActivity {


    TextView regnumbert,rcowner1,vehicle_type1,chassisnum1;
    String  ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewqrcode);


        Button button=findViewById(R.id.recharge);
        Button viewqr=findViewById(R.id.viewqr);


        regnumbert=findViewById(R.id.regnumbr);
        rcowner1=findViewById(R.id.owner);
        vehicle_type1=findViewById(R.id.vehicletype);
        chassisnum1=findViewById(R.id.chassisnum);
        String regnumber = getIntent().getExtras().getString("regnumber","0");
        regnumbert.setText(regnumber);
        String rcowner = getIntent().getExtras().getString("rcowner","0");
        rcowner1.setText(rcowner);
        String vehicle_type = getIntent().getExtras().getString("vehicle_type","0");
        vehicle_type1.setText(vehicle_type);
        String chassisnum = getIntent().getExtras().getString("chassisnum","0");
        chassisnum1.setText(chassisnum);
         ids = getIntent().getExtras().getString("id","0");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getBaseContext(),recharge.class));
            }
        });

        viewqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(viewqrcode.this, qrview.class);
                intent.putExtra("id", ids);  // pass your values and retrieve them in the other Activity using keyName
                startActivity(intent);
                startActivity(new Intent(getBaseContext(),qrview.class));
            }
        });



    }

}
