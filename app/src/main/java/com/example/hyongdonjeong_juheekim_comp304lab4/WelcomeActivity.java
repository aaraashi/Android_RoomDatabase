package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    TextView tvNurseInfo, tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvNurseInfo = (TextView) findViewById(R.id.textView_nurseInfo);
        tvWelcome = (TextView) findViewById(R.id.textView_welcome);

        SharedPreferences nursePreference = getSharedPreferences("NursePref", MODE_PRIVATE);

        tvNurseInfo.setText(nursePreference.getString("nurseNameString","")
                + "(" + nursePreference.getString("nurseIdString","") + ")");
        tvWelcome.setText("Welcome " + nursePreference.getString("nurseNameString","") + "!");

    }

    public void patientInfo (View v){
        Intent intent = new Intent(WelcomeActivity.this, PatientInfoActivity.class );
        startActivity(intent);
    }

    public void testInfo (View v){
        Intent intent = new Intent(WelcomeActivity.this, TestInfoActivity.class );
        startActivity(intent);
    }
}
