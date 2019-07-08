package com.example.hyongdonjeong_juheekim_comp304lab4;
import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class TestViewModel extends AndroidViewModel {

    private TestRepository testRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Test>> allTests;
    private LiveData<List<Test>> patientTests;
    private int patientId;

    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        insertResult = testRepository.getInsertResult();
        allTests = testRepository.getAllTests();
        patientTests = testRepository.getPatientTests(patientId);
    }
    //
    public void insert(Test test) { testRepository.insert(test); }

    public void update(Test test) { testRepository.update(test); }

    public void delete(Test test) { testRepository.delete(test); }

    public void deleteAllTest() { testRepository.deleteAllTest(); }

    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public LiveData<List<Test>> getAllTests() { return allTests; }

    public LiveData<List<Test>> getPatientTests(int patientId) {
        patientTests = testRepository.getPatientTests(patientId);
        return patientTests;
    }

    public void setResult(int resultOk, Intent data) {
    }
}
