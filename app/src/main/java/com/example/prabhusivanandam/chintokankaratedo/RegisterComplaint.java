package com.example.prabhusivanandam.chintokankaratedo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Prabhu Sivanandam on 24-May-17.
 */

public class RegisterComplaint extends Fragment {

    EditText complaintSubject,complaintBody;
    TextView registerButton;
    ProgressDialog dialog;
    String complaint_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.register_complaint,container,false);
        complaintSubject=(EditText)view.findViewById(R.id.complaintSubject);
        complaintBody=(EditText)view.findViewById(R.id.complaintBody);
        registerButton=(TextView)view.findViewById(R.id.registerComplaintButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject,body;
                subject= complaintSubject.getText().toString();
                body=complaintBody.getText().toString();
                if(subject.isEmpty()||subject.trim().length()<1)
                {
                    complaintSubject.setError("Enter a subject");
                }
                if(body.isEmpty()||body.trim().length()<1)
                {
                    complaintBody.setError("Enter a complaint");
                }
                else
                {
                    dialog=new ProgressDialog(getContext());
                    dialog.setMessage("Please wait");
                    dialog.setTitle("Registering Complaint");
                    dialog.show();
                    SharedPreferences preferences=getActivity().getSharedPreferences("complaint_count", Context.MODE_PRIVATE);
                    complaint_id=preferences.getString("complaint_id","0");
                    int id=Integer.parseInt(complaint_id);
                    ComplaintEntity complaint=new ComplaintEntity(id,subject,body);
                    id+=1;
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("complaint_id",id+"");
                    editor.commit();
                    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Complaints");
                    reference.child(String.valueOf(complaint.getComplaint_id())).setValue(complaint);
                    dialog.dismiss();
                    Toast.makeText(getContext(),"Registered Complaint Successfully",Toast.LENGTH_LONG).show();
                    complaintBody.setText("");
                    complaintSubject.setText("");
                }
            }
        });
        return view;
    }
}
