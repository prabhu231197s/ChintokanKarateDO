package com.example.prabhusivanandam.chintokankaratedo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Prabhu Sivanandam on 10-May-17.
 */

public class Signup extends Activity{
    KarateKA ka;
    ProgressDialog dialog2;
    ProgressDialog dialog;
    EditText name,cpassword,age,phone,email,belt,address,emergency_number,ka_id,bloodgroup,father_name,mother_name,dojo,username,password,role;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        name=(EditText)findViewById(R.id.namefield);
        age=(EditText)findViewById(R.id.agefield);
        phone=(EditText)findViewById(R.id.phonefield);
        email=(EditText)findViewById(R.id.emailfield);
        belt=(EditText)findViewById(R.id.beltfield);
        address=(EditText)findViewById(R.id.addressfield);
        emergency_number=(EditText)findViewById(R.id.emergencyfield);
        ka_id=(EditText)findViewById(R.id.kaidfield);
        bloodgroup=(EditText)findViewById(R.id.bloodgroupfield);
        father_name=(EditText)findViewById(R.id.fatherfield);
        mother_name=(EditText)findViewById(R.id.motherfield);
        dojo=(EditText)findViewById(R.id.dojofield);
        username=(EditText)findViewById(R.id.usernamefield);
        password=(EditText)findViewById(R.id.passwordfield);
        cpassword=(EditText)findViewById(R.id.cpasswordfield);
        role=(EditText)findViewById(R.id.rolefield);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Please wait");
        dialog.setTitle("Registering..");
        dialog2=new ProgressDialog(this);
        dialog2.setTitle("Redirecting to signin");
        dialog2.setMessage("Please Wait");
    }
    public void onRegisterClick(View v)
    {
        String mName=name.getText().toString();
        String mRole=role.getText().toString();
        String mAge=age.getText().toString();
        String mPhone=phone.getText().toString();
        String mEmail=email.getText().toString();
        String mBelt=belt.getText().toString();
        String mAddress=address.getText().toString();
        String mEmeregency_number=emergency_number.getText().toString();
        String mKa_id=ka_id.getText().toString();
        String mBloodgroup=bloodgroup.getText().toString();
        String mFathername=father_name.getText().toString();
        String mMothername=mother_name.getText().toString();
        String mDojo=dojo.getText().toString();
        String mUsername=username.getText().toString();
        String mPassword=password.getText().toString();
        String mCpassword=cpassword.getText().toString();

        if(mName.isEmpty())
        {
            name.setError("Please enter name");
        }
        if(mAge.isEmpty())
        {
            age.setError("Please enter age");
        }
        if(mPhone.isEmpty())
            phone.setError("Please enter phone number");
        if(mEmail.isEmpty())
            email.setError("Please enter email");
        if(mBelt.isEmpty())
            belt.setError("Please enter belt");
        if(mAddress.isEmpty())
            address.setError("Please enter address");
        if(mEmeregency_number.isEmpty())
            emergency_number.setError("Please enter emergency number");
        if(mKa_id.isEmpty())
            ka_id.setError("Enter Ka-id");
        if(mBloodgroup.isEmpty())
            bloodgroup.setError("Enter blood group");
        if(mFathername.isEmpty())
            father_name.setError("Enter father name");
        if(mMothername.isEmpty())
            mother_name.setError("Enter mother name");
        if(mDojo.isEmpty())
            dojo.setError("Enter dojo");
        if(mUsername.isEmpty())
            username.setError("Enter username");
        if(mPassword.isEmpty())
            password.setError("Specify password");
        if(mCpassword.isEmpty())
            cpassword.setError("Re-enter Password");
        if(mRole.isEmpty())
            cpassword.setError("Enter your role at dojo");
        if(!mPassword.isEmpty()&&!mCpassword.isEmpty()&&mPassword.equals(mCpassword))
        {
            //do registration here
            dialog.show();
            ka=new KarateKA(mName,mAge,mPhone,mEmail,mBelt,mAddress,mEmeregency_number,mKa_id,"0",mBloodgroup,mFathername,mMothername,mDojo,mUsername,mPassword,mRole,"0");
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ka-s");
            reference.child(ka.getUsername()).setValue(ka);
            dialog.dismiss();
            Toast.makeText(Signup.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
            name.setText("");
            phone.setText("");
            age.setText("");
            email.setText("");
            belt.setText("");
            address.setText("");
            emergency_number.setText("");
            ka_id.setText("");
            bloodgroup.setText("");
            father_name.setText("");
            mother_name.setText("");
            dojo.setText("");
            role.setText("");
            password.setText("");
            cpassword.setText("");
            dialog2.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog2.dismiss();
                    finish();
                }
            },2000);
        }
        else
        {
            Toast.makeText(Signup.this,"Enter the passwords correctly",Toast.LENGTH_LONG).show();
        }
    }
}
