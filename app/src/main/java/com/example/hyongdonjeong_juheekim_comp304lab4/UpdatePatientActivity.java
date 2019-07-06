package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UpdatePatientActivity extends AppCompatActivity {

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

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patient = new Patient();

        editText_fname = findViewById(R.id.editText_fname);
        editText_lname = findViewById(R.id.editText_lname);
        editText_p_dept = findViewById(R.id.editText_p_dept);
        editText_p_nurse = findViewById(R.id.editText_p_nurseID);
        editText_p_room = findViewById(R.id.editText_p_room);

        intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
//            String fName = intent.getStringExtra("fName");
//            String lName = intent.getStringExtra("lName");
//            String nurseId = intent.getStringExtra("nurseId");
//            String dept = intent.getStringExtra("dept");
//            String room = intent.getStringExtra("room");
            editText_fname.setText(intent.getStringExtra(EXTRA_FNAME));
            editText_lname.setText(intent.getStringExtra(EXTRA_LNAME));
            editText_p_nurse.setText(intent.getStringExtra(EXTRA_NURSEID));
            editText_p_dept.setText(intent.getStringExtra(EXTRA_DEPT));
            editText_p_room.setText(intent.getStringExtra(EXTRA_ROOM));
        } else {
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

        patientViewModel.getAllPatient().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(@Nullable List<Patient> result) {
                String output = "";
                for (Patient patient : result) {
                    output += patient.getFirstname() + "\n";
                }
                //textViewDisplay.setText(output);
            }
        });

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
        patient.setNurseId(Integer.parseInt(editText_p_nurse.getText().toString()));
        patient.setRoom(editText_p_room.getText().toString());

        if (intent.hasExtra(EXTRA_ID)) {
            //patientDao.updateRow(editText_fname.getText().toString(), editText_lname.getText().toString(), Integer.parseInt(editText_p_nurse.getText().toString()), editText_p_dept.getText().toString(), editText_p_room.getText().toString(), Integer.parseInt(intent.getStringExtra(EXTRA_ID)));
            patientViewModel.update(patient);
        }
        else {
            patientViewModel.insert(patient);
        }

        //displayToast("The patient information has been updated!");
    }

    public void deletePatient (View v){
        patient.setFirstname(editText_fname.getText().toString());
        patient.setLastname(editText_lname.getText().toString());
        patient.setDepartment(editText_p_dept.getText().toString());
        patient.setNurseId(Integer.parseInt(editText_p_nurse.getText().toString()));
        patient.setRoom(editText_p_room.getText().toString());

        patientViewModel.delete(patient);

    }

    public void displayToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
