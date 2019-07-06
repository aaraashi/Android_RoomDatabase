package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class NurseRepository {

    private final NurseDao nurseDao;

    public NurseRepository(NurseDao nurseDao) {
        this.nurseDao = nurseDao;
    }

    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Nurse>> nurseList;

    public NurseRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        nurseDao = db.nurseDao();
        nurseList = nurseDao.getAllNurses();
    }

    LiveData<List<Nurse>> getAllNurses() { return nurseList; }

    public void insert(Nurse nurse) { insertAsync(nurse); }

    public LiveData<Integer> getInsertResult() { return insertResult; }

    private void insertAsync(final Nurse nurse){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nurseDao.insert(nurse);
                    insertResult.postValue(1);
                } catch (Exception e){
                    insertResult.postValue(0);
                }

            }
        }).start();
    }

 }
