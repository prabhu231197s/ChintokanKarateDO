package com.example.prabhusivanandam.chintokankaratedo;

import android.content.SharedPreferences;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Prabhu Sivanandam on 11-May-17.
 */

public class Events {
    String event_name,place,venue,cause,entry_fee,organizer,contact_number,contact_person,contact_mail,event_date;
    String event_id;
    public Events()
    {

    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public Events(String event_name, String place, String venue, String cause, String entry_fee, String organizer, String contact_number, String contact_person, String contact_mail, String event_date) {
        this.event_name = event_name;
        this.place = place;
        this.venue = venue;
        this.cause = cause;
        this.entry_fee = entry_fee;
        this.organizer = organizer;
        this.contact_number = contact_number;
        this.contact_person = contact_person;
        this.contact_mail = contact_mail;
        this.event_date=event_date;

        generateId();
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(String entry_fee) {
        this.entry_fee = entry_fee;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getContact_mail() {
        return contact_mail;
    }

    public void setContact_mail(String contact_mail) {
        this.contact_mail = contact_mail;
    }

    public void generateId() {
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("event_id");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String id=(String)dataSnapshot.getValue();
                int x=Integer.parseInt(event_id);
                x+=1;
                reference.setValue(""+x);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
