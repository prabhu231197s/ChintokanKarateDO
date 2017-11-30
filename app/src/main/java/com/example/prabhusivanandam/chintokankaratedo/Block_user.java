package com.example.prabhusivanandam.chintokankaratedo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Prabhu Sivanandam on 12-May-17.
 */

public class Block_user extends AppCompatActivity {

    //Declaration to get the username to be blocked


    EditText usernaeme;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.block_user);
        usernaeme=(EditText)findViewById(R.id.blockusername);
    }

    public void onBlockClick(View v)
    {

        //Change the block flag to 1
        //Change the login flag also to zero so that next time he gets blocked to login
        //Also change the login preference to proceed to login to push the blocked user to the login screen


        String username=usernaeme.getText().toString();
        //block user here
        if(username.isEmpty())
        {

        }
        else
        {
            final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ka-s/"+username);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    KarateKA ka=new KarateKA();
                    ka=dataSnapshot.getValue(KarateKA.class);
                    ka.setBlock_flag("1");
                    ka.setLoginFlag("0");
                    reference.setValue(ka);
                    SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("loggeduser","proceedtologin");
                    editor.commit();
                    Toast.makeText(Block_user.this,"Blocked successfully",Toast.LENGTH_LONG).show();
                    usernaeme.setText("");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Block_user.this,"Check your internet connection",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void onUnblockClick(View v)
    {

        //Just change the block flag to 0
        //Also push the unblocked user to the login screen

        String username=usernaeme.getText().toString();
        //unblock user here
        if(username.isEmpty())
        {

        }
        else
        {
            final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ka-s/"+username);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    KarateKA ka=new KarateKA();
                    ka=dataSnapshot.getValue(KarateKA.class);
                    ka.setBlock_flag("0");
                    reference.setValue(ka);
                    SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("loggeduser","proceedtologin");
                    editor.commit();
                    Toast.makeText(Block_user.this,"Unblocked successfully",Toast.LENGTH_LONG).show();
                    usernaeme.setText("");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Block_user.this,"Check your internet connection",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
