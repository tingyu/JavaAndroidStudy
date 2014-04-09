package com.example.studrecordapp;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseConnector {

    // database name
    private static final String DATABASE_NAME = "StudRecords";
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper

    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context) {
        // create a new DatabaseOpenHelper
        databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME,
                null, 1);
    } // end DatabaseConnector constructor

    // open the database connection
    public void open() throws SQLException {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public void close() {
        if (database != null)
            database.close(); // close the database connection
    } // end method close

    // inserts a new record of student score in the database
    public void insertRecord(int studId, int q1, int q2, int q3, int q4, int q5) {
        ContentValues newRecord = new ContentValues();
        newRecord.put("studId", studId);
        newRecord.put("q1", q1);
        newRecord.put("q2", q2);
        newRecord.put("q3", q3);
        newRecord.put("q4", q4);
        newRecord.put("q5", q5);

        open(); // open the database
        database.insert("records", null, newRecord);
        close(); // close the database
    } // end method insertContact

    // inserts a new record in the database
    public void updateContact(int studId, int q1, int q2, int q3, int q4, int q5) {
        ContentValues editRecord = new ContentValues();
        // editRecord.put("studId", studId);
        editRecord.put("q1", q1);
        editRecord.put("q2", q2);
        editRecord.put("q3", q3);
        editRecord.put("q4", q4);
        editRecord.put("q5", q5);

        open(); // open the database
        database.update("records", editRecord, "studId=" + studId, null);
        close(); // close the database
    } // end method updateRecords

    // return a Cursor with all records information in the database
    public Cursor getAllRecords() {
        return database.query("records", new String[] { "studId", "q1", "q2",
                "q3", "q4", "q5" }, null, null, null, null, null, null);
    } // end method getAllRecords

    // get a Cursor containing all information about the contact specified
    // by the given id
    public Cursor getOneRecord(long id) {
        return database.query("records", null, "studId=" + id, null, null,
                null, null, null);
    } // end method getOnContact

    // delete the contact specified by the given String name
    public void deleteRecord(long id) {
        open(); // open the database
        database.delete("records", "studId=" + id, null);
        close(); // close the database
    } // end method deleteContact

    int getHighScore(int i) {
        open(); // open the database

        Cursor cursor = database.query("records", new String[] { "MAX(q" + i + ")" }, 
        null, null, null, null, null);
        try {
        cursor.moveToFirst();
        return cursor.getInt(0);
        } finally {
        cursor.close();
        }
    }
    
    public int getLowScore(int i) {
        open(); // open the database

        Cursor cursor = database.query("records", new String[] { "min(q" + i + ")" }, 
        null, null, null, null, null);
        try {
        cursor.moveToFirst();
        return cursor.getInt(0);
        } finally {
        cursor.close();
        }
    }
    
    public int getAvgScore(int i) {
        open(); // open the database
        Cursor cursor = database.query("records", new String[] { "avg(q" + i + ")" }, 
        null, null, null, null, null);
        try {
        cursor.moveToFirst();
        return cursor.getInt(0);
        } finally {
        cursor.close();
        }
    }
    
    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                CursorFactory factory, int version) {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the records table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
            // query to create a new table named contacts
            String createQuery = "CREATE TABLE records"
                    + "(id integer primary key autoincrement,"
                    + "studId integer,"
                    + "q1 integer, q2 integer, q3 integer,"
                    + "q4 integer, q5 integer);";

            db.execSQL(createQuery); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        } // end method onUpgrade
    } // end class DatabaseOpenHelper
}
