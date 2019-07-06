package com.example.hyongdonjeong_juheekim_comp304lab4;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class TestViewModel extends AndroidViewModel {

    private TestRepository TestRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Test>> allTests;


    public TestViewModel(@NonNull Application application) {
        super(application);
        TestRepository = new TestRepository(application);
        insertResult = TestRepository.getInsertResult();
        allTests = TestRepository.getAllTests();
    }
    //
    public void insert(Test test) { TestRepository.insert(test); }

    public void update(Test test) { TestRepository.update(test); }

    public void delete(Test test) { TestRepository.delete(test); }

    public void deleteAllTest() { TestRepository.deleteAllTest(); }

    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    LiveData<List<Test>> getAllTests() { return allTests; }
}
