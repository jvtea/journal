package com.example.joost.journal;

import java.io.Serializable;
import java.time.LocalDateTime;

public class JournalEntry implements Serializable {

    int id;
    String title;
    String content;
    String mood;
    String dateTime;

    public JournalEntry(int id, String title, String content, String mood, String dateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.dateTime = dateTime;
    }

    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getDateTime() {
        return dateTime;
    }


}
