package com.example.kohler_final;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ItemCursorAdapter extends CursorAdapter {
    public ItemCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.todo_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView itemText = view.findViewById(R.id.item_text);
        TextView dateText = view.findViewById(R.id.date_text);
        CheckBox checkbox = view.findViewById(R.id.checkBox);

        itemText.setText(cursor.getString(cursor.getColumnIndex(DBHelper.ITEM_COL)));
        dateText.setText(cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COL)));
        checkbox.setChecked(true);
    }
}
