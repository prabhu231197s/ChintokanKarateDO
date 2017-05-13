package com.example.prabhusivanandam.chintokankaratedo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int count=0;
        setContentView(R.layout.activity_main);
        final SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        final String status=preferences.getString("loggeduser","proceedtologin");
        Log.d("e",""+status);


        final DatabaseReference refe=FirebaseDatabase.getInstance().getReference("Upcoming_Events");
        refe.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int c= (int) dataSnapshot.getChildrenCount();
                SharedPreferences eventcount=getSharedPreferences("count",MODE_PRIVATE);
                SharedPreferences.Editor ed=eventcount.edit();
                ed.putInt("eve_count",c);
                ed.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Check your connectivity",Toast.LENGTH_LONG).show();
            }
        });




        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Ka-s/"+status);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren())
                {
                    KarateKA ka=new KarateKA();
                    ka=dataSnapshot.getValue(KarateKA.class);
                    Log.d("name",ka.getName());
                    SharedPreferences block=getSharedPreferences("blockstatus",MODE_PRIVATE);
                    SharedPreferences.Editor editor=block.edit();
                    editor.putString("blockstatus",ka.getBlock_flag());
                    editor.commit();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                    Log.d("error","network");
                Toast.makeText(MainActivity.this,"Check your internet connection",Toast.LENGTH_LONG).show();
            }
        });
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
                Toast.makeText(MainActivity.this,"Check your internet connection",Toast.LENGTH_LONG).show();
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
                    SharedPreferences pref=getSharedPreferences("blockstatus",MODE_PRIVATE);
                    String block_flag=pref.getString("blockstatus","0");
                    Log.d("block",block_flag);
                    if(block_flag.equals("0"))
                    {
                        finish();
                        startActivity(new Intent(MainActivity.this,Dashboard.class));
                    }
                    else
                    {
                        finish();
                        Toast.makeText(MainActivity.this,"You are blocked.Contact admin",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this,Homescreen.class));
                    }
                }
            }
        },3000);
    }
}
