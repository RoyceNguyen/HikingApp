package com.stclaircollege.rnb.hikingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.stclaircollege.rnb.hikingapp.Model.Hike;
import com.stclaircollege.rnb.hikingapp.R;
import com.stclaircollege.rnb.hikingapp.Util.Constants;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater mInflater;
    private ItemHikeListener mListener;

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

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.edit_hike_name.setText(listData.get(position).hikeName);
        holder.edit_number_of_bag_nights.setText(listData.get(position).noOfBagNights + "");
        holder.edit_number_of_day_hikes.setText(listData.get(position).noOfDayHikes + "");
        holder.edit_distance.setText(listData.get(position).distance + "");
        holder.edit_contact_info.setText(listData.get(position).contactInfo);
        holder.edit_daily_breakdown.setText(listData.get(position).dailyBreakdown);
        if (listData.get(position).unit == 1) {
            holder.switch_unit.setChecked(true);
        } else {
            holder.switch_unit.setChecked(false);
        }

        holder.edit_hike_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                listData.get(position).hikeName = s.toString();
                if (mListener != null) mListener.onEditTextChanged(position, 0, s.toString());
            }
        });
        holder.edit_number_of_day_hikes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    listData.get(position).noOfDayHikes = Integer.parseInt(s.toString());
                } else {
                    listData.get(position).noOfDayHikes = 0;
                }
                if (mListener != null) mListener.onEditTextChanged(position, 1, s.toString());
            }
        });
        holder.edit_number_of_bag_nights.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    listData.get(position).noOfBagNights = Integer.parseInt(s.toString());
                } else {
                    listData.get(position).noOfBagNights = 0;
                }
                if (mListener != null) mListener.onEditTextChanged(position, 2, s.toString());
            }
        });
        holder.edit_distance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    listData.get(position).distance = Float.parseFloat(s.toString());
                } else {
                    listData.get(position).distance = 0;
                }
                if (mListener != null) mListener.onEditTextChanged(position, 3, s.toString());
            }
        });
        holder.edit_contact_info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                listData.get(position).contactInfo = s.toString();
                if (mListener != null) mListener.onEditTextChanged(position, 4, s.toString());
            }
        });
        holder.edit_daily_breakdown.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                listData.get(position).dailyBreakdown = s.toString();
                if (mListener != null) mListener.onEditTextChanged(position, 5, s.toString());
            }
        });
        holder.switch_unit.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked ==  true) {
                    listData.get(position).distance = listData.get(position).distance * Constants.KiloVsMile;
                } else {
                    listData.get(position).distance = listData.get(position).distance / Constants.KiloVsMile;
                }
                holder.edit_distance.setText("" + listData.get(position).distance);
                if (mListener != null) mListener.onUnitSwitchChanged(position, isChecked);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return listData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public EditText edit_hike_name;
        public EditText edit_number_of_day_hikes;
        public EditText edit_number_of_bag_nights;
        public EditText edit_distance;
        public EditText edit_contact_info;
        public EditText edit_daily_breakdown;
        public SwitchButton switch_unit;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            edit_hike_name = itemView.findViewById(R.id.edit_hike_name);
            edit_number_of_day_hikes = itemView.findViewById(R.id.edit_number_of_day_hikes);
            edit_number_of_bag_nights = itemView.findViewById(R.id.edit_number_of_bag_nights);
            edit_distance = itemView.findViewById(R.id.edit_distance);
            edit_contact_info = itemView.findViewById(R.id.edit_contact_info);
            edit_daily_breakdown = itemView.findViewById(R.id.edit_daily_breakdown);
            switch_unit = itemView.findViewById(R.id.switch_unit);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) mListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setEventListener(ItemHikeListener itemHikeListener) {
        this.mListener = itemHikeListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemHikeListener {
        void onItemClick(View view, int position);
        void onEditTextChanged(int position, int index, String s);
        void onUnitSwitchChanged(int position, boolean isChecked);
    }
}