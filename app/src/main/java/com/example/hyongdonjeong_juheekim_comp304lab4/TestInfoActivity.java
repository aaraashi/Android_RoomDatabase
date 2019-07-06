package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class TestInfoActivity extends AppCompatActivity {

    private TestViewModel TestViewModel;
    private RecyclerView recyclerView;
    public static final int ADD_Test_REQUEST = 1;
    public static final int EDIT_Test_REQUEST = 2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info);
        recyclerView = findViewById(R.id.test_recycler_view);
        recyclerView.setHasFixedSize(true);

        final TestAdapter adapter = new TestAdapter();
        recyclerView.setAdapter(adapter);

        TestViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        TestViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(@Nullable List<Test> tests) {
                adapter.setTests(tests);
            }
        });

//        adapter.setOnItemClickListener(new TestAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Test Test) {
//                Intent intent = new Intent(TestInfoActivity.this, UpdateTestActivity.class);
//                intent.putExtra(UpdateTestActivity.EXTRA_ID, String.valueOf(Test.getTestId()));
//                intent.putExtra(UpdateTestActivity.EXTRA_FNAME, Test.getFirstname());
//                intent.putExtra(UpdateTestActivity.EXTRA_LNAME, Test.getLastname());
//                intent.putExtra(UpdateTestActivity.EXTRA_NURSEID, String.valueOf(Test.getNurseId()));
//                intent.putExtra(UpdateTestActivity.EXTRA_DEPT, Test.getDepartment());
//                intent.putExtra(UpdateTestActivity.EXTRA_ROOM, Test.getRoom());
//                startActivityForResult(intent, EDIT_Test_REQUEST);
//            }
//        });
    }


    public void addNewTest (View v){
        Intent intent = new Intent(TestInfoActivity.this, UpdateTestActivity.class );
        startActivity(intent);
    }
}
