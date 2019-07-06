package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class UpdateTestActivity extends AppCompatActivity {

    private TestViewModel TestViewModel;
    private TestDao TestDao;
    private AppDatabase appDatabase;
    public static EditText editText_t_patient, editText_t_nusre, editText_bpl, editText_bph, editText_temp, editText_weight, editText_height, editText_esl, editText_esr;
    Test test;
    Intent intent;

    public static final String EXTRA_TESTID = "hyongdonjeong_juheekim_comp304lab4.EXTRA_TESTID";
    public static final String EXTRA_PATIENTID = "hyongdonjeong_juheekim_comp304lab4.EXTRA_PATIENTID";
    public static final String EXTRA_T_NURSEID = "hyongdonjeong_juheekim_comp304lab4.EXTRA_T_NURSEID";
    public static final String EXTRA_BPL = "hyongdonjeong_juheekim_comp304lab4.EXTRA_BPL";
    public static final String EXTRA_BPH = "hyongdonjeong_juheekim_comp304lab4.EXTRA_BPH";
    public static final String EXTRA_TEMP = "hyongdonjeong_juheekim_comp304lab4.EXTRA_TEMP";
    public static final String EXTRA_WEIGHT = "hyongdonjeong_juheekim_comp304lab4.EXTRA_WEIGHT";
    public static final String EXTRA_HEIGHT = "hyongdonjeong_juheekim_comp304lab4.EXTRA_HEIGHT";
    public static final String EXTRA_ESL = "hyongdonjeong_juheekim_comp304lab4.EXTRA_ESL";
    public static final String EXTRA_ESR = "hyongdonjeong_juheekim_comp304lab4.EXTRA_ESR";

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_test);

        TestViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        test = new Test();

        editText_t_patient = findViewById(R.id.editText_t_patientId);
        editText_t_nusre = findViewById(R.id.editText_t_nurseId);
        editText_bpl = findViewById(R.id.editText_t_BPL);
        editText_bph = findViewById(R.id.editText_t_BPH);
        editText_temp = findViewById(R.id.editText_t_temp);
        editText_weight = findViewById(R.id.editText_t_weight);
        editText_height = findViewById(R.id.editText_t_height);
        editText_esl = findViewById(R.id.editText_t_ESL);
        editText_esr = findViewById(R.id.editText_t_ESR);

        intent = getIntent();

        if (intent.hasExtra(EXTRA_TESTID)) {

            editText_t_patient.setText(intent.getStringExtra(EXTRA_PATIENTID));
            editText_t_nusre.setText(intent.getStringExtra(EXTRA_T_NURSEID));
            editText_bpl.setText(intent.getStringExtra(EXTRA_BPL));
            editText_bph.setText(intent.getStringExtra(EXTRA_BPH));
            editText_temp.setText(intent.getStringExtra(EXTRA_TEMP));
            editText_weight.setText(intent.getStringExtra(EXTRA_WEIGHT));
            editText_height.setText(intent.getStringExtra(EXTRA_HEIGHT));
            editText_esl.setText(intent.getStringExtra(EXTRA_ESL));
            editText_esr.setText(intent.getStringExtra(EXTRA_ESR));

        } else {
        }

        TestViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(UpdateTestActivity.this, "Test successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateTestActivity.this, "Error saving Test", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TestViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(@Nullable List<Test> result) {
                String output = "";
                for (Test Test : result) {
                    //output += Test.getFirstname() + "\n";
                }
                //textViewDisplay.setText(output);
            }
        });

        TestViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(UpdateTestActivity.this, "Test successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateTestActivity.this, "Error saving Test", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void updateTest (View v){

        test.setPatientId(Integer.parseInt(editText_t_patient.getText().toString()));
        test.setNurseId(Integer.parseInt(editText_t_nusre.getText().toString()));
        test.setBPL(Integer.parseInt(editText_bpl.getText().toString()));
        test.setBPH(Integer.parseInt(editText_bph.getText().toString()));
        test.setTemperature(Double.parseDouble(editText_temp.getText().toString()));
        test.setWeight(Double.parseDouble(editText_weight.getText().toString()));
        test.setHeight(Double.parseDouble(editText_height.getText().toString()));
        test.setESL(Double.parseDouble(editText_esl.getText().toString()));
        test.setESR(Double.parseDouble(editText_esr.getText().toString()));
        TestViewModel.insert(test);

//        if (intent.hasExtra(EXTRA_TESTID)) {
//            //TestDao.updateRow(editText_fname.getText().toString(), editText_lname.getText().toString(), Integer.parseInt(editText_p_nurse.getText().toString()), editText_p_dept.getText().toString(), editText_p_room.getText().toString(), Integer.parseInt(intent.getStringExtra(EXTRA_ID)));
//            TestViewModel.update(test);
//        }
//        else {
//            TestViewModel.insert(test);
//        }

        //displayToast("The Test information has been updated!");
    }

    public void deleteTest (View v) {

//        test.setPatientId(Integer.parseInt(editText_t_patient.getText().toString()));
//        test.setNurseId(Integer.parseInt(editText_t_nusre.getText().toString()));
//        test.setBPL(Integer.parseInt(editText_bpl.getText().toString()));
//        test.setBPH(Integer.parseInt(editText_bph.getText().toString()));
//        test.setTemperature(Double.parseDouble(editText_temp.getText().toString()));
//        test.setWeight(Double.parseDouble(editText_weight.getText().toString()));
//        test.setHeight(Double.parseDouble(editText_height.getText().toString()));
//        test.setESL(Double.parseDouble(editText_esl.getText().toString()));
//        test.setESR(Double.parseDouble(editText_esr.getText().toString()));
        TestViewModel.deleteAllTest();
    }

    public void displayToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
