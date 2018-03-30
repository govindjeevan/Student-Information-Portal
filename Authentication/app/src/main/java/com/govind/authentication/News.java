package com.govind.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class News extends AppCompatActivity {

    EditText mNewsText;
    Button mPost;
    ArrayList<String> listitems=new ArrayList<>();
    EditText mNewsContent;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mNews = mRootRef.child("news");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mNewsText=findViewById(R.id.editTextNews);
        mPost=findViewById(R.id.buttonPost);
        mNewsContent=findViewById(R.id.editTextContent);

    }

    @Override
    protected void onStart(){
        super.onStart();

        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsPost newspost= new NewsPost();
                newspost.setTitle(mNewsText.getText().toString());
                newspost.setContent(mNewsContent.getText().toString());
                String key = mNews.push().getKey();
                newspost.setnewsID(key);
                mNews.child(key).setValue(newspost);
                startActivity(new Intent(News.this, NewsView.class));
            }
        });

    }

    }