package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UpdateTestActivity extends AppCompatActivity {

    private TextView tvNurseInfo;
    private TestViewModel testViewModel;
    private TestDao TestDao;
    private AppDatabase appDatabase;
    public static TextView textView_t_patient;
    public static EditText editText_t_nusre, editText_bpl, editText_bph, editText_temp, editText_weight, editText_height, editText_esl, editText_esr;
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

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_test);

        // display nurse information: first name (id)
        tvNurseInfo = (TextView) findViewById(R.id.textView_nurseInfo);
        SharedPreferences nursePreference = getSharedPreferences("NursePref", MODE_PRIVATE);
        tvNurseInfo.setText(nursePreference.getString("nurseIdString",""));

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        test = new Test();

        textView_t_patient = findViewById(R.id.testView_t_patientId);
        editText_t_nusre = findViewById(R.id.editText_t_nurseId);
        editText_bpl = findViewById(R.id.editText_t_BPL);
        editText_bph = findViewById(R.id.editText_t_BPH);
        editText_temp = findViewById(R.id.editText_t_temp);
        editText_weight = findViewById(R.id.editText_t_weight);
        editText_height = findViewById(R.id.editText_t_height);

        intent = getIntent();

        if (intent.hasExtra(EXTRA_TESTID)) {

            textView_t_patient.setText(intent.getStringExtra(EXTRA_PATIENTID));
            editText_t_nusre.setText(intent.getStringExtra(EXTRA_T_NURSEID));
            editText_bpl.setText(intent.getStringExtra(EXTRA_BPL));
            editText_bph.setText(intent.getStringExtra(EXTRA_BPH));
            editText_temp.setText(intent.getStringExtra(EXTRA_TEMP));
            editText_weight.setText(intent.getStringExtra(EXTRA_WEIGHT));
            editText_height.setText(intent.getStringExtra(EXTRA_HEIGHT));
        }else {
            textView_t_patient.setText(intent.getStringExtra(EXTRA_PATIENTID));
            editText_t_nusre.setText(nursePreference.getString("nurseIdString",""));

        }

        testViewModel.getInsertResult().observe(this, new Observer<Integer>() {
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

        test.setPatientId(Integer.parseInt(textView_t_patient.getText().toString()));
        test.setNurseId(editText_t_nusre.getText().toString());
        test.setBPL(Integer.parseInt(editText_bpl.getText().toString()));
        test.setBPH(Integer.parseInt(editText_bph.getText().toString()));
        test.setTemperature(Double.parseDouble(editText_temp.getText().toString()));
        test.setWeight(Double.parseDouble(editText_weight.getText().toString()));
        test.setHeight(Double.parseDouble(editText_height.getText().toString()));

        if (intent.hasExtra(EXTRA_TESTID)) {
            int id = getIntent().getIntExtra(EXTRA_TESTID, -1);
            test.setTestId(id);
            testViewModel.update(test);
        }
        else {
            testViewModel.insert(test);
        }

        displayToast("The test information has been saved!");

        Intent intent = new Intent(UpdateTestActivity.this, TestInfoActivity.class );
        intent.putExtra(TestInfoActivity.EXTRA_PATIENTID, textView_t_patient.getText().toString());
        startActivity(intent);

    }

    public void nurseInfo (View v){
        Intent intent = new Intent(UpdateTestActivity.this, UpdateNurseActivity.class );
        startActivity(intent);
    }

    public void deleteTest (View v) {

        int id = getIntent().getIntExtra(EXTRA_TESTID, -1);
        test.setTestId(id);
        testViewModel.delete(test);
        displayToast("Test deleted!");

        Intent intent = new Intent(UpdateTestActivity.this, TestInfoActivity.class );
        intent.putExtra(TestInfoActivity.EXTRA_PATIENTID, textView_t_patient.getText().toString());
        startActivity(intent);

    }

    public void backToMenu(View v) {
        Intent intent = new Intent(UpdateTestActivity.this, WelcomeActivity.class );
        startActivity(intent);
    }

    public void displayToast(String message){
        Toast.makeText(UpdateTestActivity.this, message, Toast.LENGTH_LONG).show();
    }

}
