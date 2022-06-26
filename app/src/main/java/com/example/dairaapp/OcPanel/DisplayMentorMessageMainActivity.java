package com.example.dairaapp.OcPanel;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.CurrentUser;
import com.example.dairaapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayMentorMessageMainActivity extends AppCompatActivity {

    TextView msg;
    String mentor_id, msg_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_mentor_message_main);
        msg = findViewById(R.id.mentormsgshow);
        CurrentUser currentUser = CurrentUser.getInstance();
        String oc_id;
        oc_id = "e8882c9702";
//        oc_id = currentUser.getUserName(this);

        FirebaseDatabase.getInstance().getReference().child("ocs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if(child.getKey().toString().equals(oc_id)){
                        Log.d("TAG", "OC Child" + child.getKey());
                        mentor_id = child.child("mentor_id").getValue().toString();
                        Log.d("TAG", "OC mentor" + child.getKey());
                    }
                }
                if (mentor_id.length() > 0){
                    FirebaseDatabase.getInstance().getReference().child("mentormsgs").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                if(child.getKey().toString().equals(mentor_id)){
                                    msg_str = child.child("msg").getValue().toString();
                                }
                            }
                            msg.setText(msg_str);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}