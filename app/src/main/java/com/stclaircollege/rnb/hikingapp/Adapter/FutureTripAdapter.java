package com.stclaircollege.rnb.hikingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stclaircollege.rnb.hikingapp.Model.Trip;
import com.stclaircollege.rnb.hikingapp.R;

import java.util.ArrayList;
import java.util.List;



public class FutureTripAdapter extends RecyclerView.Adapter<FutureTripAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater mInflater;
    private ItemTripListener mListener;

    private List<Trip> listData = new ArrayList<>();

    // data is passed into the constructor
    public FutureTripAdapter(Context context, List<Trip> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_future_trip, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.text_trip_location.setText(listData.get(position).location);
        holder.text_trip_statdate.setText(listData.get(position).startDate);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return listData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text_trip_location;
        public TextView text_trip_statdate;
        public ViewHolder(View itemView) {
            super(itemView);
            text_trip_location = itemView.findViewById(R.id.text_trip_location);
            text_trip_statdate = itemView.findViewById(R.id.text_trip_statdate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) mListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setEventListener(ItemTripListener itemHikeListener) {
        this.mListener = itemHikeListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemTripListener {
        void onItemClick(View view, int position);
    }
}