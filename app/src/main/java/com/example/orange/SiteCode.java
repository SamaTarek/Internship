package com.example.orange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SiteCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_code);


        Button button = (Button) findViewById(R.id.btnPre);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPre();
            }
        });

        Button button2 = (Button) findViewById(R.id.btnLeg);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLeg();
            }
        });


    }

    public void openPre()
    {
        Intent intent = new Intent(this, RechargeData.class);
        startActivity(intent);
    }

    public void openLeg()
    {
        Intent intent = new Intent(this, LegacyData.class);
        startActivity(intent);
    }
}
