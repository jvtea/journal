package com.example.joost.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.DropBoxManager;
import android.widget.EditText;

import java.util.Map;


public class EntryDatabase extends SQLiteOpenHelper {

    // ZORG DAT JE VERSION NOG KAN BLIJVEN VARIEREN!!
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "entries";


//    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    private EntryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static EntryDatabase instance;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableSQL =   "create table entries ( _id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, " +
                            "content TEXT, mood TEXT, dateTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(tableSQL);

        // make sample entry
        ContentValues values = new ContentValues();
        values.put("title", "Leuke dag");
        values.put("content", "Echt een leuke dag. HAHAHAHAHA");
        values.put("mood", "TE GEK");
        db.insert("entries",null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ENTRIES");
        onCreate(db);
    }

    // returns instance of SQL table
    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new EntryDatabase(context);
            return instance;
        }
    }


    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM entries", null);
        return cursor;
    }

    public void insertItem (JournalEntry entry) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        // put values from entry object
        values.put("title", entry.getTitle());
        values.put("content", entry.getContent());
        values.put("mood", entry.getMood());

        db.insert("entries",null, values);
    }

    public void deleteItem (long id) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM entries WHERE _id = " + id);
//        db.delete(entries, id + " = ?",new String[]{Long.toString(id)} );
//        db.delete
    }

}



