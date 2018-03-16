package com.govind.homedashboard;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class News extends AppCompatActivity {

    static int id=1;
    /**
     * A simple {@link Fragment} subclass.
     */

        public News() {
            // Required empty public constructor
        }

//    public class MessageData{
//        public String body;
//
//        public MessageData(){
//
//        }
//
//        public MessageData(String body){
//            this.body=body;
//        }
//
//        public Map<String, Object> toMap() {
//            HashMap<String, Object> result = new HashMap<>();
//            result.put("body", body);
//
//            return result;
//        }
//    }

        EditText text;
        ListView listView;

        String admin = "admin";
        Button sendButton;

        private DatabaseReference mDatabase;
        ArrayList<String> listitems=new ArrayList<>();

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.activity_news, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("NEWS");
            listView = (ListView) view.findViewById(R.id.list_view);

            text = (EditText)view.findViewById(R.id.editMessage);
            sendButton =(Button) view.findViewById(R.id.buttonSend);
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data = text.getText().toString();
                    if(!data.equals("")) {
                        MessageData datamap = new MessageData(data);
                        mDatabase.push().setValue(data);
                        if (text.length() > 0) {
                            text.getText().clear();
                        }
                    }
                }
            });
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //LinearLayout layout = (LinearLayout) findViewById(R.id.info);
//                LinearLayout layout = new LinearLayout(getActivity());
//                layout.setBackgroundColor(getResources().getColor(R.color.viewBackground));
//                MessageData message = dataSnapshot.getValue(MessageData.class);
                    listitems.clear();
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()){
                        String message = messageSnapshot.getValue(String.class);
                        if (message==null ){
                            Toast.makeText(getContext(), "Null Message detected :", Toast.LENGTH_SHORT).show();
                            return;
                        }
//
                        listitems.add(message);

                    }
                    ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,listitems);
                    listView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Message detected : ", Toast.LENGTH_SHORT).show();


//                arrayAdapter.add(message.body);
//                arrayAdapter.notifyDataSetChanged();
//                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
//                    Note note = noteDataSnapshot.getValue(Note.class);
//                    notes.add(note);
//                }
//                adapter.updateList(notes);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Message cancelled", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

