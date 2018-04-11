package com.govind.authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView signOut;
    private FirebaseAuth auth;
    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Log.d(TAG,"Hello"+ user);
                if (user == null) {
                    Log.d(TAG,"BYE");
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };


        //defining cards

        CardView timetableCard = (CardView) findViewById(R.id.timetable_card);
        CardView pollsCard = (CardView) findViewById(R.id.poll_card);
        CardView newsCard = (CardView) findViewById(R.id.news_card);
        CardView forumCard = (CardView) findViewById(R.id.forum_card);
        CardView signOut=findViewById(R.id.sgnOut);

        //adding click listeners to cards
        timetableCard.setOnClickListener(this);
        pollsCard.setOnClickListener(this);
        forumCard.setOnClickListener(this);
        newsCard.setOnClickListener(this);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Hello there vola"+ user);
                auth.signOut();
                Log.d(TAG,"Hello there vola"+ user);
                if (user == null) {
                    Log.d(TAG,"BYE");
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });



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






/*

public class MainActivity extends AppCompatActivity {

    private CardView signOut;
    private FirebaseAuth auth;
    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Log.d(TAG,"Hello"+ user);
                if (user == null) {
                    Log.d(TAG,"BYE");
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        Button signOut=findViewById(R.id.sgnOut);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

    }

    public void signOut() {
        auth.signOut();
    }


} */
