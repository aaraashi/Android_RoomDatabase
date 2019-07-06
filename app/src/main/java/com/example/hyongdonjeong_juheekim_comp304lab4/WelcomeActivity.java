package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
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
