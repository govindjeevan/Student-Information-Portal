package com.govind.homedashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class home extends AppCompatActivity implements View.OnClickListener {

    private CardView pollsCard, forumCard, feedCard, newsCard, timetableCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //defining cards

        timetableCard= (CardView) findViewById(R.id.timetable_card);
        pollsCard= (CardView) findViewById(R.id.poll_card);
        newsCard= (CardView) findViewById(R.id.news_card);
        forumCard= (CardView) findViewById(R.id.forum_card);

        //adding click listeners to cards
        timetableCard.setOnClickListener(this);
        pollsCard.setOnClickListener(this);
        forumCard.setOnClickListener(this);
        newsCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.timetable_card: i=new Intent (this, TimeTable.class);startActivity(i);break;
            case R.id.poll_card: i=new Intent (this, Polls.class);startActivity(i);break;
            case R.id.news_card: i=new Intent (this, News.class);startActivity(i);break;
            case R.id.forum_card: i=new Intent (this, Forum.class);startActivity(i);break;
            default: break;

        }
     }
}
