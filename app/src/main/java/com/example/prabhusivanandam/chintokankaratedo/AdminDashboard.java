package com.example.prabhusivanandam.chintokankaratedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Prabhu Sivanandam on 12-May-17.
 */

public class AdminDashboard extends AppCompatActivity {

    //Nothing in functionality just the dashboard for the admins with four cards


    CardView eventcard,blockcard,chatcard,complaintcard,detailscard;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admindashboard);
        eventcard=(CardView)findViewById(R.id.addevent);
        blockcard=(CardView)findViewById(R.id.block_user);
        chatcard=(CardView)findViewById(R.id.adminchat);
        complaintcard=(CardView)findViewById(R.id.complaint);
        detailscard=(CardView)findViewById(R.id.view_details);
    }

    public void EventaddClick(View view)
    {
        startActivity(new Intent(AdminDashboard.this,AddEvent.class));
    }

    public void onBlockUserClick(View v)
    {
        startActivity(new Intent(AdminDashboard.this,Block_user.class));
    }

    public void onAdminChatClick(View v)
    {
        startActivity(new Intent(AdminDashboard.this,AdminChat.class));
    }

    public void onComplaintsClick(View v)
    {
        startActivity(new Intent(AdminDashboard.this,ViewComplaints.class));
    }

    public void onStudentDetailsClick(View v)
    {
        startActivity(new Intent(AdminDashboard.this,StudentDetails.class));
    }
}
