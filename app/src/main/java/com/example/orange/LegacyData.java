package com.example.orange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class LegacyData extends AppCompatActivity {

    EditText siteCode, Reading;
    String code,KWH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legacy_data);


        siteCode=(EditText)findViewById(R.id.code);
        Reading=(EditText)findViewById(R.id.LegReading);

    }

    public void record (View view)
    {
        code=siteCode.getText().toString();
        KWH=Reading.getText().toString();
        String method = "legacyData";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,code,KWH);
        finish();

    }
}