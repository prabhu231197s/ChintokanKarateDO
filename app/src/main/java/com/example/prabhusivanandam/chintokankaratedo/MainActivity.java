package com.example.prabhusivanandam.chintokankaratedo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        final String status=preferences.getString("loggeduser","proceedtologin");
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("event_id");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String id=(String)dataSnapshot.getValue();
                SharedPreferences eventpref=getSharedPreferences("event_id",MODE_PRIVATE);
                SharedPreferences.Editor editor=eventpref.edit();
                editor.putString("event_id",id);
                editor.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(status.equals("proceedtologin"))
                {
                    finish();
                    startActivity(new Intent(MainActivity.this,Homescreen.class));
                }
                else
                {
                    finish();
                    startActivity(new Intent(MainActivity.this,Dashboard.class));
                }
            }
        },3000);
    }
}
