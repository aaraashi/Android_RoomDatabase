package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DatabaseExActivity extends AppCompatActivity {

    private NurseViewModel nurseViewModel;
    private Button btnInsert;
    private EditText editTextName;
    private TextView textViewDisplay;
    Nurse nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDisplay = findViewById(R.id.textViewDisplay);

        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);

        nurse = new Nurse();

        nurseViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result ==1) {
                    Toast.makeText(DatabaseExActivity.this,"Nurse successfully saved", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DatabaseExActivity.this,"Error saving nurse", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nurseViewModel.getAllNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(@Nullable List<Nurse> result) {
                String output="";
                for(Nurse nurse : result){
                    output+= nurse.getFirstname() + "\n";
                }
                textViewDisplay.setText(output);
            }
        });

        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextName = findViewById(R.id.editTextName);
                nurse.setFirstname(editTextName.getText().toString());
                nurseViewModel.insert(nurse);
            }
        });

    }

    public void getList(View view) { nurseViewModel.getAllNurses(); }
}
