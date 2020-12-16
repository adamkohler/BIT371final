package com.example.kohler_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class MainActivity extends AppCompatActivity {

    private ListView ToDoList;
    private ArrayList<String> data;
    private int boxLocation;
    private int done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, CreateItemActivity.class);
        FloatingActionButton fab = findViewById(R.id.add_item_btn);
        fab.setOnClickListener((view) -> startActivity(intent));
        ToDoList = findViewById(R.id.Item_list);
        ToDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox cb = (CheckBox) view.findViewById(R.id.position);
                // use int i + 1 for location of click
                //Log.i("INFO", "CheckedBox line " + l + " pressed value for 'l'");
                //Log.i("INFO", "CheckBox line " + i + " pressed value for 'i'");
                boxLocation = i + 1;
            }
        });
        DBHelper db = new DBHelper(this, DBHelper.DATABASE_NAME, null, 1);
        db.setDone(boxLocation, true);
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

        ToDoList = findViewById(R.id.Item_list);
        ToDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                boxLocation = i + 1;

                if (cursor.moveToPosition(i))
                {
                    done = cursor.getInt(cursor.getColumnIndex("done"));
                    //since cursor starts -1 then the first item in the table is 0? seems weird.
                    //Log.i("INFO", "Record "+ i + " has done value of " + done);
                }
                if (done == 0)
                    db.setDone(boxLocation, true);
                else
                    db.setDone(boxLocation, false);
            }
        });
    }
}
    /*@Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("d", data);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            data = (ArrayList<String>) savedInstanceState.getSerializable("d");
            ItemCursorAdapter adapter = new ItemCursorAdapter(this, data, true);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }*/
