package com.example.prabhusivanandam.chintokankaratedo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Prabhu Sivanandam on 24-May-17.
 */

public class ViewComplaintsRecyclerAdapter extends RecyclerView.Adapter<ViewComplaintsRecyclerAdapter.ViewHolder>{

    ArrayList<ComplaintEntity> complaintEntities=new ArrayList<>();
    ComplaintEntity entity;

    public ViewComplaintsRecyclerAdapter(ArrayList<ComplaintEntity> complaintEntities)
    {
        this.complaintEntities=complaintEntities;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_cards,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        entity=complaintEntities.get(position);
        holder.complaintSubject.setText(entity.getSubject());
        holder.complaintBody.setText(entity.getBody());
    }

    @Override
    public int getItemCount() {
        return complaintEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView complaintSubject,complaintBody;
        public ViewHolder(View itemView) {
            super(itemView);
            complaintSubject=(TextView)itemView.findViewById(R.id.tvComplaintSubject);
            complaintBody=(TextView)itemView.findViewById(R.id.tvComplaintBody);
        }
    }

}
