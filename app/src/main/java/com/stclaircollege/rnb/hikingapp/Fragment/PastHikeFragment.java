package com.stclaircollege.rnb.hikingapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


import com.stclaircollege.rnb.hikingapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PastHikeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PastHikeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastHikeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView list;

    private OnFragmentInteractionListener mListener;

    public PastHikeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastHikeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastHikeFragment newInstance(String param1, String param2) {
        PastHikeFragment fragment = new PastHikeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //Creating fragment manager
    FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_past_hike, container, false);

//        fm = getActivity().getSupportFragmentManager();
//        //Had to add this fab.show in order to create a trip
//        fab.show();
//        fab.setImageResource(R.drawable.ic_add_box_black_24dp);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.addToBackStack(null);
//                ft.replace(R.id.content_main, new AddTripFragment());
//                ft.commit();
//            }
//        });
//
//        list = view.findViewById(R.id.tripslist);
//        DatabaseHandler db = new DatabaseHandler(getContext());
//        final ArrayList<Trip> tripslist = db.getAllTrips();
//        db.closeDB();
//
//        //create custom adapter
//        final CustomAdapter adapter = new CustomAdapter(getContext(), tripslist);
//        list.setAdapter(adapter);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView location = view.findViewById(R.id.locationListView);
//                TextView startDate = view.findViewById(R.id.startDateListView);
//
//                //TextView details = (TextView) view.findViewById(R.id.details);
//                //ImageView chevron = (ImageView) view.findViewById(R.id.chevron);
//
//                //open a database connection here
//                DatabaseHandler db = new DatabaseHandler(getContext());
//
//                Trip loc = db.getTrip(tripslist.get(position).getLocation());
//                Trip date = db.getTrip(tripslist.get(position).getLocation());
//                db.closeDB();
//
//                //update the text of build
//                if(location.getText() == "" || startDate.getText() == "" ) {
//                    location.setText(loc.getLocation());
//                    startDate.setText(date.getStartDate());
//                    //update the text of the show more
//                    //details.setText("Click to show less");
//                    //update the chevron image
//                    //chevron.setImageResource(R.drawable.ic_expand_less_black_24dp);
//                }
//                else{
//                    location.setText("");
//                    startDate.setText("");
//                    //details.setText("Click to show more");
//                    //update the chevron image
//                    //chevron.setImageResource(R.drawable.ic_expand_more_black_24dp);
//                }
//
//            }
//        });
//
//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                DatabaseHandler db = new DatabaseHandler(getContext());
//                Trip trip = tripslist.get(position);
//                db.deleteTrip(trip.getId());
//                db.closeDB();
//                tripslist.remove(position);
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//        });

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
