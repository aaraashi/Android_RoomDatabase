package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.ListIterator;

public class NurseViewModel extends AndroidViewModel {

    private NurseRepository nurseRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Nurse>> allNurses;

    public NurseViewModel(@NonNull Application application){
        super(application);
        nurseRepository = new NurseRepository(application);
        insertResult = nurseRepository.getInsertResult();
        allNurses = nurseRepository.getAllNurses();
    }

    public void insert(Nurse nurse) { nurseRepository.insert(nurse); }

    public void update(Nurse nurse) { nurseRepository.update(nurse); }

    public LiveData<Integer> getInsertResult() { return insertResult; }

    LiveData<List<Nurse>> getAllNurses() { return allNurses; }

}
