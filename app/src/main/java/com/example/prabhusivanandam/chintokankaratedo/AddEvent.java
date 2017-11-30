package com.example.prabhusivanandam.chintokankaratedo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Prabhu Sivanandam on 11-May-17.
 */

public class AddEvent extends AppCompatActivity {

    //Declaration for the variables and containers for the events Model class


    EditText event_name,event_place,event_venue,event_cause,event_date,event_entry_fee,event_organizer,event_incharge,event_contact_number,event_contact_mail;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);
        btn=(Button)findViewById(R.id.addeventbtn);
        event_name=(EditText)findViewById(R.id.event_name);
        event_place=(EditText)findViewById(R.id.event_place);
        event_venue=(EditText)findViewById(R.id.event_venue);
        event_cause=(EditText)findViewById(R.id.event_cause);
        event_date=(EditText)findViewById(R.id.event_date);
        event_entry_fee=(EditText)findViewById(R.id.event_entry_fee);
        event_organizer=(EditText)findViewById(R.id.event_organizer);
        event_incharge=(EditText)findViewById(R.id.event_contact_person);
        event_contact_number=(EditText)findViewById(R.id.event_contact_number);
        event_contact_mail=(EditText)findViewById(R.id.event_email);
    }

    public void onaddEventClick(View v) {

        //Click handler which adds the event to the database

        String mEvent_name, mEvent_place, mEvent_venue, mEvent_cause, mEvent_date, mEvent_Entry_fee, mEvent_organizer, mEvent_incharge, mEvent_contact_number, mEvent_contact_mail;
        mEvent_name = event_name.getText().toString();
        mEvent_place = event_place.getText().toString();
        mEvent_venue = event_venue.getText().toString();
        mEvent_date = event_date.getText().toString();
        mEvent_cause = event_cause.getText().toString();
        mEvent_Entry_fee = event_entry_fee.getText().toString();
        mEvent_organizer = event_organizer.getText().toString();
        mEvent_incharge = event_incharge.getText().toString();
        mEvent_contact_number = event_contact_number.getText().toString();
        mEvent_contact_mail = event_contact_mail.getText().toString();


        //Check for the null pointer exception


        if (mEvent_name.isEmpty() || mEvent_place.isEmpty() || mEvent_venue.isEmpty() || mEvent_date.isEmpty() || mEvent_cause.isEmpty() || mEvent_Entry_fee.isEmpty() || mEvent_organizer.isEmpty() || mEvent_incharge.isEmpty() || mEvent_contact_number.isEmpty() || mEvent_contact_mail.isEmpty()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(AddEvent.this);
            dialog.setTitle("Warning");
            dialog.setMessage("Please fill all the fields");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog.show();
        } else {
            SharedPreferences preferences=getSharedPreferences("event_id",MODE_PRIVATE);
            String id=preferences.getString("event_id","0");
            Events newEvent = new Events(mEvent_name, mEvent_place, mEvent_venue, mEvent_date, mEvent_cause, mEvent_Entry_fee, mEvent_organizer, mEvent_incharge, mEvent_contact_number, mEvent_contact_mail);
            newEvent.setEvent_id(id);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Upcoming_Events");
            reference.child(String.valueOf(newEvent.getEvent_id())).setValue(newEvent);
            event_name.setText("");
            event_place.setText("");
            event_venue.setText("");
            event_cause.setText("");
            event_entry_fee.setText("");
            event_date.setText("");
            event_organizer.setText("");
            event_incharge.setText("");
            event_contact_number.setText("");
            event_contact_mail.setText("");
            Toast.makeText(AddEvent.this, "Event Added Succesfully", Toast.LENGTH_SHORT).show();
            SharedPreferences pref=getSharedPreferences("event_id",MODE_PRIVATE);
            SharedPreferences.Editor editor=pref.edit();
            int x=Integer.parseInt(id);
            x+=1;
            editor.putString("event_id",""+x);
            editor.commit();
        }
    }
}
