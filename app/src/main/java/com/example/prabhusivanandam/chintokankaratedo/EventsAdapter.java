package com.example.prabhusivanandam.chintokankaratedo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Prabhu Sivanandam on 12-May-17.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>{

    //Adapter for the recycler view to display the upcoming events

    ArrayList<Events> eventsList=new ArrayList<>();
    Events event;


    public EventsAdapter(ArrayList<Events> eventsList)
    {
        this.eventsList=eventsList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.events_cards,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        event=eventsList.get(position);
        holder.tv1.setText("Event Name:"+event.getEvent_name());
        holder.tv2.setText("Place     :"+event.getPlace());
        holder.tv3.setText("Venue     :"+event.getVenue());
        holder.tv10.setText("Date:      "+event.getEvent_date());
        holder.tv4.setText("Cause     :"+event.getCause());
        holder.tv5.setText("Entry Fee :"+event.getEntry_fee());
        holder.tv6.setText("Organizer :"+event.getOrganizer());
        holder.tv7.setText("Incharge  :"+event.getContact_person());
        holder.tv8.setText("Phone     :"+event.getContact_number());
        holder.tv9.setText("Event Name:"+event.getContact_mail());

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.tv1);
            tv2=(TextView)itemView.findViewById(R.id.tv2);
            tv3=(TextView)itemView.findViewById(R.id.tv3);
            tv4=(TextView)itemView.findViewById(R.id.tv4);
            tv5=(TextView)itemView.findViewById(R.id.tv5);
            tv6=(TextView)itemView.findViewById(R.id.tv6);
            tv7=(TextView)itemView.findViewById(R.id.tv7);
            tv8=(TextView)itemView.findViewById(R.id.tv8);
            tv9=(TextView)itemView.findViewById(R.id.tv9);
            tv10=(TextView)itemView.findViewById(R.id.tv10);
        }
    }
}
