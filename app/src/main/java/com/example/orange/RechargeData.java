package com.example.orange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class RechargeData extends AppCompatActivity {

    EditText Code, AccumReading,PreReadingBefKWH,PreReadingBefEGP,PreReadingAftKWH,PreReadingAftEGP;
    String code, accumReading,preReadingBefKWH,preReadingBefEGP,preReadingAftKWH,preReadingAftEGP;
    ImageView ReadingImg; //the image taken (need to create it in xml file )

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_data);

        Code=(EditText)findViewById(R.id.code);
        AccumReading=(EditText)findViewById(R.id.AccumReading);
        PreReadingBefKWH=(EditText)findViewById(R.id.PreReadingBef1);
        PreReadingBefEGP=(EditText)findViewById(R.id.PreReadingBef2);
        PreReadingAftKWH=(EditText)findViewById(R.id.PreReadingAft1);
        PreReadingAftEGP=(EditText)findViewById(R.id.PreReadingAft2);

    }

    public void record1 (View view)
    {
        code=Code.getText().toString();
        accumReading=AccumReading.getText().toString();

        preReadingBefKWH=PreReadingBefKWH.getText().toString();
        preReadingBefEGP=PreReadingBefEGP.getText().toString();
        preReadingAftKWH=PreReadingAftKWH.getText().toString();
        preReadingAftEGP=PreReadingAftEGP.getText().toString();
        String method = "rechargeData";
        BackgroundTask backgroundTask1 = new BackgroundTask(this);
        backgroundTask1.execute(method,code,accumReading,preReadingBefKWH,preReadingBefEGP,preReadingAftKWH,preReadingAftEGP);
        finish();

    }

}