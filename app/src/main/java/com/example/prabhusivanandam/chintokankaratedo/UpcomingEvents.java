package com.example.prabhusivanandam.chintokankaratedo;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class UpcomingEvents extends android.support.v4.app.Fragment{
    RecyclerView rv;
    EventsAdapter adapter;
    ArrayList<Events> eventList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.upcoming_events,container,false);
        rv=(RecyclerView)v.findViewById(R.id.rv);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity());
        eventList=new ArrayList<>();
        int count=0;
        SharedPreferences eventcount= getActivity().getSharedPreferences("count", Context.MODE_PRIVATE);
        count=eventcount.getInt("eve_count",0);
        Log.d("count:",count+"");
        rv.setLayoutManager(manager);
        adapter=new EventsAdapter(eventList);
        rv.setAdapter(adapter);
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        for(int i=count;i>=1;i--)
        {
            reference=FirebaseDatabase.getInstance().getReference("Upcoming_Events/"+i);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    eventList.add(dataSnapshot.getValue(Events.class));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        rv.hasFixedSize();
        return v;
    }
}
