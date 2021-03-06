package com.example.prabhusivanandam.chintokankaratedo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Prabhu Sivanandam on 15-May-17.
 */

public class GroupchatAdapter extends RecyclerView.Adapter<GroupchatAdapter.ViewHolder> {

    ArrayList<UserMessages> messagesArrayList=new ArrayList<>();
    UserMessages message;

    public GroupchatAdapter(ArrayList<UserMessages> mList)
    {
        this.messagesArrayList=mList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_cards,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        message=messagesArrayList.get(position);
        holder.username.setText(message.getUsername());
        holder.mBody.setText(message.body);
    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView username;
        TextView mBody;
        public ViewHolder(View itemView) {
            super(itemView);
            username=(TextView)itemView.findViewById(R.id.musername);
            mBody=(TextView)itemView.findViewById(R.id.mbody);
        }
    }
}
