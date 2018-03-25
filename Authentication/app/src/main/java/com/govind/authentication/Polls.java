package com.govind.authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Polls extends AppCompatActivity {

    public static final String TAG = Polls.class.getSimpleName();
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private int i=0;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);


        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        newQuestion(String.valueOf(i++),"Want Class tommorrow?","Yes","No");

        mDatabase.addValueEventListener(questionListener);



    }

    private void newQuestion(String questionID, String question, String opt1, String opt2) {
        Question q = new Question(question, opt1, opt2);

        mDatabase.child("Questions").child(questionID).setValue(q);
    }

    ValueEventListener questionListener = new ValueEventListener() {


        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // Get Post object and use the values to update the UI
            Question q = dataSnapshot.getValue(Question.class);
            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };


}
