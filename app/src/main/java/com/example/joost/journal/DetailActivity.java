package com.example.joost.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        JournalEntry retrievedEntry = (JournalEntry) intent.getSerializableExtra("clicked_entry");

        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.content);
        ImageView moodImage = findViewById(R.id.moodImage);
        TextView dateTime = findViewById(R.id.dateTime);

        title.setText(retrievedEntry.title);
        content.setText(retrievedEntry.content);
        dateTime.setText(retrievedEntry.dateTime);

        int drawableID;

        switch (retrievedEntry.mood) {
            case "great":
                drawableID = getResources().getIdentifier("great", "drawable", getPackageName());
                moodImage.setImageDrawable(getDrawable(drawableID));
                break;
            case "happy":
                drawableID = getResources().getIdentifier("happy", "drawable", getPackageName());
                moodImage.setImageDrawable(getDrawable(drawableID));
                break;
            case "sad":
                drawableID = getResources().getIdentifier("sad", "drawable", getPackageName());
                moodImage.setImageDrawable(getDrawable(drawableID));
                break;
            case "miserable":
                drawableID = getResources().getIdentifier("miserable", "drawable", getPackageName());
                moodImage.setImageDrawable(getDrawable(drawableID));
        }
        //moodImage.setText(retrievedEntry.mood);

    }
}
