package com.example.prabhusivanandam.chintokankaratedo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Prabhu Sivanandam on 15-May-17.
 */

public class UserMessages {
    String username,body,mid;

    public UserMessages()
    {

    }
    public UserMessages(String username, String body) {
        this.username = username;
        this.body = body;
        mid=generateMid();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        mid = mid;
    }

    public String generateMid()
    {
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("user_message_id");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int x=Integer.parseInt((String)dataSnapshot.getValue());
                x+=1;
                reference.setValue(""+x);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mid;
    }
}
