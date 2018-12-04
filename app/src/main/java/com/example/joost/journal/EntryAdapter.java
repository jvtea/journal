package com.example.joost.journal;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {
//    Cursor cursor;

    public EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor);
    }

    // fills correct elements with data from cursor
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = view.findViewById(R.id.title);
        title.setText(cursor.getString(cursor.getColumnIndex("title")));

        TextView dateTime = view.findViewById(R.id.dateTime);
        dateTime.setText(cursor.getString(cursor.getColumnIndex("dateTime")));

        TextView mood = view.findViewById(R.id.mood);
        mood.setText(cursor.getString(cursor.getColumnIndex("mood")));
    }
}
