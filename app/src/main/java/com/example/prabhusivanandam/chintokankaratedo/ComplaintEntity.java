package com.example.prabhusivanandam.chintokankaratedo;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Prabhu Sivanandam on 24-May-17.
 */

public class ComplaintEntity {

    int complaint_id;
    String subject;
    String body;

    public ComplaintEntity(int complaint_id, String subject, String body) {
        this.complaint_id = complaint_id;
        this.subject = subject;
        this.body = body;
        generateId();
    }

    public ComplaintEntity()
    {

    }

    public int getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void generateId()
    {
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("complaint_id");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String x= (String) dataSnapshot.getValue();
                int y=Integer.parseInt(x);
                y+=1;
                reference.setValue(""+y);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
