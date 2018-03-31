package com.govind.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class News extends AppCompatActivity {
    // imports
    private EditText textTitle;
    private EditText textDesc;
    private Button postBtn;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;
    private static final String TAG = News.class.getSimpleName();

    private Button newsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        // initializing objects
        postBtn = (Button) findViewById(R.id.postBtn);
        textDesc = (EditText) findViewById(R.id.textDesc);
        textTitle = (EditText) findViewById(R.id.textTitle);
        if(textTitle==null)
            Log.d(TAG,"THIS IS NULL");
        databaseRef = database.getInstance().getReference().child("News");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        newsBtn=(Button) findViewById(R.id.newsBtn);
        //picking image from gallery

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsPost newspost = new newsPost(textDesc.getText().toString(),textTitle.getText().toString());
                databaseRef.push().setValue(newspost);
                textTitle.setText("");
                textDesc.setText("");
            }
        });

        newsBtn.setOnClickListener(new View.OnClickListener(){
           @Override
                public void onClick(View view){
                Intent i=new Intent(view.getContext(), ViewNews.class);
                view.getContext().startActivity(i);
            }
        });

    }
}
