package com.example.hyongdonjeong_juheekim_comp304lab4;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TestDao {
    @Insert
    void insert(Test test);

    @Update
    void update(Test test);

    @Delete
    void delete(Test test);

    @Query("Delete From Test")
    void deleteAllTest();

    @Query("Select * from Test")
    LiveData<List<Test>> getAllTests();

    @Query("Select * from Test where patientId = :patientId")
    LiveData<List<Test>> getPatientTests(int patientId);
//    @Query("SELECT * FROM Test WHERE TestId IN(:testId)")
//    public abstract List findByIds(int[] testId);
}
