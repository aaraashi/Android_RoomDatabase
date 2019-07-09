package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    private TextView tvNurseInfo, tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // display nurse information: first name (id)
        tvNurseInfo = (TextView) findViewById(R.id.textView_nurseInfo);
        tvWelcome = (TextView) findViewById(R.id.textView_welcome);
        SharedPreferences nursePreference = getSharedPreferences("NursePref", MODE_PRIVATE);
        tvNurseInfo.setText(nursePreference.getString("nurseIdString",""));
        tvWelcome.setText("Welcome " + nursePreference.getString("nurseFNameString","") + "!");

    }

    public void nurseInfo (View v){
        Intent intent = new Intent(WelcomeActivity.this, UpdateNurseActivity.class );
        startActivity(intent);
    }

    public void patientInfo (View v){
        Intent intent = new Intent(WelcomeActivity.this, PatientInfoActivity.class );
        startActivity(intent);
    }

    public void testInfo (View v){
        Intent intent = new Intent(WelcomeActivity.this, TestPatientInfoActivity.class );
        startActivity(intent);
    }

    public void maintenance (View v) {

        displayToast("This menu is under maintenance.");
    }

    public void displayToast(String message){
        Toast.makeText(WelcomeActivity.this, message, Toast.LENGTH_LONG).show();
    }

}
