package com.codetribe.project;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CodeTribe on 2017/10/03.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Event> eventArrayList;


    public EventAdapter(Context context, ArrayList<Event> eventArrayList) {
        this.context = context;
        this.eventArrayList = eventArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.eventlist_cardlayout,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Event event= eventArrayList.get(position);

        holder.imgeventpic.setImageResource(event.getEventImages());
        holder.txteventname.setText(event.getEventName());
        holder.txteventInformation.setText(event.getEventinformation());
        holder.txteventadddress.setText(event.getEventaddress());
        holder.txteventdate.setText(event.getEventdate());


    }

    @Override
    public int getItemCount() {
        return eventArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imgeventpic;
        TextView txteventname;
        TextView txteventInformation;
        TextView txteventadddress;
        TextView txteventdate;
        CardView cardView;



        public ViewHolder(View itemView) {
            super(itemView);

            imgeventpic=(ImageView)itemView.findViewById(R.id.imgEventProfilepic);
            txteventname=(TextView)itemView.findViewById(R.id.txtEventName);
            txteventInformation=(TextView)itemView.findViewById(R.id.txtEventInfo);
            txteventadddress=(TextView)itemView.findViewById(R.id.txtEventAddress);
            txteventdate=(TextView)itemView.findViewById(R.id.txtEventDate);
            cardView=(CardView)itemView.findViewById(R.id.card);

        }
    }
}
