package com.example.kohler_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Things";
    public static final String TABLE_NAME = "ToDoList";
    public static final String DONE_COL = "done";
    public static final String ITEM_COL = "item";
    public static final String DATE_COL = "date";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DBHelper.TABLE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBHelper.DONE_COL + " INTEGER," +
                DBHelper.ITEM_COL + " TEXT," +
                DBHelper.DATE_COL + " TEXT" +
                ")");
    }

    public void setDone(int id, boolean done) {
        ContentValues values = new ContentValues();
        values.put(DONE_COL, done);
        getWritableDatabase().update(TABLE_NAME, values, "_id=?", new String[]{Integer.toString(id)});
    }

    public int insertItem(String item, String date) {
        ContentValues values = new ContentValues();
        values.put(ITEM_COL, item);
        values.put(DATE_COL, date);
        values.put(DONE_COL, false);
        return (int) getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
