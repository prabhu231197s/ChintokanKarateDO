package com.example.prabhusivanandam.chintokankaratedo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.*;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Prabhu Sivanandam on 10-May-17.
 */
public class Homescreen extends Activity{

    TextView admin;
    ProgressDialog dialog;
    EditText et1,et2;
    Button btn1,btn2;
    TextView tv1;
    String username=null;
    String password=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        btn1=(Button)findViewById(R.id.loginButton);
        btn2=(Button)findViewById(R.id.signupButton);
        tv1=(TextView)findViewById(R.id.AboutUs);
        admin=(TextView)findViewById(R.id.admin);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Please wait");
        dialog.setTitle("Logging in");
    }


    public void onLoginClick(View v)
    {
        username=et1.getText().toString();
        password=et2.getText().toString();
        if(username.isEmpty()||password.isEmpty())
        {
            Toast.makeText(Homescreen.this,"Enter the credentials",Toast.LENGTH_LONG).show();
        }
        else {
            et1.setText("");
            et2.setText("");
            dialog.show();
            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ka-s/" + username);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        KarateKA ka = new KarateKA();
                        ka = dataSnapshot.getValue(KarateKA.class);
                        if (ka.getPassword().equals(password)) {
                            if (ka.getLoginFlag().equals("0")) {
                                if(ka.getBlock_flag().equals("0")) {
                                    dialog.dismiss();
                                    ka.setLoginFlag("1");
                                    Toast.makeText(Homescreen.this, "Login Success", Toast.LENGTH_LONG).show();
                                    reference.setValue(ka);
                                    SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("loggeduser", ka.getUsername());
                                    editor.commit();
                                    Intent i = new Intent(Homescreen.this, Dashboard.class);
                                    i.putExtra("username", username);
                                    startActivity(i);
                                }
                                else
                                {
                                    AlertDialog.Builder alert=new AlertDialog.Builder(Homescreen.this);
                                    alert.setTitle("Warning");
                                    alert.setMessage("Contact admin");
                                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    dialog.show();
                                }
                            } else {
                                dialog.dismiss();
                                Toast.makeText(Homescreen.this, "Multiple logins not allowed", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            dialog.dismiss();
                            Toast.makeText(Homescreen.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        dialog.dismiss();
                        Toast.makeText(Homescreen.this, "User does not exist", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Homescreen.this, "Connectivity Errors", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void onSignupClick(View v)
    {
        startActivity(new Intent(Homescreen.this,Signup.class));
    }
    public void aboutUsClick(View v)
    {
        startActivity(new Intent(Homescreen.this,Aboutus.class));
    }

    public void onAdminClick(View v)
    {
        startActivity(new Intent(Homescreen.this,AdminSectionLogin.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
