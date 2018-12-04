package com.example.joost.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    EntryDatabase db;
    EntryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get entry database, cursor and entry adapter
        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();


        adapter = new EntryAdapter(this, cursor);


        // link adapter to listview
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new ListViewItemLongClick());
        listView.setOnItemClickListener(new ListViewItemClick());

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    public void FloatingActionClick(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    private void updateData() {
        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();

        adapter = new EntryAdapter(this, cursor);

        Cursor newCursor = db.selectAll();

        adapter.swapCursor(newCursor);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }


    private class ListViewItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            // get cursor and corresponding information to pass to JournalEntry
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String dateTime = cursor.getString(cursor.getColumnIndex("dateTime"));

            JournalEntry clickedEntry = new JournalEntry(_id, title, content, mood, dateTime);

            Log.d("hoi", clickedEntry.title);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("clicked_entry", clickedEntry);

            startActivity(intent);
        }
    }

    private class ListViewItemLongClick implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

            int index = cursor.getColumnIndex("_id");
            int columnID = cursor.getInt(index);

            db.deleteItem(columnID);

            updateData();

            return false;
        }
    }
}

