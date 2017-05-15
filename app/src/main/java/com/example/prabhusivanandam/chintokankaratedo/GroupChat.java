package com.example.prabhusivanandam.chintokankaratedo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Prabhu Sivanandam on 15-May-17.
 */

public class GroupChat extends Fragment{

    EditText mBody;
    ImageView sendBtn;
    String username;
    ArrayList<UserMessages> userMessages=new ArrayList<>();
    GroupchatAdapter adapter;
    RecyclerView user_rv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.group_chat,container,false);
        user_rv=(RecyclerView)v.findViewById(R.id.userchat_rv);
        sendBtn=(ImageView)v.findViewById(R.id.usersendButton);
        mBody=(EditText)v.findViewById(R.id.usermessage);
        SharedPreferences preferences=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        username=preferences.getString("loggeduser","anonymous");
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        GroupchatAdapter adapter=new GroupchatAdapter(userMessages);
        user_rv.setLayoutManager(manager);
        user_rv.setAdapter(adapter);
        user_rv.hasFixedSize();
        return v;
    }

    public void OnSendClick(View v)
    {
        String mbody=mBody.getText().toString();
        if(mbody.isEmpty()||mbody.trim().length()==0)
        {

        }
        else
        {
            SharedPreferences r=getActivity().getSharedPreferences("user_m_id",Context.MODE_PRIVATE);
            String id=r.getString("mes_id","0");
            Messages messages=new Messages(username,mbody);
            messages.setMid(id);
            SharedPreferences.Editor editor=r.edit();
            int x=Integer.parseInt(id);
            x+=1;
            editor.putString("mes_id",""+x);
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User_Messages");
            reference.child(String.valueOf(id)).setValue(messages);
        }
    }
}
