package com.stclaircollege.rnb.hikingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.stclaircollege.rnb.hikingapp.Model.Hike;
import com.stclaircollege.rnb.hikingapp.R;

import java.util.ArrayList;
import java.util.List;


public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater mInflater;
    private ItemHikeListener mClickListener;

    private List<Hike> listData = new ArrayList<>();

    // data is passed into the constructor
    public HikeAdapter(Context context, List<Hike> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_hike, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return listData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemHikeListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemHikeListener {
        void onItemClick(View view, int position);
    }
}