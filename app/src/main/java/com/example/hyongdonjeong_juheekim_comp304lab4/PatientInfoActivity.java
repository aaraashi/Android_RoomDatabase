package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PatientInfoActivity extends AppCompatActivity {

    private TextView tvNurseInfo;
    private PatientViewModel patientViewModel;
    private RecyclerView recyclerView;
    public static final int ADD_PATIENT_REQUEST = 1;
    public static final int EDIT_PATIENT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        // display nurse information: first name (id)
        tvNurseInfo = (TextView) findViewById(R.id.textView_nurseInfo);
        SharedPreferences nursePreference = getSharedPreferences("NursePref", MODE_PRIVATE);
        tvNurseInfo.setText(nursePreference.getString("nurseIdString",""));

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        final PatientAdapter adapter = new PatientAdapter();
        recyclerView.setAdapter(adapter);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.getAllPatient().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(@Nullable List<Patient> patients) {
                adapter.setPatients(patients);
            }
        });

        adapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient patient) {
                Intent intent = new Intent(PatientInfoActivity.this, UpdatePatientActivity.class);
                intent.putExtra(UpdatePatientActivity.EXTRA_ID, patient.getPatientId());
                intent.putExtra(UpdatePatientActivity.EXTRA_FNAME, patient.getFirstname());
                intent.putExtra(UpdatePatientActivity.EXTRA_LNAME, patient.getLastname());
                intent.putExtra(UpdatePatientActivity.EXTRA_NURSEID, patient.getNurseId());
                intent.putExtra(UpdatePatientActivity.EXTRA_DEPT, patient.getDepartment());
                intent.putExtra(UpdatePatientActivity.EXTRA_ROOM, patient.getRoom());
                startActivityForResult(intent, EDIT_PATIENT_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                patientViewModel.delete(adapter.getPatientsAt(viewHolder.getAdapterPosition()));
                Toast.makeText(PatientInfoActivity.this, "Test deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }


    public void nurseInfo (View v){
        Intent intent = new Intent(PatientInfoActivity.this, UpdateNurseActivity.class );
        startActivity(intent);
    }

    public void addNewPatient (View v){
        Intent intent = new Intent(PatientInfoActivity.this, UpdatePatientActivity.class );
        startActivity(intent);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(PatientInfoActivity.this, WelcomeActivity.class );
        startActivity(intent);
    }
}
