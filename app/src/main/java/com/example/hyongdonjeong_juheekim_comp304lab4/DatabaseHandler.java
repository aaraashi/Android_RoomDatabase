package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler {

    //Variables
    public static final String PATIENTID="ID";
    public static final String FIRSTNAME="FirstName";
    public static final String LASTNAME="LastName";
    public static final String DEPT="Dept";
    public static final String NURSEID="NurseID";
    public static final String ROOM="Room";
    public static final String TABLE_NAME="Patient";
    public static final String DATABASE_NAME="PatientTestDB";
    public static final int DATABASE_VERSION=1;
    //Table query
    public static final String TABLE_CREATE="create table Patient(ID text not null, FirstName text not null, LastName text not null);";
    DataBaseHelper dbhelper;
    Context context;
    SQLiteDatabase db;
    public DatabaseHandler(Context ctx)
    {
        this.context=ctx;
        dbhelper=new DataBaseHelper(context);
    }
    private static class DataBaseHelper extends SQLiteOpenHelper
    {
        //Create a helper object to create, open, and/or manage a database.
        public DataBaseHelper(Context ctx)
        {
            super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
        }
        @Override
        //Called when the database is created for the first time.
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(TABLE_CREATE);//Here create a table
        }
        @Override
        //Called when the database needs to be upgraded.
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Patient");
            onCreate(db);
        }
    }
    public DatabaseHandler open()
    {
        //Create and/or open a database that will be used for reading and writing.
        db=dbhelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        //Close any open database object.
        dbhelper.close();
    }
    //Insert record in the database
    public long InsertData(String ID, String FirstName, String LastName, String Dept, String NurseID, String Room )
    {
        //This class is used to store a set of values
        ContentValues content = new ContentValues();
        content.put(PATIENTID, ID);
        content.put(FIRSTNAME, FirstName);
        content.put(LASTNAME, LastName);
        content.put(DEPT, Dept);
        content.put(NURSEID, NurseID);
        content.put(ROOM, Room);
        //Adds a value to the set.
        return db.insertOrThrow(TABLE_NAME,null, content);
    }
    //Display record from the database
    public Cursor DisplayData()
    {
        //Select query
        return db.rawQuery("SELECT * FROM Patient", null);
    }
}

