package com.example.kohler_final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class CreateItemActivity extends AppCompatActivity {

    EditText date;
    EditText item;
    DatePickerDialog datePickerD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        Button createbtn = findViewById(R.id.create_btn);
        item = findViewById(R.id.item_name);
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerD = new DatePickerDialog(CreateItemActivity.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, mYear, mMonth, mDay);
                datePickerD.show();
            }
        });

        //Add stuff to database here?
        DBHelper dbhelper = new DBHelper(this, DBHelper.DATABASE_NAME, null, 1);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        createbtn.setOnClickListener((view) -> {
            String ItemString = item.getText().toString();
            String DateString = date.getText().toString();
            if (TextUtils.isEmpty(ItemString) || TextUtils.isEmpty(DateString)){
                Log.i("INFO", "Empty Value");
                return;
            }
            Log.i("INFO", String.format("Saving Item %s, Date %s", ItemString, DateString));

            dbhelper.insertItem(ItemString, DateString);
            Toast.makeText(this, "Item Created", Toast.LENGTH_LONG).show();
            item.getText().clear();
            date.getText().clear();
        });


        //Return to MainActivity
    }
}