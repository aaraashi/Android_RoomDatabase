package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TestInfoActivity extends AppCompatActivity {

    private TextView tvNurseInfo;
    private TestViewModel testViewModel;
    private RecyclerView recyclerView;
    public static final int ADD_TEST_REQUEST = 1;
    public static final int EDIT_TEST_REQUEST = 2;

    Intent intentGet;
    public static final String EXTRA_PATIENTID = "hyongdonjeong_juheekim_comp304lab4.EXTRA_PATIENTID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info);

        recyclerView = findViewById(R.id.test_recycler_view);
        recyclerView.setHasFixedSize(true);

        final TestAdapter adapter = new TestAdapter();
        recyclerView.setAdapter(adapter);

        // display nurse information: first name (id)
        tvNurseInfo = (TextView) findViewById(R.id.textView_nurseInfo);
        SharedPreferences nursePreference = getSharedPreferences("NursePref", MODE_PRIVATE);
        tvNurseInfo.setText(nursePreference.getString("nurseIdString",""));

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        intentGet = getIntent();
        int patientId = Integer.parseInt(intentGet.getStringExtra(EXTRA_PATIENTID));
        tvNurseInfo.setText(tvNurseInfo.getText() + ":" + intentGet.getStringExtra(EXTRA_PATIENTID) );
        testViewModel.getPatientTests(patientId).observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(@Nullable List<Test> tests) {
                adapter.setTests(tests);
            }
        });

        adapter.setOnItemClickListener(new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Test test) {
                Intent intent = new Intent(TestInfoActivity.this, UpdateTestActivity.class);
                intent.putExtra(UpdateTestActivity.EXTRA_TESTID, test.getTestId());
                intent.putExtra(UpdateTestActivity.EXTRA_PATIENTID, String.valueOf(test.getPatientId()));
                intent.putExtra(UpdateTestActivity.EXTRA_T_NURSEID, String.valueOf(test.getNurseId()));
                intent.putExtra(UpdateTestActivity.EXTRA_BPL, String.valueOf(test.getBPL()));
                intent.putExtra(UpdateTestActivity.EXTRA_BPH, String.valueOf(test.getBPH()));
                intent.putExtra(UpdateTestActivity.EXTRA_TEMP, String.valueOf(test.getTemperature()));
                intent.putExtra(UpdateTestActivity.EXTRA_WEIGHT, String.valueOf(test.getWeight()));
                intent.putExtra(UpdateTestActivity.EXTRA_HEIGHT, String.valueOf(test.getHeight()));

                startActivityForResult(intent, EDIT_TEST_REQUEST);
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
                testViewModel.delete(adapter.getTestsAt(viewHolder.getAdapterPosition()));
                Toast.makeText(TestInfoActivity.this, "Test deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void nurseInfo (View v){
        Intent intent = new Intent(TestInfoActivity.this, UpdateNurseActivity.class );
        startActivity(intent);
    }

    public void addNewTest (View v){
        intentGet = getIntent();
        Intent intent = new Intent(TestInfoActivity.this, UpdateTestActivity.class );
        intent.putExtra(UpdateTestActivity.EXTRA_PATIENTID, intentGet.getStringExtra(EXTRA_PATIENTID));

        startActivity(intent);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(TestInfoActivity.this, WelcomeActivity.class );
        startActivity(intent);
    }

    public void displayToast(String message){
        Toast.makeText(TestInfoActivity.this, message, Toast.LENGTH_LONG).show();
    }

}
