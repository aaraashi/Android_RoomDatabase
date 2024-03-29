package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UpdatePatientActivity extends AppCompatActivity {

    private TextView tvNurseInfo;
    private PatientViewModel patientViewModel;
    private PatientDao patientDao;
    private AppDatabase appDatabase;
    public static EditText editText_fname, editText_lname, editText_p_nurse, editText_p_dept, editText_p_room;
    Patient patient;
    Intent intent;

    public static final String EXTRA_ID = "hyongdonjeong_juheekim_comp304lab4.EXTRA_ID";
    public static final String EXTRA_FNAME = "hyongdonjeong_juheekim_comp304lab4.EXTRA_FNAME";
    public static final String EXTRA_LNAME = "hyongdonjeong_juheekim_comp304lab4.EXTRA_LNAME";
    public static final String EXTRA_NURSEID = "hyongdonjeong_juheekim_comp304lab4.EXTRA_NURSEID";
    public static final String EXTRA_DEPT = "hyongdonjeong_juheekim_comp304lab4.EXTRA_DEPT";
    public static final String EXTRA_ROOM = "hyongdonjeong_juheekim_comp304lab4.EXTRA_ROOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);

        // display nurse information: first name (id)
        tvNurseInfo = (TextView) findViewById(R.id.textView_nurseInfo);
        SharedPreferences nursePreference = getSharedPreferences("NursePref", MODE_PRIVATE);
        tvNurseInfo.setText(nursePreference.getString("nurseIdString",""));

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patient = new Patient();

        editText_fname = findViewById(R.id.editText_fname);
        editText_lname = findViewById(R.id.editText_lname);
        editText_p_dept = findViewById(R.id.editText_p_dept);
        editText_p_nurse = findViewById(R.id.editText_p_nurseID);
        editText_p_room = findViewById(R.id.editText_p_room);

        intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            editText_fname.setText(intent.getStringExtra(EXTRA_FNAME));
            editText_lname.setText(intent.getStringExtra(EXTRA_LNAME));
            editText_p_nurse.setText(intent.getStringExtra(EXTRA_NURSEID));
            editText_p_dept.setText(intent.getStringExtra(EXTRA_DEPT));
            editText_p_room.setText(intent.getStringExtra(EXTRA_ROOM));
        } else {
            editText_p_nurse.setText(nursePreference.getString("nurseIdString",""));
        }

        patientViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(UpdatePatientActivity.this, "Patient successfully saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(UpdatePatientActivity.this, "Error saving patient", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void updatePatient (View v){

        patient.setFirstname(editText_fname.getText().toString());
        patient.setLastname(editText_lname.getText().toString());
        patient.setDepartment(editText_p_dept.getText().toString());
        patient.setNurseId(editText_p_nurse.getText().toString());
        patient.setRoom(editText_p_room.getText().toString());

        if (intent.hasExtra(EXTRA_ID)) {

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            patient.setPatientId(id);
            patientViewModel.update(patient);
            displayToast("The patient information has been updated!");
        }
        else {
            patientViewModel.insert(patient);
            displayToast("The patient information has been saved!");
        }

        Intent intent = new Intent(UpdatePatientActivity.this, PatientInfoActivity.class );
        startActivity(intent);

    }

    public void nurseInfo (View v){
        Intent intent = new Intent(UpdatePatientActivity.this, UpdateNurseActivity.class );
        startActivity(intent);
    }

    public void deletePatient (View v){

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        patient.setPatientId(id);
        patientViewModel.delete(patient);
        displayToast("Patient deleted");

        Intent intent = new Intent(UpdatePatientActivity.this, PatientInfoActivity.class );
        startActivity(intent);

    }

    public void backToMenu(View v) {
        Intent intent = new Intent(UpdatePatientActivity.this, WelcomeActivity.class );
        startActivity(intent);
    }

    public void displayToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
