package com.example.prabhusivanandam.chintokankaratedo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * Created by Prabhu Sivanandam on 13-May-17.
 */

public class ViewStudentDetails extends AppCompatActivity {

    ProgressDialog dialog;
    String username;
    TextView kaName,kaAge,kaPhoneNumber,kaEmail,kaAddress,kaEmergencyNumber,kaBloodGroup,kaFatherName,kaMotherName,kaDojo,kaUserName,kaRole,kaBelt,kaId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student_details);
        username=getIntent().getStringExtra("user");
        Log.d("tag",username);
        kaName=(TextView)findViewById(R.id.studentName);
        kaAge=(TextView)findViewById(R.id.studentAge);
        kaPhoneNumber=(TextView)findViewById(R.id.studentPhoneNumber);
        kaEmail=(TextView)findViewById(R.id.studentEmail);
        kaAddress=(TextView)findViewById(R.id.studentAddress);
        kaEmergencyNumber=(TextView)findViewById(R.id.studentEmergencyNumber);
        kaBloodGroup=(TextView)findViewById(R.id.studentBloodGroup);
        kaFatherName=(TextView)findViewById(R.id.studentFatherName);
        kaMotherName=(TextView)findViewById(R.id.studentMotherName);
        kaDojo=(TextView)findViewById(R.id.studentDojo);
        kaUserName=(TextView)findViewById(R.id.studentUserName);
        kaRole=(TextView)findViewById(R.id.studentRole);
        kaBelt=(TextView)findViewById(R.id.studentBelt);
        kaId=(TextView)findViewById(R.id.studentKaId);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching Details");
        dialog.setMessage("Please Wait");
        dialog.show();
        fetchDetails();
    }

    public void fetchDetails()
    {
        //fetching from database
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ka-s/"+username);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                KarateKA ka=new KarateKA();
                ka=dataSnapshot.getValue(KarateKA.class);
                //Log.d("tag",ka.getName());
                kaName.setText           ("Name:  "+ka.getName());
                kaAge.setText            ("Age:  "+ka.getAge());
                kaPhoneNumber.setText    ("Phone:  "+ka.getPhone());
                kaEmail.setText          ("Email:  "+ka.getEmail());
                kaAddress.setText        ("Address:  "+ka.getAddress());
                kaEmergencyNumber.setText("Emergency number:  "+ka.getEmergency_number());
                kaBloodGroup.setText     ("Blood Group:  "+ka.getBloodgroup());
                kaFatherName.setText     ("Father Name:  "+ka.getFather_name());
                kaMotherName.setText     ("Mother Name:  "+ka.getMother_name());
                kaDojo.setText           ("Dojo:  "+ka.getDojo());
                kaUserName.setText       ("Username:  "+ka.getUsername());
                kaRole.setText           ("Role:  "+ka.getRole());
                kaBelt.setText           ("Belt:  "+ka.getBelt());
                kaId.setText             ("KA-ID:  "+ka.getKa_id());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewStudentDetails.this,"Check your connection",Toast.LENGTH_LONG).show();
            }
        });
        dialog.dismiss();
    }

}
