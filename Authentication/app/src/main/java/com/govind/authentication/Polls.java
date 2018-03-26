package com.govind.authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Polls extends AppCompatActivity {

    TextView mConditionTextview;
    Button mButtonSunny;
    Button mButtonFoggy;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionReference = mRootRef.child("condition");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);


        mConditionTextview=findViewById(R.id.textViewCondition);
        mButtonSunny=findViewById(R.id.buttonSunny);
        mButtonFoggy=findViewById(R.id.buttonFoggy);

    }


    @Override
    protected void onStart(){
        super.onStart();

        mConditionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text  = dataSnapshot.getValue(String.class);
                mConditionTextview.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mButtonSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConditionReference.setValue("Sunny");
            }
        });

        mButtonFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConditionReference.setValue("Foggy");
            }
        });
    }
}
