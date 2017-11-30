package com.example.prabhusivanandam.chintokankaratedo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Prabhu Sivanandam on 30-May-17.
 */

public class ViewMyProfile extends Fragment{

    TextView tvKaId,tvKaName,tvKaAge,tvKaPhone,tvKaEmail,tvKaAddress,tvEmergencyNumber,tvBloodGroup,tvDojo,tvBelt,tvEditProfile;
    ProgressDialog dialog;
    KarateKA ka;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_profile_activity,container,false);
        tvKaId=(TextView)view.findViewById(R.id.tvKaId);
        tvKaName=(TextView)view.findViewById(R.id.tvKaName);
        tvKaAge=(TextView)view.findViewById(R.id.tvKaAge);
        tvKaPhone=(TextView)view.findViewById(R.id.tvKaPhone);
        tvKaEmail=(TextView)view.findViewById(R.id.tvKaEmail);
        tvKaAddress=(TextView)view.findViewById(R.id.tvKaAddress);
        tvEmergencyNumber=(TextView)view.findViewById(R.id.tvKaEmergecyNumber);
        tvBloodGroup=(TextView)view.findViewById(R.id.tvKaBloodGroup);
        tvDojo=(TextView)view.findViewById(R.id.tvKaDojo);
        tvBelt=(TextView)view.findViewById(R.id.tvKaBelt);
        tvEditProfile=(TextView)view.findViewById(R.id.tvSaveChanges);
        SharedPreferences preferences=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String username=preferences.getString("loggeduser","prabhu231197s");
        dialog=new ProgressDialog(getContext());
        dialog.setMessage("Please Wait...");
        dialog.setTitle("Fetching");
        dialog.show();
        ka=new KarateKA();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ka-s/"+username);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ka=dataSnapshot.getValue(KarateKA.class);
                tvKaId.setText(String.valueOf(ka.getKa_id()));
                tvKaName.setText(ka.getName());
                tvKaAge.setText(ka.getAge());
                tvKaPhone.setText(ka.getPhone());
                tvKaEmail.setText(ka.getEmail());
                tvKaAddress.setText(ka.getAddress());
                Log.d("Tag",String.valueOf(ka.getEmergency_number()));
                tvEmergencyNumber.setText(String.valueOf(ka.getEmergency_number()));
                tvBloodGroup.setText(ka.getBloodgroup());
                tvDojo.setText(ka.getDojo());
                tvBelt.setText(ka.getBelt());
                dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(),"Check your connection",Toast.LENGTH_LONG).show();
            }
        });
        tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Contact Admin for any changes",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
