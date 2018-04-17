package com.stclaircollege.rnb.hikingapp.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stclaircollege.rnb.hikingapp.Adapter.CompletedTripAdapter;
import com.stclaircollege.rnb.hikingapp.Adapter.FutureTripAdapter;
import com.stclaircollege.rnb.hikingapp.Model.Trip;
import com.stclaircollege.rnb.hikingapp.R;
import com.stclaircollege.rnb.hikingapp.Util.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class CompletedTripFragment extends Fragment implements CompletedTripAdapter.ItemTripListener {
    private CompletedTripListener mListener;

    private RecyclerView recyclerView;
    private List<Trip> list_trips = new ArrayList<>();
    private CompletedTripAdapter adapter;

    private DatabaseHandler handler;

    public CompletedTripFragment() {
    }

    @SuppressLint("ValidFragment")
    public CompletedTripFragment(CompletedTripListener listener) {
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
        View view = inflater.inflate(R.layout.fragment_completed_trip, container, false);
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        list_trips = handler.getCompletedTrips();
        adapter = new CompletedTripAdapter(getContext(), list_trips);
        adapter.setEventListener(CompletedTripFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mListener != null) {
            mListener.onClickEditCompletedTrip(list_trips.get(position).id);
        }
    }

    public interface CompletedTripListener {
        void onClickEditCompletedTrip(int trip_id);
    }
}
