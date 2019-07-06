package com.example.hyongdonjeong_juheekim_comp304lab4;


import androidx.room.Dao;
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

//    @Query("SELECT * FROM Test WHERE TestId IN(:testId)")
//    public abstract List findByIds(int[] testId);
}
