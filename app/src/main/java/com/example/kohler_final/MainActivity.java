package com.example.kohler_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ListView ToDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, CreateItemActivity.class);
        FloatingActionButton fab = findViewById(R.id.add_item_btn);
        fab.setOnClickListener((view) -> startActivity(intent));
        ToDoList = findViewById(R.id.Item_list);
    }

    //On Resume view function? After the db has been updated with new to do item?
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper db = new DBHelper(this, DBHelper.DATABASE_NAME, null, 1);
        SQLiteDatabase reader = db.getReadableDatabase();
        String[] columns = {"_id", DBHelper.DONE_COL, DBHelper.ITEM_COL, DBHelper.DATE_COL};
        Cursor cursor = reader.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        ItemCursorAdapter cursorAdapter = new ItemCursorAdapter(this, cursor, true);
        ToDoList.setAdapter(cursorAdapter);
    }
}