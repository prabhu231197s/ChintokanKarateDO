package com.example.prabhusivanandam.chintokankaratedo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class AdminSectionLogin extends AppCompatActivity {

    //Login for the admins : check for the userid and their password

    EditText admin_user,admin_pass;
    Button login_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);
        admin_pass=(EditText)findViewById(R.id.adminpass);
        admin_user=(EditText)findViewById(R.id.adminuser);
        login_btn=(Button)findViewById(R.id.adminloginbutton);
    }

    public void onAdminLoginClick(final View view)
    {
        final String username=admin_user.getText().toString();
        final String userpass=admin_pass.getText().toString();
        if(username.isEmpty())
        {
            admin_user.setError("Enter username");
        }
        if(userpass.isEmpty())
        {
            admin_pass.setError("Enter password");
        }
        if(username.isEmpty()&&userpass.isEmpty())
        {
            admin_user.setError("Enter username");
            admin_pass.setError("Enter password");
        }
        else {
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("admins/"+username);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("eror   ",""+username+"  "+dataSnapshot.hasChildren());
                    if(dataSnapshot.hasChildren())
                    {
                        Admin admin=new Admin();
                        admin=dataSnapshot.getValue(Admin.class);
                        if(admin.getPassword().equals(userpass))
                        {
                            admin_user.setText("");
                            admin_pass.setText("");
                            SharedPreferences adminpreferences=getSharedPreferences("adminlogin",MODE_PRIVATE);
                            SharedPreferences.Editor editor=adminpreferences.edit();
                            editor.putString("loggedadmin",username);
                            editor.commit();
                            Toast.makeText(AdminSectionLogin.this,"Login Success",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AdminSectionLogin.this,AdminDashboard.class));
                        }
                        else
                        {
                            Toast.makeText(AdminSectionLogin.this,"Incorrect Password",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(AdminSectionLogin.this,"You are not an admin",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}
