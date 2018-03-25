package com.govind.authentication;



import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class News extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference journalCloudEndPoint;
    private DatabaseReference tagCloudEndPoint;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mDatabase =  FirebaseDatabase.getInstance().getReference();
        journalCloudEndPoint = mDatabase.child("journalentris");
        tagCloudEndPoint = mDatabase.child("tags");


        addInitialDataToFirebase();

    }



    private void addInitialDataToFirebase() {

        List<JournalEntry> sampleJournalEntries = SampleData.getSampleJournalEntries();
        for (JournalEntry journalEntry: sampleJournalEntries){
            String key = journalCloudEndPoint.push().getKey();
            journalEntry.setJournalId(key);
            journalCloudEndPoint.child(key).setValue(journalEntry);
        }



    }
}


