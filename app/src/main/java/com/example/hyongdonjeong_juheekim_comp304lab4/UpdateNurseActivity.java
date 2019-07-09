package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateNurseActivity extends AppCompatActivity {
    private NurseViewModel nurseViewModel;
    private NurseDao nurseDao;
    private AppDatabase appDatabase;
    private TextView textView_id;
    public static EditText editText_fname, editText_lname, editText_password1, editText_password2, editText_dept;
    Nurse nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nurse);

        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);
        nurse = new Nurse();

        textView_id = findViewById(R.id.textView_userid);
        editText_fname = findViewById(R.id.editText_fname);
        editText_lname = findViewById(R.id.editText_lname);
        editText_password1 = findViewById(R.id.editText_password1);
        editText_password2 = findViewById(R.id.editText_password2);
        editText_dept = findViewById(R.id.editText_dept);

        // display nurse information: first name (id)
        SharedPreferences nursePreference = getSharedPreferences("NursePref", MODE_PRIVATE);
        textView_id.setText(nursePreference.getString("nurseIdString",""));
        editText_fname.setText(nursePreference.getString("nurseFNameString",""));
        editText_lname.setText(nursePreference.getString("nurseLNameString",""));
        editText_dept.setText(nursePreference.getString("nurseDeptString",""));

        nurseViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(UpdateNurseActivity.this, "Nurse successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateNurseActivity.this, "Error saving nurse", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void updateNurse (View v){

        if (!emptyValidation()) {

            SharedPreferences nursePreference = getSharedPreferences("NursePref", MODE_PRIVATE);
            String nurseId = nursePreference.getString("nurseIdString", "");

            if (editText_password1.getText().toString().equals(editText_password2.getText().toString())) {
                nurse.setFirstname(editText_fname.getText().toString());
                nurse.setLastname(editText_lname.getText().toString());
                nurse.setDepartment(editText_dept.getText().toString());
                nurse.setPassword(editText_password1.getText().toString());
                nurse.setNurseId(nurseId);

                displayToast("nurseId:" + nurseId + "Firstname:" + editText_fname.getText().toString()
                        + "Lastname:" + editText_lname.getText().toString()
                        + "Department:" + editText_dept.getText().toString()
                        + "Password:" + editText_password1.getText().toString());

                nurseViewModel.update(nurse);
                displayToast("The nurse information has been updated!");

                Intent intent = new Intent(UpdateNurseActivity.this, WelcomeActivity.class);
                startActivity(intent);

            } else {
                displayToast("Passwords are not same!");
                return;
            }
        }else{
            displayToast("Empty Fields!");
        }
    }

    public void displayToast(String message){
        Toast.makeText(UpdateNurseActivity.this, message, Toast.LENGTH_LONG).show();
    }

    // nurse id and password empty validation
    private boolean emptyValidation() {
        if (TextUtils.isEmpty(editText_password1.getText().toString()) || TextUtils.isEmpty(editText_password2.getText().toString())
        || TextUtils.isEmpty(editText_fname.getText().toString()) || TextUtils.isEmpty(editText_lname.getText().toString())
        || TextUtils.isEmpty(editText_dept.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}
