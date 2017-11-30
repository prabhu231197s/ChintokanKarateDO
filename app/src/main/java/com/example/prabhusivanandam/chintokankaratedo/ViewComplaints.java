package com.example.prabhusivanandam.chintokankaratedo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Prabhu Sivanandam on 12-May-17.
 */

public class ViewComplaints extends AppCompatActivity {

    ArrayList<ComplaintEntity> complaints;
    ComplaintEntity entity;
    RecyclerView complaintsRecyclerView;
    ViewComplaintsRecyclerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_complaints);
        complaintsRecyclerView=(RecyclerView)findViewById(R.id.rvComplaints);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        complaintsRecyclerView.setLayoutManager(manager);
        complaints=new ArrayList<>();
        SharedPreferences preferences=getSharedPreferences("complaint_count",MODE_PRIVATE);
        String count=preferences.getString("complaint_id","0");
        final int c=Integer.parseInt(count);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        for(int i=c-1;i>0;i--)
        {
            reference=FirebaseDatabase.getInstance().getReference("Complaints/"+i);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    entity=dataSnapshot.getValue(ComplaintEntity.class);
                    complaints.add(entity);
                    adapter=new ViewComplaintsRecyclerAdapter(complaints);
                    complaintsRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    complaintsRecyclerView.hasFixedSize();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ViewComplaints.this,"Check your internet Connection",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
