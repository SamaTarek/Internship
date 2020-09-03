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


       /* Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Areas, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
*/
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