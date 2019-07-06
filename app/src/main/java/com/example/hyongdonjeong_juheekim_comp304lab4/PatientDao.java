package com.example.hyongdonjeong_juheekim_comp304lab4;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {
    @Insert
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

    @Query("UPDATE Patient SET firstname=:firstName, lastname=:lastName, nurseId=:nurseID, department=:department, room=:room  WHERE patientId=:patientID")
    void updateRow(String firstName, String lastName, int nurseID, String department, String room, int patientID);

    @Query("Delete From Patient")
    void deleteAllPatient();

    @Query("Select * from Patient")
    LiveData<List<Patient>> getAllPatient();

//    @Query("SELECT * FROM Patient WHERE PatientId IN(:patientId)")
//    public abstract List findByIds(int[] patientId);
}
