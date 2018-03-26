package com.govind.authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsView extends AppCompatActivity {

    private static final String LOG_TAG =NewsView.class.getSimpleName();
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mNews = mRootRef.child("news");
    private RecyclerView newsRV;
    private FirebaseRecyclerAdapter<NewsPost, NewsViewHolder>;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        initializeScreen();
    }

    private void initializeScreen() {
        newsRV=(RecyclerView)findViewById(R.id.news_rv);
        newsRV.setLayoutManager(new LinearLayoutManager(NewsView.this));
        setupAdapter();

    }

    private void setupAdapter() {
        mNewsAdapter=new FirebaseRecyclerAdapter<NewsPost, NewsViewHolder>(){
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, NewsView model, int position){

            };
        };
    }

    @Override
    protected void onStart(){
        super .onStart();

        final List <NewsPost> newsList= new ArrayList<NewsPost>();
        mNews.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot notesnapshot: dataSnapshot.getChildren()){
                    NewsPost newspost = notesnapshot.getValue(NewsPost.class);
                    Log.d(LOG_TAG,newspost.content);
                    newsList.add(newspost);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(LOG_TAG, databaseError.getMessage());
            }
        });
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        public NewsViewHolder(View itemView){
            super(itemView);

        }
    }
}
