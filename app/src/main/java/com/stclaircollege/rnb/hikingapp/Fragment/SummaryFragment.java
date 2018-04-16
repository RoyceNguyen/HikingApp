package com.stclaircollege.rnb.hikingapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stclaircollege.rnb.hikingapp.Adapter.HikeAdapter;
import com.stclaircollege.rnb.hikingapp.Adapter.TripAdapter;
import com.stclaircollege.rnb.hikingapp.Model.Hike;
import com.stclaircollege.rnb.hikingapp.Model.Trip;
import com.stclaircollege.rnb.hikingapp.R;
import com.stclaircollege.rnb.hikingapp.Util.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class SummaryFragment extends Fragment implements TripAdapter.ItemTripListener {
    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private List<Trip> list_trips = new ArrayList<>();
    private TripAdapter adapter;

    private DatabaseHandler handler;

    public SummaryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new DatabaseHandler(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_future_trip, container, false);
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        list_trips = handler.getAllTrips();
        adapter = new TripAdapter(getContext(), list_trips);
        adapter.setEventListener(SummaryFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    public interface OnFragmentInteractionListener {
    }
}
