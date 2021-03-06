package com.example.prabhusivanandam.chintokankaratedo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //To store the username who is logged in get it from the shared preferences
    String username;
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ka-s/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent i=getIntent();
        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        username=preferences.getString("loggeduser","prabhu231197s");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Chintokan Karate DO INDIA", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Dashboard.this);
            alertDialog.setTitle("Warning");
            alertDialog.setMessage("Are you sure to LogOut?");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Ka-s/"+username);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            KarateKA ka = new KarateKA();
                            ka=dataSnapshot.getValue(KarateKA.class);
                            ka.setLoginFlag("0");
                            ref.setValue(ka);
                            SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("loggeduser","proceedtologin");
                            editor.commit();
                            finishAffinity();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(Dashboard.this,"Connectivity Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                    Dashboard.super.onBackPressed();
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.student_updates) {
            // student updates
        } else if (id == R.id.upcoming_events) {
            //upcoming events
            UpcomingEvents events=new UpcomingEvents();
            getSupportFragmentManager().beginTransaction().replace(R.id.fr,events).commit();

        } else if (id == R.id.training_routines) {
            //training routines
            TrainingRoutines routines=new TrainingRoutines();
            getSupportFragmentManager().beginTransaction().replace(R.id.fr,routines).commit();
        } else if (id == R.id.group_chat) {
            //group forum
            GroupChat chat=new GroupChat();
            getSupportFragmentManager().beginTransaction().replace(R.id.fr,chat).commit();
        }
          else if (id == R.id.techniques) {
            //techniques
        }
        else if(id==R.id.edit_Profile)
        {
            //edit profile
            ViewMyProfile viewMyProfile=new ViewMyProfile();
            getSupportFragmentManager().beginTransaction().replace(R.id.fr,viewMyProfile).commit();
        }
        else if(id==R.id.downloads)
        {
            //download fragment here
        }
        else if(id==R.id.regcomplaint)
        {
            //complaints register here
            RegisterComplaint complaint=new RegisterComplaint();
            getSupportFragmentManager().beginTransaction().replace(R.id.fr,complaint).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
