package com.example.hyongdonjeong_juheekim_comp304lab4;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Nurse.class, Patient.class, Test.class},version =1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "PatientDB.db";
    public abstract NurseDao nurseDao();
    public abstract PatientDao patientDao();
    public abstract TestDao testDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME).addCallback(roomCallback).build();
//                AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(roomCallback).build();
            //populateData();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PatientDao patientDao;
        private Patient patient;
        private TestDao testDao;
        private Test test;

        private PopulateDbAsyncTask(AppDatabase db) {
            patientDao = db.patientDao();
            testDao = db.testDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            patientDao.insert(new Patient(patient.getPatientId(), "Chalie", "Mack", "Gen", "n200", "301"));
//            patientDao.insert(new Patient(patient.getPatientId(),"Kate", "Lynn", "Gen", "n200", "202"));
//            patientDao.insert(new Patient(patient.getPatientId(),"Jason", "Kim", "Eem", "n400", "502"));
//            testDao.insert(new Test(test.getTestId(), 2, "n200", 80, 120, 35.2, 6.2, 7.1));
//            testDao.insert(new Test(test.getTestId(), 3, "n200", 80, 120, 35.2, 6.2, 7.1));
            return null;
        }

    }


}
