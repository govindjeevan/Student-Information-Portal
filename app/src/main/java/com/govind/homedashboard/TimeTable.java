package com.govind.homedashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TimeTable extends AppCompatActivity {
    private static final String TAG = "activity_time_table";
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);



        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String userId = mDatabase.push().getKey();

        // creating user object
        User user = new User("GovinddTamada", "ravi@androidhive.info");

        // pushing user to 'users' node using the userId
        mDatabase.child(userId).setValue(user);



        mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                Log.d(TAG, "User name: " + user.name + ", email " + user.email);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        String newEmail = "androidhsddive@gmail.com";

        mDatabase.child(userId).child("email").setValue(newEmail);




    }






}


