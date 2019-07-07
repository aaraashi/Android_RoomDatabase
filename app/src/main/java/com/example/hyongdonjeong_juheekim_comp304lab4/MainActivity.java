package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private NurseDao nurseDao;
    private EditText etNurseId, etPassword;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(this, AppDatabase.class, "PatientDB.db")
                .allowMainThreadQueries()
                .build();
        nurseDao = db.nurseDao();

        etNurseId = (EditText) findViewById(R.id.editText_username);
        etPassword = (EditText) findViewById(R.id.editText_password);
        btLogin = (Button) findViewById(R.id.button_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            try {
                                Nurse nurse = nurseDao.getNurse(etNurseId.getText().toString(), etPassword.getText().toString());
                                if(nurse!=null){

                                    SharedPreferences nursePreference = getSharedPreferences("NursePref", MODE_PRIVATE);
                                    SharedPreferences.Editor prefEditor = nursePreference.edit();
                                    prefEditor.putString("nurseIdString",etNurseId.getText().toString());
                                    prefEditor.putString("nurseIdString", nurse.getNurseId());
                                    prefEditor.putString("nurseFNameString", nurse.getFirstname());
                                    prefEditor.putString("nurseLNameString", nurse.getLastname());
                                    prefEditor.putString("nursePasswordString", nurse.getPassword());
                                    prefEditor.putString("nurseDeptString", nurse.getDepartment());
                                    prefEditor.commit();

                                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class );
                                    startActivity(intent);
                                    finish();

                                }else{
                                    Toast.makeText(MainActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                                }
//                            } catch (Exception e){
//
//                                Toast.makeText(MainActivity.this, "Nurser ID should be number.", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    }, 1000);

                }else{
                    Toast.makeText(MainActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // create new account of nurse
    public void register (View v){
        Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class );
        startActivity(intent);
    }

    // nurse id and password empty validation
    private boolean emptyValidation() {
        if (TextUtils.isEmpty(etNurseId.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }


}
