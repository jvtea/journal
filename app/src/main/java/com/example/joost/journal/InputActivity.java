package com.example.joost.journal;

import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {


    private String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }


    public void addEntry (View view){
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

        EditText title = findViewById(R.id.editTitle);
        EditText content = findViewById(R.id.editContent);

        String titleText = title.getText().toString();
        String contentText = content.getText().toString();

        JournalEntry entry = new JournalEntry(titleText, contentText, mood);

        db.insertItem(entry);

        finish();

    }

    public void MoodPick(View view) {

        switch (view.getId()) {
            case R.id.great:
                mood = "great";
                break;
            case R.id.happy:
                mood = "happy";
                break;
            case R.id.sad:
                mood = "sad";
                break;
            case R.id.miserable:
                mood = "miserable";
                break;
        }

        TextView moodText = findViewById(R.id.moodText);

        moodText.setText("Mood set to " + mood + ".");

    }


}
