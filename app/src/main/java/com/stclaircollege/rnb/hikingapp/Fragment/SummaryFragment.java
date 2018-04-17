package com.stclaircollege.rnb.hikingapp.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stclaircollege.rnb.hikingapp.Adapter.FutureTripAdapter;
import com.stclaircollege.rnb.hikingapp.Model.Hike;
import com.stclaircollege.rnb.hikingapp.Model.Trip;
import com.stclaircollege.rnb.hikingapp.R;
import com.stclaircollege.rnb.hikingapp.Util.DatabaseHandler;

import java.util.List;

public class SummaryFragment extends Fragment {

    private SummaryListener mListener;
    private TextView text_hikes_completed;
    private TextView text_miles;
    private TextView text_kilometers;
    private TextView text_noofday_hikes;
    private TextView text_noofbag_nights;

    private DatabaseHandler handler;

    public SummaryFragment() {
    }


    @SuppressLint("ValidFragment")
    public SummaryFragment(SummaryFragment.SummaryListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new DatabaseHandler(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        bindView(view);
        setView();
        return view;
    }

    private void setView() {
        List<Trip> trips = handler.getCompletedTrips();
        int hikes_completed = 0;
        float miles = 0;
        float kilometers = 0;
        int noofday_hikes = 0;
        int noofbag_nights  = 0;
        for (Trip trip : trips) {
            String[] hikes_string = trip.hikes.split(",");
            hikes_completed += hikes_string.length;
            for  (int i = 0; i < hikes_string.length; i++) {
                int id = Integer.parseInt(hikes_string[i]);
                Hike hike = handler.getHike(id);
                noofday_hikes += hike.noOfDayHikes;
                noofbag_nights += hike.noOfBagNights;
                if (hike.unit == 0) {
                    kilometers += hike.distance;
                } else {
                    miles += hike.distance;
                }
            }
        }

        text_hikes_completed.setText("" + hikes_completed);
        text_miles.setText("" + miles);
        text_kilometers.setText("" + kilometers);
        text_noofday_hikes.setText("" + noofday_hikes);
        text_noofbag_nights.setText("" + noofbag_nights);
    }

    private void bindView(View view) {
        text_hikes_completed = view.findViewById(R.id.text_hikes_completed);
        text_miles = view.findViewById(R.id.text_miles);
        text_kilometers = view.findViewById(R.id.text_kilometers);
        text_noofday_hikes = view.findViewById(R.id.text_noofday_hikes);
        text_noofbag_nights = view.findViewById(R.id.text_noofbag_nights);
    }

    public interface SummaryListener {
    }
}
