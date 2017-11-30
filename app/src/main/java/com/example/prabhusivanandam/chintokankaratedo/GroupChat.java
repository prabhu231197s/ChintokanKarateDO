package com.example.prabhusivanandam.chintokankaratedo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Prabhu Sivanandam on 15-May-17.
 */

public class GroupChat extends Fragment{

    EditText mBody;
    Handler mHandler;
    ImageView sendBtn;
    String username;
    ArrayList<UserMessages> userMessages=new ArrayList<>();
    RecyclerView user_rv;
    GroupchatAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.group_chat,container,false);
        user_rv=(RecyclerView)v.findViewById(R.id.userchat_rv);
        sendBtn=(ImageView)v.findViewById(R.id.usersendButton);
        mBody=(EditText)v.findViewById(R.id.usermessage);
        SharedPreferences r=getActivity().getSharedPreferences("user_m_id",Context.MODE_PRIVATE);
        String id=r.getString("mes_id","0");
        int count=Integer.parseInt(id);
        SharedPreferences preferences=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        username=preferences.getString("loggeduser","anonymous");
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        adapter=new GroupchatAdapter(userMessages);
        manager.setReverseLayout(true);
        user_rv.setLayoutManager(manager);
        mHandler=new Handler();
        user_rv.setAdapter(adapter);
        user_rv.hasFixedSize();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        for (int i=1;i<count;i++)
        {
            reference=FirebaseDatabase.getInstance().getReference("User_Messages/"+i);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserMessages messages=new UserMessages();
                    messages=dataSnapshot.getValue(UserMessages.class);
                    userMessages.add(messages);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mbody=mBody.getText().toString();
                if(mbody.isEmpty()||mbody.trim().length()==0)
                {

                }
                else
                {
                    SharedPreferences r=getActivity().getSharedPreferences("user_m_id",Context.MODE_PRIVATE);
                    String id=r.getString("mes_id","0");
                    UserMessages messages=new UserMessages(username,mbody);
                    messages.setMid(id);
                    mBody.setText("");
                    SharedPreferences.Editor editor=r.edit();
                    int x=Integer.parseInt(id);
                    x+=1;
                    editor.putString("mes_id",""+x);
                    editor.commit();
                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User_Messages");
                    reference.child(String.valueOf(id)).setValue(messages);
                    userMessages.add(messages);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        mRunnable.run();
        return v;
    }

    private Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            //Toast.makeText(getContext(),"Refreshing",Toast.LENGTH_LONG).show();
            //Refresh the shared preference here
            SharedPreferences preferences=getActivity().getSharedPreferences("user_m_id",Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor=preferences.edit();
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference("user_message_id");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    editor.putString("mes_id",String.valueOf(dataSnapshot.getValue()));
                    editor.commit();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            int count=Integer.parseInt(preferences.getString("mes_id","0"));
           // userMessages.clear();
           /* DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
            for (int i=1;i<count;i++)
            {
                ref=FirebaseDatabase.getInstance().getReference("User_Messages/"+i);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserMessages messages=new UserMessages();
                        messages=dataSnapshot.getValue(UserMessages.class);
                        userMessages.add(messages);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }*/
            GroupChat.this.mHandler.postDelayed(mRunnable,3000);
        }
    };

}
