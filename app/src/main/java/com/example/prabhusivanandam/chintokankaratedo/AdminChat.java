package com.example.prabhusivanandam.chintokankaratedo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Prabhu Sivanandam on 12-May-17.
 */

public class AdminChat extends AppCompatActivity {

    EditText mBody;
    ImageView sendBtn;
    String admin="admin";
    ArrayList<Messages> messages=new ArrayList<>();
    RecyclerView adminChatRv;
    AdminChatAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_chat);
        mBody=(EditText)findViewById(R.id.message);
        sendBtn=(ImageView)findViewById(R.id.sendButton);
        messages=new ArrayList<>();
        SharedPreferences adminpreferences=getSharedPreferences("adminlogin",MODE_PRIVATE);
        admin=adminpreferences.getString("loggedadmin","admin");
        int count=0;
        SharedPreferences refer=getSharedPreferences("m_id",MODE_PRIVATE);
        count=Integer.parseInt(refer.getString("mes_id","0"));
        Log.d("Messagecnt",""+count);
        adapter=new AdminChatAdapter(messages);
        adminChatRv=(RecyclerView)findViewById(R.id.adminchat_rv);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        adminChatRv.setLayoutManager(manager);
        adminChatRv.setAdapter(adapter);
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        for(int i=1;i<count;i++)
        {
            reference=FirebaseDatabase.getInstance().getReference("AdminMessages/"+i);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    messages.add(dataSnapshot.getValue(Messages.class));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        adminChatRv.hasFixedSize();
    }

    public void onSendClick(View v)
    {
        String message=mBody.getText().toString();
        if(message.isEmpty()||message.trim().length()==0)
        {

        }
        else
        {
            SharedPreferences message_id=getSharedPreferences("m_id",MODE_PRIVATE);
            String m_id=message_id.getString("mes_id","0");
            Messages newMessage=new Messages(admin,message);
            newMessage.setMid(m_id);
            messages.add(newMessage);
            adapter.notifyDataSetChanged();
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("AdminMessages");
            reference.child(String.valueOf(m_id)).setValue(newMessage);
            mBody.setText("");
            SharedPreferences mes=getSharedPreferences("m_id",MODE_PRIVATE);
            SharedPreferences.Editor editor=mes.edit();
            int x=Integer.parseInt(m_id);
            x+=1;
            editor.putString("mes_id",""+x);
            editor.commit();
        }
    }
}
