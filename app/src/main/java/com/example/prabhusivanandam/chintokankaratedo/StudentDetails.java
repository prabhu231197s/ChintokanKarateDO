package com.example.prabhusivanandam.chintokankaratedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Prabhu Sivanandam on 23-May-17.
 */

public class StudentDetails extends AppCompatActivity {

    Button fetchBtn;
    EditText userName;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details);
        fetchBtn=(Button)findViewById(R.id.btnFetchDetails);
        userName=(EditText)findViewById(R.id.student_Name);
    }

    public void onFetchClick(View v)
    {
        uname=userName.getText().toString();
        if(uname.isEmpty()||uname.trim().isEmpty())
        {
            Toast.makeText(StudentDetails.this,"Please enter a username to fetch...",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent=new Intent(StudentDetails.this,ViewStudentDetails.class);
            intent.putExtra("user",uname);
            userName.setText("");
            startActivity(intent);
        }
    }

}
