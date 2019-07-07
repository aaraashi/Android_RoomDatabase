package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class TestInfoActivity extends AppCompatActivity {

    private TestViewModel testViewModel;
    private RecyclerView recyclerView;
    public static final int ADD_TEST_REQUEST = 1;
    public static final int EDIT_TEST_REQUEST = 2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info);


        recyclerView = findViewById(R.id.test_recycler_view);
        recyclerView.setHasFixedSize(true);

        final TestAdapter adapter = new TestAdapter();
        recyclerView.setAdapter(adapter);

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
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
                intent.putExtra(UpdateTestActivity.EXTRA_BPH, String.valueOf(test.getNurseId()));
                intent.putExtra(UpdateTestActivity.EXTRA_TEMP, String.valueOf(test.getTemperature()));
                intent.putExtra(UpdateTestActivity.EXTRA_WEIGHT, String.valueOf(test.getWeight()));
                intent.putExtra(UpdateTestActivity.EXTRA_HEIGHT, String.valueOf(test.getHeight()));
                intent.putExtra(UpdateTestActivity.EXTRA_ESL, String.valueOf(test.getESL()));
                intent.putExtra(UpdateTestActivity.EXTRA_ESR, String.valueOf(test.getESR()));
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

    public void addNewTest (View v){
        Intent intent = new Intent(TestInfoActivity.this, UpdateTestActivity.class );
        startActivity(intent);
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == ADD_TEST_REQUEST && resultCode == RESULT_OK) {
//            int testId = data.getIntExtra(UpdateTestActivity.EXTRA_TESTID, 1);
//            int patientId = data.getIntExtra(UpdateTestActivity.EXTRA_PATIENTID, 1);
//            int nurseId = data.getIntExtra(UpdateTestActivity.EXTRA_T_NURSEID, 1);
//            int BPL = data.getIntExtra(UpdateTestActivity.EXTRA_BPL, 1);
//            int BPH = data.getIntExtra(UpdateTestActivity.EXTRA_BPH, 1);
//            double temp = data.getDoubleExtra(UpdateTestActivity.EXTRA_TEMP, 1.0);
//            double weight = data.getDoubleExtra(UpdateTestActivity.EXTRA_WEIGHT, 1.0);
//            double height = data.getDoubleExtra(UpdateTestActivity.EXTRA_HEIGHT, 1.0);
//            double ESL = data.getDoubleExtra(UpdateTestActivity.EXTRA_ESL, 1.0);
//            double ESR = data.getDoubleExtra(UpdateTestActivity.EXTRA_ESR, 1.0);
//
//            Test test = new Test(patientId, nurseId, BPL, BPH, temp, weight, height, ESL, ESR);
//            testViewModel.insert(test);
//
//            Toast.makeText(this, "Test saved", Toast.LENGTH_SHORT).show();
//
//        } else if (requestCode == EDIT_TEST_REQUEST && resultCode == RESULT_OK) {
//            int id = data.getIntExtra(UpdateTestActivity.EXTRA_TESTID, -1);
//
//            if (id == -1) {
//                Toast.makeText(this, "Test can't be updated", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            //int testId = data.getIntExtra(UpdateTestActivity.EXTRA_TESTID, 1);
//            int patientId = data.getIntExtra(UpdateTestActivity.EXTRA_PATIENTID, 1);
//            int nurseId = data.getIntExtra(UpdateTestActivity.EXTRA_T_NURSEID, 1);
//            int BPL = data.getIntExtra(UpdateTestActivity.EXTRA_BPL, 1);
//            int BPH = data.getIntExtra(UpdateTestActivity.EXTRA_BPH, 1);
//            double temp = data.getDoubleExtra(UpdateTestActivity.EXTRA_TEMP, 1.0);
//            double weight = data.getDoubleExtra(UpdateTestActivity.EXTRA_WEIGHT, 1.0);
//            double height = data.getDoubleExtra(UpdateTestActivity.EXTRA_HEIGHT, 1.0);
//            double ESL = data.getDoubleExtra(UpdateTestActivity.EXTRA_ESL, 1.0);
//            double ESR = data.getDoubleExtra(UpdateTestActivity.EXTRA_ESR, 1.0);
//
//            Test test = new Test(patientId, nurseId, BPL, BPH, temp, weight, height, ESL, ESR);
//
//            test.setTestId(id);
//            testViewModel.update(test);
//
//            Toast.makeText(this, "Test updated", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Test not saved", Toast.LENGTH_SHORT).show();
//        }
//    }
}
