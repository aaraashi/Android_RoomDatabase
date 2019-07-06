package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NurseDao {
    @Insert
    void insert(Nurse nurse);

    @Update
    void update(Nurse nurse);

//    @Query("SELECT * FROM Nurse WHERE NurseId IN(:nurseId)")
//    public abstract List findByIds(int[] nurseId);

    @Query("Select * from Nurse")
    LiveData<List<Nurse>> getAllNurses();
}
