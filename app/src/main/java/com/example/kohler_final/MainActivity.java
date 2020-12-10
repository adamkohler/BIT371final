package com.example.kohler_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    }
}