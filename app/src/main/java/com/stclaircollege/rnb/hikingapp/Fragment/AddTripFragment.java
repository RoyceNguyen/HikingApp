package com.stclaircollege.rnb.hikingapp.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.greenfrvr.hashtagview.HashtagView;
import com.stclaircollege.rnb.hikingapp.Adapter.HikeAdapter;
import com.stclaircollege.rnb.hikingapp.Model.Hike;
import com.stclaircollege.rnb.hikingapp.R;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTripFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private TextView text_start_date;
    private TextView text_end_date;
    private Spinner spinner_trip_organizer;
    private Button btn_trip_organizer;
    private Button btn_participants;
    private EditText edit_number_of_days;
    private EditText edit_accommodations;
    private EditText edit_hike_name;
    private HashtagView hashtags;

    private RecyclerView recyclerView;
    private List<Hike>list_hikes = new ArrayList<>();
    private HikeAdapter adapter;

    List<String> list_members = new ArrayList<>();
    List<String> list_participants = new ArrayList<>();
    List<String> list_temp = new ArrayList<>();
    this.list_hikes = new ArrayList<>();
    this.list_hikes.add(new Hike());


    public AddTripFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.list_members = new ArrayList<>();
        this.list_participants = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_trip, container, false);
        bindView(view);
        setRecyclerView();
        return view;
    }

    void setRecyclerView() {
        adapter = new HikeAdapter(getContext(), list_hikes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    void bindView(View view) {
        SupportPlaceAutocompleteFragment autocompleteFragment = (SupportPlaceAutocompleteFragment)getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
            }
            @Override
            public void onError(Status status) {
            }
        });
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);
        recyclerView = view.findViewById(R.id.recyclerView);
        text_start_date = view.findViewById(R.id.text_start_date);
        text_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SpinnerDatePickerDialogBuilder()
                        .context(getContext())
                        .callback(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                text_start_date.setText(String.format("%4d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
                            }
                        })
                        .defaultDate(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE))
                        .build()
                        .show();
            }
        });
        text_end_date = view.findViewById(R.id.text_end_date);
        text_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SpinnerDatePickerDialogBuilder()
                        .context(getContext())
                        .callback(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                text_end_date.setText(String.format("%4d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
                            }
                        })
                        .defaultDate(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DATE))
                        .build()
                        .show();
            }
        });
        spinner_trip_organizer = view.findViewById(R.id.spinner_trip_organizer);
        btn_trip_organizer = view.findViewById(R.id.btn_trip_organizer);
        btn_trip_organizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .content("Input trip organizer")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if  (input.toString().trim().length()==0) return;
                                list_members.add(input.toString());
                                String[] array = new String[list_members.size()];
                                list_members.toArray(array);
                                ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, array);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner_trip_organizer.setAdapter(adapter);
                            }
                        }).show();
            }
        });
        btn_participants = view.findViewById(R.id.btn_participants);
        btn_participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title("Select participants")
                        .items(list_members)
                        .itemsCallbackMultiChoice(
                                null,
                                new MaterialDialog.ListCallbackMultiChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                        list_temp.clear();
                                        for (int i = 0; i < which.length; i++) {
                                            list_temp.add(list_members.get(which[i]));
                                        }
                                        return true;
                                    }
                                })
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                list_participants = list_temp;
                                setHashTagData();
                                dialog.dismiss();
                            }
                        })
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .alwaysCallMultiChoiceCallback()
                        .autoDismiss(false)
                        .positiveText("Add")
                        .neutralText("Cancel")
                        .show();
            }
        });
        edit_number_of_days = view.findViewById(R.id.edit_number_of_days);
        edit_accommodations = view.findViewById(R.id.edit_accommodations);
        edit_hike_name = view.findViewById(R.id.edit_hike_name);
        hashtags = view.findViewById(R.id.hashtags);

        view.findViewById(R.id.btn_add_hike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_hikes.add(new Hike());
                setRecyclerView();
            }
        });
        view.findViewById(R.id.btn_remove_hike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_hikes.size() > 1) {
                    list_hikes.remove(list_hikes.size() - 1);
                    setRecyclerView();
                }
            }
        });
    }

    private void setHashTagData() {
        hashtags.setData(list_participants, new HashtagView.DataTransform<String>() {
            @Override
            public CharSequence prepare(String item) {
                SpannableString spannableString = new SpannableString(item);
                spannableString.setSpan(new SuperscriptSpan(), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#efeef2")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onClickCreatTripButton();
        void onClickClearButton();
    }
}
