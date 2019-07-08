package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository patientRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Patient>> allPatients, patients;
    private String nurseId;
    //
    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        insertResult = patientRepository.getInsertResult();
        allPatients = patientRepository.getAllPatient();
        patients = patientRepository.getPatients(nurseId);
    }
    //
    public void insert(Patient patient) { patientRepository.insert(patient); }

    public void update(Patient patient) { patientRepository.update(patient); }

    public void delete(Patient patient) { patientRepository.delete(patient); }

    public void deleteAllPatient() { patientRepository.deleteAllPatient(); }

    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    LiveData<List<Patient>> getAllPatient() { return allPatients; }

    LiveData<List<Patient>> getPatients(String nurseId) {
        patients = patientRepository.getPatients(nurseId);
        return patients;
    }
}
