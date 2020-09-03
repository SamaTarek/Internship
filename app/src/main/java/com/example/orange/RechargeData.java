package com.example.orange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class RechargeData extends AppCompatActivity {

    EditText AccumReading,PreReadingBefKWH,PreReadingBefEGP,PreReadingAftKWH,PreReadingAftEGP;
    ImageView ReadingImg; //the image taken (need to create it in xml file )

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_data);



    }
}