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
import android.widget.Toast;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {
    private NurseViewModel nurseViewModel;
    private EditText editText_fname, editText_lname, editText_password1, editText_password2, editText_dept;
    private Button button_submit;
    Nurse nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);

        nurse = new Nurse();

        nurseViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result ==1) {
                    Toast.makeText(CreateAccountActivity.this,"Nurse successfully saved", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CreateAccountActivity.this,"Error saving nurse", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_fname = findViewById(R.id.editText_fname);
                editText_lname = findViewById(R.id.editText_lname);
                editText_password1 = findViewById(R.id.editText_password1);
                editText_password2 = findViewById(R.id.editText_password2);
                editText_dept = findViewById(R.id.editText_dept);

                if (editText_password1.getText().toString().equals(editText_password2.getText().toString())){
                    nurse.setFirstname(editText_fname.getText().toString());
                    nurse.setLastname(editText_lname.getText().toString());
                    nurse.setPassword(editText_password1.getText().toString());
                    nurse.setDepartment(editText_dept.getText().toString());

                    nurseViewModel.insert(nurse);

                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class );
                    startActivity(intent);

                }else{
                    Toast.makeText(CreateAccountActivity.this,"Passwords are not same.", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

    public void getList(View view) { nurseViewModel.getAllNurses(); }}
