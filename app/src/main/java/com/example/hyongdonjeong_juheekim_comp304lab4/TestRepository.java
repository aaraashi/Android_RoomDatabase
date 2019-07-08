package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TestRepository {
    private final TestDao testDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    public TestRepository(TestDao testDao) {
        this.testDao = testDao;
    }
    private LiveData<List<Test>> allTests;
    private LiveData<List<Test>> patientTests;
    private int patientId;

    public TestRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        testDao = db.testDao();
        allTests = testDao.getAllTests();
        patientTests = testDao.getPatientTests(patientId);
    }

    public LiveData<List<Test>> getAllTests() { return allTests; }

    public LiveData<List<Test>> getPatientTests(int patientId) {
        patientTests = testDao.getPatientTests(patientId);
        return patientTests;
    }

    public void insert(Test test) {
        new TestRepository.InsertTestAsyncTask(testDao).execute(test);
    }

    public void update(Test test) { new TestRepository.UpdateTestAsyncTask(testDao).execute(test);}

    public void delete(Test test) { new TestRepository.DeleteTestAsyncTask(testDao).execute(test);}

    public void deleteAllTest() { new TestRepository.DeleteAllTestAsyncTask(testDao).execute();}

    public LiveData<Integer> getInsertResult() { return insertResult; }

    private void insertAsync(final Test test){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testDao.insert(test);
                    insertResult.postValue(1);
                } catch (Exception e){
                    insertResult.postValue(0);
                }

            }
        }).start();
    }

    private static class InsertTestAsyncTask extends AsyncTask<Test, Void, Void> {
        private TestDao testDao;

        private InsertTestAsyncTask(TestDao testDao) {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... tests) {
            testDao.insert(tests[0]);
            return null;
        }
    }

    private static class UpdateTestAsyncTask extends AsyncTask<Test, Void, Void> {
        private TestDao testDao;

        private UpdateTestAsyncTask(TestDao testDao) {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... tests) {
            testDao.update(tests[0]);
            return null;
        }
    }

    private static class DeleteTestAsyncTask extends AsyncTask<Test, Void, Void> {
        private TestDao testDao;

        private DeleteTestAsyncTask(TestDao testDao) {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... tests) {
            testDao.delete(tests[0]);
            return null;
        }
    }

    private static class DeleteAllTestAsyncTask extends AsyncTask<Void, Void, Void> {
        private TestDao testDao;

        private DeleteAllTestAsyncTask(TestDao testDao) {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            testDao.deleteAllTest();
            return null;
        }
    }
}
